package servicio;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import modelo.Puja;

@XmlRootElement
public class Listado {
	
	public static class ResumenExtendido {
		private String url;
		private PujaResumen resumen;

		// Métodos get y set
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public PujaResumen getResumen() {
			return resumen;
		}
		public void setResumen(PujaResumen resumen) {
			this.resumen = resumen;
		}
		

	}

	private List<ResumenExtendido> puja;
	// Métodos get y set

	public List<ResumenExtendido> getPuja() {
		return puja;
	}

	public void setPuja(List<ResumenExtendido> puja) {
		this.puja = puja;
	}

}
