package soap;

import javax.xml.ws.Endpoint;

public class Publicador {

	// Problema JAXWS y JDK17
	// --add-opens java.base/java.lang=ALL-UNNAMED
	
	public static void main(String[] args) {
		
		Endpoint.publish(
				"http://localhost:9999/ws/saludo", 
				new SaludoImpl());
		
		System.out.println("Servicio saludo funcionando");
	}
}
