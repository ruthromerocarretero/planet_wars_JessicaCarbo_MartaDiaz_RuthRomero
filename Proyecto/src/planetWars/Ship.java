package planetWars;

public abstract class Ship implements MilitaryUnit {
    private int armor;
    private int initialArmor;
    private int baseDamage;

    public Ship(int armor, int baseDamage) {
        this.armor = armor;
        this.baseDamage = baseDamage;
        this.initialArmor = armor; 
    }

    @Override
    public int attack() {
        return getBaseDamage();  // Simplemente retorna el daño base.
    }

    @Override
    public void takeDamage(int damage) {
        this.armor = Math.max(0, this.armor - damage);  // Resta el daño y asegura que el armor no sea negativo.
    }

    @Override
    public int getActualArmor() {
        return getArmor();
    }

    @Override
    public void resetArmor() {
        setArmor(getInitialArmor());
    }


    @Override
    public int getMetalCost() {
        return 0;
    }

    @Override
    public int getDeuteriumCost() {
        return 0;  
    }

    @Override
    public int getChanceGeneratinWaste() {
        return 0; 
    }

    @Override
    public int getChanceAttackAgain() {
        return 0;  
    }

    // Getters y Setters
    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getInitialArmor() {
        return initialArmor;
    }

    public void setInitialArmor(int initialArmor) {
        this.initialArmor = initialArmor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}

