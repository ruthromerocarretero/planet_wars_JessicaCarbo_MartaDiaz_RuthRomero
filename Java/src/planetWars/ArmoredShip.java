package planetWars;

public class ArmoredShip extends Ship{
    //	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;
    private int technologyDefense;
    private int technologyAttack;


    public ArmoredShip(int armor, int baseDamage) {
        //super(armor, baseDamage);
        setArmor(ARMOR_ARMOREDSHIP + (technologyDefense*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100);
        setBaseDamage(BASE_DAMAGE_ARMOREDSHIP + (technologyAttack*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100);
        this.setInitialArmor(armor);
    }

    public ArmoredShip() {
        super();
        setArmor(ARMOR_ARMOREDSHIP);
        setBaseDamage(BASE_DAMAGE_ARMOREDSHIP);
        setInitialArmor(ARMOR_ARMOREDSHIP);
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
        return METAL_COST_ARMOREDSHIP;
    }

    public int getDeuteriumCost() {
        return DEUTERIUM_COST_ARMOREDSHIP;
    }

    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_ARMOREDSHIP;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_ARMOREDSHIP;
    }


    public void  resetArmor(){
        setArmor(getInitialArmor());
    }

// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
//	TODO Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit
}
