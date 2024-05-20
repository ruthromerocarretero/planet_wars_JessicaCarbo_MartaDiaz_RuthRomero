package planetWars;

public class BattleShip extends Ship {

	public BattleShip(int armor, int baseDamage) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public int attack() {
    	
        return getBaseDamage();  
    }

    @Override
    public void takeDamage(int damage) {
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
        return 0;  // Ejemplo, necesita detalles específicos.
    }

    @Override
    public int getChanceGeneratinWaste() {
        return 0;  // Ejemplo, necesita detalles específicos.
    }

    @Override
    public int getChanceAttackAgain() {
        return 0;  // Ejemplo, necesita detalles específicos.
    }

}
