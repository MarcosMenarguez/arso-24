package encuestas.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import encuestas.modelo.Encuesta;

@NoRepositoryBean
public interface RepositorioEncuestas extends CrudRepository<Encuesta, String> {
	
	List<Encuesta> findByTitulo(String titulo);


}
