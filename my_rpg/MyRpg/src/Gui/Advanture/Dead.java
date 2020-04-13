package Gui.Advanture;

import javax.swing.*;

import Basic.Player;
import Gui.Gui;

import java.awt.*;
import java.awt.event.*;

public class Dead extends JPanel{
    JButton btn_move;
    JPanel box;

    public Dead() {
        super();
        this.setFocusable(true);
        box = new JPanel();
        btn_move = new JButton("  重來  ");
        btn_move.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_move);
        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 65, 20));
        this.add(box, BorderLayout.SOUTH);

        btn_move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                Gui.reset();
                Player.COIN = 0;
                Player.EXP = 0;
            }
        });

    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGate(g);
    }

    public void drawGate(Graphics g) {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/you_died.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}