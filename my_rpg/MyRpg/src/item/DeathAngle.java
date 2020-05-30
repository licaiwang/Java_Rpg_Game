package item;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import Basic.Player;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.Battle;
import Gui.Advanture.BattleSidePanel;
import Gui.Advanture.draw.DrawBlood;
import Gui.Helper.MusicHelper;
import monster.Monster;
import phase.BattlePhase;
import phase.BattleTemp;

public class DeathAngle extends Item {

    /*
     *
     * 寶具 - 死告天使
     * 
     * 
    */
    public DeathAngle(String name, Integer id, String rarity, boolean isattack) {
        super(name, id, rarity, isattack);
    }

    @Override
    public void ability() {
        // 寶具能力
        if (Player.MP == 0) {
            JOptionPane.showMessageDialog(null, "你的魔力不足以發動此寶具！");
        } else {
            Random r = new Random();
            int activate = r.nextInt(20);
            if (activate > 8) {
                Player.MP = 0;
                BattleSidePanel.resetMp();
                Battle.setGridPanel(false);
                Battle.isActivate = true;
                Battle.id = 6;
                Thread playMusic = new MusicHelper("item/dealthAngel.wav");
                playMusic.start();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        Battle.isActivate = false;
                    }
                }, 14850);
                timer.schedule(new TimerTask() {
                    public void run() {
                        Battle.drawBlood();
                        Thread playMusic = new MusicHelper("/monster/" + Battle.monster_list.get(0) + ".wav");
                        playMusic.start();
                    }
                }, 13500);
                timer.schedule(new TimerTask() {
                    public void run() { 
                        BattleTemp.M_HP = 0;
                        BattlePhase.checkMonsterDeadOPlayer();
                        Player.EXP -= Monster.EXP;
                        Player.COIN += Monster.DropCoin(Monster.LEVEL);
                        BattleSidePanel.ExpLabel.setText("下一級：" + Player.EXP);
                        BattleSidePanel.coinLabel.setText("金幣：" + Player.COIN);
                        if (BattleTemp.isUpgrade()) {
                            Player.LEVEL += 1;
                            Player.UpgradePlayer(Player.LEVEL);
                            BattleSidePanel.resetLabel();
                        }      
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            public void run() {
                                AdvantureBackground.showRoad();
                                Battle.makeButtonable();
                                DrawBlood.isBattle = false;
                            }
                        }, 2000);// 2 秒
                    }
                }, 14500);

            } else {
                JOptionPane.showMessageDialog(null, "山中的老人沒有回應你的祈禱！");
                Player.MP -= 1;
                BattleSidePanel.resetMp();
            }
        }
    }

}