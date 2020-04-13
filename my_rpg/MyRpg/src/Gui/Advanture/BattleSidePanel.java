package Gui.Advanture;

import Gui.Avatar;
import Gui.Gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import java.awt.*;

public class BattleSidePanel extends JLabel {
	/*

    進入旅行後旁邊的面板
    
    */
	private static final long serialVersionUID = 1L;
	public static JPanel sidePanel;
	public static JLabel travelPointLabel;
	public static	JLabel LvLabel;
	public static JLabel ExpLabel;
	public static JLabel HpLabel;
	public static JLabel MpLabel;
	static String DEFULT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/battlePanel/";
	ImageIcon heartIcon = new ImageIcon(DEFULT_PATH + "heart.png");
	ImageIcon mpIcon = new ImageIcon(DEFULT_PATH + "mp.png");
	ImageIcon expIcon = new ImageIcon(DEFULT_PATH + "exp.png");
	ImageIcon travelIcon = new ImageIcon(DEFULT_PATH + "travel.png");
	ImageIcon lvIcon = new ImageIcon(DEFULT_PATH + "lv.png");
	Integer Lv ;
	Integer Exp;
	Integer Hp ;
	Integer Mp;
	Integer Tp;
	

	
	public BattleSidePanel() {
		super();
		// init
		Lv = Gui.player.getLevel();
		Exp = Gui.player.getExp();
		Hp = Gui.player.getHp();
		Mp = Gui.player.getMp();
		Tp = Gui.player.getTP();



		JButton btn_4 = new JButton("  回城  ");
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
		sidePanel.add(Box.createRigidArea(new Dimension(100,0)));
		// 體力值
		Box box = Box.createVerticalBox();
		box.setAlignmentX(Component.CENTER_ALIGNMENT);
		travelPointLabel = new JLabel();
		travelPointLabel.setText("體力值：" + Tp);
		travelPointLabel.setFont(new Font("Serif", Font.BOLD, 14));
		travelPointLabel.setOpaque(true);
		travelPointLabel.setIcon(travelIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(travelPointLabel);
		LvLabel = new JLabel("等級：" + Lv);
		LvLabel.setFont(new Font("Serif", Font.BOLD, 14));
		LvLabel.setOpaque(true);
		LvLabel.setIcon(lvIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(LvLabel);
		ExpLabel = new JLabel("經驗值：" + Exp);
		ExpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		ExpLabel.setOpaque(true);
		ExpLabel.setIcon(expIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(ExpLabel);
		HpLabel = new JLabel("生命值：" + Hp);
		HpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		HpLabel.setOpaque(true);
		HpLabel.setIcon(heartIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(HpLabel);
		MpLabel = new JLabel("魔力：" + Mp);
		MpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		MpLabel.setOpaque(true);
		MpLabel.setIcon(mpIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(MpLabel);
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
				Gui.showTownSidePanel();
				Gui.resetPannel(6);
			}
		});
		boxb.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxb.add(gridPanel);
		sidePanel.add(boxb);
	}

}