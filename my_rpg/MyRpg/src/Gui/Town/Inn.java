package Gui.Town;

import javax.swing.*;
import java.awt.*;

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
        final Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/sleep.gif").getImage();
        g.drawImage(image, getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, this);
        String msg = "體力已補充完畢！";
        g.setColor(Color.WHITE);
        g.setFont(new Font(msg, Font.BOLD, 20));
        g.drawString(msg, getWidth()/3+getWidth()/12, getHeight()/8);
    }


}
