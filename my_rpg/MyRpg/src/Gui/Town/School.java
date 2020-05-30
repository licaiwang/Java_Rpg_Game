package Gui.Town;

import Gui.Helper.MusicHelper;
import Magic.MagicBase;
import Skill.BattleSkillBase;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Basic.Player;
import Basic.ResReader;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.miginfocom.swing.MigLayout;
import java.awt.*;

public class School extends JPanel {
    static JLabel shardLabel;
    static JLabel cDLabel;
    static JPanel SchoolLabel;
    static List<Double> hasget = new ArrayList<Double>();

    public School() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        hasget.add(0.1);
        hasget.add(1.1);
        hasget.add(2.1);
        hasget.add(3.1);
        // Middle
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new MigLayout("wrap 2", "270[]20[]", "150[]20[]"));
        gridPanel.setOpaque(false);
        JButton btn_1 = new JButton("  隨機戰技 X  1 記憶光碟  ");
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_2 = new JButton("  基礎戰技 X 10 記憶碎片 ");
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_3 = new JButton("  進階戰技 X 30 記憶碎片  ");
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_4 = new JButton("  大師戰技 X 50 記憶碎片  ");
        btn_4.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_5 = new JButton("  隨機魔法 X  1 記憶光碟  ");
        btn_5.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_6 = new JButton("  基礎魔法 X 10 記憶碎片  ");
        btn_6.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_7 = new JButton("  進階魔法 X 30 記憶碎片  ");
        btn_7.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_8 = new JButton("  冠位魔法 X 50 記憶碎片  ");
        btn_8.setMargin(new Insets(10, 10, 10, 10));
        btn_1.setPreferredSize(new Dimension(150, 30));
        btn_2.setPreferredSize(new Dimension(150, 30));
        btn_3.setPreferredSize(new Dimension(150, 30));
        btn_4.setPreferredSize(new Dimension(150, 30));
        btn_5.setPreferredSize(new Dimension(150, 30));
        btn_6.setPreferredSize(new Dimension(150, 30));
        btn_7.setPreferredSize(new Dimension(150, 30));
        btn_8.setPreferredSize(new Dimension(150, 30));
        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetCdLabel(1)) {
                    gainRandomSkill();
                }
            }
        });
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (resetshardLabel(10)) {
                    gainBasicSkill();
                }

            }
        });
        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetshardLabel(30)) {
                    gainAdvanceSkill();
                }

            }
        });
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetshardLabel(50)) {
                    gainMasterSkill();
                }
            }
        });
        btn_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(resetCdLabel(1)){
                    gainRandomMagic();
                }
            }
        });
        btn_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetshardLabel(10)) {
                    gainBasicMagic();
                }
            }
        });

        btn_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetshardLabel(30)) {
                    gainAdvanceMagic();
                }

            }
        });

        btn_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetshardLabel(50)) {
                    gainMasterMagic();
                }
            }
        });

        gridPanel.add(btn_1);
        gridPanel.add(btn_5);
        gridPanel.add(btn_2);
        gridPanel.add(btn_6);
        gridPanel.add(btn_3);
        gridPanel.add(btn_7);
        gridPanel.add(btn_4);
        gridPanel.add(btn_8);

        // Bottom
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setOpaque(false);
        shardLabel = new JLabel("持有的記憶碎片:  " + Player.memoryShard);
        shardLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        shardLabel.setOpaque(true);
        shardLabel.setBorder(new LineBorder(Color.black, 3));
        cDLabel = new JLabel("持有的記憶光碟:    " + Player.memoryCd);
        cDLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        cDLabel.setOpaque(true);
        cDLabel.setBorder(new LineBorder(Color.black, 3));
      

        boxPanel.add(shardLabel);
        boxPanel.add(cDLabel);

        this.add(gridPanel);
        this.add(boxPanel);
    }

 
    boolean resetshardLabel(Integer cost) {
        if (Player.memoryShard >= 10) {
            Player.memoryShard -= cost;
            shardLabel.setText("持有的記憶碎片:  " + Player.memoryShard);
            Thread playMusic = new MusicHelper("getSkill.wav");
            playMusic.start();
            return true;
        } else {
            shardLabel.setText("記憶碎片不足!");
            return false;
        }
    }

    public static void resetshardAmount() {
        shardLabel.setText("持有的記憶碎片:  " + Player.memoryShard);
    }

    boolean resetCdLabel(Integer cost) {
        if (Player.memoryCd > 0) {
            Player.memoryCd -= cost;
            cDLabel.setText("持有的記憶光碟:  " + Player.memoryCd);
            Thread playMusic = new MusicHelper("getSkill.wav");
            playMusic.start();
            return true;
        } else {
            cDLabel.setText("記憶光碟不足!");
            return false;
        }
    }

    public static void resetCdAmount() {
        cDLabel.setText("持有的記憶光碟:  " + Player.memoryCd);
    }


    public static void gainRandomSkill() {
 
        Random r = new Random();
        int index = r.nextInt(20);
        if (index == 20) {
            gainMasterSkill();
        } else if (index >= 14 && index < 20) {
            gainAdvanceSkill();
        } else {
            gainBasicSkill();
        }
    }

    private static void gainBasicSkill() {
        Random r = new Random();
        int index = r.nextInt(BattleSkillBase.Basic_skill_name.size());
        if (!isGain(index + 0.1)) {
            hasget.add(index + 0.1);
            String gainSkillname = BattleSkillBase.Basic_skill_name.get(index);
            BattleSkillBase.unlockSkill(gainSkillname);
            JOptionPane.showMessageDialog(null, "獲得了基礎技能  " + gainSkillname);
        } else {
            JOptionPane.showMessageDialog(null, "獲得了重複的技能，已轉換為 100 NT");
            Player.COIN += 100;
        }

    }

    private static void gainAdvanceSkill() {
        Random r = new Random();
        int index = r.nextInt(BattleSkillBase.Advance_skill_name.size());
        if (!isGain(index + 0.2)) {
            hasget.add(index + 0.2);
            String gainSkillname = BattleSkillBase.Advance_skill_name.get(index);
            BattleSkillBase.unlockSkill(gainSkillname);

            JLabel label = new JLabel("獲得了進階技能  " + gainSkillname);
            label.setForeground(Color.BLUE);

            JOptionPane.showMessageDialog(null, label);
        } else {
            JOptionPane.showMessageDialog(null, "獲得了重複的技能，已轉換為 250 NT");
            Player.COIN += 250;
        }
    }

    private static void gainMasterSkill() {
        Random r = new Random();
        int index = r.nextInt(BattleSkillBase.Master_skill_name.size());
        if (!isGain(index + 0.3)) {
            hasget.add(index + 0.3);
            String gainSkillname = BattleSkillBase.Master_skill_name.get(index);
            BattleSkillBase.unlockSkill(gainSkillname);
            JLabel label = new JLabel("獲得了大師技能  " + gainSkillname);
            label.setForeground(Color.RED);
            JOptionPane.showMessageDialog(null, label);
        } else {
            JOptionPane.showMessageDialog(null, "獲得了重複的技能，已轉換為 500 NT");
            Player.COIN += 500;
        }
    }


    protected void gainRandomMagic() {
        Random r = new Random();
        int index = r.nextInt(20);
        if (index == 20) {
            gainMasterMagic();
        } else if (index >= 14 && index < 20) {
            gainAdvanceMagic();
        } else {
            gainBasicMagic();
        }
    }


    protected void gainBasicMagic() {
        Random r = new Random();
        int index = r.nextInt(MagicBase.Basic_Magic_name.size());
        if (!isGain(index + 0.4)) {
            hasget.add(index + 0.4);
            String gainMagicname = MagicBase.Basic_Magic_name.get(index);
            MagicBase.unlockMagic(gainMagicname);
            JOptionPane.showMessageDialog(null, "獲得了基礎魔法  " + gainMagicname);
        } else {
            JOptionPane.showMessageDialog(null, "獲得了重複的魔法，已轉換為 100 NT");
            Player.COIN += 250;
        }
    }

  
    
    protected void gainAdvanceMagic() {
        Random r = new Random();
        int index = r.nextInt(MagicBase.Advance_Magic_name.size());
        if (!isGain(index + 0.5)) {
            hasget.add(index + 0.5);
            String gainMagicname = MagicBase.Advance_Magic_name.get(index);
            MagicBase.unlockMagic(gainMagicname);
            JLabel label = new JLabel("獲得了進階技能  " +  gainMagicname);
            label.setForeground(Color.BLUE);
            JOptionPane.showMessageDialog(null, label);
        } else {
            JOptionPane.showMessageDialog(null, "獲得了重複的魔法，已轉換為 250 NT");
            Player.COIN += 500;
        }
    }


    protected void gainMasterMagic() {
        Random r = new Random();
        int index = r.nextInt(MagicBase.Master_Magic_name.size());
        if (!isGain(index + 0.6)) {
            hasget.add(index + 0.6);
            String gainMagicname = MagicBase.Master_Magic_name.get(index);
            MagicBase.unlockMagic(gainMagicname);
            JLabel label = new JLabel("獲得了冠位魔法  " +  gainMagicname);
            label.setForeground(Color.RED);
            JOptionPane.showMessageDialog(null, label);
        } else {
            JOptionPane.showMessageDialog(null, "獲得了重複的魔法，已轉換為 500 NT");
            Player.COIN += 100;
        }
    }

    private static Boolean isGain(double gain_index) {
        for (int i = 0; i < hasget.size(); i++) {
            if (gain_index == hasget.get(i)) {
                return true;
            }
        }
        return false;
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.school, 0, 0, getWidth(), getHeight(), this);
    }

}
