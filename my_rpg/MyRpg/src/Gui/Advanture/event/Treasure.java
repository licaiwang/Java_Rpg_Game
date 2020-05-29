package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Basic.Player;
import Basic.ResReader;
import Gui.BottomPanel;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.CreateButton;
import Gui.Helper.MusicHelper;
import Gui.Town.School;

import java.awt.event.*;
import java.util.Random;

public class Treasure extends JPanel {
    JButton btn_hand ;
    JButton btn_ignore ;
    JPanel  box;
    boolean Isclick;
    Integer[] money = {250,500,750,1000};
    public Treasure()
    {
    super();
    box = new JPanel();
    createButton();
    box.add(btn_hand);
    box.add(btn_ignore);
    box.setOpaque(false);
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createEmptyBorder(20,0,80,0));
    this.add(box,BorderLayout.SOUTH);
    }
    private static final long serialVersionUID = 1L;
   
    private static int getRandom() {
        /*
        *
        * 1- 隨機獲得 250 , 500 , 750 , 1000 元 - 40%
        * 2- 浪費 3 體力 - 40%
        * 3- 獲得 5 記憶碎片 - 20%
        *
        */
        Integer[] rad = {1,1,1,1,2,2,2,2,3,3};
        Random r = new Random();
        int index = r.nextInt(10);
        return rad[index];
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if(!Isclick)
        {
             drawEvent(g);
        }else{
            drawConsequence(g);
        }
       
    }
    public void drawEvent(Graphics g)
    {
        g.drawImage(ResReader.treasure,0, 0 , getWidth(), getHeight(), this);
    }
    public void drawConsequence(Graphics g)
    {
        int id = getRandom();
        switch (id) {
            case 1:
                Random r = new Random();
                int getMoney = r.nextInt(3);
                Player.COIN += money[getMoney];
                BattleSidePanel.resetCoin();
                g.drawImage(ResReader.treasure_1,0, 0 , getWidth(), getHeight(), this);
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
                break;
            case 2:
                Player.TP -= 3;
                BattleSidePanel.resetTp();
                g.drawImage(ResReader.treasure_2,0, 0 , getWidth(), getHeight(), this);
                break;
            case 3:
                Player.memoryShard += 5;
                School.resetshardAmount();
                g.drawImage(ResReader.treasure_3,0, 0 , getWidth(), getHeight(), this);
                break;
        }
       
    }

public void createButton()
{

    btn_hand = new CreateButton("  打開 ");
    btn_hand .setMargin(new Insets(10, 10, 10, 10));
    btn_ignore = new CreateButton("  無視 ");
    btn_ignore.setMargin(new Insets(10, 10, 10, 10));
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

}