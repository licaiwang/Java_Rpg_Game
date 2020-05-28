package Gui.Advanture;

import javax.swing.*;
import Basic.Player;
import Basic.ResReader;
import Gui.Gui;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Dead extends JPanel {
    private static final long serialVersionUID = 1L;
    JButton btn_move;
    JPanel box;
    public Dead() {
        super();
        this.setFocusable(true);
        box = new JPanel();
        btn_move = new JButton("  重新來過  ");
        btn_move.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_move);
        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 65, 20));
        this.add(box, BorderLayout.SOUTH);

        btn_move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {	
                    Gui.reset();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Player.COIN = 0;
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.youDied, 0, 0, getWidth(), getHeight(), this);
    }
}