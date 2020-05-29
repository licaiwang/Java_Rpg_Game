package monster.Boss;

import java.util.Random;

import Gui.BottomPanel;
import Gui.Helper.MusicHelper;
import monster.Monster;
import phase.BattleTemp;

public class Boss_1 extends Monster {
    /*
     *
     * Boss_1 - 泰格達不死隊
     * 
     * 招式：
     * 
     * 1. 普通攻擊 - 40 % 2. 二連擊 - 30 % 3. 提升攻擊力(中) - 10 % 4. 
     * 提升速度 (小)，防禦力 (小) - 20 %
     * 
     * 特殊： 在未持有死告天使的情況下，這個 Boss 不會死 且每被玩家擊倒一次會再生成一個不死隊隊員，然後攻擊力 * 2
     * 
     * 在持有死告天使且發動的時候必發動成功，但玩家還是必須再擊倒" "正常版" 的不死隊
     * 
     * 
     */
    public static Boolean isStrike = false;
    public static Boolean isAttack = false;
    public static Boolean isSecond = false;
    public static Boolean isdead = false;
    public static String Name = "UndeadLengend";
    public static Integer skillId = 0;
  
    public Boss_1(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp) {
        super(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp);
    }

    public static Integer Atkskill() {
        // 一階段 1 ~ 3
        // 二階段 2 ~ 4
        if(!isdead)
        {
              
            Random r = new Random();
            int opt = r.nextInt(10);
            if(isSecond)
            {   
                int skill_2[] = {1,1,2,2,2,3,3,4,4,4};
                opt = skill_2[opt];
            }else{
                int skill_1[] = {1,1,1,1,2,2,2,3,3,3};
                opt = skill_1[opt];
            }

            switch (opt) {
                case 1:
                    isAttack = true;
                    skillId = 1;
                    return normalAtk();
                case 2:
                    isAttack = true;
                    skillId = 2;
                    return doubleStrike();
                case 3:
                    isAttack = false;
                    skillId = 3;
                    return enhanceAtk();
                case 4:
                    isAttack = false;
                    skillId = 4;
                    return Atk_enhanceDefSpeed();
            }
        }
        return 0;     
    }

    private static Integer enhanceAtk() {
        // 提升攻擊力，回血
        Thread fire = new MusicHelper("boss/fire.wav");
        fire.start();
        if(BattleTemp.M_HP < 150)
        {
            BattleTemp.M_HP += 15;
        }   
        BattleTemp.ATK += 5;
        BottomPanel.content = ("不死隊的身上出現了紅色的火焰！");
        BottomPanel.resetTextArea();
        return 0;
    }

    private static Integer Atk_enhanceDefSpeed() {
        // 降低防禦，提升速度
        Thread roar = new MusicHelper("boss/scream.wav");
        roar.start();
        BattleTemp.DEF -= 3;
        BattleTemp.SPEED += 10;
        BottomPanel.content = ("不死隊發瘋似的朝你襲來！");
        BottomPanel.resetTextArea();
        return 0;
    }

    private static Integer doubleStrike() {
        // 攻擊兩次
        Thread roar = new MusicHelper("boss/scream1.wav");
        roar.start();
        BattleTemp.M_isStrike = true;
        return BattleTemp.M_ATK;
    }

    private static Integer normalAtk() {
        // 普攻
        Thread hit = new MusicHelper("boss/hit.wav");
        hit.start();
        return BattleTemp.M_ATK;
    }
}