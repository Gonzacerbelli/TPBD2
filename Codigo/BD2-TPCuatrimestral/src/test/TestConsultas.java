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
        	
        	System.out.println("Elija consulta (1 a 8): ");
        	String opcion =new BufferedReader(new InputStreamReader(System.in)).readLine();
        	int num = Integer.parseInt(opcion);
        	String query = "";
        	String nroSucursal="";
        	String fechaInicio="";
        	String fechaFin="";
        	List<DBObject> data = new ArrayList<DBObject>();
        	
        	switch(num) {
        	
        	case 1:
        		
            	System.out.println("Consulta 1");
            	System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
            	if (nroSucursal.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
            	
            	DBObject consulta1 = (DBObject) JSON.parse(query);
            	
            	///se lo paso al getCollectionData() para que solo me imprima estos campos
            	String campos1 = "{_id : 0,detalle : 1,totalVenta : 1}";	
            	DBObject fields1 = (DBObject) JSON.parse(campos1);
    				
    			System.out.println(query);
    			
    			
    			
    			 data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta1,fields1);
    			
    			
    			// String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    			// System.out.println(dataMapped);
    			
    			
    			 if (!data.isEmpty()) {
                	 for(DBObject doc : data) {
         				String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
         				System.out.println(dataMapped);
         			}}else {
         				System.out.println("Ningun documento coincide con la busqueda!");
         			}
    			 
    			break;
        	case 2:
            	System.out.println("Consulta 2");
            	System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.println("Filtrar por Obra Social? 1-> SI 0-> NO : ");
            	String obraSocial = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0") && obraSocial.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'cliente.obraSocial' : 'Privado'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if(nroSucursal.equals("0") && obraSocial.equals("1")){
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'cliente.obraSocial' : {$not : {$regex : 'Privado'}}}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}else if(!nroSucursal.equals("0") && obraSocial.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'cliente.obraSocial' : 'Privado'}]},{_id : 0,detalle : 1,totalVenta : 1})";	
            	}else if(!nroSucursal.equals("0") && obraSocial.equals("1")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'cliente.obraSocial' : {$not : {$regex : 'Privado'}}}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
            	
            	//parse a dbObject
            	DBObject consulta2 = (DBObject) JSON.parse(query);
            	String campos2 = "{_id : 0,detalle : 1,totalVenta : 1}";	
            	DBObject fields2 = (DBObject) JSON.parse(campos2);
            	
            	System.out.println(query);
            	
            	 data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta2,fields2);
            	 
            	 if (!data.isEmpty()) {
            	 for(DBObject doc : data) {
     				String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
     				System.out.println(dataMapped);
     			}}else {
     				System.out.println("Ningun documento coincide con la busqueda!");
     			}
        		
        		break;
        	case 3:
        		System.out.println("Consulta 3");
            	System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.println("Forma de Pago: ");
            	String formaDePago = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0") && formaDePago.equals("Efectivo")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'formaDePago' : 'Efectivo'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if(nroSucursal.equals("0") && formaDePago.equals("Tarjeta")){
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'formaDePago' : 'Tarjeta'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}else if(!nroSucursal.equals("0") && formaDePago.equals("Efectivo")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'formaDePago' : 'Efectivo'}]},{_id : 0,detalle : 1,totalVenta : 1})";	
            	}else if(!nroSucursal.equals("0") && formaDePago.equals("Tarjeta")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'formaDePago' : 'Efectivo'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
        		
            	//parse a dbObject
            	DBObject consulta3 = (DBObject) JSON.parse(query);
            	String campos3 = "{_id : 0,detalle : 1,totalVenta : 1,formaDePago : 1}";	
            	DBObject fields3 = (DBObject) JSON.parse(campos3);
            	
            	System.out.println(query);
            	
           	 	data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta3,fields3);
        		
           	 if (!data.isEmpty()) {
            	 for(DBObject doc : data) {
     				String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
     				System.out.println(dataMapped);
     			}}else {
     				System.out.println("Ningun documento coincide con la busqueda!");
     			}
           	 break;
           	 
        	case 4:
        		System.out.println("Consulta 4");
            	System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.println("Farmacia o Perfumeria?: ");
            	String sector = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0") && sector.equals("Farmacia")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'detalle.producto.esMedicamento' : false }]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if(nroSucursal.equals("0") && sector.equals("Perfumeria")){
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'detalle.producto.esMedicamento' : true }]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}else if(!nroSucursal.equals("0") && sector.equals("Farmacia")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'detalle.producto.esMedicamento' : false }]},{_id : 0,detalle : 1,totalVenta : 1})";	
            	}else if(!nroSucursal.equals("0") && sector.equals("Perfumeria")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'detalle.producto.esMedicamento' : true }]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
            	
            	//parse a dbObject
            	DBObject consulta4 = (DBObject) JSON.parse(query);
            	String campos4 = "{_id : 0,detalle : 1,totalVenta : 1,formaDePago : 1}";	
            	DBObject fields4 = (DBObject) JSON.parse(campos4);
            	
            	System.out.println(query);
            	double total = 0;
           	 	data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta4,fields4);
        		
           	 if (!data.isEmpty()) {
            	 for(DBObject doc : data) {
            		 total+= (double) doc.get("totalVenta");
     				String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
     				System.out.println(dataMapped);
     			}}else {
     				System.out.println("Ningun documento coincide con la busqueda!");
     			}
           	 
           	 	if(nroSucursal.equals("0")) {
           	 	System.out.println("Total de "+sector+" para la cadena completa: "+total);}
           	 	else {
           	 	System.out.println("Total de "+sector+" para la sucursal "+nroSucursal+": "+total);
           	 	}
           	 break;
        		
        		
            	//cierre del menu!
        	default: System.out.println("Saliste...");
        	break;
        	
        	
        	}
        	
			
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
