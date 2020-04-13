package Gui.Town;
import Gui.Helper.MusicHelper;
import Gui.Gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Basic.Player;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;

public class Market extends JPanel {
    JLabel  moneyLabel;
    public Market() {
        super();
  
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        // Middle
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new MigLayout("wrap 1", "270[]20[]", "150[]20[]"));
        gridPanel.setOpaque(false);
        JButton btn_1 = new JButton("  普拿疼     X 120 NT  ");
        btn_1.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_2 = new JButton("  斯斯膠囊   X 100 NT ");
        btn_2.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_3 = new JButton("  瑞得西委   X 800 NT  ");
        btn_3.setMargin(new Insets(10, 10, 10, 10));
        JButton btn_4 = new JButton("  記憶的光碟 X 500 NT  ");
        btn_4.setMargin(new Insets(10, 10, 10, 10));

        btn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                resetMoney(120);
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
			}

		});
		btn_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
                resetMoney(100);
			}
		});
		btn_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
                resetMoney(800);
			}
		});
        btn_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Thread playMusic = new MusicHelper("coin.wav");
                playMusic.start();
                resetMoney(500);
			}
        });
        gridPanel.add(btn_1);
        gridPanel.add(btn_2);
        gridPanel.add(btn_3);
        gridPanel.add(btn_4);
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
        boxPanel.setOpaque(false);
        moneyLabel = new JLabel("持有的新台幣:  "+ Player.COIN);
        moneyLabel.setFont(new Font("Serif", Font.PLAIN,28));
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
        drawMarket(g);
    }

    protected void drawMarket(final Graphics g) {
        final Image image = new ImageIcon("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/market.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    protected void resetMoney(int i) {
       
        Player.COIN -= i;
        if( Player.COIN  >= 0)
        {
            moneyLabel.setText("持有的新台幣:  "+  Player.COIN);
            switch (i) {
                case 500:
                    Gui.player.gainCD(1);
                    break;
            }
        }else{
            Player.COIN += i;
            moneyLabel.setText("你只有:  "+ Player.COIN +"塊!");
        }
    }

    public void resetAmount()
    {
        moneyLabel.setText("持有的新台幣:  "+ Player.COIN);
    }

}
