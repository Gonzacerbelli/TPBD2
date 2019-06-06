package modelo;

public class Cliente extends Persona{
	private long idCliente;
	private String obraSocial;
	private String numeroAfiliado;
	
	public Cliente() {}
	
	
	public Cliente(long idCliente,String apellido, String nombre, long dni, Domicilio domicilio,String obraSocial, String numeroAfiliado) {
		super(apellido, nombre, dni, domicilio);
		this.idCliente = idCliente;
		this.obraSocial = obraSocial;
		this.numeroAfiliado = numeroAfiliado;
	}


	public long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(long idEmpleado) {
		this.idCliente = idEmpleado;
	}


	public String getObraSocial() {
		return obraSocial;
	}


	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}


	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}


	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}


	@Override
	public String toString() {
		return "Cliente [idEmpleado=" + idCliente + ", obraSocial=" + obraSocial + ", numeroAfiliado=" + numeroAfiliado
				+ ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", domicilio=" + domicilio + "]";
	}
	
	
	
	
	
	
	
	
	

}
