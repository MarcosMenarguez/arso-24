package servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import modelo.Puja;
import repositorio.EntidadNoEncontrada;
import repositorio.Repositorio;
import repositorio.RepositorioException;

@Stateless
public class ServicioPujas implements IServicioPujas{
	
	@EJB(beanName="RepositorioPujas")
	private Repositorio<Puja, String> repositorio;

	@Override
	public String pujar(Puja puja) {
		try {
			return repositorio.add(puja);
		} catch (RepositorioException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void eliminarPuja(String id) {
		try {
			repositorio.delete(id);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<PujaResumen> listarPujas(String articulo) {

		List<PujaResumen> resultado = new ArrayList<PujaResumen>();
		List<Puja> pujas;
		try {
			pujas = repositorio.getAll();
			for(Puja p:pujas) {
				if(p.getArticulo().equals(articulo)) {
					PujaResumen pr = new PujaResumen();
					pr.setArticulo(p.getArticulo());
					pr.setId(p.getId());
					pr.setImporte(p.getImporte());
					pr.setPujador(p.getPujador());
					resultado.add(pr);
				}
			}
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
		
		return resultado;
		
	}

}
