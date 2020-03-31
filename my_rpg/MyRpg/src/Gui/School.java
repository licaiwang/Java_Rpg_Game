package Gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;

public class School extends JPanel {
    Integer memoryShard = 100;
    Integer memoryCd = 10;
    JLabel shardLabel;
    JLabel cDLabel;

    public School() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Middle
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new MigLayout("wrap 2", "270[]20[]", "150[]20[]"));
        gridPanel.setOpaque(false);
        JButton btn_1 = new JButton("  隨機戰技 X  1 光碟  ");
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_2 = new JButton("  普通戰技 X 10 碎片 ");
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_3 = new JButton("  進階戰技 X 30 碎片  ");
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_4 = new JButton("  大師戰技 X 50 碎片  ");
        btn_4.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_5 = new JButton("  隨機魔法 X  1 光碟  ");
        btn_5.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_6 = new JButton("  通常魔法 X 10 碎片  ");
        btn_6.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_7 = new JButton("  高階魔法 X 30 碎片  ");
        btn_7.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_8 = new JButton("  冠位魔法 X 50 碎片  ");
        btn_8.setMargin(new Insets(10, 10, 10, 10));

        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCdLabel(1);
            }
        });
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetshardLabel(10);
            }
        });
        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetshardLabel(30);
            }
        });
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetshardLabel(50);
            }
        });
        btn_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCdLabel(1);
            }
        });
        btn_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetshardLabel(10);
            }
        });

        btn_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetshardLabel(30);
            }
        });

        btn_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetshardLabel(50);
            }
        });

        gridPanel.add(btn_1);
        gridPanel.add(btn_5);
        gridPanel.add(btn_2);
        gridPanel.add(btn_6);
        gridPanel.add(btn_3);
        gridPanel.add(btn_7);
        gridPanel.add(btn_4);
        gridPanel.add(btn_8);

        // Bottom
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setOpaque(false);
        shardLabel = new JLabel("持有的記憶碎片:  " + memoryShard);
        shardLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        shardLabel.setOpaque(true);
        shardLabel.setBorder(new LineBorder(Color.black, 3));
        cDLabel = new JLabel("持有的記憶光碟:    " + memoryCd);
        cDLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        cDLabel.setOpaque(true);
        cDLabel.setBorder(new LineBorder(Color.black, 3));
        boxPanel.add(shardLabel);
        boxPanel.add(cDLabel);

        this.add(gridPanel);
        this.add(boxPanel);
    }

    void resetshardLabel(Integer cost) {
        // JLabel 無需 repaint()
        memoryShard -= cost;
        if (memoryShard > 0) {
            shardLabel.setText("持有的記憶碎片:  " + memoryShard);
        } else {
            shardLabel.setText("持有的記憶碎片不足！");
            memoryShard += cost;
        }
    }

    void resetCdLabel(Integer cost) {
        // JLabel 無需 repaint()
        memoryCd -= cost;
        if (memoryCd > 0) {
            cDLabel.setText("持有的記憶光碟:  " + memoryCd);
        } else {
            cDLabel.setText("持有的記憶光碟不足！");
            memoryCd += cost;
        }
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawSleep(g);
    }

    protected void drawSleep(final Graphics g) {
        final Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/school.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}
