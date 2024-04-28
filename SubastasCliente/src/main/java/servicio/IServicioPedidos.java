package servicio;

import javax.ejb.Remote;

import modelo.Direccion;
import modelo.MetodoPago;

@Remote
public interface IServicioPedidos {
	
	void comenzarPedido(String usuario);
	void addArticulo(String articulo);
	void addDatosEnvio(Direccion direccionEnvio);
	void addInformacionPago(MetodoPago infoPago);
	void confirmarPedido();

}
