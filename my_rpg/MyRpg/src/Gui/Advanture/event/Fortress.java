package Gui.Advanture.event;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Fortress extends JPanel {

    /**
     *
     */
    public static JButton btn_back;
    public static JPanel gridPanel; 

    JPanel box;

    JPanel puzzle_1;
    JPanel puzzle_2;
    JPanel puzzle_3;
    JPanel puzzle_4;
    JPanel puzzle_5;
    JPanel puzzle_6;
    JPanel puzzle_7;
    JPanel puzzle_8;
    JPanel puzzle_9;

    public static int degree[];
    public static int answer[]; 

    private static final long serialVersionUID = 1L;

    public Fortress() {
        super();
        setFocusable(true);
        setOpaque(false);
        degree = new int[9];
        answer = new int[9];
        puzzle_1 = new Puzzle(0);
        initRandomDegree(0);
        puzzle_2 = new Puzzle(1);
        initRandomDegree(1);
        puzzle_3 = new Puzzle(2);
        initRandomDegree(2);
        puzzle_4 = new Puzzle(3);
        initRandomDegree(3);
        puzzle_5 = new Puzzle(4);
        initRandomDegree(4);
        puzzle_6 = new Puzzle(5);
        initRandomDegree(5);
        puzzle_7 = new Puzzle(6);
        initRandomDegree(6);
        puzzle_8 = new Puzzle(7);
        initRandomDegree(7);
        puzzle_9 = new Puzzle(8);
        initRandomDegree(8);

        box = new JPanel();
        box.setOpaque(false);
        btn_back = new JButton("  折返  ");
        btn_back.setMargin(new Insets(10, 10, 10, 10));
        box.add(btn_back);

        gridPanel = new JPanel();
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

    protected void initRandomDegree(int id) {
        // 隨機轉 90 , 180 , 270 度，或者不轉
        Random random = new Random();
        int chose = random.nextInt(4);
        int[] opt = { 0, 1, 2 ,3};
        switch (opt[chose]) {
            case 0:
                degree[id] = 90;
                answer[id] =  1;
                break;
            case 1:
                degree[id] = 180;
                answer[id] =   2;
                break;
            case 2:
                degree[id] = 270;
                answer[id] =   3;
                break;
            default:
                degree[id] =   0;
                break;
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.fortress, 0, 0, getWidth(), getHeight(), this);
    }
}