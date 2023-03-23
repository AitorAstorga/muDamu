package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dto.Enfermedades;
import dto.Sintomas;
import dto.SintomasPredicciones;
import facade.EnfermedadFacade;
import facade.SintomaFacade;

@Path("enfermedades_sintomas")
public class EnfermedadSintomasResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of platformResource
	 */
	public EnfermedadSintomasResource() {
	}
	
	@GET
	@Path("enfermedades")
	@Produces("application/xml")
	public Response getEnferXml(@QueryParam("predID") String predID) {
		Response res;
		Enfermedades enfermedades = new Enfermedades();
		
		EnfermedadFacade f = new EnfermedadFacade();
		enfermedades = f.load(Integer.parseInt(predID));
		if (enfermedades==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(enfermedades).build();
			return res;
		}
	}
	
	@GET
	@Path("sintomas")
	@Produces("application/xml")
	public Response getSintXml() {
		Response res;
		Sintomas sintomas = new Sintomas();
		
		SintomaFacade f = new SintomaFacade();
		sintomas = f.load();
		if (sintomas==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(sintomas).build();
			System.out.println(res);
			return res;
		}
	}
	
	@GET
	@Path("sintomasPaciente")
	@Produces("application/xml")
	public Response getSintPacXml(@QueryParam("predID") String predID) {
		Response res;
		SintomasPredicciones prediccion = new SintomasPredicciones();
		
		SintomaFacade f = new SintomaFacade();
		prediccion = f.loadSintomaPaciente(Integer.parseInt(predID));
		if (prediccion==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(prediccion).build();
			return res;
		}
	}
}
