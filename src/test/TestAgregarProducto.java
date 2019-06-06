package test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dao.MongoDao;
import modelo.Producto;

public class TestAgregarProducto {

	public static void main(String[] args) {

        Producto producto1 = new Producto (1, "Shampoo", "Dove", 68.96, false);
        Producto producto2 = new Producto (2, "Acondicionador", "Sedal", 44.12, false);
        Producto producto3 = new Producto (3, "Jabón de tocador", "Suave", 15.77, false);
        Producto producto4 = new Producto (4, "Pasta dental", "Colgate", 64.62, false);
        Producto producto5 = new Producto (5, "Afeitadora", "Gillette", 102.85, false);
        Producto producto6 = new Producto (6, "Crema de pelo", "Elvive", 44.96, false);
        Producto producto7 = new Producto (7, "Antitranspirante", "Dove", 79.58, false);
        Producto producto8 = new Producto (8, "Aspirina", "Bayer", 13.63, true);
        Producto producto9 = new Producto (9, "Ibuprofeno", "Sacadolor", 14.32, true);
        Producto producto10 = new Producto (10, "Tafirol", "Sacadolor", 19.14, true);
        Producto producto11 = new Producto (11, "Dioxadol", "Fiebremenos", 236.50, true);
        Producto producto12 = new Producto (12, "Pastillas de carbono", "Casiactivia", 413.63, true);
        Producto producto13 = new Producto (13, "Agua oxigenada", "Oxiplus", 222.22, true);

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
