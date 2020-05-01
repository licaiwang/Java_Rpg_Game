package Gui.Advanture.event;

import Gui.Gui;
import Gui.Advanture.AdvantureBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BackTown extends JPanel {
    JButton btn_move;
    JPanel box;

    public BackTown() {
        super();
        this.setFocusable(true);
        box = new JPanel();
        btn_move = new JButton("  傳送回城  ");
        btn_move.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_move);
        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 20));
        this.add(box, BorderLayout.SOUTH);

        btn_move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdvantureBackground.advPanel.removeAll();
                Gui.showTownSidePanel();
				Gui.resetPannel(6);
            }
        });

    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCompass(g);
    }

    public void drawCompass(Graphics g) {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/back.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}