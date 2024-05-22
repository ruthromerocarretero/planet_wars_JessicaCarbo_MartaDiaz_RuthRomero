package planetWars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class Planet {
	private int technologyDefense;
	private int technologyAtack;
	private int metal;
	private int deuterium;
	private int upgradeDefenseTechnologyDeuteriumCost;
	private int upgradeAttackTechnologyDeuteriumCost;
	private ArrayList<MilitaryUnit>[] army;
	
	
	
	public Planet(int technologyDefense, int technologyAtack, int metal, int deuterium,
			int upgradeDefenseTechnologyDeuteriumCost, int upgradeAttackTechnologyDeuteriumCost,
			ArrayList<MilitaryUnit>[] army) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
		this.metal = metal;
		this.deuterium = deuterium;
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
		this.army = new ArrayList[7];
		
        for (int i = 0; i < army.length; i++) {
        	this.army[i] = new ArrayList<>();
        }
	}
	
	
	
	 public void upgradeTechnologyDefense() throws ResourceException {
		// se calcula el costo de la actualización actual
		    int currentUpgradeCost = upgradeDefenseTechnologyDeuteriumCost;
	        if (deuterium >= upgradeDefenseTechnologyDeuteriumCost) { // Verifica si hay suficiente deuterium
	        	deuterium -= currentUpgradeCost;
	        	// Si se cumple, le resta el coste de la mejora
	            technologyDefense++; // Lo incrementa en uno
	            upgradeDefenseTechnologyDeuteriumCost *= 1.1;  // Incrementa el costo en un 10%
	        } else { // Si no se cumple la condicion, entonces lanza una excepcion pq no hay suficiente "Deuterium" 
	            throw new ResourceException("Insufficient deuterium to upgrade defense technology.");
	        }
	    }
	 
	 public void upgradeTechnologyAttack() throws ResourceException {
		    int currentUpgradeCost = upgradeAttackTechnologyDeuteriumCost;
	        if (deuterium >=  currentUpgradeCost) {
	            deuterium -=  currentUpgradeCost;
	            technologyAtack++;
	            upgradeAttackTechnologyDeuteriumCost *= 1.1;  
	        } else {
	            throw new ResourceException("No hay suficiente deuterio para mejorar la tecnología de ataque.");
	        }
	    }
	 

	 public int newLightHunter(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_LIGTHHUNTER * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_LIGTHHUNTER * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[0].add(new LigthHunter(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_LIGTHHUNTER, deuterium / Variables.DEUTERIUM_COST_LIGTHHUNTER);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_LIGTHHUNTER * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_LIGTHHUNTER * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[0].add(new LigthHunter(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Light Hunters.");
		        }
		    }
		}

	 
	 public int newHeavyHunter(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_HEAVYHUNTER * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_HEAVYHUNTER * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[1].add(new HeavyHunter(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_HEAVYHUNTER, deuterium / Variables.DEUTERIUM_COST_HEAVYHUNTER);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_HEAVYHUNTER * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_HEAVYHUNTER * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[1].add(new HeavyHunter(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Heavy Hunters.");
		        }
		    }
		}
	 public int newBattleShip(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_BATTLESHIP * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_BATTLESHIP * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[2].add(new BattleShip(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_BATTLESHIP, deuterium / Variables.DEUTERIUM_COST_BATTLESHIP);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_BATTLESHIP * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_BATTLESHIP * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[2].add(new BattleShip(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Battle Ships.");
		        }
		    }
		}

		public int newArmoredShip(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_ARMOREDSHIP * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_ARMOREDSHIP * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[3].add(new ArmoredShip(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_ARMOREDSHIP, deuterium / Variables.DEUTERIUM_COST_ARMOREDSHIP);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_ARMOREDSHIP * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_ARMOREDSHIP * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[3].add(new ArmoredShip(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Armored Ships.");
		        }
		    }
		}

		public int newMissileLauncher(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_MISSILELAUNCHER * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_MISSILELAUNCHER * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[4].add(new MissileLauncher(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_MISSILELAUNCHER, deuterium / Variables.DEUTERIUM_COST_MISSILELAUNCHER);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_MISSILELAUNCHER * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_MISSILELAUNCHER * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[4].add(new MissileLauncher(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Missile Launchers.");
		        }
		    }
		}

		public int newIonCannon(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_IONCANNON * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_IONCANNON * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[5].add(new IonCannon(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_IONCANNON, deuterium / Variables.DEUTERIUM_COST_IONCANNON);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_IONCANNON * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_IONCANNON * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[5].add(new IonCannon(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Ion Cannons.");
		        }
		    }
		}

		public int newPlasmaCannon(int n) throws ResourceException {
		    int totalMetalCost = Variables.METAL_COST_PLASMACANNON * n;
		    int totalDeuteriumCost = Variables.DEUTERIUM_COST_PLASMACANNON * n;

		    if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
		        metal -= totalMetalCost;
		        deuterium -= totalDeuteriumCost;

		        for (int i = 0; i < n; i++) {
		            army[6].add(new PlasmaCannon(i, i));  
		        }
		        return n;
		    } else {
		        int maxBuildable = Math.min(metal / Variables.METAL_COST_PLASMACANNON, deuterium / Variables.DEUTERIUM_COST_PLASMACANNON);
		        if (maxBuildable > 0) {
		            int partialMetalCost = Variables.METAL_COST_PLASMACANNON * maxBuildable;
		            int partialDeuteriumCost = Variables.DEUTERIUM_COST_PLASMACANNON * maxBuildable;

		            metal -= partialMetalCost;
		            deuterium -= partialDeuteriumCost;

		            for (int i = 0; i < maxBuildable; i++) {
		                army[6].add(new PlasmaCannon(i, i));  
		            }
		            throw new ResourceException(maxBuildable + " Light Hunters added.");
		        } else {
		            throw new ResourceException("Not enough resources to build any Plasma Cannons.");
		        }
		    }
		}

	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAtack() {
		return technologyAtack;
	}

	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public int getDeuterium() {
		return deuterium;
	}

	public void setDeuterium(int deuterium) {
		this.deuterium = deuterium;
	}

	public int getUpgradeDefenseTechnologyDeuteriumCost() {
		return upgradeDefenseTechnologyDeuteriumCost;
	}

	public void setUpgradeDefenseTechnologyDeuteriumCost(int upgradeDefenseTechnologyDeuteriumCost) {
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
	}

	public int getUpgradeAttackTechnologyDeuteriumCost() {
		return upgradeAttackTechnologyDeuteriumCost;
	}

	public void setUpgradeAttackTechnologyDeuteriumCost(int upgradeAttackTechnologyDeuteriumCost) {
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}

	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
	
	public void printStats() {
	    System.out.println("Planet Stats");
	    System.out.println("TECHNOLOGY");
	    System.out.println(String.format("%-20s %20d", "Atack Technology:", technologyAtack)); // Corrección aquí
	    System.out.println(String.format("%-20s %20d", "Defense Technology:", technologyDefense)); // Corrección aquí
	    System.out.println("DEFENSES");
	    System.out.println(String.format("%-20s %20d", "Missile Launcher:", army[4].size()));
	    System.out.println(String.format("%-20s %20d", "Ion Cannon:", army[5].size()));
	    System.out.println(String.format("%-20s %20d", "Plasma Cannon:", army[6].size()));
	    System.out.println("FLEET");
	    System.out.println(String.format("%-20s %20d", "Ligh Hunter:", army[0].size()));
	    System.out.println(String.format("%-20s %20d", "Heavy Hunter:", army[1].size()));
	    System.out.println(String.format("%-20s %20d", "Battle Ship:", army[2].size()));
	    System.out.println(String.format("%-20s %20d", "Armored Ship:", army[3].size()));
	    System.out.println("RESOURCES");
	    System.out.println(String.format("%-20s %20d", "METAL:", metal));
	    System.out.println(String.format("%-20s %20d", "DEUTERIUM:", deuterium));
	}

	

}