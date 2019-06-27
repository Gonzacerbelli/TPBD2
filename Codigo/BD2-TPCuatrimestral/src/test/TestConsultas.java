package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import modelo.Producto;
import modelo.RankingClientes;
import modelo.Sort;

public class TestConsultas {

	
	
	public static void main(String[] args) {
		
		

        ObjectMapper mapper = new ObjectMapper();
        
        
        
        try {
        	
        	System.out.println("Elija consulta:");
        	System.out.println("1 - Detalle y totales de ventas para la cadena completa y por sucursal, entre fechas.");
        	System.out.println("2 - Detalle y totales de ventas para la cadena completa y por sucursal, por obra social o privados entre fechas.");
        	System.out.println("3 - Detalle y totales de cobranza para la cadena completa y por sucursal, por medio de pago y entre fechas.");
        	System.out.println("4 - Detalle y totales de ventas de productos, total de la cadena y por sucursal, entre fechas, diferenciados entre farmacia y perfumería.");
        	System.out.println("5 - Ranking de ventas de productos, total de la cadena y por sucursal, entre fechas, por monto.");
        	System.out.println("6 - Ranking de ventas de productos, total de la cadena y por sucursal, entre fechas, por cantidad vendida.");
        	System.out.println("7 - Ranking de clientes por compras, total de la cadena y por sucursal, entre fechas, por monto.");
        	System.out.println("8 - Ranking de clientes por compras, total de la cadena y por sucursal, entre fechas, por cantidad vendida.");
        	System.out.println("IMPORTANTE: Para consultar la cadena, ingrese 0 en sucursal.");
        	System.out.println("IMPORTANTE: La fecha debe ser ingresada en formato 'AAAA-MM-DD'.");
        	System.out.print("Opción: ");
        	String opcion =new BufferedReader(new InputStreamReader(System.in)).readLine();
        	
        	int num = Integer.parseInt(opcion);
        	String query = "";
        	String nroSucursal="";
        	String fechaInicio="";
        	String fechaFin="";
        	List<DBObject> data = new ArrayList<DBObject>();
        	double total = 0;
        	
        	ArrayList<RankingClientes> rankingMonto = new ArrayList<RankingClientes>();
        	rankingMonto.add(new RankingClientes(1,0,0));
        	rankingMonto.add(new RankingClientes(2,0,0));
        	rankingMonto.add(new RankingClientes(3,0,0));
        	rankingMonto.add(new RankingClientes(4,0,0));
        	rankingMonto.add(new RankingClientes(5,0,0));
        	rankingMonto.add(new RankingClientes(6,0,0));
        	rankingMonto.add(new RankingClientes(8,0,0));
        	rankingMonto.add(new RankingClientes(7,0,0));
        	
        	ArrayList<RankingClientes> rankingCantidadCompras = new ArrayList<RankingClientes>();
        	rankingCantidadCompras.add(new RankingClientes(1,0,0));
        	rankingCantidadCompras.add(new RankingClientes(2,0,0));
        	rankingCantidadCompras.add(new RankingClientes(3,0,0));
        	rankingCantidadCompras.add(new RankingClientes(4,0,0));
        	rankingCantidadCompras.add(new RankingClientes(5,0,0));
        	rankingCantidadCompras.add(new RankingClientes(6,0,0));
        	rankingCantidadCompras.add(new RankingClientes(7,0,0));	
        	rankingCantidadCompras.add(new RankingClientes(8,0,0));
        	
        	//menu
        	switch(num) {
        	
        	case 1:
        		
            	System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
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
    			data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta1,fields1);
    			
    			double totalVentas = 0;
    			
    			if (!data.isEmpty()) {
    				if (nroSucursal.equals("0")) {
         				System.out.println("Resultados para la cadena:");
         			} else {
         				System.out.println("Resultados para la sucursal " + nroSucursal + ": ");
         			}
    				System.out.println("Detalle de ventas: ");
    				for(DBObject doc : data) {
    					totalVentas += (double) doc.get("totalVenta");
    					DBObject detalle = (DBObject) doc.get("detalle");
    					
    					String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(detalle);
            			System.out.println(dataMapped);
         			}

         			System.out.println("Total de ventas: " + totalVentas );
         			
    			} else {
         			System.out.println("Ningun documento coincide con la busqueda!");
         		}
    			break;

        	case 2:
            	System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.print("Filtrar por Obra Social? 1-> SI 0-> NO : ");
            	String obraSocial = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0") && obraSocial.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'cliente.obraSocial' : 'Privado'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if (nroSucursal.equals("0") && obraSocial.equals("1")){
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'cliente.obraSocial' : {$not : {$regex : 'Privado'}}}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if (!nroSucursal.equals("0") && obraSocial.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'cliente.obraSocial' : 'Privado'}]},{_id : 0,detalle : 1,totalVenta : 1})";	
            	} else if (!nroSucursal.equals("0") && obraSocial.equals("1")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'cliente.obraSocial' : {$not : {$regex : 'Privado'}}}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
            	
            	//parse a dbObject
            	DBObject consulta2 = (DBObject) JSON.parse(query);
            	String campos2 = "{_id : 0,detalle : 1,totalVenta : 1}";	
            	DBObject fields2 = (DBObject) JSON.parse(campos2);
            	data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta2,fields2);
            	
            	double totalVentas2 = 0;
    			
            	if (!data.isEmpty()) {
            		if (nroSucursal.equals("0")) {
         				System.out.println("Resultados para la cadena:");
         			} else {
         				System.out.println("Resultados para la sucursal " + nroSucursal + ": ");
         			}
    				System.out.println("Detalle de ventas: ");
    				for(DBObject doc : data) {
    					totalVentas2 += (double) doc.get("totalVenta");
    					DBObject detalle = (DBObject) doc.get("detalle");
    					
    					String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(detalle);
            			System.out.println(dataMapped);
         			}

         			System.out.println("Total de ventas: " + totalVentas2 );
            	} else {
            		System.out.println("Ningun documento coincide con la busqueda!");
     			}
        		break;

        	case 3:
        		System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.print("Forma de Pago (Efectivo o Tarjeta): ");
            	String formaDePago = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0") && formaDePago.equals("Efectivo")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'formaDePago' : 'Efectivo'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if (nroSucursal.equals("0") && formaDePago.equals("Tarjeta")){
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'formaDePago' : 'Tarjeta'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if (!nroSucursal.equals("0") && formaDePago.equals("Efectivo")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'formaDePago' : 'Efectivo'}]},{_id : 0,detalle : 1,totalVenta : 1})";	
            	} else if (!nroSucursal.equals("0") && formaDePago.equals("Tarjeta")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'formaDePago' : 'Efectivo'}]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
        		
            	//parse a dbObject
            	DBObject consulta3 = (DBObject) JSON.parse(query);
            	String campos3 = "{_id : 0,detalle : 1,totalVenta : 1,formaDePago : 1}";	
            	DBObject fields3 = (DBObject) JSON.parse(campos3);            	
           	 	data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta3,fields3);
           	 	
            	double totalVentas3 = 0;
        		
           	 	if (!data.isEmpty()) {
	           	 	if (nroSucursal.equals("0")) {
	     				System.out.println("Resultados para la cadena:");
	     			} else {
	     				System.out.println("Resultados para la sucursal " + nroSucursal + ": ");
	     			}
					System.out.println("Detalle de ventas: ");
					for(DBObject doc : data) {
						totalVentas3 += (double) doc.get("totalVenta");
						DBObject detalle = (DBObject) doc.get("detalle");
						
						String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(detalle);
	        			System.out.println(dataMapped);
	     			}
	
	     			System.out.println("Total de ventas: " + totalVentas3 );
           	 	} else {
     				System.out.println("Ningun documento coincide con la busqueda!");
     			}
           	 	break;
           	 
        	case 4:
        		System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	System.out.print("Farmacia o Perfumeria?: ");
            	String sector = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0") && sector.equals("Farmacia")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'detalle.producto.esMedicamento' : false }]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if (nroSucursal.equals("0") && sector.equals("Perfumeria")){
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}},{'detalle.producto.esMedicamento' : true }]},{_id : 0,detalle : 1,totalVenta : 1})";
            	} else if (!nroSucursal.equals("0") && sector.equals("Farmacia")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'detalle.producto.esMedicamento' : false }]},{_id : 0,detalle : 1,totalVenta : 1})";	
            	} else if (!nroSucursal.equals("0") && sector.equals("Perfumeria")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}, {'detalle.producto.esMedicamento' : true }]},{_id : 0,detalle : 1,totalVenta : 1})";
            	}
            	
            	//parse a dbObject
            	DBObject consulta4 = (DBObject) JSON.parse(query);
            	String campos4 = "{_id : 0,detalle : 1,totalVenta : 1,formaDePago : 1}";	
            	DBObject fields4 = (DBObject) JSON.parse(campos4);
            	total=0;
           	 	data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta4,fields4);
           	 	
            	double totalVentas4 = 0;
        		
           	 	if (!data.isEmpty()) {
	           	 	if (nroSucursal.equals("0")) {
	     				System.out.println("Resultados para la cadena:");
	     			} else {
	     				System.out.println("Resultados para la sucursal " + nroSucursal + ": ");
	     			}
					System.out.println("Detalle de ventas: ");
					for(DBObject doc : data) {
						totalVentas4 += (double) doc.get("totalVenta");
						DBObject detalle = (DBObject) doc.get("detalle");
						
						String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(detalle);
	        			System.out.println(dataMapped);
	     			}
		           	 
	           	 	if (nroSucursal.equals("0")) {
	           	 		System.out.println("Total de "+sector+" para la cadena completa: "+totalVentas4);
	           	 	} else {
	           	 		System.out.println("Total de "+sector+" para la sucursal "+nroSucursal+": "+totalVentas4);
	           	 	}
           	 	} else {
           	 		System.out.println("Ningun documento coincide con la busqueda!");
     			}
           	 	break;
           	 
        	case 5:
        		System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]}";
            	} else {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]}";
				}

				// parse a dbObject
				DBObject consulta5 = (DBObject) JSON.parse(query);
				String campos5 = "{_id : 1, totalVenta : 1}";
				DBObject fields5 = (DBObject) JSON.parse(campos5);
				String sort5 = "{totalVenta : -1}";
				DBObject orden5 = (DBObject) JSON.parse(sort5);

			//	System.out.println(query);
				double totalVentas5 = 0;
				data = MongoDao.getInstance().getCollectionDataSorted("Farmacia", "Venta", consulta5, fields5,orden5);

				// data print
				if (!data.isEmpty()) {
					if (nroSucursal.equals("0")) {
	     				System.out.println("Resultados para la cadena:");
	     			} else {
	     				System.out.println("Resultados para la sucursal " + nroSucursal + ": ");
	     			}
					System.out.println("ID y totales de ventas: ");
					for(DBObject doc : data) {
						totalVentas5 += (double) doc.get("totalVenta");
    					
						String dataMapped = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
    					System.out.println(dataMapped);
	     			}
					if (nroSucursal.equals("0")) {
						System.out.println("Total de ventas para la cadena completa: " + String.format("%.2f", totalVentas5));
					} else {
						System.out.println("Total de ventas para la sucursal " + nroSucursal + ": " + totalVentas5);
					}
				} else {
					System.out.println("Ningun documento coincide con la busqueda!");
				}
				break;
				
        	case 6:
        		
        		System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
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
        		
        	case 7:
        		System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]}";
            	} else {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]}";
				}
            	
            	DBObject consulta7 = (DBObject) JSON.parse(query);
				String campos7 = "{cliente : 1, totalVenta : 1}";
				DBObject fields7 = (DBObject) JSON.parse(campos7);
				String sort7 = "{totalVenta : -1}";
				DBObject orden7 = (DBObject) JSON.parse(sort7);
				data = MongoDao.getInstance().getCollectionDataSorted("Farmacia", "Venta", consulta7, fields7, orden7);
				
				
				
				// pongo los datos en la lista correspondiente
				if (!data.isEmpty()) {
					for (DBObject doc : data) {
						total += (double) doc.get("totalVenta");
						DBObject cliente = (DBObject) doc.get("cliente");
						rankingMonto.get((int) cliente.get("_id")).setMontoCompra((double)doc.get("totalVenta")+
								rankingMonto.get((int) cliente.get("_id")).getMontoCompra());
						
						
					}
				} else {
					System.out.println("\nATENCIÓN: Ningun documento coincide con la busqueda!");
				}
				
				//data sort --> DESCENDIENTE
				Collections.sort(rankingMonto, new Comparator<RankingClientes>(){
					public int compare(RankingClientes c1, RankingClientes c2) {
						return Integer.valueOf((int) c2.getMontoCompra()).compareTo((int) c1.getMontoCompra());
					}
				});
				
				
				//data print
				if (!data.isEmpty()) { 
					System.out.println("\nClientes por monto de compra: ");
				for(RankingClientes rank : rankingMonto) {
				System.out.println("Cliente: "+rank.getCliente() +", Monto de Compra: "+ String.format("%.2f", rank.getMontoCompra()));

				}
				if (nroSucursal.equals("0")) {
					System.out.println("Total de ventas para la cadena completa: " + String.format("%.2f", total));
				} else {
					System.out.println("Total de ventas para la sucursal " + nroSucursal + ": " + String.format("%.2f", total));
				}
				
				}

        		break;
        		
        	case 8:
        		System.out.print("Sucursal a consultar: ");
            	nroSucursal = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de inicio: ");
            	fechaInicio = new BufferedReader(new InputStreamReader(System.in)).readLine();
                
                System.out.print("Fecha de fin: ");
            	fechaFin = new BufferedReader(new InputStreamReader(System.in)).readLine();
            	
            	if (nroSucursal.equals("0")) {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}]}";
            	} else {
            		query = "{$and: [{fecha: {$gte: '" + fechaInicio + "'}}, {fecha: {$lte: '" + fechaFin + "'}}, {'ticket': {$regex:'000" + nroSucursal + "-'}}]}";
				}
            	
            	DBObject consulta8 = (DBObject) JSON.parse(query);
				String campos8 = "{cliente : 1, detalle : 1, totalVenta : 1, cantidadProductos : 1}";
				DBObject fields8 = (DBObject) JSON.parse(campos8);
				data = MongoDao.getInstance().getCollectionData("Farmacia", "Venta", consulta8, fields8);
				
				// data print
				if (!data.isEmpty()) {
					for (DBObject doc : data) {
						DBObject cliente = (DBObject) doc.get("cliente");
						int cantidadProductos = (int) doc.get("cantidadProductos");
						total+= (double) doc.get("totalVenta");
						rankingCantidadCompras.get((int) cliente.get("_id")).setCantidadVentas(rankingCantidadCompras.get((int) cliente.get("_id")).getCantidadVentas() 
								+ cantidadProductos);
					}
				} else {
					System.out.println("Ningun documento coincide con la busqueda!");
				}
				
				//data sort --> DESCENDIENTE
				Collections.sort(rankingCantidadCompras, new Comparator<RankingClientes>(){
					public int compare(RankingClientes c1, RankingClientes c2) {
						return Integer.valueOf((int) c2.getCantidadVentas()).compareTo((int) c1.getCantidadVentas());
					}
				});
				
				
				//data print
				if (!data.isEmpty()) { 
					System.out.println("\nClientes por monto de compra: ");
				for(RankingClientes rank : rankingCantidadCompras) {
				System.out.println("Cliente: "+rank.getCliente() +", Cantidad Comprada: "+ rank.getCantidadVentas());

				}
				if (nroSucursal.equals("0")) {
					System.out.println("Total de ventas para la cadena completa: " + String.format("%.2f", total));
				} else {
					System.out.println("Total de ventas para la sucursal " + nroSucursal + ": " + String.format("%.2f", total));
				}
				
				}

        		break;

				// cierre del menu!
        	default:
        		System.out.println();
				System.out.println("Gracias por Utilizar el servicio..");
				break;

			}

		} catch (IOException e) {
            e.printStackTrace();
        }

	}

}
