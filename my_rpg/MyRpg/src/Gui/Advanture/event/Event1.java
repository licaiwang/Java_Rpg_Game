package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.CreateButton;
import Gui.Town.School;
import Gui.Town.Storage;
import item.Item;

import java.awt.event.*;
import java.util.Random;

public class Event1 extends JPanel {
    /*
     * 
     * 德魯伊向玩家伸手
     * 
     */

    JButton btn_hand;
    JButton btn_ignore;
    JPanel box;
    boolean Isclick = false;
    boolean SecIsclick = false;
    Integer[] shard = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int id;

    public Event1() {
        super();

        box = new JPanel();
        btn_hand = new CreateButton("  伸手 ");
        btn_hand.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_hand);

        btn_ignore = new CreateButton("  無視 ");
        btn_ignore.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_ignore);

        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 20));
        this.add(box, BorderLayout.SOUTH);

        btn_ignore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id == 2 && Isclick)
                {
                    SecIsclick = true;
                    id = 0;
                    btn_ignore.setText(" 自認倒楣 "); 
                    box.repaint();
                    validate();
                    repaint();
                }
                else{
                    AdvantureBackground.showRoad();
                    Isclick = false;
                    SecIsclick = false;
                    btn_ignore.setText(" 無視 ");
                    box.add(btn_hand);
                    box.add(btn_ignore);
                    box.repaint();
                    validate();
                    repaint();
                }
                if(id == 3)
                {
                    // 解鎖死告天使
                    Item.unlock(6);
                    Storage.resetItem();
                    JOptionPane.showMessageDialog(null, " 山中的老人給了你 C 級寶具 - 死告天使!");
                }          
            }
        });

        btn_hand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Isclick = true;
                String content[] = { "收下", "掙脫這瘋子", "調查鐘聲" };
                btn_ignore.setText(content[id - 1]);
                box.remove(btn_hand);
                box.validate();
                box.repaint();
                validate();
                repaint();
            }
        });

    }

    private static final long serialVersionUID = 1L;

    private static int getRandom() {
        /*
         *
         * 1 - 獲得 1,2,3,4,5,6,7,8,9,10 記憶碎片 - 50 % 
         * 2 - 損失持有金幣的 10 %, 20% 或損失 5 點生命 - 40 %
         * 3 - 遇見山中老人 - 10%
         * 
         */
        Integer[] rad = { 1, 1, 1, 1, 1, 3, 3, 3, 3, 3 };
        Random r = new Random();
        int index = r.nextInt(10);
        return rad[index];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!Isclick && !SecIsclick) {
            drawEvent(g);
            id = getRandom();
        }
        if (Isclick && !SecIsclick) {
            drawConsequence(g);
        }
        if (Isclick && SecIsclick) {
            drawSecondConsequence(g);
        }

    }

    public void drawEvent(Graphics g) {
        g.drawImage(ResReader.event1_1, 0, 0, getWidth(), getHeight(), this);
    }

    public void drawConsequence(Graphics g) {
        switch (id) {
            case 1:
                Random r = new Random();
                int getShard = r.nextInt(10);
                Player.memoryShard += shard[getShard];
                School.resetshardAmount();
                g.drawImage(ResReader.event1_1_1, 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                g.drawImage(ResReader.event1_1_2, 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                g.drawImage(ResReader.event1_1_3, 0, 0, getWidth(), getHeight(), this);
                break;
        }
        
    }

    public void drawSecondConsequence(Graphics g) {
        Random r = new Random();
        int id = r.nextInt(2);
        switch (id) {
            case 0:
                Player.COIN /= 10;
                Player.COIN *= 9;
                BattleSidePanel.resetCoin();
                g.drawImage(ResReader.event1_1_2_1, 0, 0, getWidth(), getHeight(), this);
                break;
            case 1:
                Player.HP -= 5;
                BattleSidePanel.resetHp();
                if (Player.HP <= 0) {
                    AdvantureBackground.showDead();
                }
                g.drawImage(ResReader.event1_1_2_2, 0, 0, getWidth(), getHeight(), this);
                break;
        }
       
    }

}