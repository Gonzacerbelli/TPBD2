package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


import dao.MongoDao;
import modelo.Cliente;
import modelo.Domicilio;

public class TestConsultas {

	public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        
        try {
        	
        	String query = "";
        	
        	System.out.println("Consulta 1");
        	
        	System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
        	String nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            System.out.println("Fecha de inicio: ");
        	String fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            System.out.println("Fecha de fin: ");
        	String fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
        	if (nroSucursal.equals("0")) {
        		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]},{_id : 0,detalle : 1,totalVenta : 1})";
        	} else {
        		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]},{_id : 0,detalle : 1,totalVenta : 1})";
        	}
        	
        	DBObject dbObject = (DBObject) JSON.parse(query);
				
			//System.out.println(query);
			
			List<DBObject> data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", dbObject);
			/*
			String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			
			System.out.println(dataMapped);
			*/
			
			System.out.println(data);
			
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
