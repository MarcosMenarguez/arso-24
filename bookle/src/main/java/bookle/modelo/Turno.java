package bookle.modelo;

public class Turno {

	private String horario;	
	private Reserva reserva;

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "Turno [horario=" + horario + ", reserva=" + reserva + "]";
	}
		
}
