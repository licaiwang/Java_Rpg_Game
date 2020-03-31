package Gui;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	String content;
	Integer travelPoint = 15;
	Container mContainer;
	JPanel bottomPanel;
	JTextArea jTextArea;
	JScrollPane jScrollPane;
	FirstTown mainPage;
	Market market;
	AudioInputStream audioIn;
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
				readText("inn");
				resetTextArea();
				Thread playMusic = new MusicHelper("sleep.wav");
				playMusic.start();
			}
		});
		btn_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPannel(3);
				readText("school");
				resetTextArea();
			}
		});
		btn_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPannel(5);
				readText("market");
				resetTextArea();
			}
		});
		btn_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPannel(6);
				readText("FirstTown");
				resetTextArea();
			}
		});
		// Middle - init
		mainPage = new FirstTown();
		inn = new Inn();
		school = new School();
		market = new Market();
		mainPage.setFocusable(true);
		mainPage.setOpaque(true);
		mainPage.setBorder(new LineBorder(Color.black, 3));

		// buttom
		bottomPanel = new JPanel();
		// bottomPanel.setLayout(new FlowLayout(50, 50, 50));
		bottomPanel.setBackground(Color.white);
		bottomPanel.setBorder(new LineBorder(Color.BLUE, 3));
		// init text on buttom
		jTextArea = new JTextArea();
		jScrollPane = new JScrollPane(jTextArea);
		readText("FirstTown");
		resetTextArea();
		// Add to Container
		mContainer.add(topPanel, BorderLayout.NORTH);
		mContainer.add(mainPage);
		mContainer.add(sidePanel, BorderLayout.EAST);
		mContainer.add(bottomPanel, BorderLayout.SOUTH);

		// 背景音樂
		try {
			audioIn = AudioSystem
					.getAudioInputStream(new File("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/music/firstTown.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}

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
			case 5:
				showMarket();
				break;
			case 6:
				showTown();
				break;
		}
	}

	void showInn() {
		inn.setVisible(true);
		inn.setBackground(Color.BLACK);
		//
		mainPage.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(mainPage);
		mContainer.remove(school);
		//
		mContainer.add(inn);
		mContainer.invalidate();
		inn.repaint();
	}

	void showSchool() {
		school.setVisible(true);
		school.setFocusable(true);
		//
		mainPage.setVisible(false);
		inn.setVisible(false);
		market.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(mainPage);
		mContainer.remove(inn);
		//
		mContainer.add(school);
		mContainer.invalidate();
		school.repaint();
	}

	void showMarket() {
		market.setVisible(true);
		market.setFocusable(true);
		//
		mainPage.setVisible(false);
		inn.setVisible(false);
		school.setVisible(false);
		//
		mContainer.remove(school);
		mContainer.remove(mainPage);
		mContainer.remove(inn);
		//
		mContainer.add(market);
		mContainer.invalidate();
		market.repaint();
	}

	void showTown() {
		mainPage.setVisible(true);
		//
		inn.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(inn);
		mContainer.remove(school);
		//
		mContainer.add(mainPage);
		mContainer.invalidate();
		mainPage.repaint();
	}

	void resetTextArea() {
		jTextArea.setFont(new Font("Serif", Font.PLAIN, 20));
		jTextArea.setColumns(getWidth() / 20);
		jTextArea.setEditable(false);
		jTextArea.setLineWrap(true);
		jTextArea.setText(content);
		jScrollPane.setOpaque(false);
		bottomPanel.add(jScrollPane);
	}

	void readText(String name) {
		try {
			content = FileReaderHelper.readTextFromTxt(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
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