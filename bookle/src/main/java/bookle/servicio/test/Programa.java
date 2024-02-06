package bookle.servicio.test;

import java.time.LocalDate;

import bookle.modelo.Actividad;
import bookle.modelo.DiaAgenda;
import bookle.modelo.Turno;
import bookle.servicio.ActividadResumen;
import bookle.servicio.IServicioBookle;
import servicio.FactoriaServicios;


public class Programa {

	public static void main(String[] args) throws Exception {
		
				
		IServicioBookle servicio = FactoriaServicios.getServicio(IServicioBookle.class);
		
		// 1. Creación de una actividad
		
		Actividad actividad = new Actividad();
		actividad.setTitulo("Entrevistas de prácticas");
		actividad.setDescripcion("Enlace Zoom: ...");
		actividad.setProfesor("Marcos");
		actividad.setEmail("marcos@um.es");
		
		// Día 1
		
		DiaAgenda dia1 = new DiaAgenda();
		dia1.setFecha(LocalDate.now().plusDays(1));		
		for (int hora = 10; hora <= 14; hora++) {
		
			Turno turno = new Turno();
			turno.setHorario(hora + ":00h");
			dia1.getTurno().add(turno);
		}
		
		actividad.getAgenda().add(dia1);
		
		// Día 2
		
		DiaAgenda dia2 = new DiaAgenda();
		dia1.setFecha(LocalDate.now().plusDays(1));
		for (int hora = 17; hora <= 19; hora++) {		
			Turno turno = new Turno();
			turno.setHorario(hora + ":00h");
			dia2.getTurno().add(turno);
		}
		
		actividad.getAgenda().add(dia2);
		
		String id = servicio.crear(actividad);
		
		// 2. Actualización
		
		actividad = servicio.recuperar(id);
		
		actividad.setDescripcion("Enlace Zoom");
		
		servicio.actualizar(actividad);
		
		// 3. Reserva
		
		boolean resultado = servicio.reservar(id, LocalDate.now().plusDays(1), 1, "Juan", "juan@um.es");
		
		System.out.println(resultado);
		
		// 4. Listado de resúmenes
		
		System.out.println("Listado:");
		for (ActividadResumen resumen : servicio.recuperarTodas())
			System.out.println("\t" + resumen);
		
		
		System.out.println("Fin.");
	}
}
