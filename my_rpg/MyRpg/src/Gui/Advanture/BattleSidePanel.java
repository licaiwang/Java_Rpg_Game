package Gui.Advanture;

import Gui.Avatar;
import Gui.Gui;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Basic.Player;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;

public class BattleSidePanel extends JLabel {
	/*
	 * 
	 * 進入旅行後旁邊的面板
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel sidePanel;
	public static JLabel travelPointLabel;
	public static JLabel LvLabel;
	public static JLabel ExpLabel;
	public static JLabel HpLabel;
	public static JLabel MpLabel;
	public static JLabel coinLabel;
	public static JButton btn_4;
	static String DEFULT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/";
	ImageIcon heartIcon = new ImageIcon(DEFULT_PATH + "heart.png");
	ImageIcon mpIcon = new ImageIcon(DEFULT_PATH + "mp.png");
	ImageIcon expIcon = new ImageIcon(DEFULT_PATH + "exp.png");
	ImageIcon travelIcon = new ImageIcon(DEFULT_PATH + "travel.png");
	ImageIcon lvIcon = new ImageIcon(DEFULT_PATH + "lv.png");
	ImageIcon CoinIcon = new ImageIcon(DEFULT_PATH + "coin.png");

	public BattleSidePanel() {
		super();
		// init

		btn_4 = new JButton("  回城  ");
		btn_4.setMargin(new Insets(10, 10, 10, 10));

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
		travelPointLabel = new JLabel();
		travelPointLabel.setText("體力值：" + Player.TP);
		travelPointLabel.setFont(new Font("Serif", Font.BOLD, 14));
		travelPointLabel.setOpaque(true);
		travelPointLabel.setIcon(travelIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(travelPointLabel);
		LvLabel = new JLabel("等級：" + Player.LEVEL);
		LvLabel.setFont(new Font("Serif", Font.BOLD, 14));
		LvLabel.setOpaque(true);
		LvLabel.setIcon(lvIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(LvLabel);
		ExpLabel = new JLabel("下一級：" + Player.EXP);
		ExpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		ExpLabel.setOpaque(true);
		ExpLabel.setIcon(expIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(ExpLabel);
		HpLabel = new JLabel("生命值：" + Player.HP);
		HpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		HpLabel.setOpaque(true);
		HpLabel.setIcon(heartIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(HpLabel);

		MpLabel = new JLabel("魔力：" + Player.MP);
		MpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		MpLabel.setOpaque(true);
		MpLabel.setIcon(mpIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(MpLabel);

		coinLabel = new JLabel("金幣：" + Player.COIN);
		coinLabel.setFont(new Font("Serif", Font.BOLD, 14));
		coinLabel.setOpaque(true);
		coinLabel.setIcon(CoinIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(coinLabel);

		sidePanel.add(box);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		// Inside the Side - Down

		// Bottom
		Box boxb = Box.createVerticalBox();

		JPanel gridPanel = new JPanel();

		gridPanel.setLayout(new MigLayout("wrap 1", "50[]50", "50[]50"));
		gridPanel.add(btn_4);
		btn_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				AdvantureBackground.advPanel.setVisible(false);
				Gui.mContainer.remove(AdvantureBackground.advPanel);
				Gui.showTownSidePanel();
				Gui.resetPannel(6);
			}
		});
		boxb.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxb.add(gridPanel);
		sidePanel.add(boxb);
	}

	public static void resetLabel() {
		LvLabel.setText("等級：" + Player.LEVEL);
		ExpLabel.setText("下一級：" + Player.EXP);
		HpLabel.setText("生命值：" + Player.HP);
		MpLabel.setText("魔力：" + Player.MP);
	}

	public static void resetCoin() {
		coinLabel.setText("金幣：" + Player.COIN);
	}

	public static void resetHp() {
		HpLabel.setText("生命值：" + Player.HP);
	}

	public static void resetTp() {
		travelPointLabel.setText("體力值：" + Player.TP);
	}
	public static void resetMp() {
		MpLabel.setText("魔力：" + Player.MP);
	}

}