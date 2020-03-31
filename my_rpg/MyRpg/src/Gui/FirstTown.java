package Gui;

import javax.swing.*;
import java.awt.*;

public class FirstTown extends JPanel {
    private static final long serialVersionUID = 1L;
    public FirstTown() {
        super();
        setFocusable(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
    }

    private void drawBackground(Graphics g) {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/first_town.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}