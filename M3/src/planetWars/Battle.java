package planetWars;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Battle implements Variables {
	private Planet planet;
	private ArrayList<MilitaryUnit>[] planetArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList[][] armies;
	StringBuilder battleDevelopment;
	private int[][] initialCostFleet ;
	private int initialNumberUnitsPlanet, initialNumberUnitsEnemy;
	private int totalActualNumberUnitsPlanet, totalActualNumberUnitsEnemy;
	private int[] wasteMetalDeuterium;
	int[] enemyDrops;
	int[] planetDrops;
	private int[][] resourcesLooses;
	private int[][] initialArmies;
	private int[] actualNumberUnitsPlanet;
	private int[] actualNumberUnitsEnemy;
	private int turno;
	private int Planet ;
	StringBuilder BattleReport;


     public Battle (ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
    	 	this.planet = planet;
	        this.planetArmy = planetArmy;
	        this.enemyArmy = enemyArmy;
	        this.armies = new ArrayList[2][7];
	        this.initialArmies = new int[2][7];
	        this.battleDevelopment = new StringBuilder();
	        this.BattleReport= new StringBuilder();
	        this.initialCostFleet = new int[2][2];
	        this.initialNumberUnitsPlanet = 0;
	        this.initialNumberUnitsEnemy = 0;
	        this.wasteMetalDeuterium = new int[2];
	        this.enemyDrops = new int[7];
	        this.planetDrops = new int[7];
	        this.resourcesLooses = new int[2][3];
	        this.actualNumberUnitsPlanet = new int[7];
	        this.actualNumberUnitsEnemy = new int[7];
	        Random random = new Random();
	        this.turno = random.nextInt(2);
	}


	 public String getBattleDevelopment() {
		 return battleDevelopment.toString();
	 }
	
	 //calculamos el cost fleet
	 private void calculateInitialCostFleet() {
		    // Calcular el costo inicial de la flota del planeta
		    for (int i = 0; i < 7; ++i) {
		        if (!planetArmy[i].isEmpty()) {
		            initialCostFleet[0][0] += planetArmy[i].size() * planetArmy[i].get(0).getMetalCost();
		            initialCostFleet[0][1] += planetArmy[i].size() * planetArmy[i].get(0).getDeuteriumCost();
		        }
		    }
		    for (int i = 0; i < 7; ++i) {
		        if (!enemyArmy[i].isEmpty()) {
		            initialCostFleet[1][0] += enemyArmy[i].size() * enemyArmy[i].get(0).getMetalCost();
		            initialCostFleet[1][1] += enemyArmy[i].size() * enemyArmy[i].get(0).getDeuteriumCost();
		        }
		    }
		    int[] unitMetalValue = Variables.METAL_COST_UNITS;
		    int[] unitDeuteriumValue = Variables.DEUTERIUM_COST_UNITS;
		    for (int i = 0; i < 2; ++i) {
		        for (int j = 0; j < 7; ++j) {
		            initialCostFleet[i][0] += initialArmies[i][j] * unitMetalValue[j];
		            initialCostFleet[i][1] += initialArmies[i][j] * unitDeuteriumValue[j];
		        }
		    }
		}


	 // Calculamos initialNumberUnitsPlanet y initialNumberUnitsEnemy
	 // Dentro de la clase Battle

	 // Método para calcular el número inicial de unidades para el planeta y el enemigo
	 private void calculateInitialNumberUnits() {
		    initialNumberUnitsPlanet = 0;
		    initialNumberUnitsEnemy = 0;

		    for (int i = 0; i <= 1; ++i) {
		        for (int j = 0; j <= 6; ++j) {
		            if (i == 0) {
		                initialNumberUnitsPlanet += initialArmies[i][j];
		            } else {
		                initialNumberUnitsEnemy += initialArmies[i][j];
		            }
		        }
		    }

		 
		    actualNumberUnitsPlanet = initialArmies[0];
		     actualNumberUnitsEnemy = initialArmies[1];
		    for (int j = 0; j <= 6; ++j) {
		        actualNumberUnitsPlanet[j] = initialArmies[0][j];
		        actualNumberUnitsEnemy[j] = initialArmies[1][j];
		    }
		}

	 //calculamos los residuos generados por la batalla /// preguntar a Jordi si tengo que calcular el metal o el metodo debe ser este 	public int getChanceGeneratinWaste()
	 public int[] calculateWaste(ArrayList<MilitaryUnit> planetArmy, ArrayList<MilitaryUnit> enemyArmy) {
		 int metalWaste = 0;
		 int deuteriumWaste = 1;
		 //calculo lo que me cuesta   metal y deuterio nuestro
		 for (MilitaryUnit unit : planetArmy) {

			 int probability = unit.getChanceGeneratinWaste() * Variables.PERCENTATGE_WASTE;
			 int unitMetalWaste = probability * unit.getMetalCost();
		     int unitDeuteriumWaste = unit.getDeuteriumCost()*probability;
			 metalWaste += unitMetalWaste;
			 deuteriumWaste += unitDeuteriumWaste ; 
		 }

		 for (MilitaryUnit unit : enemyArmy) {
		
			 int probability = unit.getChanceGeneratinWaste() * Variables.PERCENTATGE_WASTE;
			 int unitMetalWaste = probability * unit.getMetalCost();
		     int unitDeuteriumWaste = probability * unit.getDeuteriumCost()*probability ;
			 metalWaste += unitMetalWaste;
			 deuteriumWaste += unitDeuteriumWaste ;
			 
		 }
	       
		 metalWaste -= initialCostFleet[0][0] + initialCostFleet[1][0];
		 deuteriumWaste -= initialCostFleet[0][1] + initialCostFleet[1][1];
	     if(deuteriumWaste <0) {
	    	deuteriumWaste =0;
	     }
		 wasteMetalDeuterium[0] = metalWaste;
		 wasteMetalDeuterium[1] = deuteriumWaste;
	        
	    return wasteMetalDeuterium ;
	 }
	    

	 private void updateResourceLosses(MilitaryUnit unit) {
		    resourcesLooses[turno][0] += unit.getMetalCost();
		    resourcesLooses[turno][1] += unit.getDeuteriumCost();
		    resourcesLooses[turno][2] += unit.getMetalCost() + 5 * unit.getDeuteriumCost();
		}

	public int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
        if (army == null || army.length == 0) {
            return -1; 
        }
        
        int totalUnits = 0;
        for (ArrayList<MilitaryUnit> group : army) {
            totalUnits += group.size();
        }
        Random random = new Random();
		int randomNumber = random.nextInt(101);
        int[] probabilities = new int[army.length];
        for (int i = 0; i < army.length; i++) {
            probabilities[i] = (int) Math.round((double) (army[i].size() * 100) / totalUnits);
        }

        int cumulativeProbability = 0;
        for (int i = 0; i < army.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomNumber <= cumulativeProbability) {
                return i; 
            }
        }
        return -1;
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
		return chanceAttackUnits.length - 1; 
	}

	
	private void updateActualNumberUnits() {
	    // Reiniciar las cantidades de unidades
	    Arrays.fill(actualNumberUnitsPlanet, 0);
	    Arrays.fill(actualNumberUnitsEnemy, 0);

	    // Contar las unidades en el ejército del planeta
	    for (ArrayList<MilitaryUnit> units : planetArmy) {
	        for (MilitaryUnit unit : units) {
	            int index = getUnitTypeIndex(unit);
	            if (index != -1) actualNumberUnitsPlanet[index]++;
	        }
	    }
 
	    for (ArrayList<MilitaryUnit> units : enemyArmy) {
	        for (MilitaryUnit unit : units) {
	            int index = getUnitTypeIndex(unit);
	            if (index != -1) actualNumberUnitsEnemy[index]++;
	        }
	    }

	    totalActualNumberUnitsPlanet = Arrays.stream(actualNumberUnitsPlanet).sum();
	    totalActualNumberUnitsEnemy = Arrays.stream(actualNumberUnitsEnemy).sum();
	}
	public void printBattleStadistics() {

	    System.out.println("BATTLE STADISTICS");
	    System.out.println(String.format("%-18s %-10s %-10s %-20s %-10s %-10s",
	            "Army Planet", "Units", "Drops", "Initial Army Enemy", "Units", "Drops"));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10d %-10d",
	            "Light Hunter", initialArmies[0][0], planetDrops[0], "Light Hunter", initialArmies[1][0], enemyDrops[0]));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10d %-10d",
	            "Heavy Hunter", initialArmies[0][1], planetDrops[1], "Heavy Hunter", initialArmies[1][1], enemyDrops[1]));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10d %-10d",
	            "Battle Ship", initialArmies[0][2], planetDrops[2], "Battle Ship", initialArmies[1][2], enemyDrops[2]));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10d %-10d",
	            "Armored Ship", initialArmies[0][3], planetDrops[3], "Armored Ship", initialArmies[1][3], enemyDrops[3]));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10s %-10s",
	            "Missile Launcher", initialArmies[0][4], planetDrops[4], "", "-", "-"));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10s %-10s",
	            "Ion Cannon", initialArmies[0][5], planetDrops[5], "", "-", "-"));
	    System.out.println(String.format("%-18s %-10d %-10d %-20s %-10s %-10s",
	            "Plasma Cannon", initialArmies[0][6], planetDrops[6], "", "-", "-"));
	    System.out.println("\n");
	    System.out.println("**********************************************************************************");
	    System.out.println(String.format("%-40s %-40s", "Cost Army Planet", "Cost Army Enemy"));
	    System.out.println(String.format("%-20s %-20s %-20s %-20s", "Metal:", initialCostFleet[0][0], "Metal:", initialCostFleet[1][0]));
	    System.out.println(String.format("%-20s %-20s %-20s %-20s", "Deuterium:", initialCostFleet[0][1], "Deuterium:", initialCostFleet[1][1]));
	    System.out.println("\n");
	    System.out.println("**********************************************************************************");
	    System.out.println(String.format("%-40s %-40s", "Losses Army Planet", "Losses Army Enemy"));
	    System.out.println(String.format("%-20s %-20s %-20s %-20s", "Metal:", resourcesLooses[0][0] , "Metal:", resourcesLooses[1][0] ));
	    System.out.println(String.format("%-20s %-20s %-20s %-20s", "Deuterium:", resourcesLooses[0][1] , "Deuterium:",resourcesLooses[1][1] ));
	    System.out.println("\n");
	    System.out.println("**********************************************************************************"); 
	    System.out.println("Waste Generated:");
	    System.out.println(String.format("Metal: %d", wasteMetalDeuterium[0]));
	    System.out.println(String.format("Deuterium: %d", wasteMetalDeuterium[1]));
	    System.out.println("\n");
	    System.out.println("##################################################################################"); 
	    System.out.println("\n");
	}

	private int getUnitTypeIndex(MilitaryUnit unit) {
	    if (unit instanceof LigthHunter) return 0;
	    if (unit instanceof HeavyHunter) return 1;
	    if (unit instanceof BattleShip) return 2;
	    if (unit instanceof ArmoredShip) return 3;
	    if (unit instanceof MissileLauncher) return 4;
	    if (unit instanceof IonCannon) return 5;
	    if (unit instanceof PlasmaCannon) return 6;
	    return -1; }
	
	private void attack(int atacker, int defense, int attackerChance,ArrayList<MilitaryUnit>[] attackingArmy, ArrayList<MilitaryUnit>[] defenseArmy) {
		
		String attackerType = getTypeByIndex(atacker);
		String defenderType = getTypeByIndex(defense);
		battleDevelopment.append( attackerType + " attacks " + defenderType + "\n\n");	
		int attackPower =0;
		boolean attackOccurred = false;
		
		for (ArrayList<MilitaryUnit> units : attackingArmy) {
			for (MilitaryUnit unit : units) {
				if ((unit instanceof LigthHunter && atacker == 0) ||
						(unit instanceof HeavyHunter && atacker == 1) ||
						(unit instanceof BattleShip && atacker == 2) ||
						(unit instanceof ArmoredShip && atacker == 3) ||
						(unit instanceof MissileLauncher && atacker == 4) ||
						(unit instanceof IonCannon && atacker == 5) ||
						(unit instanceof PlasmaCannon && atacker == 6)) {

						attackPower += unit.attack();
						unit.takeDamage(attackPower);
						battleDevelopment.append(attackerType + " generates damage = " +  attackPower +"\n");
						attackOccurred = true;
			            break; 
				}
			}
			if (attackOccurred) break;
		}
		if (attackOccurred) {
			for (ArrayList<MilitaryUnit> units : defenseArmy) {
		        Iterator<MilitaryUnit> iterator = units.iterator();
		        while (iterator.hasNext()) {
		            MilitaryUnit unit = iterator.next();
		            if ((unit instanceof LigthHunter && defense == 0) ||
		                (unit instanceof HeavyHunter && defense == 1) ||
		                (unit instanceof BattleShip && defense == 2) ||
		                (unit instanceof ArmoredShip && defense == 3) ||
		                (unit instanceof MissileLauncher && defense == 4) ||
		                (unit instanceof IonCannon && defense == 5) ||
		                (unit instanceof PlasmaCannon && defense == 6)) {
		                
		                unit.getActualArmor();
		                int actualArmor = unit.attack() - attackPower;
		                battleDevelopment.append(defenderType + " stays with armor = " + actualArmor + "\n\n");
		             
		               
		                if (actualArmor <= 0) {
		                    battleDevelopment.append("We eliminate : " + unit.getClass().getSimpleName() + "\n\n");
		                   /*  // Eliminamos la unidad de forma segura usando el iterador
		                    */
		                    iterator.remove();
		                    int index = getUnitTypeIndex(unit);
		                    
		                    if (turno == 0) {
		                    	
		                    	updateResourceLosses(unit);
			                    calculateWaste(new ArrayList<>(Arrays.asList(unit)), new ArrayList<>());
			                    planetDrops[index]++;
		                    }else {
		                    	 updateResourceLosses(unit);
				                 calculateWaste(new ArrayList<>(Arrays.asList(unit)), new ArrayList<>());
				                 enemyDrops[index]++;
		                    }
		                   
		                }
		                break;
		            }
		            if (attackOccurred) break;
	            }
	        }
	    }
	}
	public void startBattle() {
		 
	    initialitzeArmies();
	    setArmies();
	    calculateInitialCostFleet();
	    calculateInitialNumberUnits();
	    updateActualNumberUnits();
	    

	 
	    while (totalActualNumberUnitsPlanet >= (initialNumberUnitsEnemy * 0.2) && totalActualNumberUnitsEnemy >= (initialNumberUnitsPlanet * 0.2)) {
	    	battleDevelopment.append("******************** CHANGE ATTACKER ********************\n");
	    	if (turno == 0) {
	    		
	            battleDevelopment.append("Attacks fleet Planet: ");
	            attack(getPlanetGroupAttacker(), getGroupDefender(enemyArmy), Variables.CHANCE_ATTACK_PLANET_UNITS[getPlanetGroupAttacker()], planetArmy, enemyArmy);
	        } else {
	        	
	            battleDevelopment.append("Attacks fleet Enemy: ");
	            attack(getEnemyGroupAttacker(), getGroupDefender(planetArmy), Variables.CHANCE_ATTACK_ENEMY_UNITS[getEnemyGroupAttacker()], enemyArmy, planetArmy);
	        }


	        // Alternar el turno
	        turno = turno == 0 ? 1 : 0;
	       
	     
	        
	        updateActualNumberUnits();
	       
	      
	     }printBattleStadistics();
	} 
	
	
	 
	 private String getTypeByIndex(int index) {
		 switch (index) {
			case 0: return "LigthHunter";
			case 1: return "HeavyHunter";
			case 2: return "BattleShip";
			case 3: return "ArmoredShip";
			case 4: return "MissileLauncher";
			case 5: return "IonCannon";
			case 6: return "PlasmaCannon";
			default: return "Unknown";
		}
	 }

	 private void initialitzeArmies() {
		 for (ArrayList<MilitaryUnit> unitType : planetArmy) {
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
			 for (ArrayList<MilitaryUnit> unitType : enemyArmy) {
				 if (unitType != null) {
					 for (MilitaryUnit unit : unitType) {
						 if (unit instanceof LigthHunter) initialArmies[1][0] +=1 ;
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
	 
	 private void setArmies() {
		 this.armies[0] = this.planetArmy;
		 this.armies[1] = this.enemyArmy;
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






