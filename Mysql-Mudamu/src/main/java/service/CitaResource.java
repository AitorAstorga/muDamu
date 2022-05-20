package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dto.CitasMedico;
import dto.CitasPaciente;
import facade.CitaFacade;
import facade.SintomaFacade;

@Path("cita")
public class CitaResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of platformResource
	 */
	public CitaResource() {
	}
	
	@GET
	@Path("citasMedico")
	@Produces("application/xml")
	public Response getCitasMedicoXml(@QueryParam("medicoID") String medicoID) {
		Response res;
		CitasMedico citasMedico = new CitasMedico();
		
		CitaFacade f = new CitaFacade();
		citasMedico = f.loadMedico(Integer.parseInt(medicoID));
		if (citasMedico==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(citasMedico).build();
			return res;
		}
	}
	
	@GET
	@Path("citasPaciente")
	@Produces("application/xml")
	public Response getCitasPacienteXml(@QueryParam("pacienteID") String pacienteID) {
		Response res;
		CitasPaciente citasPaciente = new CitasPaciente();
		
		CitaFacade f = new CitaFacade();
		citasPaciente = f.loadPaciente(Integer.parseInt(pacienteID));
		if (citasPaciente==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(citasPaciente).build();
			System.out.println(res);
			return res;
		}
	}
	
	@GET
	@Path("citasAdministrador")
	@Produces("application/xml")
	public Response getCitasAdministradorXml() {
		Response res;
		CitasMedico citasMedico = new CitasMedico();
		
		CitaFacade f = new CitaFacade();
		citasMedico = f.loadAdministrador();
		if (citasMedico==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(citasMedico).build();
			System.out.println(res);
			return res;
		}
	}
}
