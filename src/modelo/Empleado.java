package modelo;

public class Empleado extends Persona{
	private long _id;
	private String obraSocial;
	private String numeroAfiliado;
	
	
	public Empleado() {}
	
	public Empleado(long _id,String apellido, String nombre, long dni, Domicilio domicilio,String obraSocial, String numeroAfiliado) {
		super(apellido, nombre, dni, domicilio);
		this._id = _id;
		this.obraSocial = obraSocial;
		this.numeroAfiliado = numeroAfiliado;
	}




	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
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
		return "Empleado [idEmpleado=" + _id + ", obraSocial=" + obraSocial + ", numeroAfiliado="
				+ numeroAfiliado + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", domicilio="
				+ domicilio + "]";
	}
	
	
	
	
	
	
	

}
