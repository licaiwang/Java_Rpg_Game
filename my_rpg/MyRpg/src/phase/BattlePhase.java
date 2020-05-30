package phase;

import java.util.Timer;
import java.util.TimerTask;

import Basic.Player;
import Gui.BottomPanel;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Advanture.BossBattleOne;
import Gui.Helper.MusicHelper;
import Magic.MagicBase;
import Skill.BattleSkillBase;
import monster.CreateMonster;
import monster.Boss.Boss_1;

public class BattlePhase {
    public static Integer to_M_damage = -1;

    public static void playerTurn(int damage) {
        // 是傷害類的算傷害，然後檢查持續性的能力
        if (BattleSkillBase.IsDamage) {
            countDamage(damage);
        }
        countEnhance();
    }

    public static void playerCastMagic(int damage) {
        // 是傷害類的算傷害，然後檢查持續性的能力
        if (MagicBase.IsDamage) {
            countMagicDamage(damage);
        }
        countEnhance();
    }

    private static void countEnhance() {
        //檢查持續性的能力
        for (int i = 0; i < 5; i++) {
            if (BattleTemp.Enhance_counter[i] > 0) {
                BattleTemp.Enhance_counter[i] -= 1;
            } else {
                BattleTemp.resetPower(i);
            }
        }
    }

    public static void MonsterTurn() {
        // 怪物的回合
        M_countDamage(CreateMonster.Movement());
    }

    public static int checkMonsterDeadOPlayer() {
        /*
         *
         *  怪物死亡 0 
         *  角色死亡 1 
         *  繼續戰鬥 2 
         * 
         */
        if (BattleTemp.M_HP <= 0 && Player.HP > 0) {
            to_M_damage = 250;
            return 0;
        }
        if (Player.HP <= 0) {
            Player.isDead = true;
            return 1;
        }
        return 2;
    }

    public static void countDamage(int damage) {
        /*  玩家 -> 怪物 
         *
         *  算迴避
         *      算爆擊 
         *          算傷害 
         *              若無傷害
         * 
         */
        if (BattleTemp.countSpeed() == 0 && BattleTemp.Dodge()) {
            damage -= damage;
            BottomPanel.content = "你的攻擊被迴避了!";
            BattleTemp.M_HP -= damage;
            to_M_damage += 0;
        } else if (BattleTemp.countCritical() == 1 && BattleTemp.Critical()) {
            damage += damage;
            BottomPanel.content = "正中要害,造成了" + damage + "點傷害";
            BattleTemp.M_HP -= damage;
            to_M_damage += damage * BattleTemp.piceHp;
        } else if (damage > 0) {
            BottomPanel.content = "你的攻擊造成了" + damage + "點傷害";
            BattleTemp.M_HP -= damage;
            Thread hit = new MusicHelper("boss/hit.wav");
            hit.start();
            to_M_damage += damage * BattleTemp.piceHp;
        } else {
            BottomPanel.content = "你的攻擊無法對怪物造成傷害！";
            Thread bounce = new MusicHelper("player/bounce.wav");
            bounce.start();
            to_M_damage += 0;
        }
        if (to_M_damage >= 250) {
            to_M_damage = 249;
        }

        BottomPanel.resetTextArea();
    }

    public static void countMagicDamage(int damage) {
         /* 玩家 -> 怪物  
         *
         *  算魔力
         *      算傷害 
         *          若無傷害 
         *              
         */
        if (Player.MP <= 0) {
            BottomPanel.content = "你的魔力不足了!";
            to_M_damage += 0;
        } else if (damage > 0) {
            BottomPanel.content = "你的魔法造成了" + damage + "點傷害";
            to_M_damage += damage * BattleTemp.piceHp;
        } else {
            BottomPanel.content = "你的魔法無法對怪物造成傷害！";
            to_M_damage += 0;
        }
        BattleTemp.M_HP -= damage;
        BottomPanel.resetTextArea();
    }

    public static void M_countDamage(int damage) {
         /*  怪物 -> 玩家
         *
         *  算迴避
         *      算爆擊 
         *          算傷害 
         *              若無傷害
         *                  玩家是否存活
         */
        if (BattleTemp.countSpeed() == 1 && BattleTemp.Dodge()) {
            damage -= damage;
            BottomPanel.content = "你迴避了敵人的攻擊!";
        }
        if (BattleTemp.countCritical() == 0 && BattleTemp.Critical()) {
            damage += damage;
            BottomPanel.content = "你受到了致命一擊!";
        } else {
            damage -= BattleTemp.DEF;
            if (damage > 0) {
                Player.HP -= damage;
                BottomPanel.content = ("你受到了" + damage + "點傷害");
                Thread hurt = new MusicHelper("player/hurt.wav");
                hurt.start();
            } else {
                BottomPanel.content = (" 怪物無法打穿你的護甲!");
                Thread bounce = new MusicHelper("player/bounce.wav");
                bounce.start();
            }

        }

        BattleSidePanel.HpLabel.setText("生命值：" + Player.HP);
        if (Player.HP <= 0) {
            AdvantureBackground.showDead();
            BottomPanel.content = ("你掛了");
        }
        BottomPanel.resetTextArea();
    }

    public static void Boss_countDamage(int damage) {
        /*  Boss -> 玩家
         *
         *  算迴避
         *      算爆擊 
         *          算傷害 
         *              若無傷害
         *                  玩家是否存活
         *                      如果 Boss 連擊
         * 
         */
        if (BattleTemp.countSpeed() == 1 && BattleTemp.Dodge()) {
            damage -= damage;
            BottomPanel.content = "你迴避了敵人的攻擊!";
        }
        if (BattleTemp.countCritical() == 0 && BattleTemp.Critical()) {
            damage += damage;
            BottomPanel.content = "你受到了致命一擊!";
        } else {
            damage -= BattleTemp.DEF;
            if (damage > 0) {
                Player.HP -= damage;
                BottomPanel.content = ("你受到了" + damage + "點傷害");
                Thread hurt = new MusicHelper("player/hurt.wav");
                hurt.start();
            }
        }
        BattleSidePanel.HpLabel.setText("生命值：" + Player.HP);
        if (Player.HP <= 0) {
            AdvantureBackground.showDead();
            BottomPanel.content = ("你掛了");
            BossBattleOne.isInit = true;
        }
        BottomPanel.resetTextArea();
        if (BattleTemp.M_isStrike) {
            final int d_damage = damage;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    BattleTemp.M_isStrike = false;
                    Boss_countDamage(d_damage);
                }
            }, 1000);

        }
    }

    public static void BossTurn() {
        // Boss 的回合
        if (BattleTemp.M_HP < 75) {
            Boss_1.isSecond = true;
        }
        Boss_countDamage(Boss_1.Atkskill());
    }
}