package Gui.Advanture.draw;

import javax.swing.*;

import Basic.ResReader;

import java.awt.*;

public class DrawSpecialEffect extends JPanel {
    /*
     * 畫技能
     */
    private static final long serialVersionUID = 1L;
    String Name;
    Integer Type;
    Integer frame_x;
    Integer frame_y;
    public DrawSpecialEffect(String name, int type, int x, int y) {
        super();
        Name = name;
        Type = type;
        switch (x) {
            case 400:
                frame_x = 0;
                break;
            case 800:
                 frame_x = 100;
                break;
        }
        switch (y) {
            case 400:
                frame_y = 0;
                break;
            case 500:
                frame_y = 0;
                break;
        }
       
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSkillAndMagic(g);
    }

    protected void drawSkillAndMagic(Graphics g) {
        Image image;
        switch (Type) {
            case 0:
                
                image = new ImageIcon(ResReader.path+"res/battlePanel/skill/" + Name + ".gif")
                        .getImage();
                        g.drawImage(image, frame_x, frame_y ,360, 360, this);
                break;
            case 1:
                image = new ImageIcon(ResReader.path+"res/battlePanel/magic/" + Name + ".gif")
                        .getImage();
                        g.drawImage(image, frame_x, frame_y,360, 360, this);
                break;
            case 4:
                image = new ImageIcon(ResReader.path+"res/battlePanel/boss/" + Name + ".gif")
                        .getImage();
                        g.drawImage(image, 150, 230 , 400, getHeight()/2, this);
             break;
        }
    }
}