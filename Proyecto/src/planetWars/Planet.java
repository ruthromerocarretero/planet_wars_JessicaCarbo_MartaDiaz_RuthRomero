package planetWars;

import java.util.ArrayList;

public class Planet {
	private int technologyDefense;
	private int technologyAtack;
	private int metal;
	private int deuterium;
	private int upgradeDefenseTechnologyDeuteriumCost;
	private int upgradeAttackTechnologyDeuteriumCost;
	private ArrayList<MilitaryUnit>[] army = new ArrayList[7];
	
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
		this.army = army;
	}
	
	public Planet() {
        // Suponiendo que hay 7 tipos de unidades y se inicializan aquí
        army = new ArrayList[7];
        for (int i = 0; i < army.length; i++) {
            army[i] = new ArrayList<>();
        }
    }
	
	 public void upgradeTechnologyDefense() throws ResourceException {
	        if (deuterium >= upgradeDefenseTechnologyDeuteriumCost) { // Verifica si hay suficiente "Deuterium"
	            deuterium -= upgradeDefenseTechnologyDeuteriumCost; // Si se cumple, le resta el coste de la mejora
	            technologyDefense++; // Lo incrementa en uno
	            upgradeDefenseTechnologyDeuteriumCost *= 1.1;  // Incrementa el costo en un 10%
	        } else { // Si no se cumple la condicion, entonces lanza una excepcion, pq no hay suficiente "Deuterium" 
	            throw new ResourceException("No hay suficiente deuterio para mejorar la tecnología de defensa.");
	        }
	    }
	 
	 public void upgradeTechnologyAttack() throws ResourceException {
	        if (deuterium >= upgradeAttackTechnologyDeuteriumCost) {
	            deuterium -= upgradeAttackTechnologyDeuteriumCost;
	            technologyAtack++;
	            upgradeAttackTechnologyDeuteriumCost *= 1.1;  // Incrementa el costo en un 10%
	        } else {
	            throw new ResourceException("No hay suficiente deuterio para mejorar la tecnología de ataque.");
	        }
	    }
	 
	 public void newLightHunter(int n) throws ResourceException {
	        int totalMetalCost = Variables.METAL_COST_LIGTHHUNTER * n;
	        int totalDeuteriumCost = Variables.DEUTERIUM_COST_LIGTHHUNTER * n;

	        if (metal >= totalMetalCost && deuterium >= totalDeuteriumCost) {
	            metal -= totalMetalCost;
	            deuterium -= totalDeuteriumCost;

	            for (int i = 0; i < n; i++) {
	                army[0].add(new LightHunter());  // Asumiendo que el índice 0 es para LightHunters
	            }
	        } else {
	            throw new ResourceException("No hay suficientes recursos para producir " + n + " cazadores ligeros.");
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
	
	

}
