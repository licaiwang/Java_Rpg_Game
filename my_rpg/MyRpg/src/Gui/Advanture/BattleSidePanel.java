package Gui.Advanture;

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
  /** 
     * 
     * @author  Rorschach
     * 
     * 進入旅行後旁邊的面板
     *  
     **
     */
public class BattleSidePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JPanel sidePanel;
	public static JLabel travelPointLabel;
	public static JLabel LvLabel;
	public static JLabel ExpLabel;
	public static JLabel HpLabel;
	public static JLabel MpLabel;
	public static JLabel coinLabel;
	public static JButton btn_4;

	public BattleSidePanel() {
		super();
		// init
		setOpaque(false);
		setPreferredSize(new Dimension(200, 500));
		add(Box.createRigidArea(new Dimension(150,20)));
		btn_4 = new CreateButton("  回城  ");
		btn_4.setMargin(new Insets(10, 10, 10, 10));
	
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
		travelPointLabel = new JLabel();
		travelPointLabel.setText("體力值：" + Player.TP+" ");
		
		travelPointLabel.setFont(new Font("Serif", Font.BOLD, 14));
		travelPointLabel.setOpaque(false);
		travelPointLabel.setForeground(Color.WHITE);
	
		travelPointLabel.setIcon(ResReader.travelIcon);
		box.add(travelPointLabel);
		LvLabel = new JLabel("等級：" + Player.LEVEL+" ");
		LvLabel.setFont(new Font("Serif", Font.BOLD, 14));
		LvLabel.setForeground(Color.WHITE);
		LvLabel.setBackground(Color.BLACK);
		LvLabel.setOpaque(true);
		LvLabel.setIcon(ResReader.lvIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(LvLabel);
		ExpLabel = new JLabel("下一級：" + Player.EXP+" ");
		ExpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		ExpLabel.setForeground(Color.WHITE);
		ExpLabel.setBackground(Color.BLACK);
		ExpLabel.setOpaque(true);
		ExpLabel.setIcon(ResReader.expIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(ExpLabel);
		HpLabel = new JLabel("生命值：" + Player.HP+" ");
		HpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		HpLabel.setForeground(Color.WHITE);
		HpLabel.setBackground(Color.BLACK);
		HpLabel.setOpaque(true);
		HpLabel.setIcon(ResReader.heartIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(HpLabel);

		MpLabel = new JLabel("魔力：" + Player.MP+" ");
		MpLabel.setFont(new Font("Serif", Font.BOLD, 14));
		MpLabel.setOpaque(true);
		MpLabel.setForeground(Color.WHITE);
		MpLabel.setBackground(Color.BLACK);
		MpLabel.setIcon(ResReader.mpIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(MpLabel);

		coinLabel = new JLabel("金幣：" + Player.COIN+" ");
		coinLabel.setFont(new Font("Serif", Font.BOLD, 14));
		coinLabel.setOpaque(true);
		coinLabel.setForeground(Color.WHITE);
		coinLabel.setBackground(Color.BLACK);
		coinLabel.setIcon(ResReader.CoinIcon);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(coinLabel);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.setOpaque(true);
		// Inside the Side - Down

		// Bottom
		Box boxb = Box.createVerticalBox();
		JPanel gridPanel = new JPanel();
		gridPanel.setOpaque(false);
		gridPanel.setLayout(new MigLayout("wrap 1", "50[]50", "50[]50"));
		gridPanel.add(btn_4);
		btn_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO：　reset monster hp
				CreateButton.clickSound();
				removeAll();
				MusicHelper.stopBackgroundMusic();
				MusicHelper.playBackgroundMusic("firstTown");
				setVisible(false);
				AdvantureBackground.advPanel.setVisible(false);
				Gui.mContainer.remove(AdvantureBackground.advPanel);
				Gui.showTownSidePanel();
				Gui.resetPannel(6);
			}
		});

		sidePanel.add(Box.createRigidArea(new Dimension(150,20)));
		sidePanel.add(box);
		boxb.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxb.add(gridPanel);	
		sidePanel.add(boxb);
		sidePanel.setBackground(Color.BLACK);
		add(sidePanel);
	}

	public static void resetLabel() {
		LvLabel.setText("等級：" + Player.LEVEL+" ");
		ExpLabel.setText("下一級：" + Player.EXP+" ");
		HpLabel.setText("生命值：" + Player.HP+" ");
		MpLabel.setText("魔力：" + Player.MP+" ");
	}

	public static void resetCoin() {
		coinLabel.setText("金幣：" + Player.COIN+" ");
	}

	public static void resetHp() {
		HpLabel.setText("生命值：" + Player.HP+" ");
	}

	public static void resetTp() {
		travelPointLabel.setText("體力值：" + Player.TP+" ");
	}
	public static void resetMp() {
		MpLabel.setText("魔力：" + Player.MP+" ");
	}

	public static void setBackButtonEnable()
	{
		btn_4.setEnabled(false);
	}

	public static void setBackButtonAble()
	{
		btn_4.setEnabled(false);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBattleSide(g);
    }

    public void drawBattleSide(Graphics g) {
        g.drawImage(ResReader.side, 0, 0, getWidth(), getHeight(), this);
    }
}