package encuestas.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import encuestas.modelo.Encuesta;

public interface RepositorioEncuestasMongo 
	extends RepositorioEncuestas, MongoRepository<Encuesta, String> {

}
