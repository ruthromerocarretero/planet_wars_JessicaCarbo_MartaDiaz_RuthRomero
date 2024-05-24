
package planetWars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL_DATOS = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String USUARIO = "planet";
    private static final String CONTRASEÑA = "12345";
    private static Connection conn = null;
    /*String query= "select * from planet_stats";
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(query);*/
    public static Connection getConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection(URL_DATOS, USUARIO, CONTRASEÑA);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            throw new SQLException("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
