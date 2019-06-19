package modelo;

import java.util.ArrayList;
import java.util.List;

 public class Sucursal {
	 private int _id;
	 private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	 private Empleado encargado;
	 private Domicilio domicilio;

	public Sucursal() {}
	
	public Sucursal(int _id, List<Empleado> listaEmpleados, Empleado encargado,
			Domicilio domicilio) {
		super();
		this._id = _id;
		this.listaEmpleados = listaEmpleados;
		this.encargado = encargado;
		this.domicilio = domicilio;
	}
	

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}


	public Empleado getEncargado() {
		return encargado;
	}

	public void setEncargado(Empleado encargado) {
		this.encargado = encargado;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public String toString() {
		return "Sucursal [_id=" + _id + ", listaEmpleados=" + listaEmpleados + ", encargado=" + encargado
				+ ", domicilio=" + domicilio + "]";
	}
	
	



	
	
	


	
	

	

}
