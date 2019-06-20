package modelo;

public class Detalle {
	private int _id;
	private Producto producto;
	private int cantidad;
	private double precio;
	
	
	public Detalle() {}


	public Detalle(int _id, Producto producto, int cantidad, double precio) {
		super();
		this._id = _id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Detalle [_id=" + _id + ", producto=" + producto + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}


	


	
	
	
	
	
	
	
	
	
	

}
