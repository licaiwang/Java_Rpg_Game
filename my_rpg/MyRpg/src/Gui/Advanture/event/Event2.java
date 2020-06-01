package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.CreateButton;

import java.awt.event.*;
import java.util.Random;
   /**
     * 
     * 
     * @author  Rorschach
     * 
     *  事件二  小狗帶路
     * 
     * 
     *  帶錯路 玩家幸運下降 5 - 50 %
     * 
     *  帶對路 玩家體力上升五，幸運上升 5 - 50 %
     * 
     **
     */
public class Event2 extends JPanel {

    JButton btn_hand;
    JButton btn_ignore;
    JPanel box;
    boolean Isclick;

    public Event2() {
        super();
        box = new JPanel();

        btn_hand = new CreateButton("  跟隨 ");
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
                AdvantureBackground.showRoad();
                Isclick = false;
                btn_ignore.setText(" 無視 ");
                box.add(btn_hand);
                box.add(btn_ignore);
                box.repaint();
                validate();
                repaint();
            }
        });
        btn_hand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Isclick = true;
                box.remove(btn_hand);
                btn_ignore.setText("好");
                box.repaint();
                validate();
                repaint();
            }
        });

    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!Isclick) {
            drawEvent(g);
        } else {
            drawConsequence(g);
        }
    }

    public void drawEvent(Graphics g) {
        g.drawImage(ResReader.event1_2, 0, 0, getWidth(), getHeight(), this);
    }

    public void drawConsequence(Graphics g) {
        Random r = new Random();
        int id = (r.nextInt(2)+1);
        switch (id) {
            case 1:
                Player.LUCK -= 5;
                g.drawImage(ResReader.event1_2_1, 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                Player.TP += 5;
                Player.LUCK += 5;
                BattleSidePanel.resetTp();
                g.drawImage(ResReader.event1_2_2, 0, 0, getWidth(), getHeight(), this);
                break;
        }
        
    }
}