package modelo;

public class Persona {
	protected String apellido;
	protected String nombre;
	protected long dni;
	protected Domicilio domicilio;
	
	
	public Persona(){}
	
	public Persona(String apellido, String nombre, long dni,Domicilio domicilio) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.domicilio=domicilio;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public long getDni() {
		return dni;
	}


	public void setDni(long dni) {
		this.dni = dni;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	@Override
	public String toString() {
		return "Persona [apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", domicilio=" + domicilio + "]";
	}

	



	
	
	
	
	
	

}
