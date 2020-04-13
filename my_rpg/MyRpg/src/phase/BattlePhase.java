package phase;

import Basic.Player;
import Gui.Advanture.BattleSidePanel;
import Skill.BattleSkillBase;
import monster.CreateMonster;
import monster.Monster;

public class BattlePhase {
    // 傳給畫怪物血量用
    public static Integer to_M_damage = -1;

    public static void playerTurn(int index, int whichAct) {
        switch (whichAct) {
            case 1:
                countDamage(getSkill(index));
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

        }
    }

    public static void MonsterTurn() {
        M_countDamage(CreateMonster.Movement());
    }

    public static int getSkill(int index) {
        int damage = 0;
        switch (index) {
            case 0:
                damage = BattleSkillBase.Basic_Atk();
                break;
            case 1:
                damage = BattleSkillBase.Basic_SpAtk();
                break;
            case 2:
                damage = BattleSkillBase.Basic_Penetrate();
                break;
            case 3:
                damage = BattleSkillBase.Basic_SP_Penetrate();
                break;
        }
        return damage;
    }

    public static int checkMonsterDeadOPlayer() {
        /*
         *
         * 怪物死亡回傳 0 角色死亡回傳 1 
         * 
         */
        if (BattleTemp.M_HP <= 0) {
            to_M_damage = -1;
            Player.COIN += Monster.DropCoin(BattleTemp.LEVEL);
            return 0;
        }
        if (BattleTemp.HP <= 0) {
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
        to_M_damage = Math.round(((BattleTemp.M_HP.floatValue()/(BattleTemp.M_HP.floatValue()+damage)))* 250 ); 
        System.out.println("造成了" +damage+ "點傷害，血剩下" +  BattleTemp.M_HP);
    }

    public static void M_countDamage(int damage) {
        if( BattleTemp.countSpeed() == 1 && BattleTemp.Dodge())
        {damage -= damage;}
        if( BattleTemp.countCritical() == 0 && BattleTemp.Critical())
        {damage += damage;}
        BattleTemp.HP -= damage;
        BattleSidePanel.HpLabel.setText("生命值：" + BattleTemp.HP);
        System.out.println("受到了" + damage+ "點傷害，血剩下" + BattleTemp.HP);
    }

}