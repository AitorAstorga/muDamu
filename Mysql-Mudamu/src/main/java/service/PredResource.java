package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dto.Prediccion;
import facade.PredFacade;

@Path("pred")
public class PredResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of platformResource
	 */
	public PredResource() {
	}
	
	@GET
	@Path("predicciones")
	@Produces("application/xml")
	public Response getPredXml(@QueryParam("medicoID") String medicoID) {
		Response res;
		Prediccion prediccion = new Prediccion();
		
		PredFacade f = new PredFacade();
		prediccion = f.load(Integer.parseInt(medicoID));
		if (prediccion==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(prediccion).build();
			return res;
		}
	}
}
