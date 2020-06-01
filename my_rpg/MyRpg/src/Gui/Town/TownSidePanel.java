package Gui.Town;

import Gui.Avatar;
import Gui.Gui;
import Gui.Helper.CreateButton;
import Gui.Helper.MusicHelper;

import javax.swing.*;
import Basic.Player;
import Basic.ResReader;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;

public class TownSidePanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String DEFULT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/";
	ImageIcon travelIcon = new ImageIcon(DEFULT_PATH + "travel.png");

	JLabel travelPointLabel;
	public static JPanel sidePanel;

	public TownSidePanel() {
		super();
		setOpaque(false);
		setPreferredSize(new Dimension(200, 500));
		add(Box.createRigidArea(new Dimension(150,20)));
		Integer Tp = Gui.player.getTP();
		JButton btn_1 = new CreateButton("  旅行  ");
		btn_1.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_2 = new CreateButton("  休息  ");
		btn_2.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_3 = new CreateButton("  學院  ");
		btn_3.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_4 = new CreateButton("  圖鑑  ");
		btn_4.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_5 = new CreateButton("  市集  ");
		btn_5.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_6 = new CreateButton("  廣場  ");
		btn_6.setMargin(new Insets(10, 10, 10, 10));

		// Side
		sidePanel = new JPanel();	
		sidePanel.setOpaque(false);
	
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setAlignmentY(java.awt.Component.TOP_ALIGNMENT);

		// Inside the Side - Top
		Avatar topInsidePanel = new Avatar();
		sidePanel.add(topInsidePanel);
		sidePanel.add(Box.createRigidArea(new Dimension(100, 0)));

		// 體力值
		Box box = Box.createVerticalBox();
		box.setAlignmentX(Component.CENTER_ALIGNMENT);
		box.setOpaque(false);
		travelPointLabel = new JLabel("體力值：" + Tp +" ");
		
		travelPointLabel.setFont(new Font("Serif", Font.BOLD, 14));
		
		travelPointLabel.setOpaque(false);
		travelPointLabel.setForeground(Color.WHITE);
		travelPointLabel.setIcon(travelIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(travelPointLabel);
		sidePanel.add(box);

		// Inside the Side - Down
		Box boxb = Box.createVerticalBox();
		boxb.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxb.setOpaque(false);
		JPanel gridPanel = new JPanel();
		gridPanel.setOpaque(false);
		gridPanel.setLayout(new MigLayout("wrap 1", "40[]40", "20[]20"));
		gridPanel.add(btn_1);
		gridPanel.add(btn_2);
		gridPanel.add(btn_3);
		gridPanel.add(btn_4);
		gridPanel.add(btn_5);
		gridPanel.add(btn_6);
		btn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateButton.clickSound();
				MusicHelper.stopBackgroundMusic();
				MusicHelper.playBackgroundMusic("void");
				Gui.resetPannel(1);
			}
		});

		btn_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateButton.clickSound();
				Player.TP = 25;
				Player.HP = Integer.valueOf(Player.Upgrade.get(Player.LEVEL)[2]);
				Player.Max_HP = Player.HP;
				Player.MP = Integer.valueOf(Player.Upgrade.get(Player.LEVEL)[9]);
				travelPointLabel.setText(" 體力值：" + Player.TP+" ");
				Gui.resetPannel(2);	
			}
		});
		btn_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateButton.clickSound();
				Gui.resetPannel(3);
			}
		});
		btn_4.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							CreateButton.clickSound();
							Gui.resetPannel(4);							
						}
					});

		btn_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateButton.clickSound();
				Gui.resetPannel(5);
			}
		});
		btn_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateButton.clickSound();
				Gui.resetPannel(6);
			}
		});
		boxb.add(gridPanel);
		sidePanel.add(boxb);
		sidePanel.setBackground(Color.BLACK);
		add(sidePanel);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(ResReader.side, 0, 0, getWidth(), getHeight(), this);
    }
}
