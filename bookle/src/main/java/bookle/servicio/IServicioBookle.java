package bookle.servicio;

import java.time.LocalDate;
import java.util.List;

import bookle.modelo.Actividad;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioBookle {

	/** 
	 * Metodo de alta de una actividad.
	 * 
	 * @param actividad debe ser valida respecto al modelo de dominio
	 * @return identificador de la actividad
	 */
	String crear(Actividad actividad) throws RepositorioException;
	
	/**
	 * Actualiza una actividad.
	 * @param actividad debe ser valida respecto al modelo de dominio
	 */
	void actualizar(Actividad actividad) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera una actividad utilizando el identificador. 	
	 */
	Actividad recuperar(String id)  throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Elimina una actividad utilizando el identificador.
	 */
	void borrar(String id)  throws RepositorioException, EntidadNoEncontrada;
	
		
	/**
	 * Realiza la reserva de un turno de la actividad si no está ocupado.
	 * @rerturn true si se ha podido efectuar la reserva, falso si está ocupado
	 */
	boolean reservar(String idActividad, LocalDate fecha, int indiceTurno, String alumno, String email) throws RepositorioException, EntidadNoEncontrada;
	
	
	/**
	 * Retorna un resumen de todas las actividades.	
	 */
	List<ActividadResumen> recuperarTodas() throws RepositorioException;
}
