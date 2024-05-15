package planetWars;

public class LigthHunter extends Ship{
	//	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;
	private int technologyDefense;
	private int technologyAttack;


	public LigthHunter(int armor, int baseDamage) {
		//super(armor, baseDamage);
		setArmor(ARMOR_LIGTHHUNTER + (technologyDefense*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_LIGTHHUNTER + (technologyAttack*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100);
		this.setInitialArmor(armor);
	}

	public LigthHunter() {
		super();
		setArmor(ARMOR_LIGTHHUNTER);
		setBaseDamage(BASE_DAMAGE_LIGTHHUNTER);
		setInitialArmor(ARMOR_LIGTHHUNTER);
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
		return METAL_COST_LIGTHHUNTER;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_LIGTHHUNTER;
	}

	public int getChanceGeneratinWaste(){
		return CHANCE_GENERATNG_WASTE_LIGTHHUNTER;
	}

	public int getChanceAttackAgain(){
		return CHANCE_ATTACK_AGAIN_LIGTHHUNTER;
	}


	public void  resetArmor(){
		setArmor(getInitialArmor());
	}

// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
//	TODO Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit
}
