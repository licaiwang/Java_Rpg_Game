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

    public DrawSpecialEffect(String name, int type) {
        super();
        Name = name;
        Type = type;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSkillAndMagic(g);
    }

    protected void drawSkillAndMagic(Graphics g) {
        Image image = null;
        switch (Type) {
            case 0:
                image = new ImageIcon(ResReader.path+"res/battlePanel/skill/" + Name + ".gif")
                        .getImage();
                break;
            case 1:
                image = new ImageIcon(ResReader.path+"res/battlePanel/magic/" + Name + ".gif")
                        .getImage();
                break;
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}