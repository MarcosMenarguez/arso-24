package encuestas.servicio;

import java.time.LocalDateTime;
import java.util.List;

import encuestas.modelo.Encuesta;
import repositorio.EntidadNoEncontrada;

public interface IServicioEncuestas {

	String crear(String titulo, String instrucciones, LocalDateTime apertura, LocalDateTime cierre, List<String> opciones);
	
	boolean haVotado(String id, String usuario) throws EntidadNoEncontrada;
	
	void votar(String id, int opcion, String usuario) throws EntidadNoEncontrada;
	
	Encuesta getEncuesta(String id) throws EntidadNoEncontrada;
	
	void eliminar(String id) throws EntidadNoEncontrada;
	
	List<EncuestaResumen> getListadoResumen();
}
