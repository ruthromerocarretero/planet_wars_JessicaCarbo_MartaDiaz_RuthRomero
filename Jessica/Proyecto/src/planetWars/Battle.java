package planetWars;

import java.util.ArrayList;

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
	        
	        //rellenamos initial cost flett
	        
	        int[] unitMetalValue = {
	        		Variables.METAL_COST_LIGTHHUNTER,
	        		Variables.METAL_COST_HEAVYHUNTER,
	        		Variables.METAL_COST_BATTLESHIP,
	        		Variables.METAL_COST_ARMOREDSHIP,
	        		Variables.METAL_COST_MISSILELAUNCHER,
	        		Variables.METAL_COST_IONCANNON,
	        		Variables.METAL_COST_PLASMACANNON
	        };
	        
	        int[] unitDeuteriumValue = {
	        		Variables.DEUTERIUM_COST_LIGTHHUNTER,
	        		Variables.DEUTERIUM_COST_HEAVYHUNTER,
	        		Variables.DEUTERIUM_COST_BATTLESHIP,
	        		Variables.DEUTERIUM_COST_ARMOREDSHIP,
	        		Variables.DEUTERIUM_COST_MISSILELAUNCHER,
	        		Variables.DEUTERIUM_COST_IONCANNON,
	        		Variables.DEUTERIUM_COST_PLASMACANNON 		
	        };
	        
	        
	        for (int i=0; i<= 1; ++i) {
	        	for (int j =0; j<=6; ++j ) {
	        		initialCostFleet[i][0] += (initialArmies[i][j] * unitMetalValue[j]);
	        		initialCostFleet[i][1] += (initialArmies[i][j] * unitDeuteriumValue[j]);
	        	}
	        }
	        
	        // Calculamos initialNumberUnitsPlanet y initialNumberUnitsEnemy
	        for (int i=0; i<= 1; ++i) {
	        	for (int j =0; j<=6; ++j ) {
	        		if (i == 0) initialNumberUnitsPlanet += initialArmies[i][j];
	        		if (i == 1) initialNumberUnitsEnemy += initialArmies[i][j];
	        	}
	        }
	        
	        this.actualNumberUnitsPlanet = initialArmies[0];
	        this.actualNumberUnitsEnemy = initialArmies[1];
	        
	        
	        
	      
	    
}

