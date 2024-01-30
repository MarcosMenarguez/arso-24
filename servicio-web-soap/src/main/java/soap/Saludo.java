package soap;

import javax.jws.WebService;

@WebService(targetNamespace = "http://um.es/arso")
public interface Saludo {

	String getSaludo(String nombre);
}