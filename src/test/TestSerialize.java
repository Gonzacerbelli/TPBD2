package test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dao.MongoDao;
import modelo.Producto;
 
public class TestSerialize
{
    public static void main(String[] args)
    {
        Producto emp = new Producto(1, "Perfume Rosa","BASF", 340, false);
 
        ObjectMapper mapper = new ObjectMapper();
        try
        {
        	
        	//DE POJO A JSON
            String json = mapper.writeValueAsString(emp);	//esto lo parsea a json
            System.out.println("JSON--> "+json);
            System.out.println();
           
            String beutifulJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp); //esto lo cambia para que sea mas lindo
            System.out.println("json mas lindo --> "+beutifulJson);
            //DE JSON A POJO
            Producto productoParseado = mapper.readValue(beutifulJson, Producto.class);
            System.out.println(productoParseado.toString());
            
            
            
            //agregando a la bd
            MongoDao.getInstance().agregar(json, "Farmacia", "Producto"); // funciona
            

            
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
