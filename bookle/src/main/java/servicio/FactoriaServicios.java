package servicio;

import java.util.HashMap;
import java.util.Map;

import utils.PropertiesReader;

/*
 * Factoría que encapsula la implementación de un servicio.
 * 
 * Utiliza un fichero de propiedades para cargar la implementación.
 */

public class FactoriaServicios {
	
	private static final String PROPERTIES = "servicios.properties";
	
	private static Map<Class<?>, Object> servicios = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T> T getServicio(Class<T> servicio) {
				
			
			try {
				if (servicios.containsKey(servicio)) {
					return (T) servicios.get(servicio);
				}
				else {
					PropertiesReader properties = new PropertiesReader(PROPERTIES);			
					String clase = properties.getProperty(servicio.getName());
					T instancia = (T) Class.forName(clase).getConstructor().newInstance();
					servicios.put(servicio, instancia);
					return instancia;
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("No se ha podido obtener la implementación del servicio: " + servicio.getName());
			}
			
	}
	
}
