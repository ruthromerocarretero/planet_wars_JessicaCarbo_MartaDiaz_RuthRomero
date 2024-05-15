package planetWars;

public class LigthHunter extends Ship{
//	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;


	public LigthHunter(int armor, int baseDamage) {
		//super(armor, baseDamage);
		setArmor(armor);
		setBaseDamage(baseDamage);
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

	public void tekeDamage(int receivedDamage){
		setArmor(getActualArmor()-receivedDamage);
	}
	public void  resetArmor(){
		setArmor(getInitialArmor());
	}


// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
//	TODO Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit
}
