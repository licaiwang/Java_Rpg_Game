package Gui;

import javax.swing.*;
import java.awt.*;

public class Avatar extends JPanel {

    public Avatar() {
        super();
    }

	private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        drawAvatar(g);
    }
    protected void drawAvatar(Graphics g)
    {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/avatar.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }


}
