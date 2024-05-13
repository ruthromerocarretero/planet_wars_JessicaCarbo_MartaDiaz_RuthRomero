package planetWars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public static void main(String[] args) {
        
    	// Datos para la  conexión a la base de datos
    	
    	String urlDatos = "jdbc:oracle:thin:@localhost:1521/orcl";
        String usuario = "admin";
        String pass = "admin";
        String query= "select * from planet_stats";
        System.out.println("hola");
        

        try {
        	Connection conn = DriverManager.getConnection(urlDatos,usuario,pass);
 
        	Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Conexión establecida correctamente.");
         
            System.out.println("Consulta ejecutada correctamente.");

       
            resultSet.close();
            statement.close();
            conn.close();

   


        } catch (SQLException e) {
            // este catch es para los errores que puedan surgir
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
      
        }
    }
}
