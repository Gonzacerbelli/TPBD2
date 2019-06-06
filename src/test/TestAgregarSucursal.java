package test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dao.MongoDao;
import modelo.Sucursal;

public class TestAgregarSucursal {

    
	 private int _id;
	 private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	 private Empleado encargado;
	 private Domicilio domicilio;

	public static void main(String[] args) {

        Sucursal sucursal1 = new Sucursal(1, "lista", encargado, domicilio);

		ObjectMapper mapper = new ObjectMapper();
		try {
			// agregar a la bd
			String json = mapper.writeValueAsString(producto1);
			MongoDao.getInstance().agregar(json, "Farmacia", "Producto");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
