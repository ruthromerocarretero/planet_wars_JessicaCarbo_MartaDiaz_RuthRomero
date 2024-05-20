package planetWars;

public abstract class Defense implements MilitaryUnit, Variables {
	private int armor;
	private int initialArmor;
	private  int baseDamage;
	public Defense() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Defense(int armor, int initialArmor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = initialArmor;
		this.baseDamage = baseDamage;
	}
	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}
	/**
	 * @return the initialArmor
	 */
	public int getInitialArmor() {
		return initialArmor;
	}
	/**
	 * @return the baseDamage
	 */
	public int getBaseDamage() {
		return baseDamage;
	}
	/**
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}
	/**
	 * @param initialArmor the initialArmor to set
	 */
	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}
	/**
	 * @param baseDamage the baseDamage to set
	 */
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	

}
