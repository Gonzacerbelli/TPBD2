package modelo;

public class Producto {
	private int _id;
	private String descripcion;
	private String laboratorio;
	private double precio;
	private boolean esMedicamento;
	
	
	public Producto() {}	//TIENE QUE ESTAR SINO NO SE PUEDE PARSEAR DE JSON A OBJETO


	public Producto(int _id, String descripcion, String laboratorio, double precio, boolean esMedicamento) {
		super();
		this._id = _id;
		this.descripcion = descripcion;
		this.laboratorio = laboratorio;
		this.precio = precio;
		this.esMedicamento = esMedicamento;
	}
	


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getLaboratorio() {
		return laboratorio;
	}


	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public boolean isEsMedicamento() {
		return esMedicamento;
	}


	public void setEsMedicamento(boolean esMedicamento) {
		this.esMedicamento = esMedicamento;
	}


	@Override
	public String toString() {
		return "Producto [_id=" + _id + ", descripcion=" + descripcion + ", laboratorio=" + laboratorio + ", precio="
				+ precio + ", esMedicamento=" + esMedicamento + "]";
	}
	
	
	
	
	
	
	
	
	

}
