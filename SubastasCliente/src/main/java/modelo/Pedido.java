package modelo;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable{
	
	private String id;
	private String comprador;
	private List<String> articulos;
	private Direccion infoEnvio;
	private MetodoPago infoPago;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public List<String> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<String> articulos) {
		this.articulos = articulos;
	}
	public Direccion getInfoEnvio() {
		return infoEnvio;
	}
	public void setInfoEnvio(Direccion infoEnvio) {
		this.infoEnvio = infoEnvio;
	}
	public MetodoPago getInfoPago() {
		return infoPago;
	}
	public void setInfoPago(MetodoPago infoPago) {
		this.infoPago = infoPago;
	}
	
	
	

}
