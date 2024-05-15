package planetWars;

import java.util.ArrayList;

public class TestRuth implements Variables{
    //
    int technologyDefense;
    int technologyAtack;
    int metal;
    int deuterium;
    int upgradeDefenseTechnologyDeuteriumCost;
    int upgradeAttackTechnologyDeuteriumCost;
    TestRuth(){
        technologyDefense = 1;
        technologyAtack = 2;
        metal = 1000;
        deuterium = 2000;

    }
    ArrayList<Ship> navecitas = new  ArrayList<Ship>();

    // lo que realmente tendras:
    // ArrayList<MilitaryUnit>[] army = new ArrayList<MilitaryUnit>[7];
    //Army[0] → arrayList de Ligth Hunter
    //Army[1] → arrayList de Heavy Hunter
    //Army[2] → arrayList de Battle Ship
    //Army[3] → arrayList de Armored Ship
    //Army[4] → arrayList de Missile Launcher
    //Army[5] → arrayList de Ion Cannon
    //Army[6] → arrayList de Plasma Cannon



    // public LigthHunter(int armor, int baseDamage)

    public void newLigthHunter(int n){
        // BASE_DAMAGE_LIGTHHUNTER
        // ARMOR_LIGTHHUNTER
        //PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY
        // PLUS_DAMAGE_LIGTHHUNTER_BY_TECHNOLOGY
        try {
            for (int i = 0; i<n;i++){
                if (METAL_COST_LIGTHHUNTER > metal){
                    throw new ResourceBuildingException("There is not enough metal to build a ligthhunter");
                }
                if ( DEUTERIUM_COST_LIGTHHUNTER > deuterium ) {
                        throw new ResourceBuildingException("There is not enough deuterium to build a ligthhunter");
                }
                navecitas.add(new LigthHunter(ARMOR_LIGTHHUNTER + technologyDefense*Variables.PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY,BASE_DAMAGE_LIGTHHUNTER+technologyAtack*Variables.PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY));
                //army[0].add(new LigthHunter(ARMOR_LIGTHHUNTER + technologyDefense*Variables.PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY,BASE_DAMAGE_LIGTHHUNTER+technologyAtack*Variables.PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY));
                }
        }catch (ResourceBuildingException e) {
            System.out.println(e.getMessage())
        }


    }
}
class ResourceBuildingException extends Exception{
    ResourceBuildingException(String message){
        super(message) ;
    }

        }