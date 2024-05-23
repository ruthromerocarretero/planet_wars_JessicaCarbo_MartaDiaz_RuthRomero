package planetWars;



import java.util.ArrayList;
import java.util.Random;

public class Battle implements Variables {
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
	


     public Battle (ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
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
        
    

    // Construimos la matriz para nuestro enemigo (si no es null)
    
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
	    //metodos para calcular las variable y arrays
	    
	    //Método para ir agregando el desarrollo de la batalla
     public String getBattleDevelopment() {
	        return battleDevelopment.toString();
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
	        
	        //rellenamos initial cost flett
	        
	        int[] unitMetalValue = Variables.METAL_COST_UNITS;
	        
	        
	        int[] unitDeuteriumValue = Variables.DEUTERIUM_COST_UNITS;
	        			        
	        
	        for (int i=0; i<= 1; ++i) {
	        	for (int j =0; j<=6; ++j ) {
	        		initialCostFleet[i][0] += (initialArmies[i][j] * unitMetalValue[j]);
	        		initialCostFleet[i][1] += (initialArmies[i][j] * unitDeuteriumValue[j]);
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
	        
	        // Calculamos initialNumberUnitsPlanet y initialNumberUnitsEnemy
	        for (int i=0; i<= 1; ++i) {
	        	for (int j =0; j<=6; ++j ) {
	        		if (i == 0) initialNumberUnitsPlanet += initialArmies[i][j];
	        		if (i == 1) initialNumberUnitsEnemy += initialArmies[i][j];
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
	        
	        this.actualNumberUnitsPlanet = initialArmies[0];
	        this.actualNumberUnitsEnemy = initialArmies[1];
	        wasteMetalDeuterium[0] = metalWaste;
	        wasteMetalDeuterium[1] = deuteriumWaste;
	        
	    }return wasteMetalDeuterium ;   
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

	    public String destroyShip(MilitaryUnit unit, int type) {
	        // Actualizar las pérdidas de recursos
	        resourcesLooses[turno][0] += unit.getMetalCost();
	        resourcesLooses[turno][1] += unit.getDeuteriumCost();
	        resourcesLooses[turno][2] += unit.getMetalCost() + 5 * unit.getDeuteriumCost();

	  
	        if (turno == 0) {
	            enemyArmy[type].remove(unit);
	            actualNumberUnitsEnemy[type] -= 1;
	        } else {
	            planetArmy[type].remove(unit);
	            actualNumberUnitsPlanet[type] -= 1;
	        }

	        return unit.getClass().getSimpleName() + " Destroyed";
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
	    private int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
	        if (army == null || army.length == 0) {
	            return -1;
	        }

	        int totalUnits = 0;
	        int[] chances;

	        if (army == planetArmy) {
	            chances = Variables.CHANCE_ATTACK_PLANET_UNITS;
	        } else {
	            chances = Variables.CHANCE_ATTACK_ENEMY_UNITS;
	        }

	        for (int chance : chances) {
	            totalUnits += chance;
	        }
	        if (totalUnits == 0) {
	            return -1;
	        }
	        int defendingGroupIndex = (int) (Math.random() * totalUnits);

	        int probability = 0;
	        for (int i = 0; i < chances.length; i++) {
	            probability += chances[i];
	            if (defendingGroupIndex < probability) {
	                return Math.min(i, army.length - 1); 
	            }
	        }

	        return Math.min(chances.length - 1, army.length - 1); 
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
	    public void startBattle() {
	        battleDevelopment.append("******************** CHANGE ATTACKER ********************\n");
	        if (turno == 0) {
	            int atacker = getPlanetGroupAttacker();
	            int defense = getGroupDefender(enemyArmy);
	            int attackerChance = Variables.CHANCE_ATTACK_PLANET_UNITS[atacker];
	            String attackerType = getTypeByIndex(atacker);
	            String defenderType = getTypeByIndex(defense);
	            battleDevelopment.append("Attacks fleet Planet: " + attackerType + " attacks " + defenderType);
	            
	            for (ArrayList<MilitaryUnit> units : planetArmy) {
	                for (MilitaryUnit unit : units) {
	                    if ((unit instanceof LigthHunter && atacker == 0) ||
	                        (unit instanceof HeavyHunter && atacker == 1) ||
	                        (unit instanceof BattleShip && atacker == 2) ||
	                        (unit instanceof ArmoredShip && atacker == 3) ||
	                        (unit instanceof MissileLauncher && atacker == 4) ||
	                        (unit instanceof IonCannon && atacker == 5) ||
	                        (unit instanceof PlasmaCannon && atacker == 6)) {
	                        
	                        int attackPower = unit.attack();
	                        int damage = attackPower * attackerChance;
	                        unit.takeDamage(damage);
	                        battleDevelopment.append(attackerType + " generates damage = " + damage);
	                    }
	                }
	            }
	        } else {
	            int atacker = getEnemyGroupAttacker();
	            int defense = getGroupDefender(planetArmy);
	            int attackerChance = Variables.CHANCE_ATTACK_ENEMY_UNITS[atacker-1];
	            String attackerType = getTypeByIndex(defense);
	            String defenderType = getTypeByIndex(atacker);
	            battleDevelopment.append("Attacks Enemy: " + attackerType + " attacks " + defenderType);
	            
	            for (ArrayList<MilitaryUnit> units : planetArmy) {
	                for (MilitaryUnit unit : units) {
	                    if ((unit instanceof LigthHunter && atacker == 0) ||
	                        (unit instanceof HeavyHunter && atacker == 1) ||
	                        (unit instanceof BattleShip && atacker == 2) ||
	                        (unit instanceof ArmoredShip && atacker == 3) ||
	                        (unit instanceof MissileLauncher && atacker == 4) ||
	                        (unit instanceof IonCannon && atacker == 5) ||
	                        (unit instanceof PlasmaCannon && atacker == 6)) {
	                        
	                        int attackPower = unit.attack();
	                        int damage = attackPower * attackerChance;
	                        unit.takeDamage(damage);
	                        battleDevelopment.append(attackerType + " generates damage = " + damage);
	                    }
	                }
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







