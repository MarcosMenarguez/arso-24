package repositorio;

/*
 * Excepción notificada si no existe el identificador de la entidad.
 */

@SuppressWarnings("serial")
public class EntidadNoEncontrada extends Exception {

	public EntidadNoEncontrada(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public EntidadNoEncontrada(String msg) {
		super(msg);		
	}
	
		
}
