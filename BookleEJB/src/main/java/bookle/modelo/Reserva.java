package bookle.modelo;

import java.io.Serializable;

public class Reserva implements Serializable{

	private String alumno;
	private String email;
	
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Reserva [alumno=" + alumno + ", email=" + email + "]";
	}
		
}
