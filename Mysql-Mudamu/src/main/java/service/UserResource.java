package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import facade.PredFacade;
import facade.UserFacade;
import dto.Medico;
import dto.Prediccion;
import dto.User;

/**
 * REST Web Service
 *
 * @author flarrinaga
 */
@Path("user")
public class UserResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of platformResource
	 */
	public UserResource() {
	}

	/**
	 * Retrieves representation of an instance of DTO.platformResource
	 * 
	 * @return an instance of java.lang.String
	 */
	/*
	 * Este método devuelve una lista de artículos a diferencia del siguiente que
	 * devuelve un Web Response
	 * 
	 * @GET
	 * 
	 * @Path("mostrar")
	 * 
	 * @Produces("application/xml") public List <User> getXml() { List <User>
	 * lista=ListaUsers.instance.mostrar(); return lista; }
	 */
	
	

	/**
	 * Retrieves representation of an instance of DTO.platformResource
	 * 
	 * @return an instance of java.lang.String JSON
	 */
	@GET
	@Path("mostrar")
	@Produces("application/json")
	public Response getPac() {
		UserFacade f = new UserFacade();
		List<User> lista = f.loadAllItems();
		if (lista.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().entity(lista).build();
		}
	}
	
	@GET
	@Path("mostrarMedicos")
	@Produces("application/json")
	public Response getMedicos() {
		UserFacade f = new UserFacade();
		List<User> lista = f.loadAllMedicos();
		if (lista.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().entity(lista).build();
		}
	}
	


	@GET
	@Path("login")
	@Produces("application/xml , application/json")
	public Response getUserXml(@QueryParam("username") String username) {
		Response res;
		User user = new User();
		User copy = new User();
		
		user.setTarjetaSanitaria(username);
		UserFacade f = new UserFacade();
		copy = f.load(username);
		if (copy==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
		} else {
			res = Response.ok().entity(copy).build();
			return res;
		}
	}
	
	
	@GET
	@Path("medicoLogin")
	@Produces("application/xml , application/json")
	public Response getMedicoXml(@QueryParam("username") String username) {
		Response res;
		Medico user = new Medico();
		Medico copy = new Medico();
		
		user.setUsername(username);
		UserFacade f = new UserFacade();
		copy = f.loadMedico(username);
		if (copy==null) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
			
		} else {
			res = Response.ok().entity(copy).build();
			return res;
		}
	}
	
	
	

	/*
	 * Podría ponerse por separado xml y json pero con nombres diferentes. Como el
	 * método de abajo
	 * 
	 * @GET
	 * 
	 * @Path("seleccionar")
	 * 
	 * @Produces("application/json") public Response getUserJson(@QueryParam ("id")
	 * int id) { Response res; User platform=new User(); if
	 * (ListaUsers.instance.buscarPos(id)!=-1){
	 * System.out.println("he llegado dentro"); platform=
	 * ListaUsers.instance.buscar(id); res=Response.ok().entity(platform).build();
	 * return res; } else{ res=Response.status(Response.Status.NOT_FOUND).build();
	 * return res; } }
	 */

	/**
	 * PUT method for updating or creating an instance of platformResource
	 * 
	 * @return an HTTP response with content of the updated or created resource.
	 */
	/*@PUT
	@Path("put/{id}/{name}")
	@Consumes("text/plain")
	public Response putXml(@PathParam("id") int id, @PathParam("name") String name) {
		Response res;
		/*User platform;
		UserFacade f = new UserFacade();
		platform = f.load(id);
		if (Objects.isNull(platform)) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
		} else {
			platform.setname(name);
			f.saveCd(platform);
			res = Response.ok().build();
			return res;
		}
		return null;
	}*/

	/*@PUT
	@Path("put/updateplatform")
	@Consumes("application/json")
	@Produces("application/json")
	public Response crearupdateputjson(User platform) {
		Response res;
		User resource;
		UserFacade f = new UserFacade();
		resource = f.load(platform.getpacienteID());

		if (Objects.isNull(resource)) {
			res = Response.status(Response.Status.NOT_FOUND).build();
			return res;
		} else {
			//f.saveCd(platform);
			res = Response.ok(platform).build();
			return res;
		}
	}*/

	@DELETE
	@Path("delete/{id}")
	@Consumes("text/plain")
	public Response deletePaciente(@PathParam("id") int id) {
		User platform;
		UserFacade f = new UserFacade();
		platform = f.load(id);
		if (Objects.isNull(platform)) {
			return Response.status(Response.Status.NOT_FOUND).build();

		}
		f.deletePac(id);
		return Response.noContent().build();

	}
	
	@DELETE
	@Path("deleteMedico/{username}")
	@Consumes("text/plain")
	public Response deleteMedico(@PathParam("username") String username) {
		Medico platform;
		UserFacade f = new UserFacade();
		platform = f.loadMedico(username);
		if (Objects.isNull(platform)) {
			return Response.status(Response.Status.NOT_FOUND).build();

		}
		f.deleteMed(username);
		return Response.noContent().build();

	}

	@POST
	@Path("register")
	@Consumes("application/xml")
	public Response crearPaciente(User paciente) {
		UserFacade uf = new UserFacade();
		
		uf.saveUser(paciente);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@POST
	@Path("registerMedico")
	@Consumes("application/xml")
	public Response crearMedico(Medico medico) {
		UserFacade uf = new UserFacade();
		
		uf.saveMedico(medico);
		return Response.status(Response.Status.CREATED).build();
	}
}
