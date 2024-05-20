package planetWars;

public class HeavyHunter extends Ship implements MilitaryUnit {
	
	private int armor;
    
    public HeavyHunter(int armor, int baseDamage) {
        super(armor, baseDamage);
     
    }

    public HeavyHunter() {
        this(Variables.ARMOR_HEAVYHUNTER, Variables.BASE_DAMAGE_HEAVYHUNTER);
    }

    @Override
    public int attack() {
        return baseDamage; 
    }
    
    @Override
    public void takeDamage(int receivedDamage) {
        armor -= receivedDamage; 
    }
    
    @Override
    public int getActualArmor() {
        return armor; 
    }
    
    @Override
    public int getMetalCost() {
        return Variables.METAL_COST_HEAVYHUNTER; 
    }
    
    @Override
    public int getDeuteriumCost() {
        return Variables.DEUTERIUM_COST_HEAVYHUNTER;
    }
    
    @Override
    public int getChanceGeneratinWaste() {
        return 0; 
    }
    
    @Override
    public int getChanceAttackAgain() {
        return 0;
    }
    
    @Override
    public void resetArmor() {
        return getArmor;
	 
}
}

