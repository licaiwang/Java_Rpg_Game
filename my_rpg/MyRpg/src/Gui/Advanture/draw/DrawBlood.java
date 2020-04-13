package Gui.Advanture.draw;

import javax.swing.JPanel;

import phase.BattlePhase;


import java.awt.*;
public class DrawBlood extends JPanel {
    public static Boolean isBattle = false;
    public DrawBlood() {
        super();
    }
	private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if(isBattle)
        {
            subBlood(g, BattlePhase.to_M_damage);
        }else{
            drawBlood(g);
        }
    } 
    private void drawBlood(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.drawString("HP：", 20, 45);
        g.drawRect(80,30,250,10);  
        g.setColor(Color.RED);  
        g.fillRect(80,30,250 ,10);
    }
    private void subBlood(Graphics g, int amount) {
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.drawString("HP：", 20, 45);
        g.drawRect(80,30,amount,10);  
        g.setColor(Color.RED);  
        g.fillRect(80,30,amount ,10);
    }
}
