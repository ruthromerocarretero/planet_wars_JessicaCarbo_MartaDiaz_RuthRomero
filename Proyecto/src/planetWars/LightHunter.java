package planetWars;

public class LightHunter extends Ship {
    private static final int ARMOR_LIGHTHUNTER = 1000;
    private static final int PLUS_ARMOR_LIGHTHUNTER_BY_TECHNOLOGY = 5;
    private static final int BASE_DAMAGE_LIGHTHUNTER = 300;
    private static final int PLUS_ATTACK_LIGHTHUNTER_BY_TECHNOLOGY = 10;

    public LightHunter(int technologyDefense, int technologyAttack) {
        super(
            calculateArmor(ARMOR_LIGHTHUNTER, technologyDefense, PLUS_ARMOR_LIGHTHUNTER_BY_TECHNOLOGY),
            calculateDamage(BASE_DAMAGE_LIGHTHUNTER, technologyAttack, PLUS_ATTACK_LIGHTHUNTER_BY_TECHNOLOGY)
        );
    }

    public LightHunter() {
        super(ARMOR_LIGHTHUNTER, BASE_DAMAGE_LIGHTHUNTER);
    }

    private static int calculateArmor(int baseArmor, int techLevel, int bonusPerTech) {
        return baseArmor + (int)(baseArmor * techLevel * bonusPerTech / 100.0);
    }

    private static int calculateDamage(int baseDamage, int techLevel, int bonusPerTech) {
        return baseDamage + (int)(baseDamage * techLevel * bonusPerTech / 100.0);
    }

    @Override
    public int attack() {
        return getBaseDamage();
    }

    @Override
    public void takeDamage(int damage) {
        int newArmor = getArmor() - damage;
        setArmor(Math.max(newArmor, 0)); 
    }

    @Override
    public int getActualArmor() {
        return getArmor();
    }

    @Override
    public void resetArmor() {
        setArmor(getInitialArmor());
    }
    
   
}
