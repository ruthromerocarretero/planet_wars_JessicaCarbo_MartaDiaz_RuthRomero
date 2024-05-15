package planetWars;

public abstract class Defense implements MilitaryUnit, Variables {
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;

    public Defense(int initialArmor, int baseDamage) {
        this.initialArmor = initialArmor;
        this.armor = initialArmor;
        this.baseDamage = baseDamage;
    }

    public int getArmor() {
        return armor;
    }

    public int getInitialArmor() {
        return initialArmor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

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
