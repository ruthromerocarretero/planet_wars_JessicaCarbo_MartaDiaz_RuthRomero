package primerasPruebas;

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

	//CONSTRUCTOR
    public Defense(int initialArmor, int baseDamage) {
        this.initialArmor = initialArmor;
        this.armor = initialArmor;
        this.baseDamage = baseDamage;
    }

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
