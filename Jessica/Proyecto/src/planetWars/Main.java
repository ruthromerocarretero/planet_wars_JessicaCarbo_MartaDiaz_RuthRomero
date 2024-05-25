package planetWars;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    
    private static final int ArrayList = 0;
	private static Planet planet;
    private static Battle battle;
    private static Connection conn;
    public static void main(String[] args) {
    	
    	
    	try {
            // Establecer conexión con la base de datos al inicio del programa
            conn = Conexion.getConnection();
        //ArrayList<MilitaryUnit>[] planetArmy = createPlanetArmy();


        //  planet = new Planet(0, 0, 50000, 50000, 0, 01, new ArrayList[7]);
        planet = new Planet(0, 0, 50000, 50000, 0, 01, createPlanetArmy());
        battle = new Battle(planet.getArmy(), null);
     
        // Cada 3 minutos generar flota enemiga y hacer batalla
        Timer timer = new Timer();
       
        TimerTask reportTask = new TimerTask() {
            public void run() {
                String battleDevelopment = battle.getBattleDevelopment();
                System.out.println("WE HAVE BEEN ATTACKED!!!\n");
            }
        };

        // Cada 3 minutos (180000ms) generar flota enemiga, actualizar la nuestra y hacer batalla
       

        // Cada 5 minutos (300000ms) generar reporte de batalla
        timer.scheduleAtFixedRate(reportTask, 8000, 30000);


        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcion) {
                case 1:
                    viewPlanetStats();
                    break;
                case 2:
                    build();
                    break;
                case 3:
                    upgradeTechnology();
                    break;
                case 4:
                    viewBattleReports();
                    savePlanetStats();
                    break;
                case 0:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija nuevamente.");
            }
        } while (opcion != 0);
        
        scanner.close();
    	}catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Cerrar la conexión al finalizar el programa
            Conexion.closeConnection();
        }
    	
    }

    private static ArrayList<MilitaryUnit>[] createPlanetArmy() {
        @SuppressWarnings("unchecked")
        ArrayList<MilitaryUnit>[] planetArmy = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            planetArmy[i] = new ArrayList<>();
        }
        
        int lightHunterCount = 10;
        int heavyHunterCount = 5;
        int battleShipCount = 3;
        int armoredShipCount = 4;
        int missileLauncherCount = 8;
        int ionCannonCount = 6;
        int plasmaCannonCount = 2;

        for (int i = 0; i < lightHunterCount; i++) {
            planetArmy[0].add(new LigthHunter());
        }
        for (int i = 0; i < heavyHunterCount; i++) {
            planetArmy[1].add(new HeavyHunter());
        }
        for (int i = 0; i < battleShipCount; i++) {
            planetArmy[2].add(new BattleShip());
        }
        for (int i = 0; i < armoredShipCount; i++) {
            planetArmy[3].add(new ArmoredShip());
        }
        for (int i = 0; i < missileLauncherCount; i++) {
            planetArmy[4].add(new MissileLauncher(0, 0));
        }
        for (int i = 0; i < ionCannonCount; i++) {
            planetArmy[5].add(new IonCannon(0, 0));
        }
        for (int i = 0; i < plasmaCannonCount; i++) {
            planetArmy[6].add(new PlasmaCannon(0, 0));
        }
        
        return planetArmy;
    }

    private static ArrayList<MilitaryUnit>[] createEnemyArmy() {
        @SuppressWarnings("unchecked")
        ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            enemyArmy[i] = new ArrayList<>();
        }

        int lightHunterCount = 8;
        int heavyHunterCount = 7;
        int battleShipCount = 5;
        int armoredShipCount = 3;

        for (int i = 0; i < lightHunterCount; i++) {
            enemyArmy[0].add(new LigthHunter());
        }
        for (int i = 0; i < heavyHunterCount; i++) {
            enemyArmy[1].add(new HeavyHunter());
        }
        for (int i = 0; i < battleShipCount; i++) {
            enemyArmy[2].add(new BattleShip());
        }
        for (int i = 0; i < armoredShipCount; i++) {
            enemyArmy[3].add(new ArmoredShip());
        }

        return enemyArmy;
    }

    private static void mostrarMenu() {
        
        System.out.println("\n===== Main Menu =====");
        System.out.println("1) View Planet Stats");
        System.out.println("2) Build");
        System.out.println("3) Upgrade Technology");
        System.out.println("4) View Battle Reports");
        System.out.println("0) Exit");
        System.out.print("Option > \n");
    }

    private static void viewPlanetStats() {
        System.out.println("Viewing Planet Stats");
        planet.printStats();
        
    }
    private static void build() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            mostrarSubMenuBuild();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    buildTroops();
                    break;
                case 2:
                    buildDefenses();
                    break;
                case 3:
                    System.out.println("Go back");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 3);
    }

    private static void buildTroops() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarSubMenuBuildTroops();
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    handleBuildTroops("Light Hunter");
                    break;
                case 2:
                    handleBuildTroops("Heavy Hunter");
                    break;
                case 3:
                    handleBuildTroops("Battle Ship");
                    break;
                case 4:
                    handleBuildTroops("Armored Ship");
                    break;
                case 5:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (opcion != 5);
    }

    private static void handleBuildTroops(String troopType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Amount of Units");
        System.out.print("Amount: > \n");
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return;
        }

        try {
            int built = 0;
            switch (troopType) {
                case "Light Hunter":
                    built = planet.newLightHunter(amount);
                    break;
                case "Heavy Hunter":
                    built = planet.newHeavyHunter(amount);
                    break;
                case "Battle Ship":
                    built = planet.newBattleShip(amount);
                    break;
                case "Armored Ship":
                    built = planet.newArmoredShip(amount);
                    break;
            }
            System.out.println("Added " + built + " " + troopType + "(s) to the fleet.");
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void buildDefenses() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarSubMenuBuildDefenses();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    handleBuildDefenses("Missile Launcher");
                    break;
                case 2:
                    handleBuildDefenses("Ion Cannon");
                    break;
                case 3:
                    handleBuildDefenses("Plasma Cannon");
                    break;
                case 4:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (opcion != 4);
    }
    private static void handleBuildDefenses(String defenseType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Amount of Units");
        System.out.print("Amount: >\n ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return;
        }

        try {
            int built = 0;
            switch (defenseType) {
                case "Missile Launcher":
                    built = planet.newMissileLauncher(amount);
                    break;
                case "Ion Cannon":
                    built = planet.newIonCannon(amount);
                    break;
                case "Plasma Cannon":
                    built = planet.newPlasmaCannon(amount);
                    break;
            }
            System.out.println("Added " + built + " " + defenseType + "(s) to the defenses.");
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
        }
    }


    	
    private static void upgradeTechnology() throws ResourceException {
        System.out.println("Upgrading Technology");
        int technologyAttack = planet.getTechnologyAtack();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Upgrade Technology");
            System.out.println("Actual Defense Technology: " + planet.getTechnologyDefense());
            System.out.println("Actual Attack Technology: " + planet.getTechnologyAtack());
            System.out.println();
            System.out.println("1) Upgrade Defense Technology. Cost: 2000 Deuterium");
            System.out.println("2) Upgrade Attack Technology. Cost: 2000 Deuterium");
            System.out.println("3) Go back");
            System.out.println();
            System.out.println("Deuterium resources = " + planet.getDeuterium());
            System.out.print("Option > ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    planet.upgradeTechnologyDefense();
                    break;
                case 2:
                    planet.upgradeTechnologyAttack();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 3.");
            }
        }
    }
   

    private static void viewBattleReports() {
    	
    	 ArrayList<MilitaryUnit>[] enemyArmy = createEnemyArmy();
    	    battle.setEnemyArmy(enemyArmy);
    	    battle.setPlanetArmy(planet.getArmy());
    	    battle.startBattle();
    	    
    	    // Crear e iniciar el timer
    	    Timer timer = new Timer();
    	    TimerTask reportTask = new TimerTask() {
    	        public void run() {
    	            String battleDevelopment = battle.getBattleDevelopment();
    	            if (battleDevelopment != null) {
    	                System.out.println(battleDevelopment);
    	            } else {
    	                System.out.println("No battle reports available.");
    	            }
    	        }
    	    };
    	    
    	    // Programar tarea para que se ejecute periódicamente
    	    timer.scheduleAtFixedRate(reportTask, 1000, 3000); // Cada 10 segundos
    	    
    	    System.out.println("Viewing Battle Reports");
    	    
    	    
    	}

 
    private static void mostrarSubMenuBuild() {
        System.out.println("\nBuild\n");
        System.out.println("1) Build troops");
        System.out.println("2) Build Defenses");
        System.out.println("3) Go Back");
        System.out.print("Option > ");
    }

    private static void mostrarSubMenuBuildTroops() {
        System.out.println("\n Menu Build troops\n");
        System.out.println("1) Build Light Hunter");
        System.out.println("2) Build Heavy Hunter");
        System.out.println("3) Build Battle Ship");
        System.out.println("4) Build Armored Ship");
        System.out.println("5) Go Back");
        System.out.print("Option > ");
    }
    private static void mostrarSubMenuBuildDefenses() {
        System.out.println("\nMenu Build Defenses");
        System.out.println("1) Build Missile Launcher");
        System.out.println("2) Build Ion Cannon");
        System.out.println("3) Build Plasma Cannon");
        System.out.println("4) Go Back");
        System.out.print("Option > ");
    }
    private static void savePlanetStats() {
        try {
            Conexion conn = new Conexion();
            Conexion.saveBattle(planet, new int[]{planet.getMetal(), planet.getDeuterium()});
            System.out.println("Planet stats saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving planet stats: " + e.getMessage());
        }
    }

}




