package encuestas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import encuestas.modelo.Encuesta;
import encuestas.servicio.EncuestaResumen;
import encuestas.servicio.IServicioEncuestas;

@RestController
@RequestMapping("/encuestas")
public class EncuestasController {
	private IServicioEncuestas servicio;

	@Autowired
	public EncuestasController(IServicioEncuestas servicio) {
		this.servicio = servicio;
	}

	@GetMapping("/{id}")
	public Encuesta getEncuestaById(@PathVariable String id) throws Exception {
		Encuesta encuesta = this.servicio.getEncuesta(id);

		return encuesta;
	}

	@GetMapping
	public List<EncuestaResumen> getAllEncuestas() throws Exception {

		List<EncuestaResumen> encuestas = this.servicio.getListadoResumen();

		return encuestas;
	}

}
