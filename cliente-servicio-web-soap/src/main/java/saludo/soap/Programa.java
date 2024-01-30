package saludo.soap;

import es.um.arso.Saludo;
import es.um.arso.SaludoImplService;

public class Programa {

	public static void main(String[] args) {
		
		SaludoImplService servicio = new SaludoImplService();
		Saludo puerto = servicio.getSaludoImplPort();
		System.out.println(puerto.getSaludo("Pepe"));
	}
}
