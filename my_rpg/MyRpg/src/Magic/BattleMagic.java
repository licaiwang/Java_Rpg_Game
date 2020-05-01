package Magic;

import java.util.Arrays;
import java.util.List;

import phase.BattleTemp;

public class BattleMagic {
 /*

    回傳技能傷害值

    */
    public static List<String> skill_list =  Arrays.asList("    攻擊    ", "魔法飛彈", "    刺擊    ","  魔法箭  ");
    
    public static int Basic_Atk()
    {
        return BattleTemp.ATK*999 - BattleTemp.M_DEF;     
    }
    public static int Basic_SpAtk()
    {
        return BattleTemp.SP_ATK - BattleTemp.M_SP_DEF;     
    }
    public static int Basic_Penetrate()
    {
        return BattleTemp.ATK/2;     
    }
    public static int Basic_SP_Penetrate()
    {
        return BattleTemp.SP_ATK/2;     
    }
}