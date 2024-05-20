package planetWars;

public class BattleShip extends Ship implements MilitaryUnit{
	private int technologyDefense;
	private int technologyAttack;


	public BattleShip(int armor, int baseDamage) {
		setArmor(ARMOR_BATTLESHIP + (technologyDefense*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_BATTLESHIP + (technologyAttack*PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)*1000/100);
		this.setInitialArmor(armor);
	}

	public BattleShip() {
		setArmor(ARMOR_BATTLESHIP);
		setBaseDamage(BASE_DAMAGE_BATTLESHIP);
		setInitialArmor(ARMOR_BATTLESHIP);
	}

	public int attack(){
		return getBaseDamage();
	}

	public void tekeDamage(int receivedDamage)
	{setArmor(getActualArmor()-receivedDamage);
	}

	public int getActualArmor(){
		return getArmor();
	}

	public int getMetalCost() {
		return METAL_COST_BATTLESHIP;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_BATTLESHIP;
	}

	public int getChanceGeneratinWaste(){
		return CHANCE_GENERATNG_WASTE_BATTLESHIP;
	}

	public int getChanceAttackAgain(){
		return CHANCE_ATTACK_AGAIN_BATTLESHIP;
	}


	public void  resetArmor(){
		setArmor(getInitialArmor());
	}

	@Override
	public void takeDamage(int receivedDamage) {
		// TODO Auto-generated method stub
		
	}

// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
//	TODO Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit
}
