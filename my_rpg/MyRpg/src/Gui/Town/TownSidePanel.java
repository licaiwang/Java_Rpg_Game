package Gui.Town;

import Gui.Avatar;
import Gui.BottomPanel;
import Gui.Gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;
import Gui.Helper.MusicHelper;


public class TownSidePanel {
	String DEFULT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/";
	ImageIcon travelIcon = new ImageIcon(DEFULT_PATH + "travel.png");
	JLabel travelPointLabel;
	public static JPanel sidePanel;

	public TownSidePanel() {
		super();
		Integer Tp = Gui.player.getTP();
		JButton btn_1 = new JButton("  旅行  ");
		btn_1.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_2 = new JButton("  休息  ");
		btn_2.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_3 = new JButton("  學院  ");
		btn_3.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_4 = new JButton("  圖鑑  ");
		btn_4.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_5 = new JButton("  市集  ");
		btn_5.setMargin(new Insets(10, 10, 10, 10));
		JButton btn_6 = new JButton("  廣場  ");
		btn_6.setMargin(new Insets(10, 10, 10, 10));

		// Side
		sidePanel = new JPanel();
		sidePanel.setBorder(new LineBorder(Color.black, 3));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setAlignmentY(java.awt.Component.TOP_ALIGNMENT);
		// Inside the Side - Top
		Avatar topInsidePanel = new Avatar();
		topInsidePanel.setBorder(new LineBorder(Color.RED, 3, true));
		sidePanel.add(topInsidePanel);
		sidePanel.add(Box.createRigidArea(new Dimension(100, 0)));
		// 體力值
		Box box = Box.createVerticalBox();
		box.setAlignmentX(Component.CENTER_ALIGNMENT);
		travelPointLabel = new JLabel("體力值：" + Tp);
		travelPointLabel.setFont(new Font("Serif", Font.BOLD, 14));
		travelPointLabel.setOpaque(true);
		travelPointLabel.setIcon(travelIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(travelPointLabel);
		sidePanel.add(box);
		// Inside the Side - Down
		Box boxb = Box.createVerticalBox();
		boxb.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel gridPanel = new JPanel();
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
				Gui.resetPannel(1);
			}
		});

		btn_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread playMusic = new MusicHelper("sleep.wav");
				Gui.player.TP = 25;
				travelPointLabel.setText(" 體力值：" + Gui.player.TP);
				playMusic.start();
				Gui.resetPannel(2);
				BottomPanel.readText("inn");
				BottomPanel.resetTextArea();
				
			}
		});
		btn_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gui.resetPannel(3);
				BottomPanel.readText("school");
				BottomPanel.resetTextArea();
			}
		});
		btn_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gui.resetPannel(5);
				BottomPanel.readText("market");
				BottomPanel.resetTextArea();
			}
		});
		btn_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gui.resetPannel(6);
				BottomPanel.readText("FirstTown");
				BottomPanel.resetTextArea();
			}
		});
		boxb.add(gridPanel);
		sidePanel.add(boxb);
	}
}
