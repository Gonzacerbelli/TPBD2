package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dao.MongoDao;
import modelo.Cliente;
import modelo.Domicilio;
import modelo.Producto;

public class TestAgregarProducto {
	
	public static void main(String[] args) {
		
		List<Producto> listaProductos = new ArrayList<Producto>();
		//perfumeria
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
		listaProductos.add(producto1);listaProductos.add(producto2);listaProductos.add(producto3);listaProductos.add(producto4);
		listaProductos.add(producto5);listaProductos.add(producto6);listaProductos.add(producto7);listaProductos.add(producto8);
		listaProductos.add(producto9);listaProductos.add(producto10);listaProductos.add(producto11);
		

		ObjectMapper mapper = new ObjectMapper();
		try {
			//parsear objetos a lista de DBObject
			List<DBObject> lista = new ArrayList<DBObject>();
			for(Producto producto : listaProductos) {
				String json = mapper.writeValueAsString(producto);
				lista.add((DBObject) JSON.parse(json));
				
			}
			
			MongoDao.getInstance().agregarLista(lista, "Farmacia", "Producto");
			System.out.println("Datos insertados correctamente!");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
