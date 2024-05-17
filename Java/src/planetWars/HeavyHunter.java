package planetWars;

public class HeavyHunter extends Ship{
	private int technologyDefense;
	private int technologyAttack;


	public HeavyHunter(int armor, int baseDamage) {
		setArmor(ARMOR_HEAVYHUNTER + (technologyDefense*PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_HEAVYHUNTER + (technologyAttack*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100);
		this.setInitialArmor(armor);
	}

	public HeavyHunter() {
		setArmor(ARMOR_HEAVYHUNTER);
		setBaseDamage(BASE_DAMAGE_HEAVYHUNTER);
		setInitialArmor(ARMOR_HEAVYHUNTER);
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
		return METAL_COST_HEAVYHUNTER;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_HEAVYHUNTER;
	}

	public int getChanceGeneratinWaste(){
		return CHANCE_GENERATNG_WASTE_HEAVYHUNTER;
	}

	public int getChanceAttackAgain(){
		return CHANCE_ATTACK_AGAIN_HEAVYHUNTER;
	}


	public void  resetArmor(){
		setArmor(getInitialArmor());
	}

// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
//	TODO Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit
}
