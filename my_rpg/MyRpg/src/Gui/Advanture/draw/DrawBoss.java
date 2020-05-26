package Gui.Advanture.draw;

import javax.swing.JPanel;

import Basic.ResReader;
import phase.BattlePhase;
import javax.swing.*;
import java.awt.*;

public class DrawBoss extends JPanel {
    /*
     * 
     * 畫怪物
     * 
     */

    String bossname;
    int y = 0;
    int x = 0;
    public Boolean isAtacking = false;
    public Image image;
    public static JPanel monsterPanel;

    public DrawBoss(String name) {
        super();
        bossname = name;
        image = new ImageIcon(ResReader.path + "res/battlePanel/boss/"+ bossname +".png").getImage();
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
    }
    
    private void drawBossAtk(Graphics g) {
        g.drawImage(image, x,y, getWidth(), getHeight(), this);
        validate();
        repaint();
    }

    protected void drawBoss(Graphics g)
    {
        g.drawImage(image,300,0,500,419, this);
    }


    private void drawBlood(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.setColor(Color.CYAN.darker());  
        g.drawString("HP：", 240, 465);
        g.setColor(Color.BLACK);  
        g.drawRect(300,450,500,10);  
        g.setColor(Color.RED);  
        g.fillRect(300,450,500,10);
    }
    private void subBlood(Graphics g, int amount) {
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.drawString("HP：", 20, 45);
        g.drawRect(80,30,amount,10);  
        g.setColor(Color.RED);  
        g.fillRect(80,30,amount ,10);
    }


}