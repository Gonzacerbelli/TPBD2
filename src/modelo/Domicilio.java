package modelo;

public class Domicilio {
	private long idDomicilio;
	private String calle;
	private int numero;
	private String localidad;
	private String provincia;
	
	
	public Domicilio() {}
	
	public Domicilio(long idDomicilio,String calle, int numero, String localidad, String provincia) {
		this.idDomicilio=idDomicilio;
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia;
	}


	public long getIdDomicilio() {
		return idDomicilio;
	}


	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	@Override
	public String toString() {
		return "Domicilio [idDomicilio=" + idDomicilio + ", calle=" + calle + ", numero=" + numero + ", localidad="
				+ localidad + ", provincia=" + provincia + "]";
	}


	
	
	
	
	
	
	

}
