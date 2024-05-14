package planetWars;

public class ArmoredShip extends Ship {
//	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;

    public ArmoredShip(int armor, int baseDamage) {
        super(armor, baseDamage);
// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
        setArmor(ARMOR_ARMOREDSHIP + (technologyDefense * PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY) * 1000 / 100);
        setBaseDamage(BASE_DAMAGE_HEAVYHUNTER + (technologyAttack * PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY) * 1000 / 100);
        setInitialArmor(armor);
    }

    public ArmoredShip() {
        super();
        setArmor(ARMOR_ARMOREDSHIP);
        setBaseDamage(BASE_DAMAGE_ARMOREDSHIP);
        setInitialArmor(getArmor());
    }
}