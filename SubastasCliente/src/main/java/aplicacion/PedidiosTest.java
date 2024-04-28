package aplicacion;

import java.time.LocalDate;

import javax.naming.NamingException;

import modelo.Direccion;
import modelo.MetodoPago;
import servicio.IServicioPedidos;
import util.InitialContextUtil;

public class PedidiosTest {

	public static void main(String[] args) {
			try {
				IServicioPedidos servicio = (IServicioPedidos) InitialContextUtil.getInstance().lookup(
						"ejb:/SubastaEnLinea/ServicioPedidos!servicio.IServicioPedidos?stateful");
				
				servicio.comenzarPedido("mc");
				servicio.addArticulo("espejo veneciano o lo que sea");
				servicio.addArticulo("comoda Luis XV");

				Direccion dir = new Direccion();
				dir.setCalle("Calle Mayor");
				dir.setNumero(12);
				dir.setCodigoPostal("30100");
				
				servicio.addDatosEnvio(dir);
				
				MetodoPago mp = new MetodoPago();
				
				mp.setCodigo("984378504978497855");
				mp.setProveedor("VISA");
				mp.setFechaCaducidad(LocalDate.of(2027, 5,31));
				
				servicio.addInformacionPago(mp);
				
				servicio.confirmarPedido();
				
				
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
		
		
	}

}
