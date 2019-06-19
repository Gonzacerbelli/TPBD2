package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dao.MongoDao;

import modelo.Domicilio;
import modelo.Empleado;

public class TestAgregarVendedor {
	
	public static void main(String[] args) {
		
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		// domicilios para vendedores
		Domicilio domic6 = new Domicilio(6, "Colon", 555, "La Matanza", "Buenos Aires");
		Domicilio domic7 = new Domicilio(7, "Mariete", 111, "Burzaco", "Buenos Aires");
		Domicilio domic8 = new Domicilio(8, "Trebuchet", 9700, "Adrogue", "Buenos Aires");
		
		//domicilios sucursales
		Domicilio domic9 = new Domicilio(9, "Rivadavia", 7216, "Lanus", "Buenos Aires");
		Domicilio domic10 = new Domicilio(10, "Irlanda", 821, "Burzaco", "Buenos Aires");
		Domicilio domic11 = new Domicilio(11, "Avellaneda", 8237, "Rosario", "Santa Fe");

		Empleado emp1 = new Empleado("Perez", "Jorge", 1234, domic6, 1, "208891", "Privado", "Privado");
		Empleado emp2 = new Empleado("Ramirez", "Hernan", 5567, domic7, 2, "228812", "OSDE", "8821");
		Empleado emp3 = new Empleado("Perez", "Jorge", 9921, domic8, 3, "208891", "IOMA", "8921");
		Empleado emp4 = new Empleado("Barthou", "Gonzalo", 7261, domic9, 4, "281123", "OSPECON", "1234");
		Empleado emp5 = new Empleado("Contento", "Guido", 8822, domic10, 5, "219982", "OSDE", "7721");
		Empleado emp6 = new Empleado("Maurelli", "Gustavo", 9911, domic11, 6, "279912", "Swiss Medical", "0921");
		Empleado emp7 = new Empleado("Lang", "Scott", 9010, domic11, 7, "308891", "Privado", "0100");
		Empleado emp8 = new Empleado("Stark", "Howard", 8273, domic6, 8, "44521", "Privado", "8823");
		
		listaEmpleados.add(emp1);listaEmpleados.add(emp2);listaEmpleados.add(emp3);listaEmpleados.add(emp4);listaEmpleados.add(emp5);
		listaEmpleados.add(emp6);listaEmpleados.add(emp7);listaEmpleados.add(emp8);
		

		ObjectMapper mapper = new ObjectMapper();
		try {
			//parsear objetos a lista de DBObject
			List<DBObject> lista = new ArrayList<DBObject>();
			for(Empleado empleado : listaEmpleados) {
				String json = mapper.writeValueAsString(empleado);
				lista.add((DBObject) JSON.parse(json));
				
			}
			
			MongoDao.getInstance().agregarLista(lista, "Farmacia", "Empleado");
			System.out.println("Datos insertados correctamente!");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}