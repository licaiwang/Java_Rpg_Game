package Gui.Advanture.event;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;

public class Fortress extends JPanel {

    /**
     *
     */
    JButton btn_back;
    JPanel box;

    JPanel puzzle_1 = new Puzzle(1);
    JPanel puzzle_2 = new Puzzle(2);
    JPanel puzzle_3 = new Puzzle(3);
    JPanel puzzle_4 = new Puzzle(4);
    JPanel puzzle_5 = new Puzzle(5);
    JPanel puzzle_6 = new Puzzle(6);
    JPanel puzzle_7 = new Puzzle(7);
    JPanel puzzle_8 = new Puzzle(8);
    JPanel puzzle_9 = new Puzzle(9);

    private static final long serialVersionUID = 1L;

    public Fortress() {
        super();
        setFocusable(true);
        setOpaque(false);

        box = new JPanel();
        box.setOpaque(false);

        btn_back = new JButton("  折返  ");
        btn_back.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_back);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new MigLayout("wrap 3", "335[]5[]5[]5", "90[]5[]5[]5"));
        gridPanel.setOpaque(false);

        gridPanel.add(puzzle_1);
        gridPanel.add(puzzle_4);
        gridPanel.add(puzzle_7);

        gridPanel.add(puzzle_2);
        gridPanel.add(puzzle_5);
        gridPanel.add(puzzle_8);

        gridPanel.add(puzzle_3);
        gridPanel.add(puzzle_6);  
        gridPanel.add(puzzle_9);

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 20));
        this.add(box, BorderLayout.SOUTH);

        this.add(gridPanel);

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdvantureBackground.showCampFire();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.fortress, 0, 0, getWidth(), getHeight(), this);
    }
}