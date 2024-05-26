package planetWars;

public abstract class Defense implements MilitaryUnit, Variables {
    private int armor;
    private int initialArmor;
    private int baseDamage;

    //GETTERS
    public int getArmor() {
        return armor;
    }

    public int getInitialArmor() {
        return initialArmor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    //SETTERS
    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setInitialArmor(int initialArmor) {
        this.initialArmor = initialArmor;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}