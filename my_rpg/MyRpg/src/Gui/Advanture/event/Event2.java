package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Gui.Advanture.AdvantureBackground;

import java.awt.event.*;

public class Event2 extends JPanel {
    /*
     * 
     * 狗狗引路
     * 
     */
    JButton btn_hand;
    JButton btn_ignore;
    JPanel box;

    public Event2() {
        super();
        box = new JPanel();

        btn_hand = new JButton("  跟隨 ");
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
            }
        });

    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEvent(g);
    }

    public void drawEvent(Graphics g) {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/event1_2.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}