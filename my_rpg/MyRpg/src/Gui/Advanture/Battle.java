package Gui.Advanture;

import java.awt.*;
import javax.swing.*;

import Gui.Advanture.draw.DrawBlood;
import Gui.Advanture.draw.DrawMonster;
import Gui.Advanture.draw.DrawSpecialEffect;
import Gui.Helper.MusicHelper;
import Skill.BattleSkillBase;
import monster.CreateMonster;
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
    public static JPanel box;
    List<String> monster_list = Arrays.asList("slime", "giant_rat", "fanatic");
    JPanel monsterPanel;
    JPanel gridPanel;
    JButton btn_1;
    JButton btn_2;
    JButton btn_3;
    JButton btn_4;
    public Battle() {
        super();

        // shuffle 打亂順序
        Collections.shuffle(monster_list);
        // TODO : 加入可變以及扣血
        drawMonster = new DrawMonster(monster_list.get(0));
        drawMonster.setOpaque(false);
        drawBlood = new DrawBlood();
        drawBlood.setOpaque(false);
        CreateMonster.create(monster_list.get(0));

        //
        BattleTemp.init();
        //
        box = new JPanel();
        gridPanel = new JPanel();
        //
        btn_1 = new JButton("  戰技  ");
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        btn_2 = new JButton("  魔法  ");
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        btn_3 = new JButton("  寶具  ");
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        btn_4 = new JButton("  道具  ");
        btn_4.setMargin(new Insets(10, 10, 10, 10));
        gridPanel.setLayout(new MigLayout("wrap 2", "30[]30", "30[]30"));
        gridPanel.add(btn_1);
        gridPanel.add(btn_2);
        gridPanel.add(btn_3);
        gridPanel.add(btn_4);
        gridPanel.setOpaque(false);
        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSetting(BattleSkillBase.skill_list);
                resetGridPanel();
                btn_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       
                            normalPhase(0, 1);
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
                    }
                });
                btn_4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
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
            }
        });
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
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
    }

    void normalPhase(int type, int id) {
        BattlePhase.playerTurn(type, id);
        drawSpecialEffect(type, id);
        switch (BattlePhase.checkMonsterDeadOPlayer()) {
            case 0:
                drawBlood();
                Thread playMusic = new MusicHelper("/monster/"+monster_list.get(0)+".wav");
                playMusic.start();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                         AdvantureBackground.showRoad();
                         DrawBlood.isBattle = false;
                    }
                }, 2000);// 五百毫秒
                break;
            case 1:
                DrawBlood.isBattle = false;
                AdvantureBackground.showDead();
                break;
            default:
                drawBlood();
                break;
        }
    }

    private void drawBlood()
    {
        DrawBlood.isBattle = true;
        box.remove(Battle.drawBlood);
        Battle.drawBlood = new DrawBlood();
        drawBlood.setOpaque(false);
        box.add(Battle.drawBlood);
        box.validate();
        drawBlood.repaint();
    }

    private void drawSpecialEffect(int type, int id) {

        DrawSpecialEffect effect = new DrawSpecialEffect(String.valueOf(type) + "_" + String.valueOf(id));
        DrawMonster.effectPanel.add(effect);
        effect.setOpaque(false);
        Thread playMusic = new MusicHelper("/skill/katana1.wav");
        playMusic.start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                DrawMonster.effectPanel.remove(effect);
                DrawMonster.effectPanel.validate();
                DrawMonster.effectPanel.repaint();
            }
        }, 250);// 五百毫秒
    }

    void buttonSetting(List<String> list) {
        btn_1 = new JButton(list.get(0));
        btn_2 = new JButton(list.get(1));
        btn_3 = new JButton(list.get(2));
        btn_4 = new JButton(list.get(3));
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        btn_4.setMargin(new Insets(10, 10, 10, 10));
    }

    public void resetGridPanel() {
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