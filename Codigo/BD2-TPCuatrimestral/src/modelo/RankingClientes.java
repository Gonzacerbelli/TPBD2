package modelo;

public class RankingClientes{
	int cliente;
	double montoCompra;
	int cantidadVentas;
	
	public RankingClientes(int cliente, double montoCompra, int cantidadVentas) {
		super();
		this.cliente = cliente;
		this.montoCompra = montoCompra;
		this.cantidadVentas = cantidadVentas;
	}
	

	public int getCantidadVentas() {
		return cantidadVentas;
	}


	public void setCantidadVentas(int cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}


	public int getCliente() {
		return cliente;
	}


	public void setCliente(int cliente) {
		this.cliente = cliente;
	}


	public double getMontoCompra() {
		return montoCompra;
	}


	public void setMontoCompra(double montoCompra) {
		this.montoCompra = montoCompra;
	}


	@Override
	public String toString() {
		return "RankingClientes [Cliente=" + cliente + ", montoCompra=" + montoCompra + ", cantidadVentas="
				+ cantidadVentas + "]";
	}




	

	

	
	
	
	
	

}
