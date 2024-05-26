package planetWars;

public class PlasmaCannon extends Defense implements MilitaryUnit{
    private int technologyDefense;
    private int technologyAttack;

    public PlasmaCannon(int armor, int baseDamage) {
        setArmor(ARMOR_PLASMACANNON + (technologyDefense*PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY)*1000/100);
        setBaseDamage(BASE_DAMAGE_PLASMACANNON + (technologyAttack*PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY)*1000/100);
        this.setInitialArmor(armor); //revisar porque no debería de tener parámetros
    }
    public int attack(){
        return getBaseDamage();
    }

    public void takeDamage(int receivedDamage)
    {setArmor(getActualArmor()-receivedDamage);
    }

    public int getActualArmor(){
        return ARMOR_PLASMACANNON;
    }

    public int getMetalCost() {
        return METAL_COST_PLASMACANNON;
    }

    public int getDeuteriumCost() {
        return DEUTERIUM_COST_PLASMACANNON;
    }

    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_PLASMACANNON;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_PLASMACANNON;
    }


    public void  resetArmor(){
        setArmor(getInitialArmor());
    }
	
}