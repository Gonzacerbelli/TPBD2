package test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dao.MongoDao;

import dao.MongoDao;
import modelo.Domicilio;
import modelo.Empleado;

public class TestAgregarVendedores {

	public static void main(String[] args) {

		// domicilios para vendedores
		Domicilio domic6 = new Domicilio(6, "Colon", 555, "La Matanza", "Buenos Aires");
		Domicilio domic7 = new Domicilio(7, "Mariete", 111, "Burzaco", "Buenos Aires");
		Domicilio domic8 = new Domicilio(8, "Trebuchet", 9700, "Adrogue", "Buenos Aires");

		Empleado emp1 = new Empleado(1, "Ramirez", "Marcos", 77654, domic6, "OSDE", "91182");
		Empleado emp2 = new Empleado(2, "Pedrete", "Nestor", 6651, domic7, "IOMA", "19283");
		Empleado emp3 = new Empleado(3, "Marino", "Flavio", 41756, domic8, "OSPECON", "44118");
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// agregar a la bd

			String json = mapper.writeValueAsString(emp3);
			MongoDao.getInstance().agregar(json, "Farmacia", "Empleado");

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
