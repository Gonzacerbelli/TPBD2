package modelo;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBObject;

public class Venta {
	private String fecha;
	private String ticket;
	private double totalVenta;
	private String formaDePago;
	private List<Detalle> detalle = new ArrayList<Detalle>();
	private Empleado vendedor;
	private Empleado cajero;
	private Cliente cliente;
	
	public Venta() {}
	
	public Venta(String fecha, String ticket, double totalVenta, String formaDePago, List<Detalle> detalle,
			Empleado vendedor, Empleado cajero, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.ticket = ticket;
		this.totalVenta = totalVenta;
		this.formaDePago = formaDePago;
		this.detalle = detalle;
		this.vendedor = vendedor;
		this.cajero = cajero;
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public List<Detalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<Detalle> detalle) {
		this.detalle = detalle;
	}

	public Empleado getVendedor() {
		return vendedor;
	}

	public void setVendedor(Empleado vendedor) {
		this.vendedor = vendedor;
	}

	public Empleado getCajero() {
		return cajero;
	}

	public void setCajero(Empleado cajero) {
		this.cajero = cajero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Venta [fecha=" + fecha + ", ticket=" + ticket + ", totalVenta=" + totalVenta + ", formaDePago="
				+ formaDePago + ", detalle=" + detalle + ", vendedor=" + vendedor + ", cajero=" + cajero + ", cliente="
				+ cliente + "]";
	}

	public List<String> mapVentas(List<DBObject> objectList) {
		List<String> lista = new ArrayList<String>();
		for (int i = 0; i < objectList.size(); i++) {
			DBObject objeto = objectList[i];
			lista.add("Total venta: " + objeto.get("totalVenta"));
		}
		return lista;
	}

}
