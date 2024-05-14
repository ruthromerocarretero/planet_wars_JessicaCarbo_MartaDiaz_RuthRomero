package planetWars;

public class LigthHunter extends Ship{
//	private int ARMOR_LIGTHHUNTER;
//	private int  PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY;

	public LigthHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
// TODO de donde saco nivelTecnologia? technologyDefense del planeta? hay que hacerlo en las dos
		setArmor(ARMOR_LIGTHHUNTER + (technologyDefense*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_LIGTHHUNTER + (technologyAttack*PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(armor);
	}

	public LigthHunter() {
		super();
		setArmor(ARMOR_LIGTHHUNTER);
		setBaseDamage(BASE_DAMAGE_LIGTHHUNTER);
		setInitialArmor(getArmor());
	}

//	TODO Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit
}
//método para calcular la armadura
/*  protected int calculateArmor(int armor) {
     int technologyLevel = getDefenseTechnologyLevel(); // Obtener el nivel de tecnología de defensa
     int plusArmor = technologyLevel * PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY; // Calcular el plus de armadura
     return armor + (armor * plusArmor / 100); // Calcular la armadura final
 }

 // método para calcular el daño base
 protected int calculateBaseDamage(int baseDamage) {
     int technologyLevel = getAttackTechnologyLevel(); // Obtener el nivel de tecnología de ataque
     int plusDamage = technologyLevel * PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY; // Calcular el plus de daño
     return baseDamage + (baseDamage * plusDamage / 100); // Calcular el daño base final
 }*/