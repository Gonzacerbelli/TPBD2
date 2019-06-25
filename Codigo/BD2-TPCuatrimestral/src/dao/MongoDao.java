package dao;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

import modelo.Empleado;

public class MongoDao {

	private static MongoDao instance = null;

	protected MongoDao() {
	}

	public static MongoDao getInstance() {
		if (instance == null) {
			instance = new MongoDao();
		}
		return instance;
	}

	public void agregar(String json, String DB, String collection) {
		try {
			MongoClient client = new MongoClient("localhost"); // cliente
			DB database = client.getDB(DB); // base de datos con la que se trabaja
			DBCollection coleccion = database.getCollection(collection); // la collection (representaria la tabla)

			DBObject documento = (DBObject) JSON.parse(json); // parsearlo a documento
			coleccion.insert(documento); // insertar

		} catch (UnknownHostException e) {
			System.out.println("Error Ingresando el dato");
		}
	}
	
	
	public void agregarLista(List<DBObject> lista,String DB,String collection) {
		try {
			MongoClient client = new MongoClient("localhost"); // cliente
			DB database = client.getDB(DB); // base de datos con la que se trabaja
			DBCollection coleccion = database.getCollection(collection); // la collection (representaria la tabla)
			coleccion.insert(lista);
		}catch(UnknownHostException e) {
			System.out.println("Error Ingresando el dato");
		}
	}
	
	public List<DBObject> getCollectionData(String DB, String collection, DBObject query) {
		List<DBObject> data = null;
		try {
			MongoClient client = new MongoClient("localhost");
			DB database = client.getDB(DB);
			DBCollection coleccion = database.getCollection(collection);
			data = coleccion.find(query).toArray();
		} catch (UnknownHostException e) {
			System.out.println("Error consultando db: " + e.getMessage());
		}
		return data;
	}
	
	
	

}
