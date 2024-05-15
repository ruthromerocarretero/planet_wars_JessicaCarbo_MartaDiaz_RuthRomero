package planetWars;

public interface MilitaryUnit {
    abstract int attack();
    abstract void tekeDamage(int receivedDamage);
    abstract int getActualArmor();
●   abstract int getMetalCost();
    abstract int getDeuteriumCost();
    abstract int getChanceGeneratinWaste();
    abstract int getChanceAttackAgain();
    abstract void resetArmor();
}

