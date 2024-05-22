package planetWars;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    
    private static Planet myPlanet;

    public static void main(String[] args) {
    	TimerTask task1 = new TimerTask() {
            public void run() {
                System.out.println("Hola mundo");
            }
        };

        // Segunda tarea: imprimir "Segunda tarea ejecutada" cada 10 segundos
        TimerTask task2 = new TimerTask() {
            public void run() {
                System.out.println("Segunda tarea ejecutada");
            }
        };

        // Crear un objeto Timer
        Timer timer = new Timer();

        // Programar la primera tarea para que se ejecute después de 10 segundos (10000 milisegundos)
        // y luego se repita cada 5 segundos (5000 milisegundos)
        timer.schedule(task1, 10000, 5000);

        // Programar la segunda tarea para que se ejecute después de 15 segundos (15000 milisegundos)
        // y luego se repita cada 10 segundos (10000 milisegundos)
        timer.schedule(task2, 15000, 10000);
        myPlanet = new Planet(0, 0, 50000, 50000, 0, 01, new ArrayList[7]); // Inicializamos el planeta
        
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

    private static void mostrarMenu() {
        myPlanet.printStats();
        System.out.println("\n===== Main Menu =====");
        System.out.println("1) View Planet Stats");
        System.out.println("2) Build");
        System.out.println("3) Upgrade Technology");
        System.out.println("4) View Battle Reports");
        System.out.println("5) View Thread Coming");
        System.out.println("0) Exit");
        System.out.print("Option > ");
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
        System.out.print("Amount: > ");
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
                    built = myPlanet.newLightHunter(amount);
                    break;
                case "Heavy Hunter":
                    built = myPlanet.newHeavyHunter(amount);
                    break;
                case "Battle Ship":
                    built = myPlanet.newBattleShip(amount);
                    break;
                case "Armored Ship":
                    built = myPlanet.newArmoredShip(amount);
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
        System.out.print("Amount: > ");
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
                    built = myPlanet.newMissileLauncher(amount);
                    break;
                case "Ion Cannon":
                    built = myPlanet.newIonCannon(amount);
                    break;
                case "Plasma Cannon":
                    built = myPlanet.newPlasmaCannon(amount);
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
        System.out.println("Viewing Battle Reports...");
        // Lógica para ver los informes de batalla
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