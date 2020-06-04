package Gui.Advanture.draw;

import javax.swing.*;

import Basic.ResReader;

import java.awt.*;
    /**
     * 
     * 
     * @author  Rorschach
     * 
     * 畫玩家身上的特效或怪物對玩家特效
     * 
     **
     */
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
                image = new ImageIcon(ResReader.loader.getResource("res/battlePanel/boss/"+ Name +".gif")).getImage();
                g.drawImage(image,0, 150, 240,240, this);
                break;
            case 1:
                image = new ImageIcon(ResReader.loader.getResource("res/battlePanel/magic/"+ Name +".gif")).getImage();
                g.drawImage(image,150,250, 240,240, this);
                break;
            default:
                image = new ImageIcon(ResReader.loader.getResource("res/battlePanel/skill/"+ Name +".png")).getImage();
                g.drawImage(image,0, y, 360,360, this);
                break;
        }
        validate();
        repaint();
    }

}