package Gui.Advanture;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.Helper.MusicHelper;

import java.awt.event.*;
import java.util.Random;

public class Road extends JPanel {
    /*
     *
     * 這邊是做隨機選擇發生的是以及檢查劇情的地方
     * 
     * 事件 - 30% 寶物 - 10% 怪物 - 60%
     * 
     * 如果累積走了 100 步 - 抵達營火
     *
     * 
     */
    private static final long serialVersionUID = 1L;
    public static int totalStep = 0;
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
                totalStep += 1 ;
                if(totalStep >= 1)
                {    
                    AdvantureBackground.showCampFire();
                    return;
                }
                if (Player.TP > 1) {
                    Thread playMusic = new MusicHelper("player/step.wav");
                    playMusic.start();
                    Player.TP -= 1;
                    BattleSidePanel.travelPointLabel.setText("體力值：" + Player.TP);
                    AdvantureBackground.showRandom(getRandom());
                } else {
                    Player.TP -= 1;
                    BattleSidePanel.travelPointLabel.setText("體力值：" + Player.TP);
                    AdvantureBackground.showCampus();
                }
            }
        });
    }

    private static int getRandom() {
        /*
        * 
        * 1,2,3  - 不同事件
        * 4      - 寶藏
        * 5      - 怪獸
        *
        */
        Integer[] rad = {1,2,3,4,5,5,5,5,5,5};       
        Random r = new Random();
        int index = r.nextInt(10);
        return rad[index];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.road, 0, 0, getWidth(), getHeight(), this);
    }

}