package servicio;

import java.io.Serializable;

public class PujaResumen implements Serializable{
	
	private String id;
	private String articulo;
	private String pujador;
	private Double importe;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getPujador() {
		return pujador;
	}
	public void setPujador(String pujador) {
		this.pujador = pujador;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}


	
	
}
