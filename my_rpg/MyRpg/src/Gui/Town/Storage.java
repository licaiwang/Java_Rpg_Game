package Gui.Town;

import javax.swing.*;
import java.awt.*;

public class Storage extends JLabel{

    public Storage() {
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
    }


}
