package Gui.Town;

import javax.swing.*;

import Basic.ResReader;

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
        g.drawImage(ResReader.firstTown, 0, 0, getWidth(), getHeight(), this);
    }
}