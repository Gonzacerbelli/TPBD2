package modelo;

public class Producto {
	private long idProducto;
	private String descripcion;
	private String laboratorio;
	private double precio;
	private boolean esMedicamento;
	
	
	public Producto() {}	//TIENE QUE ESTAR SINO NO SE PUEDE PARSEAR DE JSON A OBJETO
	
	public Producto(long idProducto, String descripcion, String laboratorio, double precio, boolean esMedicamento) {
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.laboratorio = laboratorio;
		this.precio = precio;
		this.esMedicamento = esMedicamento;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
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
		return "Producto [idProducto=" + idProducto + ", descripcion=" + descripcion + ", laboratorio=" + laboratorio
				+ ", precio=" + precio + ", esMedicamento=" + esMedicamento + "]";
	}
	
	
	
	
	
	
	

}
