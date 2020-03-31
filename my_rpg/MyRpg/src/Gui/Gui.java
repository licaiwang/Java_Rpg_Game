package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Gui extends JFrame {
	Integer travelPoint = 15;
	Container mContainer;
	FirstTown mainPage;
	Inn inn;
	School school;
	JLabel travelPointLabel;

	public Gui(final String title) {
		super(title);
		this.setSize(960, 800);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		mContainer = this.getContentPane();
		mContainer.setLayout(new BorderLayout(8, 6));
		mContainer.setBackground(Color.YELLOW);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));

		// Top
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.black);

		// Side
		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(new LineBorder(Color.black, 3));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
		sidePanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		// Inside the Side - Top
		Avatar topInsidePanel = new Avatar();
		topInsidePanel.setBorder(new LineBorder(Color.RED, 3, true));
		sidePanel.add(topInsidePanel);

		// 體力值
		travelPointLabel = new JLabel("           體力值：" + travelPoint + "        ");
		travelPointLabel.setOpaque(true);
		travelPointLabel.setBorder(new LineBorder(Color.black, 3));
		travelPointLabel.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
		// 這可以用來創造空間做 padding
		sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sidePanel.add(travelPointLabel);

		// Inside the Side - Down
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new MigLayout("wrap 1", "40[]40", "20[]20"));
		gridPanel.add(btn_1);
		gridPanel.add(btn_2);
		gridPanel.add(btn_3);
		gridPanel.add(btn_4);
		gridPanel.add(btn_5);
		gridPanel.add(btn_6);
		sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sidePanel.add(gridPanel);

		btn_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetTravelPointLabel();
				resetPannel(2);
			}
		});
		btn_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPannel(3);
			}
		});
		btn_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPannel(6);
			}
		});
		// Middle - init
		mainPage = new FirstTown();
		inn = new Inn();
		school = new School();
		mainPage.setFocusable(true);
		mainPage.setOpaque(true);
		mainPage.setBorder(new LineBorder(Color.black, 3));

		// buttom
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(50, 50, 50));
		bottomPanel.setBackground(Color.white);
		bottomPanel.setBorder(new LineBorder(Color.BLUE, 3));

		// Add to Container
		mContainer.add(topPanel, BorderLayout.NORTH);
		mContainer.add(mainPage);
		mContainer.add(sidePanel, BorderLayout.EAST);
		mContainer.add(bottomPanel, BorderLayout.SOUTH);
	}

	void resetTravelPointLabel() {
		// JLabel 無需 repaint()
		travelPoint = 25;
		travelPointLabel.setText("           體力值：" + travelPoint + "        ");
	}

	void resetPannel(Integer which) {
		switch (which) {
			case 2:
				showInn();
				break;
			case 3:
				showSchool();
				break;
			case 6:
				showTown();
				break;
		}
	}

	void showSchool() {
		school.setVisible(true);
		mainPage.setVisible(false);
		inn.setVisible(false);
		school.setFocusable(true);
		mContainer.remove(mainPage);
		mContainer.remove(inn);
		mContainer.add(school);
		mContainer.invalidate();
		school.repaint();
	}

	void showTown() {
		mainPage.setVisible(true);
		inn.setVisible(false);
		school.setVisible(false);
		mContainer.remove(inn);
		mContainer.remove(school);
		mContainer.add(mainPage);
		mContainer.invalidate();
		mainPage.repaint();
	}

	void showInn() {
		inn.setVisible(true);
		mainPage.setVisible(false);
		school.setVisible(false);

		inn.setBackground(Color.BLACK);
		mContainer.remove(mainPage);
		mContainer.remove(school);
		mContainer.add(inn);
		mContainer.invalidate();
		inn.repaint();
	}

	public static void main(String[] args) {
		Gui mylayout = new Gui("Test");
		mylayout.setVisible(true);
	}

}
/*
 * JLabel mainPage = new JLabel("CENTER", SwingConstants.CENTER);
 * mainPage.setOpaque(true); mainPage.setBorder(new LineBorder(Color.black, 3));
 */