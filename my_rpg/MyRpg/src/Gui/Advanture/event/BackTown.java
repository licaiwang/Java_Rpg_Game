package Gui.Advanture.event;

import Gui.Gui;
import Gui.Advanture.AdvantureBackground;

import javax.swing.*;

import Basic.ResReader;

import java.awt.*;
import java.awt.event.*;

public class BackTown extends JPanel {
    private static final long serialVersionUID = 1L;
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
                removeAll();
				setVisible(false);
				AdvantureBackground.advPanel.setVisible(false);
				Gui.mContainer.remove(AdvantureBackground.advPanel);
				Gui.showTownSidePanel();
				Gui.resetPannel(6);
            }
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.compass, 0, 0, getWidth(), getHeight(), this);
    }
}