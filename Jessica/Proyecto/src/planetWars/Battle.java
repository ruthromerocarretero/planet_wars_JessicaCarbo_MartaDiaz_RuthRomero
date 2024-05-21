package planetWars;

import java.util.ArrayList;
import java.util.Random;

public class Battle implements Variables {
	private ArrayList<MilitaryUnit>[] planetArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList[][] armies;
	private String battleDevelopment;
	private int[][] initialCostFleet ;
	private int initialNumberUnitsPlanet, initialNumberUnitsEnemy;
	private int[] wasteMetalDeuterium;
	private int[] enemyDrops;
	private int[] planetDrops;
	private int[][] resourcesLooses;
	private int[][] initialArmies;
	private int[] actualNumberUnitsPlanet;
	private int[] actualNumberUnitsEnemy;
	private Random random;
	private int turno;
	private int [] ejercitoplanet;
	private int[] ejercitoEnemigo;
	private

	    // Constructor
	    public Battle(ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
	        this.planetArmy = planetArmy;
	        this.enemyArmy = enemyArmy;

	        // Initialize other variables
	        this.armies = new ArrayList[2][7];
	        this.battleDevelopment = "";
	        this.initialCostFleet = new int[2][2];
	        this.initialNumberUnitsPlanet = 0;
	        this.initialNumberUnitsEnemy = 0;
	        this.wasteMetalDeuterium = new int[2];
	        this.enemyDrops = new int[7];
	        this.planetDrops = new int[7];
	        this.resourcesLooses = new int[2][3];
	        this.actualNumberUnitsPlanet = new int[7];
	        this.actualNumberUnitsEnemy = new int[7];
	        this.random = new Random();
	        this.turno = random.nextInt(2);
	        this.ejercitoplanet = CHANCE_ATTACK_PLANET_UNITS;
	        this.ejercitoEnemigo = CHANCE_ATTACK_ENEMY_UNITS ;
	        
	        
	        //inicializo array armies
	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < 7; j++) {
	                armies[i][j] = new ArrayList<MilitaryUnit>();
	            }
	        }

	        
	        for (int i = 0; i < 7; i++) {
	            planetArmy[i] = new ArrayList<MilitaryUnit>();
	            enemyArmy[i] = new ArrayList<MilitaryUnit>();
	            armies[0][i] = planetArmy[i]; // Ejército del planeta en la primera fila
	            armies[1][i] = enemyArmy[i]; // Ejército enemigo en la segunda fila
	        }
	        
	        for (ArrayList<MilitaryUnit> unitType : planetArmy) {// construimos la matriz para nuestro ejercito
	        	for (MilitaryUnit unit : unitType) {
	        		if (unit instanceof LigthHunter) initialArmies[0][0] =+ 1;
	        		if (unit instanceof HeavyHunter) initialArmies[0][1] =+ 1;
	        		if (unit instanceof BattleShip) initialArmies[0][2] =+ 1;
	        		if (unit instanceof ArmoredShip) initialArmies[0][3] =+ 1;
	        		if (unit instanceof MissileLauncher) initialArmies[0][4] =+ 1;
	        		if (unit instanceof IonCannon) initialArmies[0][5] =+ 1;
	        		if (unit instanceof PlasmaCannon) initialArmies[0][6] =+ 1;
	        	}
	        }
	        for (ArrayList<MilitaryUnit> unitType : enemyArmy) {// construimos la matriz para nuestro enemigo
	        	for (MilitaryUnit unit : unitType) {
	        		if (unit instanceof LigthHunter) initialArmies[1][0] =+ 1;
	        		if (unit instanceof HeavyHunter) initialArmies[1][1] =+ 1;
	        		if (unit instanceof BattleShip) initialArmies[1][2] =+ 1;
	        		if (unit instanceof ArmoredShip) initialArmies[1][3] =+ 1;
	        		if (unit instanceof MissileLauncher) initialArmies[1][4] =+ 1;
	        		if (unit instanceof IonCannon) initialArmies[1][5] =+ 1;
	        		if (unit instanceof PlasmaCannon) initialArmies[1][6] =+ 1;
	        	}
	        } 
	    }
	    //metodos para calcular las variable y arrays
	    
	    //Método para ir agregando el desarrollo de la batalla
	    public void addToBattleDevelopment(String development) {
	        battleDevelopment += development + "\n";
	    }
	    public String getBattleDevelopment() {
	        return battleDevelopment;
	    }
	    
	    //calculamos el cost fleet 
	    private void calculateInitialCostFleet() { //no hace falta hacer return por que se actualiza directamente en esta clase y puede ser accedida por otros metodos
	        for (int i = 0; i <= 1; ++i) {
	            for (int j = 0; j <= 6; ++j) {
	                if (i == 0) {
	                    initialCostFleet[i][0] += planetArmy[j].size() * planetArmy[j].get(0).getMetalCost();
	                    initialCostFleet[i][1] += planetArmy[j].size() * planetArmy[j].get(0).getDeuteriumCost();
	                } else {
	                    initialCostFleet[i][0] += enemyArmy[j].size() * enemyArmy[j].get(0).getMetalCost();
	                    initialCostFleet[i][1] += enemyArmy[j].size() * enemyArmy[j].get(0).getDeuteriumCost();
	                }
	            }
	        }
	    }
 	    
	    // Calculamos initialNumberUnitsPlanet y initialNumberUnitsEnemy
	 // Dentro de la clase Battle

	 // Método para calcular el número inicial de unidades para el planeta y el enemigo
	 private void calculateInitialNumberUnits() {
	     for (int i = 0; i <= 1; ++i) {
	         for (int j = 0; j <= 6; ++j) {
	             if (i == 0) {
	                 initialNumberUnitsPlanet += initialArmies[i][j];
	             } else {
	                 initialNumberUnitsEnemy += initialArmies[i][j];
	             }
	         }
	     }
	     // Asignar los valores actuales de unidades a los arreglos correspondientes
	     actualNumberUnitsPlanet = initialArmies[0];
	     actualNumberUnitsEnemy = initialArmies[1];
	 }

	    
	    //calculamos los residuos generados por la batalla /// preguntar a Jordi si tengo que calcular el metal o el metodo debe ser este 	public int getChanceGeneratinWaste()
	    public int[] calculateWaste() {
	        int metalWaste = 0;
	        int deuteriumWaste = 0;

	        //calculo lo que me cuesta   metal y deuterio nuestro
	        for (ArrayList<MilitaryUnit> units : planetArmy) {
	            for (MilitaryUnit unit : units) {
	                if (unit.getActualArmor() <= 0) { //Miramos si la nave ha sido destruida
	                    metalWaste += unit.getMetalCost();
	                    deuteriumWaste += unit.getDeuteriumCost();
	                }
	            }
	        }

	        //calculo lo que me cuesta   metal y deuterio enemigo
	        for (ArrayList<MilitaryUnit> units : enemyArmy) {
	            for (MilitaryUnit unit : units) {
	                if (unit.getActualArmor() <= 0) { // Miramos si la nave enemiga ha sido destruida
	                    metalWaste += unit.getMetalCost();
	                    deuteriumWaste += unit.getDeuteriumCost();
	                }
	            }
	        }
	       
	        metalWaste -= initialCostFleet[0][0] + initialCostFleet[1][0];
	        deuteriumWaste -= initialCostFleet[0][1] + initialCostFleet[1][1];
	        
	        wasteMetalDeuterium[0] = metalWaste;
	        wasteMetalDeuterium[1] = deuteriumWaste;
	        return wasteMetalDeuterium ;   
	    }
	    
	    public int[][] calculateDrops() {
	        
	        // Calcula las pérdidas materiales del ejército del planeta
	        for (ArrayList<MilitaryUnit> units : planetArmy) {
	            for (MilitaryUnit unit : units) {
	                int actualArmor = unit.getActualArmor();
	                if (actualArmor <= 0) {
                        planetDrops[0] += unit.getMetalCost(); 
                        planetDrops[1] += unit.getDeuteriumCost(); 
	                }
	            }
	        }
	        // Calcula las pérdidas materiales del ejército enemigo
	        for (ArrayList<MilitaryUnit> units : enemyArmy) {
	            for (MilitaryUnit unit : units) {
	                int actualArmor = unit.getActualArmor();
	                if (actualArmor <= 0) { 
                        enemyDrops[0] += unit.getMetalCost(); 
                        enemyDrops[1] += unit.getDeuteriumCost(); 
	                }
	            }
	        }
	        int[][] drops = {enemyDrops, planetDrops};
	        return drops;
	    }

	    public void updateResourcesLooses (MilitaryUnit ship, int type) {
	        // Actualizar las pérdidas de recursos
	        resourcesLooses[turno][0] += ship.getMetalCost();
	        resourcesLooses[turno][1] += ship.getDeuteriumCost();
	        resourcesLooses[turno][2] += ship.getMetalCost() + 5 * ship.getDeuteriumCost();

	        // Eliminar la nave del ejército correspondiente y actualizar el número de unidades
	        if (turno == 0) {
	            enemyArmy[type].remove(ship);
	            actualNumberUnitsEnemy[type] -= 1;
	        } else {
	            planetArmy[type].remove(ship);
	            actualNumberUnitsPlanet[type] -= 1;
	        }

	        //return ship.getClass().getSimpleName() + "Destroyed";
	    }
	    // Método para actualizar las cantidades de unidades actuales
	    private void updateActualNumberUnits() {
	        // Reiniciar las cantidades de unidades
	        for (int i = 0; i < 7; i++) {
	            actualNumberUnitsPlanet[i] = 0;
	            actualNumberUnitsEnemy[i] = 0;
	        }
	        
	        // Contar las unidades en el ejército del planeta
	        for (ArrayList<MilitaryUnit> units : planetArmy) {
	            for (MilitaryUnit unit : units) {
	                if (unit instanceof LigthHunter) {
	                    actualNumberUnitsPlanet[0]++;
	                } else if (unit instanceof HeavyHunter) {
	                    actualNumberUnitsPlanet[1]++;
	                } else if (unit instanceof BattleShip) {
	                    actualNumberUnitsPlanet[2]++;
	                } // Y así sucesivamente para cada tipo de unidad
	            }
	        }
	        
	        // Contar las unidades en el ejército enemigo
	        for (ArrayList<MilitaryUnit> units : enemyArmy) {
	            for (MilitaryUnit unit : units) {
	                if (unit instanceof LigthHunter) {
	                    actualNumberUnitsEnemy[0]++;
	                } else if (unit instanceof HeavyHunter) {
	                    actualNumberUnitsEnemy[1]++;
	                } else if (unit instanceof BattleShip) {
	                    actualNumberUnitsEnemy[2]++;
	                } 
	            }
	        }
	    }
	    //mecanica del juego 
	    public void calculateTurnequipo() {
	        if (turno == 0) {
	        	attack(planetArmy, enemyArmy);
	            turno = 1;  // Cambia el turno al enemigo
	        } else {
	            attack(enemyArmy, planetArmy);
	            turno = 0;  // Cambia el turno al planeta
	        }
	        //return turno;
	    }
	    
	    public int getGroupDefender(ArrayList<MilitaryUnit>[] army){
	    	ejercitoplanet = CHANCE_ATTACK_PLANET_UNITS;
	    	return turno = random.nextInt(2);

	    	
	    	
	    }
	    
	   
		private void attack(ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
			// TODO Auto-generated method stub
			
			
		}
	    
		
		
		
}

