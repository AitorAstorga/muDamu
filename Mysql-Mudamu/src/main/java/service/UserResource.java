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

import facade.UserFacade;
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
	
	@GET
	@Path("mostrar")
	@Produces("application/xml")
	public Response getXml() {
		UserFacade f = new UserFacade();
		List<User> lista = f.loadAllItems();
		// Al responder utilizando un objeto Response necesitamos utilizar GenericEntity
		// para que el parseo XML no de error
		GenericEntity<List<User>> entity = new GenericEntity<List<User>>(lista) {
		};
		if (lista.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().entity(entity).build();
		}
	}

	/**
	 * Retrieves representation of an instance of DTO.platformResource
	 * 
	 * @return an instance of java.lang.String JSON
	 */
	@GET
	@Path("mostrar")
	@Produces("application/json")
	public Response getJson() {
		UserFacade f = new UserFacade();
		List<User> lista = f.loadAllItems();
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

		
		user.setUsername(username);
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
	@PUT
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
		}*/
		return null;
	}

	@PUT
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
	}

	/*
	 * @PUT
	 * 
	 * @Path("put/{id}")
	 * 
	 * @Consumes("text/plain") public void crearupdateput(@PathParam("id") int id) {
	 * int pos=ListaUsers.instance.buscarPos(id); User platform=new User(); if
	 * (pos==-1){ platform.setId(id);
	 * platform.setTitle("titulo Nuevo User con PUT");
	 * platform.setSummary("Breve descripcion nuevo User PUT");
	 * platform.setDescription("Descripcion del nuevo User PUT");
	 * ListaUsers.instance.anadir(platform);} else{
	 * ListaUsers.instance.modificar(pos, "titulo modificado con PUT",
	 * "Breve modificacion PUT", "Descripcion modificacion PUT"); } }
	 * 
	 */

	@DELETE
	@Path("delete/{id}")
	@Consumes("text/plain")
	public Response deleteplatform(@PathParam("id") int id) {
		User platform;
		UserFacade f = new UserFacade();
		platform = f.load(id);
		if (Objects.isNull(platform)) {
			return Response.status(Response.Status.NOT_FOUND).build();

		}
		f.deleteCdItem(id);
		return Response.noContent().build();

	}

	@POST
	@Path("crear1")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// @Consumes("application/x-www-form-urlencoded")
	public Response crear1(@FormParam("id") int id, @FormParam("tarjeta") int tarjeta, @FormParam("username") String username,
			@FormParam("password") String password) {
		User user = new User();
		user.setpacienteID(id);
		user.setTarjetaSanitaria(tarjeta);
		user.setUsername(username);	
		user.setPassword(password);
		//platform.setname(name);

		UserFacade f = new UserFacade();
		f.saveUser(user);
		return Response.status(Response.Status.CREATED).build();
	}

	@POST
	@Path("register")
	@Consumes("application/xml")
	public Response crear3(User platform) {
		UserFacade uf = new UserFacade();
		
		uf.saveUser(platform);
		return Response.status(Response.Status.CREATED).build();
	}
}
