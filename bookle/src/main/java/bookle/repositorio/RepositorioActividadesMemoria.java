package bookle.repositorio;

import java.time.LocalDate;

import bookle.modelo.Actividad;
import bookle.modelo.DiaAgenda;
import bookle.modelo.Turno;
import repositorio.RepositorioMemoria;

/*
 * Esta clase es opcional.
 * Su utilidad es que en tiempo de compilación se compruebe que Actividad
 * implementa la interfaz Identificable.
 */
public class RepositorioActividadesMemoria extends RepositorioMemoria<Actividad> {

	public RepositorioActividadesMemoria() {

		try {
			// 1. Creación de una actividad

			Actividad actividad = new Actividad();
			actividad.setId("1");
			actividad.setTitulo("Entrevistas de prácticas");
			actividad.setDescripcion("Enlace Zoom: ...");
			actividad.setProfesor("Marcos");
			actividad.setEmail("marcos@um.es");

			// Día 1

			DiaAgenda dia1 = new DiaAgenda();
			dia1.setFecha(LocalDate.now().plusDays(1)); // mañana

			for (int hora = 10; hora <= 14; hora++) {

				Turno turno = new Turno();
				turno.setHorario(hora + ":00h");
				dia1.getTurno().add(turno);
			}

			actividad.getAgenda().add(dia1);

			add(actividad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
