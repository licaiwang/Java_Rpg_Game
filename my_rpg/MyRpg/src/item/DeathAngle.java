package item;

import java.util.Random;

import Gui.Advanture.Battle;
import phase.BattleTemp;

public class DeathAngle extends Item {

    public DeathAngle(String name,Integer id, String rarity, boolean isattack) {
        super(name,id, rarity, isattack);       
    }


    @Override
    public  void ability() {       
        Random r = new Random();
        int activate = r.nextInt(20);
        if(activate > 8)
        {
            BattleTemp.M_HP -= 999999;
            Battle.damageCountPhase() ;
        }else{
        }
    }
    
}