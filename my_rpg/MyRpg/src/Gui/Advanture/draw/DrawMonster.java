package Gui.Advanture.draw;

import javax.swing.JPanel;

import Basic.ResReader;
import phase.BattlePhase;
import javax.swing.*;
import java.awt.*;

public class DrawMonster extends JPanel{
    /*

    畫怪物
    
    */

    String monsterName;
    int y=0;
    int x=0;
    public Boolean isAtacking = false;
    public  Image image;
    public static JPanel monsterPanel;
    public DrawMonster(String name) {
        super();
        monsterName = name;
        image = new ImageIcon(ResReader.path + "res/battlePanel/monster/"+ monsterName +".png").getImage();
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
                if(isAtacking)
                {

                    if(y > - 100)
                    {
                        y -- ;                   
                    }
                    if(x < 100)
                    {
                        x ++ ;
                    }
                    if(x == 20 &&  y == -20) 
                    {
                        isAtacking = false;
                        x = 0;
                        y = 0;
                    }
                    drawMonsterAtk(g);              
                }
                else{
                    drawMonster(g);
                }         
                break;
        } 

    }
    
    private void drawMonsterAtk(Graphics g) {
        g.drawImage(image, x,y, getWidth(), getHeight(), this);
        validate();
        repaint();
    }

    protected void drawMonster(Graphics g)
    {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    protected void drawMonsterDead(Graphics g, int y)
    {
        g.drawImage(image, 0,y, getWidth(), getHeight(), this);
        validate();
        repaint();
    }
}