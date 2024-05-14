package planetWars;

public class BattleShip extends Ship{
//	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;

	public BattleShip(int armor, int baseDamage) {
		super(armor, baseDamage);
// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
		setArmor(ARMOR_BATTLESHIP + (technologyDefense*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_HEAVYHUNTER + (technologyAttack*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(armor);
	}

	public BattleShip() {
		super();
		setArmor(ARMOR_BATTLESHIP);
		setBaseDamage(BASE_DAMAGE_BATTLESHIP);
		setInitialArmor(getArmor());
	}
