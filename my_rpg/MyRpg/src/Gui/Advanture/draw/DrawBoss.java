package Gui.Advanture.draw;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Basic.ResReader;
import monster.Boss.Boss_1;

import phase.BattleTemp;

import java.awt.*;

    /**
     * 
     * 
     * @author  Rorschach
     * 
     * 畫 Boss
     * 
     **
     */
public class DrawBoss extends JPanel {
    int y = 400;
    int x = 0;
    public static JPanel monsterPanel; 

    public DrawBoss() {
        super();
       
        monsterPanel= new JPanel();
        monsterPanel.setPreferredSize(new Dimension(800,500));
        monsterPanel.setOpaque(false);
        add(monsterPanel);  
    }

	private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!Boss_1.isdead)
        {
            drawBoss(g);
            drawBlood(g);
        }

        if(Boss_1.isdead)
        {
            Boss_1.isSecond = false;
            if(y < 800)
            {
                y ++; 
            }
            drawBossdead(g);
        }
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

        Image secondBoss = new ImageIcon(ResReader.Current_Dic + ("/res/boss/b_1.png")).getImage();
        Image thirdBoss = new ImageIcon(ResReader.Current_Dic + ("/res/boss/b_2.png")).getImage();
        Image fire = new ImageIcon(ResReader.Current_Dic + ("/res/boss/4_3.gif")).getImage();
        g.drawImage(secondBoss, 150,y,180,220, this);
        g.drawImage(fire,100, 150 , 300, getHeight()/2, this);
        g.drawImage(thirdBoss, 600,y,180,220, this);
        g.drawImage(fire,600, 150 , 300, getHeight()/2, this);
    }

    protected void drawBoss(Graphics g)
    {
        Image boss_1 = new ImageIcon(ResReader.Current_Dic+("/res/boss/boss_1.png")).getImage();
        g.drawImage(boss_1,360,0,247,419, this);
    }
    protected void drawBossdead(Graphics g)
    {
        Image boss_1 = new ImageIcon(ResReader.Current_Dic+("/res/boss/boss_1.png")).getImage();
        g.drawImage(boss_1,360,y-160,247,419, this);
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