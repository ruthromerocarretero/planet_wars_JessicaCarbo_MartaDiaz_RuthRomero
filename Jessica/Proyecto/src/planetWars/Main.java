package planetWars;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    
    private static final int ArrayList = 0;
	private static Planet planet;
    private static Battle battle;

    public static void main(String[] args) {
        //ArrayList<MilitaryUnit>[] planetArmy = createPlanetArmy();


        //  planet = new Planet(0, 0, 50000, 50000, 0, 01, new ArrayList[7]);
        planet = new Planet(0, 0, 50000, 50000, 0, 01, createPlanetArmy());
        battle = new Battle(planet.getArmy(), null);

        // Cada 3 minutos generar flota enemiga y hacer batalla
        Timer timer = new Timer();
        TimerTask battleTask = new TimerTask() {
            public void run() {
                ArrayList<MilitaryUnit>[] enemyArmy = createEnemyArmy();
                battle.setEnemyArmy(enemyArmy);
                battle.setPlanetArmy(planet.getArmy());
                battle.startBattle();
            }
        };

        TimerTask reportTask = new TimerTask() {
            public void run() {
                String battleDevelopment = battle.getBattleDevelopment();
                System.out.println(battleDevelopment);
            }
        };

        // Cada 3 minutos (180000ms) generar flota enemiga, actualizar la nuestra y hacer batalla
        timer.scheduleAtFixedRate(battleTask, 18000, 10000);

        // Cada 5 minutos (300000ms) generar reporte de batalla
        timer.scheduleAtFixedRate(reportTask, 30000, 10000);


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
                    break;
                case 5:
                    viewThreadComing();
                    break;
                case 0:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija nuevamente.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }

    private static ArrayList<MilitaryUnit>[] createPlanetArmy() {
        @SuppressWarnings("unchecked")
        ArrayList<MilitaryUnit>[] planetArmy = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            planetArmy[i] = new ArrayList<>();
        }
        planetArmy[0].add(new LigthHunter());
        planetArmy[1].add(new HeavyHunter());
        planetArmy[2].add(new BattleShip());
        planetArmy[3].add(new ArmoredShip());
        planetArmy[4].add(new MissileLauncher(0, 0));
        planetArmy[5].add(new IonCannon(0, 0));
        planetArmy[6].add(new PlasmaCannon(0, 0));
        return planetArmy;
    }

    private static ArrayList<MilitaryUnit>[] createEnemyArmy() {
        @SuppressWarnings("unchecked")
        ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            enemyArmy[i] = new ArrayList<>();
        }
        enemyArmy[0].add(new LigthHunter());
        enemyArmy[1].add(new HeavyHunter());
        enemyArmy[2].add(new BattleShip());
        enemyArmy[3].add(new ArmoredShip());
        enemyArmy[4].add(new MissileLauncher(0, 0));
        enemyArmy[5].add(new IonCannon(0, 0));
        enemyArmy[6].add(new PlasmaCannon(0, 0));
        return enemyArmy;
    }

    private static void mostrarMenu() {
        planet.printStats();
        System.out.println("\n===== Main Menu =====");
        System.out.println("1) View Planet Stats");
        System.out.println("2) Build");
        System.out.println("3) Upgrade Technology");
        System.out.println("4) View Battle Reports");
        System.out.println("5) View Thread Coming");
        System.out.println("0) Exit");
        System.out.print("Option > \n");
    }

    private static void viewPlanetStats() {
        System.out.println("Viewing Planet Stats");
        // Lógica para ver las estadísticas del planeta
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


    private static void upgradeTechnology() {
        System.out.println("Upgrading Technology...");
        // Lógica para mejorar tecnologías
    }

    private static void viewBattleReports() {
        System.out.println("Viewing Battle Reports");
        

    }

    private static void viewThreadComing() {
        System.out.println("Viewing Thread Coming...");
        // Lógica para ver la flota enemiga próxima a atacar
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

}




