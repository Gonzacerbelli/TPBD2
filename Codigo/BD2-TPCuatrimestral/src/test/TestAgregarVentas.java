package test;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import org.codehaus.jackson.map.ObjectMapper;

import dao.MongoDao;
import modelo.Cliente;
import modelo.Detalle;
import modelo.Domicilio;
import modelo.Empleado;
import modelo.Producto;
import modelo.Venta;

public class TestAgregarVentas {
	
	
	
	public static void main(String[] args) {
		
		Domicilio domic1 = new Domicilio(1, "25 de mayo", 712, "Burzaco", "Buenos Aires");
		Domicilio domic2 = new Domicilio(2, "Balvanera", 415, "Quilmes", "Buenos Aires");
		Domicilio domic3 = new Domicilio(3, "Juan de Garay", 111, "Flores", "CABA");
		Domicilio domic4 = new Domicilio(4, "Avellaneda", 2111, "Floresta", "CABA");
		Domicilio domic5 = new Domicilio(5, "Malabia", 50, "Rosario", "Santa Fe");
		
		Producto producto1 = new Producto(1, "Afeitadora", "Sin Pelin", 22.94, false);
		Producto producto2 = new Producto(2, "Agua Oxigenada", "OxiStatus", 52.14, false);
		Producto producto3 = new Producto(3, "Jabon de tocador", "Antigrass", 72.43, false);
		
		//medicamentos
		Producto producto4 = new Producto(4, "Pastilla de Carbon", "SacaChispas", 82.12, true);
		Producto producto5 = new Producto(5, "Aspirina", "BASF", 82.12, true);
		Producto producto6 = new Producto(6, "Crema de hongos", "Honguitrap", 21.52, true);
		Producto producto7 = new Producto(7, "Actron", "Vitaminel", 66.12, true);
		Producto producto8 = new Producto(8, "Ibupirac", "BASF", 15.12, true);
		Producto producto9 = new Producto(9, "Amoxicilina", "BAYER", 89.50, true);
		Producto producto10 = new Producto(10, "Nicotinell", "BASF", 12.91, true);

		

		Cliente cliente1 = new Cliente(1, "Perez", "Pedro", 1123, domic1, "IOMA", "1234");
		Cliente cliente2 = new Cliente(2, "Ramos", "Hector", 3214, domic2, "OSDE", "9982");
		Cliente cliente3 = new Cliente(3, "Godoy", "Gonzalo", 9912, domic3, "Programas Medicos", "7761");
		Cliente cliente4 = new Cliente(4, "Geier", "Daniel", 1143, domic4, "IOMA", "9911");
		Cliente cliente5 = new Cliente(5, "Chimbo", "Esteban", 5121, domic2, "OSDE", "8876");
		Cliente cliente6 = new Cliente(6, "Termo", "Ernesto", 8970, domic3, "OSEYTA", "5544");
		Cliente cliente7 = new Cliente(7, "Felauto", "Lautaro", 3319, domic5, "OSPECA", "5112");
		
		Domicilio domic6 = new Domicilio(6, "Colon", 555, "La Matanza", "Buenos Aires");
		Domicilio domic7 = new Domicilio(7, "Mariete", 111, "Burzaco", "Buenos Aires");
		Domicilio domic8 = new Domicilio(8, "Trebuchet", 9700, "Adrogue", "Buenos Aires");

		Empleado emp1 = new Empleado(1, "Ramirez", "Marcos", 77654, domic6, "OSDE", "91182");
		Empleado emp2 = new Empleado(2, "Pedrete", "Nestor", 6651, domic7, "IOMA", "19283");
		Empleado emp3 = new Empleado(3, "Marino", "Flavio", 41756, domic8, "OSPECON", "44118");
		
		//detalle venta1
		Detalle detalle1 = new Detalle(producto1, 2, producto1.getPrecio());
		Detalle detalle2 = new Detalle(producto3, 4, producto1.getPrecio());
		Detalle detalle3 = new Detalle(producto6, 1, producto1.getPrecio());
		
		
		
		Venta venta5 = new Venta(Date.valueOf(LocalDate.of(2018, 4, 3)), "0002-00000005", 215.13, "Efectivo",emp3, emp2, cliente2);
		//detalle venta1
		venta5.getDetalle().add(detalle1);
		venta5.getDetalle().add(detalle2);
		venta5.getDetalle().add(detalle3);
		
		

		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// agregar a la bd

			String json = mapper.writeValueAsString(venta5);
			MongoDao.getInstance().agregar(json, "Farmacia", "Venta");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
