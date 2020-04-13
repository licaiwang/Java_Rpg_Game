package Gui.Advanture;

import java.awt.*;
import javax.swing.*;

import Gui.Gui;

import java.awt.event.*;
import java.util.Random;

public class Road extends JPanel {
    /*
    *
    * 這邊是做隨機選擇發生的是以及檢查劇情的地方
    *  
    *  事件 - 30%   寶物  - 10%   怪物 - 60%
    *
    */
    JButton btn_move;
    JPanel box;
    public Road() {
        super();
        setVisible(true);
        setFocusable(true);
        box = new JPanel();
        btn_move = new JButton(" 前進 ");
        btn_move.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_move);
        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 65, 20));
        this.add(box, BorderLayout.SOUTH);
        btn_move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Gui.player.TP > 1) {
                    Gui.player.TP -= 1;
                    BattleSidePanel.travelPointLabel.setText("體力值：" + Gui.player.TP);
                    AdvantureBackground.showRandom(getRandom());
                } else {
                    Gui.player.TP -= 1;
                    BattleSidePanel.travelPointLabel.setText("體力值：" + Gui.player.TP);
                    AdvantureBackground.showCampus();
                }
            }
        });
    }

    private static final long serialVersionUID = 1L;



    private static int getRandom() {
        Integer[] rad = {1,2,3,4,5,5,5,5,5,5};
        Random r = new Random();
        int index = r.nextInt(10);
        return rad[index];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEvent(g);
    }

    public void drawEvent(Graphics g) {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/road.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}