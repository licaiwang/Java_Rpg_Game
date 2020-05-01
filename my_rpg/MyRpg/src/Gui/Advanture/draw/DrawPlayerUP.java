package Gui.Advanture.draw;
import javax.swing.*;
import java.awt.*;
public class DrawPlayerUP extends JPanel{
    private static final long serialVersionUID = 1L;
    String Name;
    int y=300;
    public DrawPlayerUP(String name) {
        super();
        Name = name;
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
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/skill/"+ Name +".png").getImage();
        g.drawImage(image,0, y, getWidth()/2,getHeight()/2, this);
        validate();
        repaint();
    }

}