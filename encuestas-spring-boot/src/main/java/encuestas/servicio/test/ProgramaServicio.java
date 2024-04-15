package encuestas.servicio.test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import encuestas.EncuestasApp;
import encuestas.servicio.EncuestaResumen;
import encuestas.servicio.IServicioEncuestas;

public class ProgramaServicio {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext contexto = SpringApplication.run(EncuestasApp.class, args);
		
		IServicioEncuestas servicio = contexto.getBean(IServicioEncuestas.class);
		
		// Configura la encuesta
		
		String titulo = "Fecha del parcial";
		String instrucciones = "Llevar DNI";
		LocalDateTime apertura = LocalDateTime.now();
		LocalDateTime cierre = LocalDateTime.now().plusDays(1);
		List<String> opciones = Arrays.asList("Jueves", "Viernes");
		
		// Alta de la encuesta
		
		String id = servicio.crear(titulo, instrucciones, apertura, cierre, opciones);
		
		// Voto
		
		servicio.votar(id, 1, "juan@um.es");
		
		System.out.println("¿Ha votado juan? " + servicio.haVotado(id, "juan@um.es"));
		System.out.println("¿Ha votado jose? " + servicio.haVotado(id, "jose@um.es"));
						
		for (EncuestaResumen resumen : servicio.getListadoResumen()) {
			System.out.println(resumen);

		}
		
		contexto.close();
		System.out.println("fin.");

	}
}
