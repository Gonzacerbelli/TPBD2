package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dao.MongoDao;
import modelo.Cliente;
import modelo.Detalle;
import modelo.Domicilio;
import modelo.Empleado;
import modelo.Producto;
import modelo.Venta;

public class TestAgregarVenta {

	public static void main(String[] args) {
		List<Venta> listaVentas = new ArrayList<Venta>();
		
		//clientes
		Domicilio domic1 = new Domicilio(1, "25 de mayo", 712, "Burzaco", "Buenos Aires");
		Domicilio domic2 = new Domicilio(2, "Balvanera", 415, "Quilmes", "Buenos Aires");
		Domicilio domic3 = new Domicilio(3, "Juan de Garay", 111, "Flores", "CABA");
		Domicilio domic4 = new Domicilio(4, "Avellaneda", 2111, "Floresta", "CABA");
		Domicilio domic5 = new Domicilio(5, "Malabia", 50, "Rosario", "Santa Fe");

		//clientes
		Cliente cliente1 = new Cliente(1, "Perez", "Pedro", 1123, domic1, "IOMA", "1234");
		Cliente cliente2 = new Cliente(2, "Ramos", "Hector", 3214, domic2, "OSDE", "9982");
		Cliente cliente3 = new Cliente(3, "Godoy", "Gonzalo", 9912, domic3, "Programas Medicos", "7761");
		Cliente cliente4 = new Cliente(4, "Geier", "Daniel", 1143, domic4, "IOMA", "9911");
		Cliente cliente5 = new Cliente(5, "Chimbo", "Esteban", 5121, domic2, "OSDE", "8876");
		Cliente cliente6 = new Cliente(6, "Termo", "Ernesto", 8970, domic3, "OSEYTA", "5544");
		Cliente cliente7 = new Cliente(7, "Felauto", "Lautaro", 3319, domic5, "OSPECA", "5112");
		
		// domicilios para vendedores
		Domicilio domic6 = new Domicilio(6, "Colon", 555, "La Matanza", "Buenos Aires");
		Domicilio domic7 = new Domicilio(7, "Mariete", 111, "Burzaco", "Buenos Aires");
		Domicilio domic8 = new Domicilio(8, "Trebuchet", 9700, "Adrogue", "Buenos Aires");
		Domicilio domic9 = new Domicilio(9, "Rivadavia", 7216, "Lanus", "Buenos Aires");
		Domicilio domic10 = new Domicilio(10, "Irlanda", 821, "Burzaco", "Buenos Aires");
		Domicilio domic11 = new Domicilio(11, "Avellaneda", 8237, "Rosario", "Santa Fe");
		
		
		//empleados sucursal 1
		Empleado emp1 = new Empleado("Perez", "Jorge", 1234, domic6, 1, "208891", "Privado", "Privado");
		Empleado emp2 = new Empleado("Ramirez", "Hernan", 5567, domic7, 2, "228812", "OSDE", "8821");
		Empleado emp3 = new Empleado("Perez", "Jorge", 9921, domic8, 3, "208891", "IOMA", "8921");
		
		//empleados sucursal 2
		Empleado emp4 = new Empleado("Barthou", "Gonzalo", 7261, domic9, 4, "281123", "OSPECON", "1234");
		Empleado emp5 = new Empleado("Contento", "Guido", 8822, domic10, 5, "219982", "OSDE", "7721");
		Empleado emp6 = new Empleado("Maurelli", "Gustavo", 9911, domic11, 6, "279912", "Swiss Medical", "0921");
		
		//empleados sucursal 3
		Empleado emp7 = new Empleado("Lang", "Scott", 9010, domic11, 7, "308891", "Privado", "0100");
		Empleado emp8 = new Empleado("Stark", "Howard", 8273, domic6, 8, "44521", "Privado", "8823");
		//empleado 3
		
		//productos cargados
		Producto producto1 = new Producto(1, "Afeitadora", "Sin Pelin", 22.94, false);
		Producto producto2 = new Producto(2, "Agua Oxigenada", "OxiStatus", 52.14, false);
		Producto producto3 = new Producto(3, "Jabon de tocador", "Antigrass", 72.43, false);
		Producto producto11 = new Producto(11, "Desodorante", "AXE", 12.43, false);
		
		//medicamentos
		Producto producto4 = new Producto(4, "Pastilla de Carbon", "SacaChispas", 82.12, true);
		Producto producto5 = new Producto(5, "Aspirina", "BASF", 82.12, true);
		Producto producto6 = new Producto(6, "Crema de hongos", "Honguitrap", 21.52, true);
		Producto producto7 = new Producto(7, "Actron", "Vitaminel", 66.12, true);
		Producto producto8 = new Producto(8, "Ibupirac", "BASF", 15.12, true);
		Producto producto9 = new Producto(9, "Amoxicilina", "BAYER", 89.50, true);
		Producto producto10 = new Producto(10, "Nicotinell", "BASF", 12.91, true);
		
		
		//VENTAS : 15 -> 5 POR SUCURSAL. MINIMO 3 PRODUCTOS EN CADA UNA DE ELLAS (Cargamos 15 para mostrar aunque luego en la bd cargamos 30 documentos)
		
		//VENTAS SUCURSAL 1
		List<Detalle> detalle1 = new ArrayList<Detalle>();
		detalle1.add(new Detalle(1,producto1,1,22.44));detalle1.add(new Detalle(2,producto4,2,44.45));detalle1.add(new Detalle(3,producto5,2,70.88));
		Venta venta1 = new Venta("2019-03-02", "0001-00001", 560.20, "Efectivo",detalle1,emp2, emp3, cliente1,5);
		
		List<Detalle> detalle2 = new ArrayList<Detalle>();
		detalle2.add(new Detalle(1,producto2,2,160.44));detalle2.add(new Detalle(2,producto5,2,44.45));detalle2.add(new Detalle(3,producto10,1,21.52));
		Venta venta2 = new Venta("2019-03-03", "0001-00002", 203.12, "Efectivo",detalle2,emp1, emp2, cliente2,5);
		listaVentas.add(venta1);
		listaVentas.add(venta2);
		
		List<Detalle> detalle3 = new ArrayList<Detalle>();
		detalle3.add(new Detalle(1,producto4,2,160.44));detalle3.add(new Detalle(2,producto11,2,44.45));detalle2.add(new Detalle(3,producto4,3,21.52));
		Venta venta3 = new Venta("2019-03-04", "0001-00003", 120.30, "Efectivo",detalle3,emp2, emp3, cliente6,7);
		listaVentas.add(venta3);
		
		List<Detalle> detalle4 = new ArrayList<Detalle>();
		detalle4.add(new Detalle(1,producto1,4,140.44));detalle4.add(new Detalle(2,producto4,1,41.45));detalle4.add(new Detalle(3,producto8,3,17.71));
		Venta venta4 = new Venta("2019-05-12", "0001-00004", 420.69, "Tarjeta",detalle4,emp1, emp2, cliente5,8);
		listaVentas.add(venta4);
		
		List<Detalle> detalle5 = new ArrayList<Detalle>();
		detalle5.add(new Detalle(1,producto4,1,120.44));detalle5.add(new Detalle(2,producto9,2,41.45));detalle5.add(new Detalle(3,producto2,3,12.21));
		Venta venta5 = new Venta("2019-05-13", "0001-00005", 110.69, "Tarjeta",detalle5,emp2, emp3, cliente1,6);
		listaVentas.add(venta5);
		
		
		///VENTAS SUCURSAL 2
		List<Detalle> detalle6 = new ArrayList<Detalle>();
		detalle6.add(new Detalle(1,producto4,1,31.44));detalle6.add(new Detalle(2,producto11,3,13.45));detalle6.add(new Detalle(3,producto9,5,40.88));
		Venta venta6 = new Venta("2019-02-04", "0002-00001", 160.20, "Tarjeta",detalle6,emp4, emp6, cliente3,9);
		listaVentas.add(venta6);
		
		List<Detalle> detalle7 = new ArrayList<Detalle>();
		detalle7.add(new Detalle(1,producto1,2,11.44));detalle7.add(new Detalle(2,producto11,1,54.45));detalle7.add(new Detalle(3,producto4,1,10.88));
		Venta venta7 = new Venta("2019-02-12", "0002-00002", 760.20, "Efectivo",detalle7,emp5, emp5, cliente7,4);
		listaVentas.add(venta7);
		
		List<Detalle> detalle8 = new ArrayList<Detalle>();
		detalle8.add(new Detalle(1,producto2,1,21.43));detalle8.add(new Detalle(2,producto4,1,32.99));detalle8.add(new Detalle(3,producto8,5,91.88));
		Venta venta8 = new Venta("2019-04-21", "0002-00003", 123.81, "Tarjeta",detalle8,emp6, emp4, cliente2,7);
		listaVentas.add(venta8);
		
		List<Detalle> detalle9 = new ArrayList<Detalle>();
		detalle9.add(new Detalle(1,producto1,3,31.13));detalle9.add(new Detalle(2,producto3,1,45.15));detalle9.add(new Detalle(3,producto2,1,11.88));
		Venta venta9 = new Venta("2019-05-01", "0002-00004", 203.61, "Tarjeta",detalle9,emp5, emp5, cliente5,5);
		listaVentas.add(venta9);
		
		List<Detalle> detalle10 = new ArrayList<Detalle>();
		detalle10.add(new Detalle(1,producto1,3,31.13));detalle10.add(new Detalle(2,producto2,3,35.82));detalle10.add(new Detalle(3,producto6,1,14.91));
		Venta venta10 = new Venta("2019-05-04", "0002-00005", 701.61, "Efectivo",detalle10,emp4, emp6, cliente1,7);
		listaVentas.add(venta10);
		
		//VENTAS SUCURSAL 3
		List<Detalle> detalle11 = new ArrayList<Detalle>();
		detalle11.add(new Detalle(1,producto3,3,11.13));detalle11.add(new Detalle(2,producto4,3,51.22));detalle11.add(new Detalle(3,producto11,1,44.11));
		Venta venta11 = new Venta("2019-01-21", "0003-00001", 222.91, "Efectivo",detalle11,emp7, emp3, cliente4,7);
		listaVentas.add(venta11);
		
		List<Detalle> detalle12 = new ArrayList<Detalle>();
		detalle12.add(new Detalle(1,producto7,3,31.22));detalle12.add(new Detalle(2,producto9,3,51.22));detalle12.add(new Detalle(3,producto11,1,14.11));
		Venta venta12 = new Venta("2019-02-02", "0003-00002", 103.91, "Tarjeta",detalle12,emp8, emp7, cliente4,7);
		listaVentas.add(venta12);
		
		List<Detalle> detalle13 = new ArrayList<Detalle>();
		detalle13.add(new Detalle(1,producto3,3,66.20));detalle13.add(new Detalle(2,producto4,2,66.12));detalle13.add(new Detalle(3,producto6,4,66.54));
		Venta venta13 = new Venta("2019-02-05", "0003-00003", 401.91, "Tarjeta",detalle13,emp7, emp3, cliente7,9);
		listaVentas.add(venta13);
		
		
		
		
		
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			//parsear objetos a lista de DBObject
			List<DBObject> lista = new ArrayList<DBObject>();
			for(Venta venta : listaVentas) {
				String json = mapper.writeValueAsString(venta);
				lista.add((DBObject) JSON.parse(json));
				
			}
			
			MongoDao.getInstance().agregarLista(lista, "Farmacia", "Venta");
			System.out.println("Datos insertados correctamente!");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
