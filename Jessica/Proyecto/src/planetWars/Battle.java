package planetWars;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Battle  {
	private ArrayList<MilitaryUnit>[] planetArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList[][] armies;
	StringBuilder battleDevelopment;
	private int[][] initialCostFleet ;
	private int initialNumberUnitsPlanet, initialNumberUnitsEnemy;
	private int[] wasteMetalDeuterium;
	private int[] enemyDrops;
	private int[] planetDrops;
	private int[][] resourcesLooses;
	private int[][] initialArmies;
	private int[] actualNumberUnitsPlanet;
	private int[] actualNumberUnitsEnemy;
	private int turno;
	
	

	    // Constructor
	    public Battle(ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
	        this.planetArmy = planetArmy;
	        this.enemyArmy = enemyArmy;
	        this.armies = new ArrayList[2][7];
	        this.initialArmies = new int[2][7];
	        this.battleDevelopment = new StringBuilder();
	        this.initialCostFleet = new int[2][2];
	        this.initialNumberUnitsPlanet = 0;
	        this.initialNumberUnitsEnemy = 0;
	        this.wasteMetalDeuterium = new int[2];
	        this.enemyDrops = new int[7];
	        this.planetDrops = new int[7];
	        this.resourcesLooses = new int[2][3];
	        this.actualNumberUnitsPlanet = new int[7];
	        this.actualNumberUnitsEnemy = new int[7];
	        this.turno =0;
	        initInitialArmies();
	        
	         
	    }
	    public String getBattleDevelopment() {
	        return battleDevelopment.toString();
	    }

	    private void initInitialArmies() {
	        // Inicializamos el array initialArmies y los números iniciales de unidades
	        initialArmies = new int[2][7];

	        // Construimos la matriz para nuestro ejército (si no es null)
	        if (planetArmy != null) {
	            for (ArrayList<MilitaryUnit> unitType : planetArmy) {
	                if (unitType != null) {
	                    for (MilitaryUnit unit : unitType) {
	                        if (unit instanceof LigthHunter) initialArmies[0][0] += 1;
	                        if (unit instanceof HeavyHunter) initialArmies[0][1] += 1;
	                        if (unit instanceof BattleShip) initialArmies[0][2] += 1;
	                        if (unit instanceof ArmoredShip) initialArmies[0][3] += 1;
	                        if (unit instanceof MissileLauncher) initialArmies[0][4] += 1;
	                        if (unit instanceof IonCannon) initialArmies[0][5] += 1;
	                        if (unit instanceof PlasmaCannon) initialArmies[0][6] += 1;
	                    }
	                }
	            }
	        }

	        // Construimos la matriz para nuestro enemigo (si no es null)
	        if (enemyArmy != null) {
	            for (ArrayList<MilitaryUnit> unitType : enemyArmy) {
	                if (unitType != null) {
	                    for (MilitaryUnit unit : unitType) {
	                        if (unit instanceof LigthHunter) initialArmies[1][0] += 1;
	                        if (unit instanceof HeavyHunter) initialArmies[1][1] += 1;
	                        if (unit instanceof BattleShip) initialArmies[1][2] += 1;
	                        if (unit instanceof ArmoredShip) initialArmies[1][3] += 1;
	                        if (unit instanceof MissileLauncher) initialArmies[1][4] += 1;
	                        if (unit instanceof IonCannon) initialArmies[1][5] += 1;
	                        if (unit instanceof PlasmaCannon) initialArmies[1][6] += 1;
	                    }
	                }
	            }
	        }

	        // Actualizamos el número inicial de unidades
	        for (int j = 0; j < 7; j++) {
	            actualNumberUnitsPlanet[j] = initialArmies[0][j];
	            actualNumberUnitsEnemy[j] = initialArmies[1][j];
	        }
	        initialNumberUnitsPlanet = calculateTotalUnits(planetArmy);
	        initialNumberUnitsEnemy = calculateTotalUnits(enemyArmy);
	    }

	    private int calculateTotalUnits(ArrayList<MilitaryUnit>[] armies) {
	        int total = 0;
	        if (armies != null) {
	            for (ArrayList<MilitaryUnit> army : armies) {
	                if (army != null) {
	                    total += army.size();
	                }
	            }
	        }
	        return total;
	    }
		private int[][] fleetResourceCost(ArrayList<MilitaryUnit>[] army) {
		    int[][] initialCostFleet = new int[2][2]; // Crear un array para almacenar los costos totales de metal y deuterio para tu planeta (índice 0) y para el enemigo (índice 1)

		    // Cálculo de los costos de la flota para tu planeta (índice 0) y para el enemigo (índice 1)
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
		    
		    // Rellenamos los costos iniciales de la flota para tu planeta (índice 0) y para el enemigo (índice 1)
		    for (int i = 0; i <= 1; ++i) {
		        for (int j = 0; j <= 6; ++j) {
		            initialCostFleet[i][0] += (initialArmies[i][j] * Variables.METAL_COST_UNITS[j]);
		            initialCostFleet[i][1] += (initialArmies[i][j] * Variables.DEUTERIUM_COST_UNITS[j]);
		        }
		    }

		  
		    battleDevelopment.append("planeta metal: ").append(initialCostFleet[0][0]).append(" deuterion ").append(initialCostFleet[0][1]);
		    battleDevelopment.append(" enemigo metal: ").append(initialCostFleet[1][0]).append(" deuterion ").append(initialCostFleet[1][1]);

		    return initialCostFleet; 
		}



	    
	    
	    
	    public int[] initialFleetNumber(ArrayList<MilitaryUnit>[] army) {// cuento mi ejercito
	        int[] initialUnits = new int[army.length];

	        for (int i = 0; i < army.length; i++) {
	            initialUnits[i] = army[i].size();
	        }

	        return initialUnits;
	    }
	    
	    public void switchTurno() {
	        turno = (turno == 0) ? 1 : 0;
	    }
	    
	    private int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
	        if (army == null || army.length == 0) {
	            return -1;
	        }

	        int totalGroups = army.length;
	        int totalUnits = 0;
	        int[] chances;

	        if (army == planetArmy) {
	            totalGroups = Variables.CHANCE_ATTACK_PLANET_UNITS.length;
	            chances = Variables.CHANCE_ATTACK_PLANET_UNITS;
	        } else {
	            totalGroups = Variables.CHANCE_ATTACK_ENEMY_UNITS.length;
	            chances = Variables.CHANCE_ATTACK_ENEMY_UNITS;
	        }

	        for (int i = 0; i < totalGroups; i++) {
	            totalUnits += chances[i];
	        }

	        // Selecciona un número aleatorio entre 0 y el número total de unidades
	        int defendingGroupIndex = (int) (Math.random() * totalUnits);

	        int probability = 0;
	        for (int i = 0; i < totalGroups; i++) {
	            probability += chances[i];
	            if (defendingGroupIndex < probability) {
	                return i; // Devuelve el índice del grupo seleccionado
	            }
	        }

	        return -5; // Si no se encuentra un grupo defensor válido, devuelve -1
	    }


	   
	    private int getPlanetGroupAttacker() {
	        return getGroupAttacker(Variables.CHANCE_ATTACK_PLANET_UNITS);
	    }

	    private int getEnemyGroupAttacker() {
	        return getGroupAttacker(Variables.CHANCE_ATTACK_ENEMY_UNITS);
	    }

	    private int getGroupAttacker(int[] chanceAttackUnits) {
	        int totalUnits = 0;
	        for (int chance : chanceAttackUnits) {
	            totalUnits += chance;
	        }

	        int attackingGroup = (int) (Math.random() * 100);
	        int probability = 0;

	        for (int i = 0; i < chanceAttackUnits.length; i++) {
	            int groupProbability = (chanceAttackUnits[i] * 100) / totalUnits;
	            probability += groupProbability;

	            if (attackingGroup < probability) {
	                return i;
	            }
	        }

	        return -1; // Si no se encuentra un grupo atacante válido, devuelve -1
	    }

	    
	    public void startBattle() {
	    	
	        battleDevelopment.append("******************** CHANGE ATTACKER ********************\n");

	        ArrayList<MilitaryUnit>[] attackingArmy;
	        int[] chanceAttackUnits;
	        int attackerGroupIndex = 0, defenderGroupIndex;

	        if (turno == 0) {
	            attackingArmy = planetArmy;
	            ArrayList<MilitaryUnit> attackingUnits = planetArmy[attackerGroupIndex];
	            chanceAttackUnits = Variables.CHANCE_ATTACK_PLANET_UNITS;
	            attackerGroupIndex = getPlanetGroupAttacker();
	            defenderGroupIndex = getGroupDefender(enemyArmy);
	            battleDevelopment.append("Attacks fleet  Planet: "+"\n"  );
	            System.out.println(defenderGroupIndex);
	            System.out.println(attackerGroupIndex );
	        } else {
	            attackingArmy = enemyArmy;
	            chanceAttackUnits = Variables.CHANCE_ATTACK_ENEMY_UNITS;
	            attackerGroupIndex = getEnemyGroupAttacker();
	            defenderGroupIndex = getGroupDefender(planetArmy);
	            battleDevelopment.append("Attacks fleet  enemy: ");
	            System.out.println(defenderGroupIndex);
	            System.out.println(attackerGroupIndex );
	        }

	    }


	                

	                // Generate waste if applicable
	               /* int wasteChance = defender.getChanceGeneratinWaste();
	                int chance_waste = (int) (Math.random() * 100);
	                if (wasteChance > 0 && chance_waste < wasteChance) {
	                    // Calculate wasted resources
	                    int metalCost = defender.getMetalCost();
	                    int deuteriumCost = defender.getDeuteriumCost();
	                    int totalCost = metalCost + deuteriumCost;
	                    double wastePercentage = Variables.PERCENTATGE_WASTE / 100.0;
	                    int wastedMetal = (int) (metalCost * wastePercentage);
	                    int wastedDeuterium = (int) (deuteriumCost * wastePercentage);


	                
	                if (turno == 0) {
	                    enemyArmy[defenderGroupIndex].remove(defender);
	                } else {
	                    planetArmy[defenderGroupIndex].remove(defender);
	                }
	            }
	          }
	       }
	    }*/
    
 
	    
	    public void updateResourcesLooses() {
	        for (int i = 0; i < 2; i++) {
	            resourcesLooses[i][2] = resourcesLooses[i][0] + 5 * resourcesLooses[i][1];
	        }
	    }
	    public String getBattleReport(int battles) {
	    StringBuilder report = new StringBuilder();
	    report.append("Battle Report:\n");
	    report.append("Total battles simulated: ").append(battles).append("\n\n");

	   
	    for (int i = 0; i < battles; i++) {
	        report.append("Battle ").append(i + 1).append(":\n");
	        report.append(battleDevelopment.toString()); // Agregar detalles de desarrollo de la batalla
	        report.append("\n\n");
	    }

	    return report.toString();
	}
		public ArrayList<MilitaryUnit>[] getPlanetArmy() {
			return planetArmy;
		}
		public ArrayList<MilitaryUnit>[] getEnemyArmy() {
			return enemyArmy;
		}
		public ArrayList[][] getArmies() {
			return armies;
		}
		public int[][] getInitialCostFleet() {
			return initialCostFleet;
		}
		public int getInitialNumberUnitsPlanet() {
			return initialNumberUnitsPlanet;
		}
		public int getInitialNumberUnitsEnemy() {
			return initialNumberUnitsEnemy;
		}
		public int[] getWasteMetalDeuterium() {
			return wasteMetalDeuterium;
		}
		public int[] getEnemyDrops() {
			return enemyDrops;
		}
		public int[] getPlanetDrops() {
			return planetDrops;
		}
		public int[][] getResourcesLooses() {
			return resourcesLooses;
		}
		public int[][] getInitialArmies() {
			return initialArmies;
		}
		public int[] getActualNumberUnitsPlanet() {
			return actualNumberUnitsPlanet;
		}
		public int[] getActualNumberUnitsEnemy() {
			return actualNumberUnitsEnemy;
		}
		public int getTurno() {
			return turno;
		}
		public void setPlanetArmy(ArrayList<MilitaryUnit>[] planetArmy) {
			this.planetArmy = planetArmy;
		}
		public void setEnemyArmy(ArrayList<MilitaryUnit>[] enemyArmy) {
			this.enemyArmy = enemyArmy;
		}
		public void setArmies(ArrayList[][] armies) {
			this.armies = armies;
		}
		public void setBattleDevelopment(StringBuilder battleDevelopment) {
			this.battleDevelopment = battleDevelopment;
		}
		public void setInitialCostFleet(int[][] initialCostFleet) {
			this.initialCostFleet = initialCostFleet;
		}
		public void setInitialNumberUnitsPlanet(int initialNumberUnitsPlanet) {
			this.initialNumberUnitsPlanet = initialNumberUnitsPlanet;
		}
		public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
			this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
		}
		public void setWasteMetalDeuterium(int[] wasteMetalDeuterium) {
			this.wasteMetalDeuterium = wasteMetalDeuterium;
		}
		public void setEnemyDrops(int[] enemyDrops) {
			this.enemyDrops = enemyDrops;
		}
		public void setPlanetDrops(int[] planetDrops) {
			this.planetDrops = planetDrops;
		}
		public void setResourcesLooses(int[][] resourcesLooses) {
			this.resourcesLooses = resourcesLooses;
		}
		public void setInitialArmies(int[][] initialArmies) {
			this.initialArmies = initialArmies;
		}
		public void setActualNumberUnitsPlanet(int[] actualNumberUnitsPlanet) {
			this.actualNumberUnitsPlanet = actualNumberUnitsPlanet;
		}
		public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
			this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
		}
		public void setTurno(int turno) {
			this.turno = turno;
		}
	    
}


