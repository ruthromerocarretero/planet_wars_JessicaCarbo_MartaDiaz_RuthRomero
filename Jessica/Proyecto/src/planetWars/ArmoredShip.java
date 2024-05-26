package planetWars;

public class ArmoredShip extends Ship implements MilitaryUnit{
    private int technologyDefense;
    private int technologyAttack;


    public ArmoredShip(int armor, int baseDamage) {
        setArmor(ARMOR_ARMOREDSHIP + (technologyDefense*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100);
        setBaseDamage(BASE_DAMAGE_ARMOREDSHIP + (technologyAttack*PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100);
        this.setInitialArmor(armor);
    }

    public ArmoredShip() {
        setArmor(ARMOR_ARMOREDSHIP);
        setBaseDamage(BASE_DAMAGE_ARMOREDSHIP);
        setInitialArmor(ARMOR_ARMOREDSHIP);
    }

    public int attack(){
        return getBaseDamage();
    }

    public void takeDamage(int receivedDamage)
    {setArmor(getActualArmor()-receivedDamage);
    }

    public int getActualArmor(){
        return ARMOR_ARMOREDSHIP;
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