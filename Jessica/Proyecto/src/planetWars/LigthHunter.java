package planetWars;

public class LigthHunter extends Ship{
	private int ARMOR_LIGTHHUNTER;
	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;
	public LigthHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
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
