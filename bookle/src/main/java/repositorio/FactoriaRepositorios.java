package repositorio;

import java.util.HashMap;
import java.util.Map;

import utils.PropertiesReader;

/*
 * Factoría que encapsula la implementación del repositorio.
 * 
 * Utiliza un fichero de propiedades para cargar la implementación.
 */

public class FactoriaRepositorios {
	
	private static final String PROPERTIES = "repositorios.properties";
	
	private static Map<Class<?>, Object> repositorios = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T, K, R extends Repositorio<T, K>> R getRepositorio(Class<?> entidad) {
				
			
			try {
				if (repositorios.containsKey(entidad)) {
					return (R) repositorios.get(entidad);
				}
				else {
					PropertiesReader properties = new PropertiesReader(PROPERTIES);		
					String clase = properties.getProperty(entidad.getName());
					R repositorio = (R) Class.forName(clase).getConstructor().newInstance();
					repositorios.put(entidad, repositorio);
					return repositorio;
				}
			}
			catch (Exception e) {
				
				e.printStackTrace();
				throw new RuntimeException("No se ha podido obtener el repositorio para la entidad: " + entidad.getName());
			}
			
	}
	
}
