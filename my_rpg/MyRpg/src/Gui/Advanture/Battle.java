package Gui.Advanture;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Gui.Advanture.draw.DrawBlood;
import Gui.Advanture.draw.DrawMonster;
import Gui.Advanture.draw.DrawPlayerUP;
import Gui.Advanture.draw.DrawSpecialEffect;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.MusicHelper;
import Gui.Town.Market;
import Skill.BattleSkillBase;
import item.Item;
import monster.CreateMonster;
import monster.Monster;
import net.miginfocom.swing.MigLayout;
import phase.BattlePhase;
import phase.BattleTemp;

import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Battle extends JPanel {

    private static final long serialVersionUID = 1L;
    public static DrawBlood drawBlood;
    public static DrawMonster drawMonster;
    public static DrawPlayerUP drawPlayerUP;
    public static JPanel box;
    public static JPanel drawPlayer;
    public static JPanel drawSideEffect;

    public static List<String> monster_list = Arrays.asList("slime", "giant_rat", "fanatic");
    JPanel monsterPanel;
    static JPanel gridPanel;
    static JButton btn_1;
    static JButton btn_2;
    static JButton btn_3;
    static JButton btn_4;
    public static Integer damage[] = { 0, 0, 0, 0 };
    public static Boolean[] IsDamage = { true, true, true, true };
    public static Integer[] Strik = { 0, 0, 0, 0 };
    public static Boolean[] IsEnhance = { false, false, false, false };
    public static Boolean[] IsStatus = { false, false, false, false };

    public Battle() {
        super();
        // shuffle 打亂順序
        Collections.shuffle(monster_list);
        // 初始畫怪的地方，血條，玩家特效
        drawMonster = new DrawMonster(monster_list.get(0));
        drawMonster.setOpaque(false);
        drawMonster.validate();
        DrawMonster.monsterPanel.repaint();
        drawBlood = new DrawBlood();
        drawBlood.setOpaque(false);
        drawBlood.validate();
        drawBlood.repaint();

        drawPlayer = new JPanel();
        drawPlayer.setOpaque(false);
        drawPlayer.setLayout(new BoxLayout(drawPlayer, BoxLayout.Y_AXIS));
        drawPlayer.add(Box.createRigidArea(new Dimension(0, 200)));

        // 生成怪物
        CreateMonster.createLevelOne(monster_list.get(0));
        // 初始化戰鬥臨時數據
        BattleTemp.init();

        // 初始化戰鬥畫面
        box = new JPanel();
        gridPanel = new JPanel();
        // 初始化戰鬥按鈕
        initButton();
        gridPanel.setLayout(new MigLayout("wrap 2", "30[]30", "30[]30"));
        gridPanel.add(btn_1);
        gridPanel.add(btn_2);
        gridPanel.add(btn_3);
        gridPanel.add(btn_4);
        gridPanel.setOpaque(false);
        init();
    }

    void init() {
        box.setLayout(new BorderLayout());
        box.add(gridPanel, BorderLayout.SOUTH);
        box.add(drawMonster, BorderLayout.NORTH);
        box.add(drawBlood);
        box.setOpaque(false);
        setLayout(new BorderLayout());
        add(box, BorderLayout.EAST);
        add(drawPlayer);
    }

    static void normalPhase(int type, int id, int damage) {
        if (BattleSkillBase.IsDamage) {
            drawSpecialEffect(type, id);
            BattlePhase.playerTurn(type, damage);
        } else if (BattleSkillBase.IsEnhance) {
            drawPlayerEffect();
            BattlePhase.playerTurn(type, damage);
        }
    }

    static void doBattle(int id) {
        int damage = BattleSkillBase.getSkill(id - 1);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (BattleSkillBase.Strik > 0) {
                    BattleSkillBase.Strik -= 1;
                    normalPhase(0, id, damage);
                    if (!damageCountPhase()) {
                        timer.cancel();
                    }
                } else {
                    normalPhase(0, id, damage);
                    damageCountPhase();
                    timer.cancel();
                }
            }
        }, 100, 1000);
        monsterPhase();
    }

    static void monsterPhase() {
        BattlePhase.MonsterTurn();
    }

    public static boolean damageCountPhase() {

        switch (BattlePhase.checkMonsterDeadOPlayer()) {
            case 0:
                makeButtonEnable();
                drawBlood();
                Player.EXP -= Monster.EXP;
                Player.COIN += Monster.DropCoin(Monster.LEVEL);
                BattleSidePanel.ExpLabel.setText("下一級：" + Player.EXP);
                BattleSidePanel.coinLabel.setText("金幣：" + Player.COIN);
                if (BattleTemp.isUpgrade()) {
                    Player.LEVEL += 1;
                    Player.UpgradePlayer(Player.LEVEL);
                    BattleSidePanel.resetLabel();
                }
                Thread playMusic = new MusicHelper("/monster/" + monster_list.get(0) + ".wav");
                playMusic.start();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        AdvantureBackground.showRoad();
                        makeButtonable();
                        DrawBlood.isBattle = false;
                    }
                }, 2000);// 五百毫秒
                return false;
            case 1:
                DrawBlood.isBattle = false;
                return false;
            default:
                drawBlood();
                initButton();
                return true;
        }
    }

    private static void makeButtonEnable() {
        btn_1.setEnabled(false);
        btn_2.setEnabled(false);
        btn_3.setEnabled(false);
        btn_4.setEnabled(false);
        BattleSidePanel.btn_4.setEnabled(false);
    }

    private static void makeButtonable() {
        btn_1.setEnabled(true);
        btn_2.setEnabled(true);
        btn_3.setEnabled(true);
        btn_4.setEnabled(true);
        BattleSidePanel.btn_4.setEnabled(true);
    }

    public static void drawBlood() {
        DrawBlood.isBattle = true;
        box.remove(Battle.drawBlood);
        Battle.drawBlood = new DrawBlood();
        drawBlood.setOpaque(false);
        box.add(Battle.drawBlood);
        box.validate();
        drawBlood.repaint();
    }

    public static void drawSpecialEffect(int type, int id) {
        // 畫砍怪物的特效
        DrawSpecialEffect effect = new DrawSpecialEffect(String.valueOf(type) + "_" + String.valueOf(id));
        DrawMonster.monsterPanel.add(effect);
        effect.setOpaque(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                DrawMonster.monsterPanel.remove(effect);
                DrawMonster.monsterPanel.validate();
                DrawMonster.monsterPanel.repaint();
            }
        }, 250);
    }

    public static void drawPlayerEffect() {
        // 畫玩家提升特效
        DrawPlayerUP effect2 = new DrawPlayerUP("def_up");
        effect2.setOpaque(false);
        drawPlayer.add(effect2);
        drawPlayer.validate();
        drawPlayer.repaint();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                drawPlayer.remove(effect2);
                drawPlayer.validate();
                drawPlayer.repaint();
            }
        }, 550);
    }

    static void buttonCommonSetting() {
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        btn_4.setMargin(new Insets(10, 10, 10, 10));
        btn_1.setPreferredSize(new Dimension(100, 30));
        btn_2.setPreferredSize(new Dimension(100, 30));
        btn_3.setPreferredSize(new Dimension(100, 30));
        btn_4.setPreferredSize(new Dimension(100, 30));
    }

    static void buttonNameSetting(String[] in_use_name) {
        btn_1 = new JButton(in_use_name[0]);
        btn_2 = new JButton(in_use_name[1]);
        btn_3 = new JButton(in_use_name[2]);
        btn_4 = new JButton(in_use_name[3]);
        buttonCommonSetting();
    }

    static void initButton() {
        btn_1 = new JButton("  戰技  ");
        btn_2 = new JButton("  魔法  ");
        btn_3 = new JButton("  寶具  ");
        btn_4 = new JButton("  道具  ");
        buttonCommonSetting();
        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNameSetting(BattleSkillBase.in_use_name);
                resetGridPanel();
                btn_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(1);
                    }
                });
                btn_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(2);
                    }
                });
                btn_3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(3);
                    }
                });
                btn_4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(4);
                    }
                });
            }
        });
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mType = JOptionPane.QUESTION_MESSAGE;
                int oType = JOptionPane.YES_NO_CANCEL_OPTION;
                String[] options = Item.in_use_item;
                int opt = JOptionPane.showOptionDialog(null, "啟動哪個寶具?", "請選擇", oType, mType, null, options, "啟動");
                activate(opt);
            }
        });
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mType = JOptionPane.QUESTION_MESSAGE;
                int oType = JOptionPane.YES_NO_CANCEL_OPTION;
                String[] options = { " 痛立停 ", "魔力香爐", "女神的祝福" };
                int opt = JOptionPane.showOptionDialog(null, "使用哪個道具?", "請選擇", oType, mType, null, options, "使用");
                choseResult(opt);
            }
        });
        resetGridPanel();
    }

    protected static void activate(int opt) {
        if (opt == 0) {
            if (!Item.in_use_isAttack[0]) {
                JOptionPane.showMessageDialog(null, "此為被動式寶具,效果為：");
            }else{
               Item.getAbility(Item.in_use_item[0]);
            }
        }
        if (opt == 1) {
            if (!Item.in_use_isAttack[1]) {
                JOptionPane.showMessageDialog(null, "此為被動式寶具,效果為：");
            }else{
                Item.getAbility(Item.in_use_item[1]);
            }
        }
        if (opt == 2) {
            if (!Item.in_use_isAttack[2]) {
                JOptionPane.showMessageDialog(null, "此為被動式寶具,效果為：");
            }else{
                Item.getAbility(Item.in_use_item[2]);
            }
        }
    }

    protected static void choseResult(int opt) {
        if (opt == 0) {
            if (Player.stop_pain > 1) {
                JOptionPane.showMessageDialog(null, " 回復 15% 血量！剩下(" + Player.stop_pain + ")個");
                Player.stop_pain -= 1;
                Player.HP += (int) (Player.Max_HP * 0.15);
                if (Player.HP > Player.Max_HP) {
                    Player.HP = Player.Max_HP;
                }
                BattleSidePanel.resetHp();
            } else {
                JOptionPane.showMessageDialog(null, " 沒有藥了！");
            }
        }
        if (opt == 1) {
            if (Player.magic_box > 1) {
                JOptionPane.showMessageDialog(null, " 回復 15% 魔力！剩下(" + Player.magic_box + ")個");
                Player.magic_box -= 1;
                Player.MP += (int) (Player.Max_MP * 0.15);
                if (Player.MP > Player.Max_MP) {
                    Player.MP = Player.Max_MP;
                }
                BattleSidePanel.resetMp();
            } else {
                JOptionPane.showMessageDialog(null, " 沒有藥了！");
            }

        }
        if (opt == 2) {
            if (Player.godness_bless > 1) {
                JOptionPane.showMessageDialog(null, " 全回覆！剩下(" + Player.godness_bless + ")個");
                Player.godness_bless -= 1;
                Player.HP = Player.Max_HP;
                Player.MP = Player.Max_MP;
                BattleSidePanel.resetMp();
                BattleSidePanel.resetHp();
            } else {
                JOptionPane.showMessageDialog(null, " 沒有藥了！");
            }
        }
    }

    public static void resetGridPanel() {
        gridPanel.removeAll();
        gridPanel.add(btn_1);
        gridPanel.add(btn_2);
        gridPanel.add(btn_3);
        gridPanel.add(btn_4);
        gridPanel.validate();
        gridPanel.repaint();
        gridPanel.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBattleBase(g);
    }

    public void drawBattleBase(Graphics g) {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/battleBase.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}