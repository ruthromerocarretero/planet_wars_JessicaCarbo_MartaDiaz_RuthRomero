package planetWars;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL_DATOS = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String USUARIO = "planet";
    private static final String CONTRASEÑA = "12345";
    private static Connection conn = null;

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

    public static void saveBattle(Planet planet, int[] wasteMetalDeuterium) {
    	String planetName = ""; // Asumiendo que el nombre del planeta se pasa como el primer parámetro
        int wasteMetal = wasteMetalDeuterium[0];
        int wasteDeuterium = wasteMetalDeuterium[1];
        int p_resource_metal_amount = planet.getMetal();
        int p_resource_deuterium_amount = planet.getDeuterium();
        int p_technology_defense_level = planet.getTechnologyDefense();
        int p_technology_attack_level = planet.getTechnologyAtack();
        int counter = 1; // Cambiar esto según tu lógica de aplicación
        int missileLauncherRemaining = planet.getUnitRemaining(4);
        int ionCannonRemaining = planet.getUnitRemaining(5);
        int plasmaCannonRemaining = planet.getUnitRemaining(6);
        int lightHunterRemaining = planet.getUnitRemaining(1);
        int heavyHunterRemaining = planet.getUnitRemaining(2);
        int battleshipRemaining = planet.getUnitRemaining(3);
        int armoredShipRemaining = planet.getUnitRemaining(4);

        try {
            Connection connection = Conexion.getConnection();
            CallableStatement savePlanetStat = connection.prepareCall("{CALL savePlanetStat(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            
            savePlanetStat.setString(1, planetName);
            savePlanetStat.setInt(2, wasteMetal);
            savePlanetStat.setInt(3, wasteDeuterium);
            savePlanetStat.setInt(4, p_technology_defense_level);
            savePlanetStat.setInt(5, p_technology_attack_level);
            savePlanetStat.setInt(6, counter);
            savePlanetStat.setInt(7, missileLauncherRemaining);
            savePlanetStat.setInt(8, ionCannonRemaining);
            savePlanetStat.setInt(9, plasmaCannonRemaining);
            savePlanetStat.setInt(10, lightHunterRemaining);
            savePlanetStat.setInt(11, heavyHunterRemaining);
            savePlanetStat.setInt(12, battleshipRemaining);
            savePlanetStat.setInt(13, armoredShipRemaining);

            savePlanetStat.execute();
            System.out.println("Datos insertados correctamente en la tabla Planet_stats.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

