package encuestas.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;
import encuestas.repositorio.RepositorioEncuestas;
import repositorio.EntidadNoEncontrada;

@Service
public class ServicioEncuestas implements IServicioEncuestas {

	private RepositorioEncuestas repositorio;

	@Autowired
	public ServicioEncuestas(RepositorioEncuestas repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public String crear(String titulo, String instrucciones, LocalDateTime apertura, LocalDateTime cierre,
			List<String> opciones)  {

		// Control de integridad de los datos

		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");

		if (apertura == null)
			throw new IllegalArgumentException("fecha apertura: no debe ser nula");

		if (cierre == null)
			throw new IllegalArgumentException("fecha cierre: no debe ser nula");

		if (cierre.isBefore(apertura))
			throw new IllegalArgumentException("fecha cierre: debe ser posterior a la apertura");

		if (LocalDateTime.now().isAfter(cierre))
			throw new IllegalArgumentException("fecha cierre: no debe estar en el pasado");

		if (opciones == null)
			throw new IllegalArgumentException("opciones: no debe ser una coleccion nula");

		if (opciones.size() < 2)
			throw new IllegalArgumentException("opciones: debe tener más de dos opciones");

		Encuesta encuesta = new Encuesta(titulo, instrucciones, apertura, cierre, opciones);

		String id = repositorio.save(encuesta).getId();

		return id;
	}

	@Override
	public boolean haVotado(String id, String usuario) throws EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");

		Encuesta encuesta = getEncuesta(id);

		for (Opcion opcion : encuesta.getOpciones())
			if (opcion.getVotos().contains(usuario))
				return true;

		return false;

	}

	@Override
	public void votar(String id, int opcion, String usuario) throws EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");

		if (haVotado(id, usuario))
			throw new IllegalArgumentException("usuario: ya ha votado");

		Encuesta encuesta = getEncuesta(id);

		if (opcion < 1 || opcion > encuesta.getOpciones().size())
			throw new IllegalArgumentException("opcion: indice no valido");

		LocalDateTime ahora = LocalDateTime.now();

		if (ahora.isBefore(encuesta.getApertura()) || ahora.isAfter(encuesta.getCierre()))
			throw new IllegalArgumentException("la encuesta no esta abierta");
		// FIXME: estrictamente debería ser una excepcion de estado
		// IllegalStateException

		int indice = opcion - 1; // desde 0

		encuesta.getOpciones().get(indice).getVotos().add(usuario);

		repositorio.save(encuesta);

	}

	@Override
	public Encuesta getEncuesta(String id) throws EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		Optional<Encuesta> resultado =  repositorio.findById(id);
		if (! resultado.isPresent())
			throw new EntidadNoEncontrada("no existe encuesta: " + id);
		else 		
			return resultado.get();
	}

	@Override
	public void eliminar(String id) throws EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		Encuesta encuesta = getEncuesta(id);

		repositorio.delete(encuesta);

	}

	@Override
	public List<EncuestaResumen> getListadoResumen() {

		LinkedList<EncuestaResumen> resultado = new LinkedList<>();

		for (Encuesta encuesta : repositorio.findAll()) {
			EncuestaResumen resumen = new EncuestaResumen();
			resumen.setId(encuesta.getId());
			resumen.setTitulo(encuesta.getTitulo());

			resultado.add(resumen);

		}

		return resultado;
	}

}
