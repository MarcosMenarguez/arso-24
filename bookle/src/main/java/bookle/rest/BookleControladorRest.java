package bookle.rest;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import bookle.modelo.Actividad;
import bookle.rest.Listado.ResumenExtendido;
import bookle.servicio.ActividadResumen;
import bookle.servicio.IServicioBookle;
import servicio.FactoriaServicios;

@Path("actividades")
public class BookleControladorRest {

	private IServicioBookle servicio = FactoriaServicios.getServicio(IServicioBookle.class);

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/actividades/1

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getActividad(@PathParam("id") String id) throws Exception {

		return Response.status(Response.Status.OK).entity(servicio.recuperar(id)).build();
	}

	// curl -i -X PUT -H "Content-type: application/xml" -d @1.xml http://localhost:8080/api/actividades/1

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response update(@PathParam("id") String id, Actividad actividad) throws Exception {
		if (!id.equals(actividad.getId()))
			throw new IllegalArgumentException("El identificador no coincide: " + id);

		servicio.actualizar(actividad);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -X DELETE http://localhost:8080/api/actividades/1

	@DELETE
	@Path("/{id}")
	public Response removeActividad(@PathParam("id") String id) throws Exception {
		servicio.borrar(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -X POST -H "Content-type: application/xml" -d @1.xml http://localhost:8080/api/actividades/

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createActividad(Actividad actividad) throws Exception {
		String id = servicio.crear(actividad);
		URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();
	}

	// curl -i -X POST --data "alumno=Pepe&email=pepe@um.es" http://localhost:8080/api/actividades/1/agenda/2024-02-14/turnos/1/reserva

	@POST
	@Path("/{id}/agenda/{fecha}/turnos/{indice}/reserva")
	public Response reservar(@PathParam("id") String id, @PathParam("fecha") String fecha,
			@PathParam("indice") int indice, @FormParam("alumno") String alumno, @FormParam("email") String email)
			throws Exception {
		LocalDate fechaDate = null;
		try {
			fechaDate = LocalDate.parse(fecha);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(e);
		}
		servicio.reservar(id, fechaDate, indice, alumno, email);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -H "Accept: application/xml" http://localhost:8080/api/actividades
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getListadoActividades() throws Exception {
		
		List<ActividadResumen> resultado = servicio.recuperarTodas();
		
		LinkedList<ResumenExtendido> extendido = new LinkedList<>();
		for (ActividadResumen actividadResumen : resultado) {
			ResumenExtendido resumenExtendido = new ResumenExtendido();
			resumenExtendido.setResumen(actividadResumen);

			// Construir la URL
			String id = actividadResumen.getId();
			URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
			resumenExtendido.setUrl(nuevaURL.toString());

			extendido.add(resumenExtendido);
		}
		Listado listado = new Listado();
		listado.setActividad(extendido);
		return Response.ok(listado).build();
	}

}