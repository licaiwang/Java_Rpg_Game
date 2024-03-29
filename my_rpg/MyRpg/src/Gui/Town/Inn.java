package Gui.Town;

import javax.swing.*;

import Basic.ResReader;

import java.awt.*;
   /** 
     * 
     * @author  Rorschach
     * 
     *  
     * 畫旅店
     *  
     * 
     **
     */
public class Inn extends JPanel{
 
    public Inn() {
        super();
    }

	private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g); 
        drawSleep(g);
       
    }
    protected void drawSleep(final Graphics g)
    {
        g.drawImage(ResReader.inn, getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, this);
        String msg = "體力已補充完畢！";
        g.setColor(Color.WHITE);
        g.setFont(new Font(msg, Font.BOLD, 20));
        g.drawString(msg, getWidth()/3+getWidth()/12, getHeight()/8);
    }

}
