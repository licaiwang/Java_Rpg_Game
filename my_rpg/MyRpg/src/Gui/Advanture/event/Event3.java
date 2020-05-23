package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import Gui.Town.School;

import java.awt.event.*;

public class Event3 extends JPanel {
    /*
     * 
     * 受虛空吸引的人
     * 
     */
    JButton btn_hand;
    JButton btn_ignore;
    JPanel box;
    boolean Isclick;
    Integer helpCount = 0;

    public Event3() {
        super();
        box = new JPanel();

        btn_hand = new JButton("  救援 ");
        btn_hand.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_hand);
        btn_ignore = new JButton("  無視 ");
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
                helpCount += 1;
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

        g.drawImage(ResReader.event1_3, 0, 0, getWidth(), getHeight(), this);
    }

    public void drawConsequence(Graphics g) {

        int id = 1;
        if (helpCount == 5) {
            id = 2;
            helpCount = 0;
        }
        switch (id) {
            case 1:
                break;
            case 2:
                Player.memoryCd += 1;
                School.resetCdAmount();
                break;
        }
        g.drawImage(ResReader.event1_3_, 0, 0, getWidth(), getHeight(), this);
    }
}