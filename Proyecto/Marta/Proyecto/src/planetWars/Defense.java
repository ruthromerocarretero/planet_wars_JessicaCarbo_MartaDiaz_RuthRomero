package planetWars;

public abstract class Defense implements MilitaryUnit, Variables {
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;

    public Defense(int armor, int initialArmor, int baseDamage) {
        super();
        this.armor = armor;
        this.initialArmor = initialArmor;
        this.baseDamage = baseDamage;
    }

    public int attack() {
        return baseDamage;
    }

    public void tekeDamage(int receivedDamage) {
        this.armor -= receivedDamage;
        if (this.armor < 0) {
            this.armor = 0;
        }
    }

    public int getActualArmor() {
        return this.armor;
    }

    public void resetArmor() {
        this.armor = this.initialArmor;
    }

    // Métodos abstractos para ser implementados por las clases concretas de defensa
    public abstract int getMetalCost();
    public abstract int getDeuteriumCost();
    public abstract int getChanceGeneratinWaste();
    public abstract int getChanceAttackAgain();
	
}
