package Gui.Advanture.event;

import java.awt.*;
import javax.swing.*;

import Gui.Advanture.AdvantureBackground;

import java.awt.event.*;

public class Treasure extends JPanel {
    JButton btn_hand ;
    JButton btn_ignore ;
    JPanel  box;
    public Treasure()
    {
    super();
    box = new JPanel();
   
    btn_hand = new JButton("  打開 ");
    btn_hand .setMargin(new Insets(10, 10, 10, 10));
    box.add(btn_hand);
    btn_ignore = new JButton("  無視 ");
    btn_ignore.setMargin(new Insets(10, 10, 10, 10));
    box.add(btn_ignore);
    box.setOpaque(false);
  
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createEmptyBorder(20,0,80,0));
    this.add(box,BorderLayout.SOUTH);

    btn_ignore.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdvantureBackground.showRoad();
        }
    });


    }
    private static final long serialVersionUID = 1L;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        drawEvent(g);
    }
    public void drawEvent(Graphics g)
    {
        Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/treasure.jpg").getImage();
        g.drawImage(image,0, 0 , getWidth(), getHeight(), this);
    }

}