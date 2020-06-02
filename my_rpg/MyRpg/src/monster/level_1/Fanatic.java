package monster.level_1;

import java.util.Random;

import monster.Monster;
import phase.BattleTemp;

/**
 * 
 * @author Rorschach
 * 
 * 生成狂人
 * 
 * 招式:
 *      特殊攻擊
 *      普通攻擊，然後爆擊率上升 10 %
 * 
 **
 */
public class Fanatic extends Monster {
    public Fanatic(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp,
            int level, int rarity) {
        super(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp, level, rarity);
    }

    public Integer Atkskill() {
        Random r = new Random();
        int opt = r.nextInt(2);
        switch (opt) {
            case 1:
                return BattleTemp.M_SP_ATK;              
            default:
               BattleTemp.M_LUCK += 10;
               return BattleTemp.M_ATK;  
        }
        
    }
}