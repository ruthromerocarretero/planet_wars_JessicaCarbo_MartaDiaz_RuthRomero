package planetWars;

public class MissileLauncher extends Defense implements MilitaryUnit{
    private int technologyDefense;
    private int technologyAttack;

    public MissileLauncher(int armor, int baseDamage) {
        setArmor(ARMOR_MISSILELAUNCHER + (technologyDefense*PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100);
        setBaseDamage(BASE_DAMAGE_MISSILELAUNCHER + (technologyAttack*PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100);
        this.setInitialArmor(armor); //revisar porque no debería de tener parámetros
    }
    public int attack(){
        return getBaseDamage();
    }

    public void takeDamage(int receivedDamage)
    {setArmor(getActualArmor()-receivedDamage);
    }

    public int getActualArmor(){
        return ARMOR_MISSILELAUNCHER;
    }

    public int getMetalCost() {
        return METAL_COST_MISSILELAUNCHER;
    }

    public int getDeuteriumCost() {
        return DEUTERIUM_COST_MISSILELAUNCHER;
    }

    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_MISSILELAUNCHER;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
    }


    public void  resetArmor(){
        setArmor(getInitialArmor());
    }

}