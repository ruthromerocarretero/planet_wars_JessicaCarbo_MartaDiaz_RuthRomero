package planetWars;

public class HeavyHunter extends Ship {
//	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;

	public HeavyHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
		setArmor(ARMOR_HEAVYHUNTER + (technologyDefense * PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY) * 1000 / 100);
		setBaseDamage(BASE_DAMAGE_HEAVYHUNTER + (technologyAttack * PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY) * 1000 / 100);
		setInitialArmor(armor);
	}

	public HeavyHunter() {
		super();
		setArmor(ARMOR_HEAVYHUNTER);
		setBaseDamage(BASE_DAMAGE_HEAVYHUNTER);
		setInitialArmor(getArmor());
	}
}