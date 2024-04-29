package bookle.controller;

import java.net.URI;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import bookle.modelo.Actividad;
import bookle.servicio.IServicioBookle;
import repositorio.RepositorioException;
@Path("actividades")
@Stateless
public class BookleRest {

	@EJB(beanName="ServicioBookle")
	private IServicioBookle servicio;
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response crearActividad(Actividad actividad) throws RepositorioException {
		String id = servicio.crear(actividad);
		URI nuevaURL = 
		this.uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();	
	}
	
	
}
