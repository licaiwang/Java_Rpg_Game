package Gui.Town;

import Gui.Helper.MusicHelper;
import Gui.Gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Basic.Player;
import Basic.ResReader;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;
   /** 
     * 
     * @author  Rorschach
     * 
     *  
     * 畫市場，可以購買東西
     *  
     * 
     **
     */
public class Market extends JPanel {
    JLabel moneyLabel;

    public Market() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Middle
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new MigLayout("wrap 1", "270[]20[]", "150[]20[]"));
        gridPanel.setOpaque(false);
        JButton btn_1 = new JButton("痛立停 X 100 NT");
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_2 = new JButton("魔力香爐 X 200 NT");
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_3 = new JButton("女神的祝福 X 2000 NT");
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_4 = new JButton("記憶的光碟 X 500 NT");
        btn_4.setMargin(new Insets(10, 10, 10, 10));
        btn_1.setPreferredSize(new Dimension(150, 30));
        btn_2.setPreferredSize(new Dimension(150, 30));
        btn_3.setPreferredSize(new Dimension(150, 30));
        btn_4.setPreferredSize(new Dimension(150, 30));

        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMoney(100);
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
            }

        });
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMoney(200);
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();

            }
        });
        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMoney(2000);
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
            }
        });
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMoney(500);
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
            }
        });
        gridPanel.add(btn_1);
        gridPanel.add(btn_2);
        gridPanel.add(btn_3);
        gridPanel.add(btn_4);
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setOpaque(false);
        moneyLabel = new JLabel("持有的新台幣:  " + Player.COIN);
        moneyLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        moneyLabel.setOpaque(true);
        moneyLabel.setBorder(new LineBorder(Color.black, 3));
        boxPanel.add(moneyLabel);
        this.add(gridPanel);
        this.add(boxPanel);
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(ResReader.market, 0, 0, getWidth(), getHeight(), this);
    }
    protected void resetMoney(int i) {

        Player.COIN -= i;
        if (Player.COIN >= 0) {
            moneyLabel.setText("持有的新台幣:  " + Player.COIN);
            switch (i) {
                case 100:
                    Player.stop_pain += 1;
                    break;
                case 200:
                    Player.magic_box += 1;
                    break;
                case 2000:
                    Player.godness_bless += 1;
                    break;
                case 500:
                    Gui.player.gainCD(1);
                    break;
            }
        } else {
            Player.COIN += i;
            moneyLabel.setText("你只有:  " + Player.COIN + "塊!");
        }

    }

    public void resetAmount() {
        moneyLabel.setText("持有的新台幣:  " + Player.COIN);
    }

}
