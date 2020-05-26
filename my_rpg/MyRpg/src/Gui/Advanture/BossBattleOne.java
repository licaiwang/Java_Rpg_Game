package Gui.Advanture;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.draw.DrawBoss;
import Gui.Advanture.draw.DrawPlayerUP;
import Gui.Advanture.draw.DrawSpecialEffect;
import Gui.Helper.CreateButton;
import Gui.Helper.MusicHelper;
import Magic.MagicBase;
import Skill.BattleSkillBase;
import item.Item;
import net.miginfocom.swing.MigLayout;
import phase.BattlePhase;
import phase.BattleTemp;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class BossBattleOne extends JPanel {

    private static final long serialVersionUID = 1L;
    public static Boolean isInit = true;
    public static DrawBoss drawBoss;
    public static DrawPlayerUP drawPlayerUP;
    public static JPanel box;
    public static JPanel drawPlayer;
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
    JPanel monsterPanel;
    public static Boolean isActivate = false;
    public static Integer id;

    public BossBattleOne() {
        super();  
        MusicHelper.stopBackgroundMusic();
        MusicHelper.playBackgroundMusic("bossBattle"); 
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                isInit = false;
                timer.cancel();
                init();
            }
        }, 8500); 
    }

    void init() {
        drawBoss = new DrawBoss("UndeadLengend");
        drawBoss.setOpaque(false);
        drawBoss.validate();
        DrawBoss.monsterPanel.repaint();
        drawPlayer = new JPanel();
        drawPlayer.setOpaque(false);
        drawPlayer.setLayout(new BoxLayout(drawPlayer, BoxLayout.Y_AXIS));
        drawPlayer.add(Box.createRigidArea(new Dimension(0, 200)));
        box = new JPanel();
        gridPanel = new JPanel();
        initButton();
        gridPanel.setLayout(new MigLayout("wrap 2", "30[]30", "30[]30"));
        gridPanel.add(btn_1);
        gridPanel.add(btn_2);
        gridPanel.add(btn_3);
        gridPanel.add(btn_4);
        gridPanel.setOpaque(false);
        box.setLayout(new BorderLayout());
        box.add(gridPanel, BorderLayout.EAST);
        box.add(drawBoss, BorderLayout.NORTH);
        box.setOpaque(false);
        setLayout(new BorderLayout());
        add(box, BorderLayout.EAST);
        add(drawPlayer);
        BattleTemp.init(true);
        validate();
        repaint();
    }

    static void normalPhase(int type, int id, int damage) {
        if (BattleSkillBase.IsDamage) {
            drawSkillEffect(type, id);
            BattlePhase.playerTurn(type, damage);
        } else if (BattleSkillBase.IsEnhance) {
            drawPlayerEffect();
            BattlePhase.playerTurn(type, damage);
        }
    }

    static void magicPhase(int type, int id, int damage) {
        if (MagicBase.IsDamage) {
            drawMagicEffect(type, id);
            BattlePhase.playerCastMagic(type, damage);
        } else if (MagicBase.IsEnhance) {
            drawPlayerEffect();
            BattlePhase.playerCastMagic(999 + type, damage);
        }
    }

    static void doBattle(int id) {
        int damage = BattleSkillBase.getSkill(id - 1);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (BattleSkillBase.Strik > 0) {
                    makeButtonEnable();
                    BattleSkillBase.Strik -= 1;
                    normalPhase(0, id, damage);
                    if (!damageCountPhase()) {         
                        Timer timer_M = new Timer();
                        timer_M.schedule(new TimerTask() {
                            public void run() {
                                makeButtonable();
                                //monsterPhase();
                            }
                        }, 1000);
                    }
                } else {
                    normalPhase(0, id, damage);
                    damageCountPhase();
                    //monsterPhase();
                    timer.cancel();
                }
            }
        }, 10, 1000);

    }

    static void castMagic(int id) {
        int damage = MagicBase.getMagic(id - 1);
        magicPhase(0, id, damage);
        damageCountPhase();
    }

    static void monsterPhase() {
        drawBoss.isAtacking = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                BattlePhase.MonsterTurn();
            }
        }, 1000);
    }

    public static boolean damageCountPhase() {
        switch (BattlePhase.checkMonsterDeadOPlayer()) {
            case 0:
                Player.EXP -= BattleTemp.M_EXP;
                Player.COIN +=  BattleTemp.M_COIN;
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
                        if (!Player.isDead) {
                            AdvantureBackground.showRoad();
                        }
                    }
                }, 2000);
                return false;
            case 1:
                return false;
            default:
                initButton();
                playerPause();
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

    public static void makeButtonable() {
        btn_1.setEnabled(true);
        btn_2.setEnabled(true);
        btn_3.setEnabled(true);
        btn_4.setEnabled(true);
        BattleSidePanel.btn_4.setEnabled(true);
    }



    public static void playerPause() {
        makeButtonEnable();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                makeButtonable();
            }
        }, 2000);
    }

    public static void drawSkillEffect(int type, int id) {
        DrawSpecialEffect effect = new DrawSpecialEffect(String.valueOf(type) + "_" + String.valueOf(id), 0);
        DrawBoss.monsterPanel.add(effect);
        effect.setOpaque(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                DrawBoss.monsterPanel.remove(effect);
                DrawBoss.monsterPanel.validate();
                DrawBoss.monsterPanel.repaint();
            }
        }, 250);
    }

    public static void drawMagicEffect(int type, int id) {
        DrawSpecialEffect effect = new DrawSpecialEffect(String.valueOf(type) + "_" + String.valueOf(id), 1);
        DrawBoss.monsterPanel.add(effect);
        effect.setOpaque(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                DrawBoss.monsterPanel.remove(effect);
                DrawBoss.monsterPanel.validate();
                DrawBoss.monsterPanel.repaint();
            }
        }, 250);
    }

    public static void drawPlayerEffect() {
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
        btn_4 = new JButton("  藥物  ");
        buttonCommonSetting();
        btn_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreateButton.clickSound();

                buttonNameSetting(BattleSkillBase.in_use_name);
                resetGridPanel();
                btn_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(1);
                        playerPause();
                    }
                });
                btn_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(2);
                        playerPause();
                    }
                });
                btn_3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(3);
                        playerPause();
                    }
                });
                btn_4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(4);
                        playerPause();
                    }
                });
            }
        });
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateButton.clickSound();

                buttonNameSetting(MagicBase.in_use_name);
                resetGridPanel();
                btn_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        castMagic(1);
                        playerPause();
                    }

                });
                btn_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(2);
                        playerPause();
                    }
                });
                btn_3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(3);
                        playerPause();
                    }
                });
                btn_4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doBattle(4);
                        playerPause();
                    }
                });
            }
        });

        btn_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreateButton.clickSound();
                int mType = JOptionPane.QUESTION_MESSAGE;
                int oType = JOptionPane.YES_NO_CANCEL_OPTION;
                String[] options = Item.in_use_item;
                int opt = JOptionPane.showOptionDialog(null, "寶具", "啟動", oType, mType, null, options, "確定");
                activate(opt);
            }
        });
        btn_4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreateButton.clickSound();
                int mType = JOptionPane.QUESTION_MESSAGE;
                int oType = JOptionPane.YES_NO_CANCEL_OPTION;
                String[] options = { " 痛立停 ", " 魔力香爐 ", " 女神的祝福 " };
                int opt = JOptionPane.showOptionDialog(null, "道具", "使用", oType, mType, null, options, "確定");
                choseResult(opt);
            }
        });
        resetGridPanel();
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

    public static void setGridPanel(boolean isVisible) {
        if (isVisible) {
            gridPanel.setVisible(true);
        } else {
            gridPanel.setVisible(false);
        }

    }

    protected static void activate(int opt) {
        if (opt == 0) {
            if (!Item.in_use_isAttack[0]) {
                JOptionPane.showMessageDialog(null, "");
            } else {
                Item.getAbility(Item.in_use_item[0]);
            }
            if (opt == 1) {
                if (!Item.in_use_isAttack[1]) {
                    JOptionPane.showMessageDialog(null, "");
                } else {
                    Item.getAbility(Item.in_use_item[1]);
                }
            }
            if (opt == 2) {
                if (!Item.in_use_isAttack[2]) {
                    JOptionPane.showMessageDialog(null, "");
                } else {
                    Item.getAbility(Item.in_use_item[2]);
                }
            }
        }
    }

    protected static void choseResult(int opt) {
        Thread playMusic = new MusicHelper("potion.wav");
        if (opt == 0) {
            if (Player.stop_pain >= 1) {
                playMusic.start();
                Player.stop_pain -= 1;
                JOptionPane.showMessageDialog(null, " 回復 15% 的血量，藥剩下(" + Player.stop_pain + ")個");
                Player.HP += (int) (Player.Max_HP * 0.15);
                if (Player.HP > Player.Max_HP) {
                    Player.HP = Player.Max_HP;
                }
                BattleSidePanel.resetHp();
            } else {
                JOptionPane.showMessageDialog(null, " 藥吃完了!");
            }
        }
        if (opt == 1) {
            if (Player.magic_box >= 1) {
                playMusic.start();
                Player.magic_box -= 1;
                JOptionPane.showMessageDialog(null, "  回復 15% 的魔力，藥剩下(" + Player.magic_box + ")個");
                Player.MP += (int) (Player.Max_MP * 0.15);
                if (Player.MP > Player.Max_MP) {
                    Player.MP = Player.Max_MP;
                }
                BattleSidePanel.resetMp();
            } else {
                JOptionPane.showMessageDialog(null, " 藥吃完了! ");
            }

        }
        if (opt == 2) {
            if (Player.godness_bless >= 1) {
                playMusic.start();
                Player.godness_bless -= 1;
                JOptionPane.showMessageDialog(null, " 你彷彿重生了，藥剩下(" + Player.godness_bless + ")個");
                Player.HP = Player.Max_HP;
                Player.MP = Player.Max_MP;
                BattleSidePanel.resetMp();
                BattleSidePanel.resetHp();
            } else {
                JOptionPane.showMessageDialog(null, " 藥吃完了! ");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBattleBase(g);
        if (Battle.isActivate) {
            drawItem(g, Battle.id);
        }
    }

    public void drawBattleBase(Graphics g) {
        if(isInit)
        {
            g.drawImage(ResReader.boss_init, 0, 0, getWidth(), getHeight(), this);
        }else{
            g.drawImage(ResReader.boss_1_background, 0, 0, getWidth(), getHeight(), this);
        }    
    }

    public void drawItem(Graphics g, int id) {
        Image image = new ImageIcon(ResReader.path + "res/item/" + id + ".gif").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}