package utils;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application{

	public Set<Class<?>> getClasses() {
		Set<Class<?>> list = new java.util.HashSet<Class<?>>();
		return list;
	}
}
