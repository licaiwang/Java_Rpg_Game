package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.CreateButton;

import java.awt.event.*;


public class CampFire extends JPanel{
    private static final long serialVersionUID = 1L;
    JButton btn_rest;
    JButton btn_move;
    JPanel box;
    public CampFire() {
        super();
        box = new JPanel();
        btn_rest = new CreateButton("  休息  ");
        btn_rest.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_rest);

        btn_move = new CreateButton("  往前  ");
        btn_move.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_move);

        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 20));
        this.add(box, BorderLayout.SOUTH);

        btn_rest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.HP = Player.Max_HP;
                Player.MP = Player.Max_MP;     
                BattleSidePanel.resetHp();
                BattleSidePanel.resetMp();
            }
        });
        btn_move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdvantureBackground.showFortress();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.campFire, 0, 0, getWidth(), getHeight(), this);
    }
}