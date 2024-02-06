package bookle.modelo;

public class Reserva {

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
