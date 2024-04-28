package modelo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import utils.LocalDateXmlAdapter;


@XmlRootElement
public class Puja implements Serializable{

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID) 	
	private String id;
	private String articulo;
	private String breveDescripcion;
	private String pujador;
	private Double importe;	
	private LocalDate fecha;
	
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
	@XmlJavaTypeAdapter(value = LocalDateXmlAdapter.class)
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getBreveDescripcion() {
		return breveDescripcion;
	}
	public void setBreveDescripcion(String breveDescripcion) {
		this.breveDescripcion = breveDescripcion;
	}
	
	
}
