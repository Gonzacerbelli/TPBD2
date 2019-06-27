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


public class TestAgregarCliente {
	
	public static void main(String[] args) {
		
		//domicilios de clientes
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
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		listaClientes.add(cliente1);listaClientes.add(cliente2);listaClientes.add(cliente3);listaClientes.add(cliente4);listaClientes.add(cliente5);listaClientes.add(cliente6);
		listaClientes.add(cliente7);
		
		
	
	ObjectMapper mapper = new ObjectMapper();
	try {
		//parsear objetos a lista de DBObject
		List<DBObject> lista = new ArrayList<DBObject>();
		for(Cliente cliente : listaClientes) {
			String json = mapper.writeValueAsString(cliente);
			lista.add((DBObject) JSON.parse(json));
			
		}
		
		MongoDao.getInstance().agregarLista(lista, "Farmacia", "Cliente");
		System.out.println("Datos insertados correctamente!");

		

	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}

}
