package modelo;

public class Empleado extends Persona{
	private long _id;
	private String cuil;
	private String obraSocial;
	private String numeroAfiliado;
	
	
	public Empleado() {}


	public Empleado(String apellido, String nombre, long dni, Domicilio domicilio, long _id, String cuil,
			String obraSocial, String numeroAfiliado) {
		super(apellido, nombre, dni, domicilio);
		this._id = _id;
		this.cuil = cuil;
		this.obraSocial = obraSocial;
		this.numeroAfiliado = numeroAfiliado;
	}


	public long get_id() {
		return _id;
	}


	public void set_id(long _id) {
		this._id = _id;
	}


	public String getCuil() {
		return cuil;
	}


	public void setCuil(String cuil) {
		this.cuil = cuil;
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
		return "Empleado [_id=" + _id + ", cuil=" + cuil + ", obraSocial=" + obraSocial + ", numeroAfiliado="
				+ numeroAfiliado + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
