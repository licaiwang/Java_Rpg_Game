package Gui.Advanture.draw;

import javax.swing.*;

import Basic.ResReader;

import java.awt.*;
public class DrawPlayerUP extends JPanel{
    private static final long serialVersionUID = 1L;
    String Name;
    Integer Type;
    int y = 300;
    public DrawPlayerUP(String name, int type) {
        super();
        Name = name;
        Type = type;
        setPreferredSize(new Dimension(400,400));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(y > 0)
        {
            y--;
            drawSkill(g,y);
        } 
    }

    protected void drawSkill(Graphics g, int y)
    { 
        Image image;
        switch (Type) {
            case 4:
                image = new ImageIcon(ResReader.path+"res/battlePanel/boss/"+ Name +".gif").getImage();
                g.drawImage(image,100, 0, getWidth(),getHeight(), this);
                break;
            default:
                image = new ImageIcon(ResReader.path+"res/battlePanel/skill/"+ Name +".png").getImage();
                g.drawImage(image,100, y, getWidth()/2,getHeight()/2, this);
                break;
        }
        validate();
        repaint();
    }

}