package repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Utils;

public class RepositorioMemoria<T extends Identificable> implements RepositorioString<T> {

	private HashMap<String, T> entidades = new HashMap<>();
	
	@Override
	public String add(T entity) throws RepositorioException {
		
		if (entity.getId() == null) 		
			entity.setId(Utils.createId());
		
		this.entidades.put(entity.getId(), entity);		
		
		return entity.getId();
	}

	@Override
	public void update(T entity) throws RepositorioException, EntidadNoEncontrada {
		
		if (! this.entidades.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		
		this.entidades.put(entity.getId(), entity);
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		
		if (! this.entidades.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		
		this.entidades.remove(entity.getId());
	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (! this.entidades.containsKey(id))
			throw new EntidadNoEncontrada(id + " no existe en el repositorio");
		
		return this.entidades.get(id);
	}

	@Override
	public List<T> getAll() throws RepositorioException {
		
		return new ArrayList<>(this.entidades.values());
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		
		return new ArrayList<>(this.entidades.keySet());
	}

}
