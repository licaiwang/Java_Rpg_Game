package Gui.Advanture.draw;
import javax.swing.*;
import java.awt.*;
public class DrawSpecialEffect extends JPanel {
    /*
     * 畫技能
     */
    private static final long serialVersionUID = 1L;
    String skillName;
    public DrawSpecialEffect(String name) {
        super();
        skillName = name;
        setBorder(BorderFactory.createEmptyBorder(180,180, 180, 180));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        drawSkill(g);
    }

    protected void drawSkill(Graphics g)
    {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/skill/"+ skillName +".gif").getImage();
        g.drawImage(image, 0, 0, getWidth(),getHeight(), this);
    }


}