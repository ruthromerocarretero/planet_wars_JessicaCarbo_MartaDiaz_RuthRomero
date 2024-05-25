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
	private int[] enemyDrops;
	private int[] planetDrops;
	private int[][] resourcesLooses;
	private int[][] initialArmies;
	private int[] actualNumberUnitsPlanet;
	private int[] actualNumberUnitsEnemy;
	private int turno;
	private int Planet ;
	private String name;


     public Battle (ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
    	 	this.planet = planet;
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
	        Random random = new Random();
	        this.turno = random.nextInt(2);
	
	        
	        //inicializo array armies


	}

	 //Método para ir agregando el desarrollo de la batalla
	 public String getBattleDevelopment() {
		 return battleDevelopment.toString();
	 }
	    
	 //calculamos el cost fleet
	 private void calculateInitialCostFleet() {
		    for (int i = 0; i <= 1; ++i) {
		        for (int j = 0; j <= 6; ++j) {
		            if (i == 0 && !planetArmy[j].isEmpty()) {
		                initialCostFleet[i][0] += planetArmy[j].size() * planetArmy[j].get(0).getMetalCost();
		                initialCostFleet[i][1] += planetArmy[j].size() * planetArmy[j].get(0).getDeuteriumCost();
		            } else if (i == 1 && !enemyArmy[j].isEmpty()) {
		                initialCostFleet[i][0] += enemyArmy[j].size() * enemyArmy[j].get(0).getMetalCost();
		                initialCostFleet[i][1] += enemyArmy[j].size() * enemyArmy[j].get(0).getDeuteriumCost();
		            }
		        }
		    }

		    int[] unitMetalValue = Variables.METAL_COST_UNITS;
		    int[] unitDeuteriumValue = Variables.DEUTERIUM_COST_UNITS;

		    for (int i = 0; i <= 1; ++i) {
		        for (int j = 0; j <= 6; ++j) {
		            if (i >= 0 && i < initialArmies.length && j >= 0 && j < initialArmies[i].length) {
		                initialCostFleet[i][0] += (initialArmies[i][j] * unitMetalValue[j]);
		                initialCostFleet[i][1] += (initialArmies[i][j] * unitDeuteriumValue[j]);
		            }
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

		    // Crear copias de los valores iniciales para evitar problemas de referencia
		    actualNumberUnitsPlanet = new int[initialArmies[0].length];
		    actualNumberUnitsEnemy = new int[initialArmies[1].length];

		    for (int j = 0; j <= 6; ++j) {
		        actualNumberUnitsPlanet[j] = initialArmies[0][j];
		        actualNumberUnitsEnemy[j] = initialArmies[1][j];
		    }
		}

	 //calculamos los residuos generados por la batalla /// preguntar a Jordi si tengo que calcular el metal o el metodo debe ser este 	public int getChanceGeneratinWaste()
	 public int[] calculateWaste(ArrayList<MilitaryUnit> planetArmy, ArrayList<MilitaryUnit> enemyArmy) {
		 int metalWaste = 0;
		 int deuteriumWaste = 0;
		 int probability= 0;
		 //calculo lo que me cuesta   metal y deuterio nuestro
		 for (MilitaryUnit unit : planetArmy) {
			 probability += unit.getChanceGeneratinWaste() * Variables.PERCENTATGE_WASTE;
			 metalWaste += probability*unit.getMetalCost();
			 deuteriumWaste += probability* unit.getDeuteriumCost();
				 
			 
		 }

		 //calculo lo que me cuesta   metal y deuterio enemigo
		 for (MilitaryUnit unit : enemyArmy) {
		
			 probability += unit.getChanceGeneratinWaste() * Variables.PERCENTATGE_WASTE;
			 metalWaste += probability*unit.getMetalCost();
			 deuteriumWaste += probability* unit.getDeuteriumCost();
			 
		 }
	       
		 metalWaste -= initialCostFleet[0][0] + initialCostFleet[1][0];
		 deuteriumWaste -= initialCostFleet[0][1] + initialCostFleet[1][1];
	        
		 this.actualNumberUnitsPlanet = initialArmies[0];
		 this.actualNumberUnitsEnemy = initialArmies[1];
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

	public String destroyShip(MilitaryUnit unit, int attackingArmy) {
		 // Actualizar las pérdidas de recursos
		 resourcesLooses[turno][0] += unit.getMetalCost();
		 resourcesLooses[turno][1] += unit.getDeuteriumCost();
		 resourcesLooses[turno][2] += unit.getMetalCost() + 5 * unit.getDeuteriumCost();

	  
		 if (turno == 0) {
			 enemyArmy[attackingArmy].remove(unit);
		 	 actualNumberUnitsEnemy[attackingArmy] -= 1;
		 	 return "We eliminate " + unit.getClass().getSimpleName();
		 } else {
			 planetArmy[attackingArmy].remove(unit);
			 actualNumberUnitsPlanet[attackingArmy] -= 1;
			 return "We eliminate " + unit.getClass().getSimpleName();
		 } 
	 }
	
	
    public int probabilityAtackAgain(MilitaryUnit unit) {
    	    if (unit instanceof LigthHunter) return Variables.CHANCE_ATTACK_AGAIN_LIGTHHUNTER;
    	    if (unit instanceof HeavyHunter) return Variables.CHANCE_ATTACK_AGAIN_HEAVYHUNTER;
    	    if (unit instanceof BattleShip) return Variables.CHANCE_ATTACK_AGAIN_BATTLESHIP;
    	    if (unit instanceof ArmoredShip) return Variables.CHANCE_ATTACK_AGAIN_ARMOREDSHIP;
    	    if (unit instanceof MissileLauncher) return Variables.CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
    	    if (unit instanceof IonCannon) return Variables.CHANCE_ATTACK_AGAIN_IONCANNON;
    	    if (unit instanceof PlasmaCannon) return Variables.CHANCE_ATTACK_AGAIN_PLASMACANNON;
    	    return -1; // Si el tipo de unidad no coincide
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

		return chanceAttackUnits.length - 1; // Si no se encuentra un grupo atacante válido, devuelve -1
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

	    // Contar las unidades en el ejército enemigo
	    for (ArrayList<MilitaryUnit> units : enemyArmy) {
	        for (MilitaryUnit unit : units) {
	            int index = getUnitTypeIndex(unit);
	            if (index != -1) actualNumberUnitsEnemy[index]++;
	        }
	    }

	    // Sumar todas las unidades actuales
	    totalActualNumberUnitsPlanet = Arrays.stream(actualNumberUnitsPlanet).sum();
	    totalActualNumberUnitsEnemy = Arrays.stream(actualNumberUnitsEnemy).sum();
	}

	private int getUnitTypeIndex(MilitaryUnit unit) {
	    if (unit instanceof LigthHunter) return 0;
	    if (unit instanceof HeavyHunter) return 1;
	    if (unit instanceof BattleShip) return 2;
	    if (unit instanceof ArmoredShip) return 3;
	    if (unit instanceof MissileLauncher) return 4;
	    if (unit instanceof IonCannon) return 5;
	    if (unit instanceof PlasmaCannon) return 6;
	    return -1; // Si el tipo de unidad no coincide
	}
	
	private void attack(int atacker, int defense, int attackerChance,ArrayList<MilitaryUnit>[] attackingArmy, ArrayList<MilitaryUnit>[] defenseArmy) {
		
		String attackerType = getTypeByIndex(atacker);
		String defenderType = getTypeByIndex(defense);
		battleDevelopment.append( attackerType + " attacks " + defenderType + "\n");	
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
						battleDevelopment.append(attackerType + " generates damage = " +  attackPower +"--------------\n");
						attackOccurred = true;  // Indica que el ataque ha ocurrido
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
		                battleDevelopment.append(defenderType + " stays with armor = " + actualArmor + "\n");
		               
		                
		               
		                if (actualArmor <= 0) {
		                    battleDevelopment.append("We eliminate : " + unit.getClass().getSimpleName() + "\n");
		                    iterator.remove(); // Eliminamos la unidad de forma segura usando el iterador
		                    calculateWaste(new ArrayList<>(Arrays.asList(unit)), new ArrayList<>());
		                }
		                break;
		            }
		            if (attackOccurred) break;
	            }
	        }
	    }
	}
	public void startBattle() {
	    initialArmies();
	    setArmies();
	    calculateInitialCostFleet();
	    calculateInitialNumberUnits();
	    updateActualNumberUnits();
	    int counter= 0;
	    // Bucle de la batalla
	    while (totalActualNumberUnitsPlanet > 0 && totalActualNumberUnitsEnemy > 0) {
	    	counter =+1; 
	    	if (turno == 0) {
	    		battleDevelopment.append("******************** CHANGE ATTACKER ********************\n");
	            battleDevelopment.append("Attacks fleet Planet: ");
	            attack(getPlanetGroupAttacker(), getGroupDefender(enemyArmy), Variables.CHANCE_ATTACK_PLANET_UNITS[getPlanetGroupAttacker()], planetArmy, enemyArmy);
	        } else {
	        	battleDevelopment.append("******************** CHANGE ATTACKER ********************\n");
	            battleDevelopment.append("Attacks fleet Enemy: ");
	            attack(getEnemyGroupAttacker(), getGroupDefender(planetArmy), Variables.CHANCE_ATTACK_ENEMY_UNITS[getEnemyGroupAttacker()], enemyArmy, planetArmy);
	        }


	        // Alternar el turno
	        turno = turno == 0 ? 1 : 0;

	        // Actualizar las cantidades actuales de unidades después de cada ataque
	        updateActualNumberUnits();
	        // Calcular y verificar si alguna de las condiciones de fin de batalla se cumple
	        if (totalActualNumberUnitsPlanet < initialNumberUnitsEnemy * 0.2 || totalActualNumberUnitsEnemy < initialNumberUnitsPlanet * 0.2) {
	            // Realizar cálculos adicionales o finalizar la batalla

	            if (totalActualNumberUnitsPlanet > totalActualNumberUnitsEnemy) {
	                battleDevelopment.append("Winner Planet\n");	               
	            }
	                
	            } else if (totalActualNumberUnitsPlanet < totalActualNumberUnitsEnemy) {
	                battleDevelopment.append("Winner Enemy\n");
	    
	            break; // Salir del bucle si se cumple alguna condición
	        }
	     	}
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

	 private void initialArmies() {
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

		public String getBattleReport(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		
		
		
}






