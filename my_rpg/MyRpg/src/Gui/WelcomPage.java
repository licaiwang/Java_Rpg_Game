package Gui;

import javax.swing.JPanel;

import Basic.ResReader;

import java.awt.*;

public class WelcomPage extends JPanel {
    private static final long serialVersionUID = 1L;
    public WelcomPage() {
        super();
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(ResReader.open, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(ResReader.notice, 400,500, 500,150, this);
        validate();
        repaint();
    }
}