package Gui.Advanture.draw;

import javax.swing.JPanel;

import phase.BattlePhase;

import javax.swing.*;
import java.awt.*;

public class DrawMonster extends JPanel{
    /*

    畫怪物
    
    */

    String monsterName;
    int y=0;
    public static JPanel monsterPanel;
    public DrawMonster(String name) {
        super();
        monsterName = name;
        monsterPanel= new JPanel();
        monsterPanel.setPreferredSize(new Dimension(400,400));
        monsterPanel.setOpaque(false);
        add(monsterPanel);  
    }

	private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (BattlePhase.checkMonsterDeadOPlayer()) {
            case 0:
                if(y<300)
                {
                    y++;
                    drawMonsterDead(g,y);
                }
                break;
            default:
                drawMonster(g);
                break;
        } 
    }
    protected void drawMonster(Graphics g)
    {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/monster/"+ monsterName +".png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    protected void drawMonsterDead(Graphics g, int y)
    {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/monster/"+ monsterName +".png").getImage();
        g.drawImage(image, 0,y, getWidth(), getHeight(), this);
        validate();
        repaint();
    }
}