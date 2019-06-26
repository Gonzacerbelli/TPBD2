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
        	double total = 0;
        	
        	switch(num) {
        	
        	case 1:
        		
            	System.out.println("Consulta 1: Detalle y totales de ventas para la cadena completa y por sucursal, entre fechas.");
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
            	System.out.println("Consulta 2: Detalle y totales de ventas para la cadena completa y por sucursal, por obra social o privados entre fechas");
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
        		System.out.println("Consulta 3: Detalle y totales de cobranza para la cadena completa y por sucursal, por medio de pago y entre fechas.");
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
        		System.out.println("Consulta 4: Detalle y totales de ventas de productos, total de la cadena y por sucursal, entre fechas, diferenciados entre farmacia y perfumería.");
            	System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.println("Farmacia o Perfumeria?: ");
            	String sector = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	//>>> no se que tan bien  esté esto porque no estoy seguro si "detalle.producto.esMedicamento : false" solo te trae las ventas donde el detalle sea false. <<<<
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
            	total=0;
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
           	 
        	case 5:
        		System.out.println("Consulta 5: Ranking de ventas de productos, total de la cadena y por sucursal, entre fechas, por monto.");
        		System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]}";
            	} else {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]}";
				}

				// parse a dbObject
				DBObject consulta5 = (DBObject) JSON.parse(query);
				String campos5 = "{_id : 1,totalVenta : 1}";
				DBObject fields5 = (DBObject) JSON.parse(campos5);
				String sort5 = "{totalVenta : -1}";
				DBObject orden5 = (DBObject) JSON.parse(sort5);

				System.out.println(query);
				total = 0;
				data = MongoDao.getInstance().getCollectionDataSorted("Farmacia", "Venta", consulta5, fields5,orden5);

				// data print
				if (!data.isEmpty()) {
					for (DBObject doc : data) {
						total += (double) doc.get("totalVenta");
						String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
						System.out.println(dataMapped);
					}

				} else {
					System.out.println("Ningun documento coincide con la busqueda!");
				}
				
				// print de total
				if (nroSucursal.equals("0")) {
					System.out.println("Total de ventas para la cadena completa: " + total);
				} else {
					System.out.println("Total de ventas para la sucursal " + nroSucursal + ": " + total);
				}

				
        	case 6:
        		System.out.println("Consulta 6: Ranking de ventas de productos, total de la cadena y por sucursal, entre fechas, por cantidad vendida.");
        		System.out.println("Sucursal a consultar: (Para consultar la cadena, ingrese 0) ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.println("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]}";
            	} else {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]}";
				}
            	
            	DBObject consulta6 = (DBObject) JSON.parse(query);
				String campos6 = "{_id : 1, totalVenta : 1, cantidadProductos : 1}";
				DBObject fields6 = (DBObject) JSON.parse(campos6);
				String sort6 = "{cantidadProductos : -1}";
				DBObject orden6 = (DBObject) JSON.parse(sort6);

				System.out.println(query);
				total = 0;
				data = MongoDao.getInstance().getCollectionDataSorted("Farmacia", "Venta", consulta6, fields6,orden6);
				
				// data print
				if (!data.isEmpty()) {
					for (DBObject doc : data) {
						total += (double) doc.get("totalVenta");
						String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
						System.out.println(dataMapped);
					}

				} else {
					System.out.println("Ningun documento coincide con la busqueda!");
				}
				
				// print de total
				if (nroSucursal.equals("0")) {
					System.out.println("Total de ventas para la cadena completa: " + total);
				} else {
					System.out.println("Total de ventas para la sucursal " + nroSucursal + ": " + total);
				}
        		break;
				// cierre del menu!
        	default:
        		System.out.println();
				System.out.println("Saliste...");
				break;

			}

		} catch (IOException e) {
            e.printStackTrace();
        }

	}

}
