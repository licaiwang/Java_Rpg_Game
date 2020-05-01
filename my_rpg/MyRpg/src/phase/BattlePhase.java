package phase;

import Basic.Player;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import monster.CreateMonster;

public class BattlePhase {
    // 傳給畫怪物血量用
    public static Integer to_M_damage = -1;

    public static void playerTurn(int index,int damage) {
        switch (index) {
            case 0:
                countDamage(damage);
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
        countEnhance();
    }

    private static void countEnhance() {
        // 算增幅效果是否結束
        for(int i =0; i < 5; i++)
        {
            if(BattleTemp.Enhance_counter[i] > 0)
            {
                BattleTemp.Enhance_counter[i] -= 1;
            }else{
                BattleTemp.resetPower(i);
            }  
        }
    }

    public static void MonsterTurn() {
        M_countDamage(CreateMonster.Movement());
    }

    public static int checkMonsterDeadOPlayer() {
        /*
         *
         * 怪物死亡回傳 0 角色死亡回傳 1
         * 
         */
        if (BattleTemp.M_HP <= 0 && Player.HP > 0) {
            to_M_damage = -1;
            return 0;
        }
        if (Player.HP <= 0) {
            return 1;
        }
        return 2;
    }

    public static void countDamage(int damage) {
        if (BattleTemp.countSpeed() == 0 && BattleTemp.Dodge()) {
            damage -= damage;
        }
        if (BattleTemp.countCritical() == 1 && BattleTemp.Critical()) {
            damage += damage;
        }
        BattleTemp.M_HP -= damage;
        // TODO: 公式有錯
        to_M_damage = Math.round(((BattleTemp.M_HP.floatValue() / (BattleTemp.M_HP.floatValue() + damage))) * 250);
        System.out.println("造成了" + damage + "點傷害，血剩下" + BattleTemp.M_HP);
    }

    public static void M_countDamage(int damage) {
        if (BattleTemp.countSpeed() == 1 && BattleTemp.Dodge()) {
            damage -= damage;
        }
        if (BattleTemp.countCritical() == 0 && BattleTemp.Critical()) {
            damage += damage;
        }
        damage -= BattleTemp.DEF;
        if (damage > 0) {
            Player.HP -= damage;
        }    
        BattleSidePanel.HpLabel.setText("生命值：" + Player.HP);
        if (Player.HP <= 0) {
            AdvantureBackground.showDead();
        }
        System.out.println("受到了" + damage + "點傷害，血剩下" + Player.HP);
    }

}