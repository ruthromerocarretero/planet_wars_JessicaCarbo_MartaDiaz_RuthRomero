package planetWars;

public class IonCannon extends Defense implements MilitaryUnit {
    private int technologyDefense;
    private int technologyAttack;

    public IonCannon(int armor, int baseDamage) {
        setArmor(ARMOR_IONCANNON + (technologyDefense*PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY)*1000/100);
        setBaseDamage(BASE_DAMAGE_IONCANNON + (technologyAttack*PLUS_ATTACK_IONCANNON_BY_TECHNOLOGY)*1000/100);
        this.setInitialArmor(armor); //revisar porque no debería de tener parámetros
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
        return METAL_COST_IONCANNON;
    }

    public int getDeuteriumCost() {
        return DEUTERIUM_COST_IONCANNON;
    }

    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_IONCANNON;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_IONCANNON;
    }


    public void  resetArmor(){
        setArmor(getInitialArmor());
    }
	@Override
	public void takeDamage(int receivedDamage) {
		// TODO Auto-generated method stub
		
	}
}