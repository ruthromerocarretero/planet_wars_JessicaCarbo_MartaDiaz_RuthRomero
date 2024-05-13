package planetWars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public static void main(String[] args) {
        // Datos de conexión a la base de datos
    	
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
         // Confirmar que la consulta se ha ejecutado correctamente
            System.out.println("Consulta ejecutada correctamente.");

            // Iterar sobre los resultados y mostrarlos
            while (resultSet.next()) {
                // Suponiendo que la tabla tiene columnas "columna1", "columna2", etc.
                String columna1 = resultSet.getString("columna1");
                String columna2 = resultSet.getString("columna2");
                // Obtén valores de otras columnas según sea necesario

                // Imprimir los valores de las columnas
                System.out.println("Columna1: " + columna1 + ", Columna2: " + columna2);
                // Imprime valores de otras columnas según sea necesario
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
            conn.close();

   

            // Realizar operaciones adicionales con la base de datos aquí

        } catch (SQLException e) {
            // Capturar excepciones de SQL y mostrar un mensaje de error
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
      
        }
    }
}
