package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
	private LocalDate fecha;
	private int ticket;
	private double totalVenta;
	private String formaDePago;
	private List<Detalle> detalle = new ArrayList<Detalle>();
	private Empleado vendedor;
	private Empleado cajero;
	private Cliente cliente;
	
	public Venta(LocalDate fecha, int ticket, double totalVenta, String formaDePago, Empleado vendedor, Empleado cajero,
			Cliente cliente) {
		this.fecha = fecha;
		this.ticket = ticket;
		this.totalVenta = totalVenta;
		this.formaDePago = formaDePago;
		this.vendedor = vendedor;
		this.cajero = cajero;
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
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
	
	
	
	
	

}
