package bookle.modelo;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;

import repositorio.Identificable;

@XmlRootElement
public class Actividad implements Identificable {

	private String id;
	private String titulo;
	private String descripcion;
	private String profesor;
	private String email;
	private LinkedList<DiaAgenda> agenda = new LinkedList<>();
	
	// Métodos get/set
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LinkedList<DiaAgenda> getAgenda() {
		return agenda;
	}
	
	public void setAgenda(LinkedList<DiaAgenda> agenda) {
		this.agenda = agenda;
	}
	@Override
	public String toString() {
		return "Actividad [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", profesor=" + profesor
				+ ", email=" + email + ", agendas=" + agenda + "]";
	}
		
}
