package servicio;

import java.util.List;

import javax.ejb.Local;

import modelo.Puja;

@Local
public interface IServicioPujas {

	public String pujar(Puja puja);
	public void eliminarPuja(String id);
	public List<PujaResumen> listarPujas(String articulo);
}
