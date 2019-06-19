package test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import dao.MongoDao;
import modelo.Domicilio;
import modelo.Empleado;
import modelo.Producto;
import modelo.Sucursal;

public class TestAgregarSucursales {
	
	public static void main(String[] args) {
		
		//domicilios
		// domicilio para sucursales
		Domicilio domic9 = new Domicilio(9, "Uruguay", 566, "Monserrat", "CABA");
		Domicilio domic10 = new Domicilio(10, "29 de Septiembre", 971, "Lanus", "CABA");
		Domicilio domic11 = new Domicilio(11, "25 de Mayo", 340, "Ministro Rivadavia", "Buenos Aires");
		Domicilio domic12 = new Domicilio(12, "Lunavieja", 2177, "San Lorenzo", "Santa Fe");
		
		//domicilios de sucursales
		Domicilio domic6 = new Domicilio(6, "Colon", 555, "La Matanza", "Buenos Aires");
		Domicilio domic7 = new Domicilio(7, "Mariete", 111, "Burzaco", "Buenos Aires");
		Domicilio domic8 = new Domicilio(8, "Trebuchet", 9700, "Adrogue", "Buenos Aires");
		
		//vendedores
		Empleado emp1 = new Empleado(1, "Ramirez", "Marcos", 77654, domic6, "OSDE", "91182");
		Empleado emp2 = new Empleado(2, "Pedrete", "Nestor", 6651, domic7, "IOMA", "19283");
		Empleado emp3 = new Empleado(3, "Marino", "Flavio", 41756, domic8, "OSPECON", "44118");
		
		//sucursales
		Sucursal sucursal1 = new Sucursal(1,emp1 , domic9);
		sucursal1.getListaEmpleados().add(emp1);
		sucursal1.getListaEmpleados().add(emp2);
		sucursal1.getListaEmpleados().add(emp3);
		
		Sucursal sucursal2 = new Sucursal(2,emp2 , domic10);
		sucursal2.getListaEmpleados().add(emp1);
		sucursal2.getListaEmpleados().add(emp2);
		sucursal2.getListaEmpleados().add(emp3);
		
		Sucursal sucursal3 = new Sucursal(3,emp3 , domic11);
		sucursal3.getListaEmpleados().add(emp1);
		sucursal3.getListaEmpleados().add(emp2);
		sucursal3.getListaEmpleados().add(emp3);


		ObjectMapper mapper = new ObjectMapper();
		try {
			

			String json = mapper.writeValueAsString(sucursal3);
			String coleccion = "Sucursal";
			MongoDao.getInstance().agregar(json, "Farmacia", coleccion);
			System.out.println("Agregado: "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
