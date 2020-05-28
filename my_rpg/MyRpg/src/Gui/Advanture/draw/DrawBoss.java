package Gui.Advanture.draw;

import javax.swing.JPanel;

import Basic.ResReader;
import monster.Boss.Boss_1;
import phase.BattlePhase;
import phase.BattleTemp;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class DrawBoss extends JPanel {
    /*
     * 
     * 畫怪物
     * 
     */

    String bossname;
    int y = 400;
    int x = 0;
    public Image image;
    public static JPanel monsterPanel;

    public DrawBoss(String name) {
        super();
        bossname = name;
    image = new ImageIcon(ResReader.path + "res/battlePanel/boss/"+ bossname +".png").getImage();
        //image = new ImageIcon(ResReader.path +"res/battlePanel/boss/b_1.png").getImage();
        monsterPanel= new JPanel();
        monsterPanel.setPreferredSize(new Dimension(800,500));
        monsterPanel.setOpaque(false);
        add(monsterPanel);  
    }

	private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoss(g);
        drawBlood(g);
        if(Boss_1.isSecond)
        {
            if(y>160)
            {
                y --; 
            }
            drawSecondBoss(g);
        }
    }
    
    private void drawSecondBoss(Graphics g) {
        Image secondBoss = new ImageIcon(ResReader.path +"res/battlePanel/boss/b_1.png").getImage();
        Image thirdBoss = new ImageIcon(ResReader.path +"res/battlePanel/boss/b_2.png").getImage();
        Image fire = new ImageIcon(ResReader.path+"res/battlePanel/boss/4_3.gif")
        .getImage();
        g.drawImage(secondBoss, 150,y,180,220, this);
        g.drawImage(fire,100, 150 , 300, getHeight()/2, this);
        g.drawImage(thirdBoss, 600,y,180,220, this);
        g.drawImage(fire,600, 150 , 300, getHeight()/2, this);
    }

    protected void drawBoss(Graphics g)
    {
        g.drawImage(image,360,0,247,419, this);
    }


    private void drawBlood(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.setColor(Color.CYAN.darker());  
        g.drawString("HP：", 240, 465);
        g.setColor(Color.BLACK);  
        g.drawRect(300,450,500,10);  
        g.setColor(Color.RED);  
        double hp = 500 * ((double)BattleTemp.M_HP/(150));
        g.fillRect(300,450, (int) Math.round(hp), 10);
    }

}