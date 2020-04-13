package Gui;

import javax.swing.*;


import Gui.MainGUI.MyFrame;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

public class Inn extends Hall {
 
    public Inn(final MyFrame frame, Inn inn) {
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
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/avatar.jpg").getImage();
        g.drawImage(image, getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, this);
    }


}
