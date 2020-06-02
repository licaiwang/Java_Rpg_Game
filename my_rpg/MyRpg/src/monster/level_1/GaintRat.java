package monster.level_1;

import monster.Monster;
import phase.BattleTemp;
/**
 * 
 * @author Rorschach
 * 
 * 生成巨鼠
 * 
 **
 */
public class GaintRat extends Monster 
{
   
        public GaintRat(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin,
                int exp, int level, int rarity)
        {
            super(hp, atk,  def, sp_atk,  sp_def, speed, luck, mp, coin,  exp,  level, rarity);       
        }
        public Integer Atkskill()
        {
            return BattleTemp.M_ATK;
        }
    
}