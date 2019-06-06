package test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dao.MongoDao;
import modelo.Cliente;
import modelo.Domicilio;
import modelo.Producto;

public class TestAgregarClientes {

	public static void main(String[] args) {
		// domicilios de los clientes
		Domicilio domic1 = new Domicilio(1, "25 de mayo", 712, "Burzaco", "Buenos Aires");
		Domicilio domic2 = new Domicilio(2, "Balvanera", 415, "Quilmes", "Buenos Aires");
		Domicilio domic3 = new Domicilio(3, "Juan de Garay", 111, "Flores", "CABA");
		Domicilio domic4 = new Domicilio(4, "Avellaneda", 2111, "Floresta", "CABA");
		Domicilio domic5 = new Domicilio(5, "Malabia", 50, "Rosario", "Santa Fe");

		// domicilio para sucursales
		Domicilio domic9 = new Domicilio(9, "Uruguay", 566, "Monserrat", "CABA");
		Domicilio domic10 = new Domicilio(10, "29 de Septiembre", 971, "Lanus", "CABA");
		Domicilio domic11 = new Domicilio(11, "25 de Mayo", 340, "Ministro Rivadavia", "Buenos Aires");
		Domicilio domic12 = new Domicilio(12, "Lunavieja", 2177, "San Lorenzo", "Santa Fe");

		Cliente cliente1 = new Cliente(1, "Perez", "Pedro", 1123, domic1, "IOMA", "1234");
		Cliente cliente2 = new Cliente(2, "Ramos", "Hector", 3214, domic2, "OSDE", "9982");
		Cliente cliente3 = new Cliente(3, "Godoy", "Gonzalo", 9912, domic3, "Programas Medicos", "7761");
		Cliente cliente4 = new Cliente(4, "Geier", "Daniel", 1143, domic4, "IOMA", "9911");
		Cliente cliente5 = new Cliente(5, "Chimbo", "Esteban", 5121, domic2, "OSDE", "8876");
		Cliente cliente6 = new Cliente(6, "Termo", "Ernesto", 8970, domic3, "OSEYTA", "5544");
		Cliente cliente7 = new Cliente(7, "Felauto", "Lautaro", 3319, domic5, "OSPECA", "5112");

		ObjectMapper mapper = new ObjectMapper();
		try {
			// agregar a la bd

			String json = mapper.writeValueAsString(cliente7);
			MongoDao.getInstance().agregar(json, "Farmacia", "Cliente");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
