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
          /*  System.out.println("Conexión establecida correctamente.666666666");*/
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

    public static void saveBattle(Planet planet, int[] wasteMetalDeuterium) {// arreglarlo por que lo necesito para cada planeta o enemigo no solo guardar 1
    	String planetName = ""; 
        int wasteMetal = wasteMetalDeuterium[0];
        int wasteDeuterium = wasteMetalDeuterium[1];
        int p_resource_metal_amount = planet.getMetal();
        int p_resource_deuterium_amount = planet.getDeuterium();
        int p_technology_defense_level = planet.getTechnologyDefense();
        int p_technology_attack_level = planet.getTechnologyAtack();
        int counter = 1; 
        int missileLauncherRemaining = planet.getUnitRemaining(4);
        int ionCannonRemaining = planet.getUnitRemaining(5);
        int plasmaCannonRemaining = planet.getUnitRemaining(6);
        int lightHunterRemaining = planet.getUnitRemaining(0);
        int heavyHunterRemaining = planet.getUnitRemaining(1);
        int battleshipRemaining = planet.getUnitRemaining(2);
        int armoredShipRemaining = planet.getUnitRemaining(3);

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void saveBattleStats (int resourceMetalAcquired, int resourceDeuterionAcquired) {
    	  try {
              Connection connection = Conexion.getConnection();
              CallableStatement saveBattleStats = connection.prepareCall("{CALL saveBattleStats(?, ?)}");
              saveBattleStats.setInt(1, resourceMetalAcquired);
              saveBattleStats.setInt(2, resourceDeuterionAcquired);
              
              saveBattleStats.execute();
              
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
    public static void saveBattleLog( String logEntry) {
        try {
           
            CallableStatement saveBattleLog = conn.prepareCall("{CALL SaveBattleLog(?)}");

            saveBattleLog.setString(1, logEntry);
            saveBattleLog.execute();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void savePlanetBattleDefense( int missileLauncherBuilt,
            int missileLauncherDestroyed, int ionCannonBuilt,
            int ionCannonDestroyed, int plasmaCannonBuilt,
            int plasmaCannonDestroyed) {
			try {
		
			CallableStatement savePlanetBattleDefense = conn.prepareCall("{CALL savePlanetBattleDefense(?, ?, ?, ?, ?, ?)}");
			
		
			savePlanetBattleDefense.setInt(1, missileLauncherBuilt);
	        savePlanetBattleDefense.setInt(2, missileLauncherDestroyed);
	        savePlanetBattleDefense.setInt(3, ionCannonBuilt);
	        savePlanetBattleDefense.setInt(4, ionCannonDestroyed);
	        savePlanetBattleDefense.setInt(5, plasmaCannonBuilt);
	        savePlanetBattleDefense.setInt(6, plasmaCannonDestroyed);

			savePlanetBattleDefense.execute();
	
			} catch (SQLException e) {
			e.printStackTrace();
			}
    }
    
    public static void savePlanetBattleArmy( int lightHunterBuilt,
            int lightHunterDestroyed, int heavyHunterBuilt,
            int heavyHunterDestroyed, int battleshipBuilt,
            int battleshipDestroyed, int armoredShipBuilt,
            int armoredShipDestroyed) {
			try {
		
			CallableStatement savePlanetBattleArmy = conn.prepareCall("{CALL savePlanetBattleArmy(?, ?, ?, ?, ?, ?, ?, ?)}");
			
			savePlanetBattleArmy.setInt(1, lightHunterBuilt);
	        savePlanetBattleArmy.setInt(2, lightHunterDestroyed);
	        savePlanetBattleArmy.setInt(3, heavyHunterBuilt);
	        savePlanetBattleArmy.setInt(4, heavyHunterDestroyed);
	        savePlanetBattleArmy.setInt(5, battleshipBuilt);
	        savePlanetBattleArmy.setInt(6, battleshipDestroyed);
	        savePlanetBattleArmy.setInt(7, armoredShipBuilt);
	        savePlanetBattleArmy.setInt(8, armoredShipDestroyed);
			savePlanetBattleArmy.execute();
	
			} catch (SQLException e) {
			e.printStackTrace();
			}
	}
	
    public static void saveEnemyArmy(  int lightHunterThreat,
            int lightHunterDestroyed, int heavyHunterThreat,
            int heavyHunterDestroyed, int battleshipThreat,
            int battleshipDestroyed, int armoredShipThreat,
            int armoredShipDestroyed) {
			try {
			CallableStatement saveEnemyArmy = conn.prepareCall("{CALL saveEnemyArmy(?, ?, ?, ?, ?, ?, ?, ?)}");
			saveEnemyArmy.setInt(1, lightHunterThreat);
	        saveEnemyArmy.setInt(2, lightHunterDestroyed);
	        saveEnemyArmy.setInt(3, heavyHunterThreat);
	        saveEnemyArmy.setInt(4, heavyHunterDestroyed);
	        saveEnemyArmy.setInt(5, battleshipThreat);
	        saveEnemyArmy.setInt(6, battleshipDestroyed);
	        saveEnemyArmy.setInt(7, armoredShipThreat);
	        saveEnemyArmy.setInt(8, armoredShipDestroyed);

			saveEnemyArmy.execute();
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
    }
    
}


