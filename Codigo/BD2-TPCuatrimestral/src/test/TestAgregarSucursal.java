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
import modelo.Sucursal;

public class TestAgregarSucursal {
	
	public static void main(String[] args) {
		
		List<Empleado> empleadosSucursalUno = new ArrayList<Empleado>();
		List<Empleado> empleadosSucursalDos = new ArrayList<Empleado>();
		List<Empleado> empleadosSucursalTres = new ArrayList<Empleado>();
		List<Sucursal> listaSucursales = new ArrayList<Sucursal>();
		
		// domicilios para vendedores
		Domicilio domic6 = new Domicilio(6, "Colon", 555, "La Matanza", "Buenos Aires");
		Domicilio domic7 = new Domicilio(7, "Mariete", 111, "Burzaco", "Buenos Aires");
		Domicilio domic8 = new Domicilio(8, "Trebuchet", 9700, "Adrogue", "Buenos Aires");
		Domicilio domic9 = new Domicilio(9, "Rivadavia", 7216, "Lanus", "Buenos Aires");
		Domicilio domic10 = new Domicilio(10, "Irlanda", 821, "Burzaco", "Buenos Aires");
		Domicilio domic11 = new Domicilio(11, "Avellaneda", 8237, "Rosario", "Santa Fe");
		
		//domicilios sucursales
		Domicilio domic1 = new Domicilio(12,"Atofagasta",444,"Munro","CABA");
		Domicilio domic2 = new Domicilio(13,"Piñatelli",821,"Lanus","Buenos Aires");
		Domicilio domic3 = new Domicilio(14,"Puerto Montt",901,"La Matanza","Buenos Aires");

		Empleado emp1 = new Empleado("Perez", "Jorge", 1234, domic6, 1, "208891", "Privado", "Privado");
		Empleado emp2 = new Empleado("Ramirez", "Hernan", 5567, domic7, 2, "228812", "OSDE", "8821");
		Empleado emp3 = new Empleado("Perez", "Jorge", 9921, domic8, 3, "208891", "IOMA", "8921");
		empleadosSucursalUno.add(emp1);empleadosSucursalUno.add(emp2);empleadosSucursalUno.add(emp3);
		
		Empleado emp4 = new Empleado("Barthou", "Gonzalo", 7261, domic9, 4, "281123", "OSPECON", "1234");
		Empleado emp5 = new Empleado("Contento", "Guido", 8822, domic10, 5, "219982", "OSDE", "7721");
		Empleado emp6 = new Empleado("Maurelli", "Gustavo", 9911, domic11, 6, "279912", "Swiss Medical", "0921");
		empleadosSucursalDos.add(emp4);empleadosSucursalDos.add(emp5);empleadosSucursalDos.add(emp6);
		
		Empleado emp7 = new Empleado("Lang", "Scott", 9010, domic11, 7, "308891", "Privado", "0100");
		Empleado emp8 = new Empleado("Stark", "Howard", 8273, domic6, 8, "44521", "Privado", "8823");
		empleadosSucursalTres.add(emp7);empleadosSucursalTres.add(emp8);empleadosSucursalTres.add(emp3);
		
		
		
		//SUCURSALES///
		Sucursal suc1 = new Sucursal(1, empleadosSucursalUno, emp1, domic1);
		Sucursal suc2 = new Sucursal(2, empleadosSucursalDos, emp5, domic2);
		Sucursal suc3 = new Sucursal(3, empleadosSucursalTres, emp8, domic3);
		listaSucursales.add(suc1);listaSucursales.add(suc2);listaSucursales.add(suc3);
		
		
		

		ObjectMapper mapper = new ObjectMapper();
		try {
			//parsear objetos a lista de DBObject
			List<DBObject> lista = new ArrayList<DBObject>();
			for(Sucursal sucursal : listaSucursales) {
				String json = mapper.writeValueAsString(sucursal);
				lista.add((DBObject) JSON.parse(json));
				
			}
			
			MongoDao.getInstance().agregarLista(lista, "Farmacia", "Sucursal");
			System.out.println("Datos insertados correctamente!");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}