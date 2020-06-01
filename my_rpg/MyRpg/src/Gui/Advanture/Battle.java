package Gui.Advanture;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.draw.DrawBlood;
import Gui.Advanture.draw.DrawMonster;
import Gui.Advanture.draw.DrawPlayerUP;
import Gui.Advanture.draw.DrawSpecialEffect;
import Gui.Helper.CreateButton;
import Gui.Helper.MusicHelper;
import Magic.MagicBase;
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

  /** 
     * 
     * @author  Rorschach
     * 
     *   發生戰鬥時的畫面
     * 
     *  
     **
     */

public class Battle extends JPanel {

    private static final long serialVersionUID = 1L;

    public static DrawBlood drawBlood;
    public static DrawMonster drawMonster;
    public static DrawPlayerUP drawPlayerUP;
    public static JPanel box;
    public static JPanel drawPlayer;

    public static List<String> monster_list = Arrays.asList("slime", "giant_rat", "fanatic");

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
    public static Integer drawPlayerType = 0;

    public Battle() {
        super();

        Collections.shuffle(monster_list);

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

        CreateMonster.createLevelOne(monster_list.get(0));

        BattleTemp.init(false);
        box = new JPanel();
        gridPanel = new JPanel();
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
        drawPlayerType = 0;
        if (BattleSkillBase.IsDamage) {
            drawSkillEffect(type, id);
            BattlePhase.playerTurn(damage);
        } else if (BattleSkillBase.IsEnhance) {
            drawPlayerEffect(type, id);
            BattlePhase.playerTurn(damage);
        }
    }

    static void magicPhase(int type, int id, int damage) {
        drawPlayerType = 1;
        if (MagicBase.IsDamage) {
            drawMagicEffect(type, id);
            BattlePhase.playerCastMagic(damage);
        } else if (MagicBase.IsEnhance) {
            drawPlayerEffect(type, id);
            BattlePhase.playerCastMagic(damage);
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
                        timer.cancel();
                        Timer timer_M = new Timer();
                        timer_M.schedule(new TimerTask() {
                            public void run() {
                                makeButtonable();
                                monsterPhase();
                            }
                        }, 1000);
                    }
                } else {
                    normalPhase(0, id, damage);
                    damageCountPhase();
                    monsterPhase();
                    timer.cancel();
                }
            }
        }, 10, 1000);

    }

    static void castMagic(int id) {
        int damage = MagicBase.getMagic(id - 1);
        magicPhase(1, id, damage);
        damageCountPhase();
    }

    static void monsterPhase() {
        drawMonster.isAtacking = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                BattlePhase.MonsterTurn();
            }
        }, 750);
    }

    public static boolean damageCountPhase() {

        switch (BattlePhase.checkMonsterDeadOPlayer()) {
            case 0:
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
                        if (!Player.isDead) {
                            AdvantureBackground.showRoad();
                            DrawBlood.isBattle = false;
                        }
                    }
                }, 3000);
                return false;
            case 1:
                DrawBlood.isBattle = false;
                return false;
            default:
                drawBlood();
                initButton();
                monsterPhase();
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

    public static void drawBlood() {
        DrawBlood.isBattle = true;
        box.remove(Battle.drawBlood);
        Battle.drawBlood = new DrawBlood();
        drawBlood.setOpaque(false);
        box.add(Battle.drawBlood);
        box.validate();
        drawBlood.repaint();
    }

    public static void playerPause() {
        makeButtonEnable();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                makeButtonable();
            }
        }, 3000);
    }

    public static void drawSkillEffect(int type, int id) {
        DrawSpecialEffect effect = new DrawSpecialEffect(
                String.valueOf(type) + "_" + String.valueOf(BattleSkillBase.in_use_skill[id - 1]), 0, 400, 400);
        DrawMonster.monsterPanel.add(effect);
        effect.setOpaque(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                DrawMonster.monsterPanel.remove(effect);
                DrawMonster.monsterPanel.validate();
                DrawMonster.monsterPanel.repaint();
            }
        }, 500);
    }

    public static void drawMagicEffect(int type, int id) {
        DrawSpecialEffect effect = new DrawSpecialEffect(
                String.valueOf(type) + "_" + String.valueOf(MagicBase.in_use_magic[id - 1]), 1, 400, 400);
        DrawMonster.monsterPanel.add(effect);
        effect.setOpaque(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                DrawMonster.monsterPanel.remove(effect);
                DrawMonster.monsterPanel.validate();
                DrawMonster.monsterPanel.repaint();
            }
        }, 500);
    }

    public static void drawPlayerEffect(int type, int id) {
        String name;
        if (id == 0) {
            name = String.valueOf(BattleSkillBase.in_use_skill[id - 1]);
        } else {
            name = String.valueOf(MagicBase.in_use_magic[id - 1]);
        }

        DrawPlayerUP effect2 = new DrawPlayerUP(String.valueOf(type) + "_" + name, drawPlayerType);
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
        btn_1 = new CreateButton(in_use_name[0]);

        btn_2 = new CreateButton(in_use_name[1]);
        btn_3 = new CreateButton(in_use_name[2]);
        btn_4 = new CreateButton(in_use_name[3]);
        buttonCommonSetting();
    }

    static void initButton() {
        btn_1 = new CreateButton("  戰技  ");
        btn_2 = new CreateButton("  魔法  ");
        btn_3 = new CreateButton("  寶具  ");
        btn_4 = new CreateButton("  藥物  ");
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
                        castMagic(2);
                        playerPause();
                    }
                });
                btn_3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        castMagic(3);
                        playerPause();
                    }
                });
                btn_4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        castMagic(4);
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

        switch (opt) {
            case 0:
                if (!Item.in_use_isAttack[0]) {
                    JOptionPane.showMessageDialog(null, "此為被動式寶具!");
                } else {
                    Item.getAbility(Item.in_use_item[0]);
                }
                break;
            case 1:
                if (!Item.in_use_isAttack[1]) {
                    JOptionPane.showMessageDialog(null, "此為被動式寶具!");
                } else {
                    Item.getAbility(Item.in_use_item[1]);
                }
                break;
            case 2:
                if (!Item.in_use_isAttack[2]) {
                    JOptionPane.showMessageDialog(null, "此為被動式寶具!");
                } else {
                    Item.getAbility(Item.in_use_item[2]);
                }
                break;

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
        g.drawImage(ResReader.battleBase, 0, 0, getWidth(), getHeight(), this);
    }

    public void drawItem(Graphics g, int id) {
        Image image = new ImageIcon(ResReader.loader.getResource("res/storage/item/" + id+"_1"+".gif")).getImage();
        g.drawImage(image, 0, 0, 1200, 800, this);
        validate();
        repaint();
    }
}