package Gui.Advanture;

import javax.swing.*;

import Basic.ResReader;

import Gui.Helper.CreateButton;
import Gui.Helper.MusicHelper;

import java.awt.*;
import java.awt.event.*;
   /** 
     * 
     * @author  Rorschach
     * 
     * 剛出去的大門
     *  
     **
     */
public class Gate extends JPanel {
    private static final long serialVersionUID = 1L;
    JButton btn_move;
    JPanel box;
    public Gate() {
        super();
        this.setFocusable(true);
        box = new JPanel();
        btn_move = new CreateButton("  前進  ");
        btn_move.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_move);
        box.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 65, 20));
        this.add(box, BorderLayout.SOUTH);
        btn_move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread playMusic = new MusicHelper("player/step.wav");
                playMusic.start();
                AdvantureBackground.showRoad();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.gate, 0, 0, getWidth(), getHeight(), this);
    }
}