package planetWars;

public abstract class Ship implements MilitaryUnit, Variables {

    private int armor;
    private int initialArmor;
    private int baseDamage;

    public Ship(int armor, int baseDamage) {
        super();
        this.armor = armor;
        this.initialArmor = armor;
        this.baseDamage = baseDamage;
    }
    public Ship(){

    }



    // Implementación de los métodos de MilitaryUnit, se tiene que retirar

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

    // Los siguientes métodos dependen de detalles específicos no proporcionados
    @Override
    public int getMetalCost() {
        return 0;  // Ejemplo, necesita detalles específicos.
    }

    @Override
    public int getDeuteriumCost() {


























































































































































































































































































































































































































































































         0;  // Ejemplo, necesita detalles específicos.
    }

    @Override
    public int getChanceGeneratinWaste() {
        return 0;  // Ejemplo, necesita detalles específicos.
    }

    @Override
    public int getChanceAttackAgain() {
        return 0;  // Ejemplo, necesita detalles específicos.
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