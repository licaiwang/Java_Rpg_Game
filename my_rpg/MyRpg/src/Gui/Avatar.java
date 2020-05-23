package Gui;

import javax.swing.*;

import Basic.ResReader;

import java.awt.*;

public class Avatar extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public Avatar() {
        super();
        setOpaque(false);
        setPreferredSize(new Dimension(140,140));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(ResReader.avatar, 10, 10, getWidth()-20, getHeight()-20, this);
        validate();
        repaint();
    }
}
