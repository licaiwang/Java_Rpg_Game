package Gui;

import javax.sound.sampled.AudioInputStream;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import Basic.Player;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Town.FirstTown;
import Gui.Town.Inn;
import Gui.Town.Market;
import Gui.Town.School;
import Gui.Town.TownSidePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Gui main;
	public static Player player;
	AudioInputStream audioIn;
	static Container mContainer;
	static FirstTown mainPage;
	static Market market;
	static Inn inn;
	static School school;
	static BattleSidePanel battleSidePanel;
	static AdvantureBackground advBackground;
	static BottomPanel bottomPanel;
	static TownSidePanel townSidePanel;
	static Canvas c;
	static JPanel p;
	static EmbeddedMediaPlayer emp;
	public Gui(final String title) {
		super(title);
		this.setSize(1150, 600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
		mContainer = this.getContentPane();
		mContainer.setLayout(new BorderLayout(8, 6));
		mContainer.setBackground(Color.BLACK);
	}

	static void init() {
		
		// Top
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.black);
		// Side - init
		townSidePanel = new TownSidePanel();
		// Middle - init
		mainPage = new FirstTown();
		inn = new Inn();
		school = new School();
		market = new Market();
		// bottom - init
		bottomPanel = new BottomPanel();

		// Setting
		mainPage.setFocusable(true);
		mainPage.setOpaque(true);
		mainPage.setBorder(new LineBorder(Color.black, 3));
		// Add to Container
		mContainer.add(topPanel, BorderLayout.NORTH);
		mContainer.add(mainPage);
		mContainer.add(TownSidePanel.sidePanel, BorderLayout.EAST);
		mContainer.add(BottomPanel.bottomPanel, BorderLayout.SOUTH);
		// 背景音樂
		/*
		 * try { audioIn = AudioSystem .getAudioInputStream(new
		 * File("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/music/firstTown.wav")); Clip clip
		 * = AudioSystem.getClip(); clip.open(audioIn); clip.start();
		 * clip.loop(Clip.LOOP_CONTINUOUSLY); } catch (UnsupportedAudioFileException |
		 * IOException | LineUnavailableException e1) { e1.printStackTrace(); }
		 */
	}

	public static void resetPannel(Integer which) {
		switch (which) {
			case 1:
				showBattleSidePanel();
				showBattleMainPanel();
				break;
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

	public static void showTownSidePanel() {
		townSidePanel = new TownSidePanel();
		TownSidePanel.sidePanel.setVisible(true);
		TownSidePanel.sidePanel.setFocusable(true);
		//
		BattleSidePanel.sidePanel.setVisible(false);
		//
		mContainer.remove(BattleSidePanel.sidePanel);
		//
		mContainer.add(TownSidePanel.sidePanel, BorderLayout.EAST);
		mContainer.validate();
		TownSidePanel.sidePanel.repaint();
	}

	static void showBattleSidePanel() {
		battleSidePanel = new BattleSidePanel();
		BattleSidePanel.sidePanel.setVisible(true);
		//
		TownSidePanel.sidePanel.setVisible(false);
		//
		mContainer.remove(TownSidePanel.sidePanel);
		//
		mContainer.add(BattleSidePanel.sidePanel, BorderLayout.EAST);
		mContainer.validate();
		BattleSidePanel.sidePanel.repaint();
	}

	static void showBattleMainPanel() {
		advBackground = new AdvantureBackground(mContainer.getWidth(), mContainer.getHeight());
		AdvantureBackground.advPanel.setVisible(true);
		//
		mainPage.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		inn.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(mainPage);
		mContainer.remove(school);
		mContainer.remove(inn);
		//
		mContainer.add(AdvantureBackground.advPanel);
		mContainer.validate();
		AdvantureBackground.advPanel.repaint();
	}

	static void showInn() {
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

	static void showSchool() {
		school.resetCdAmount();
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

	static void showMarket() {
		market.resetAmount();
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

	static void showTown() {
		mainPage.setVisible(true);
		//
		AdvantureBackground.advPanel.setVisible(false);
		inn.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		advBackground.setVisible(false);
		//
		mContainer.remove(AdvantureBackground.advPanel);
		mContainer.remove(market);
		mContainer.remove(inn);
		mContainer.remove(school);
		//
		mContainer.add(mainPage);
		mContainer.invalidate();
		mainPage.repaint();
	}

	public static void reset() {
		player = new Player();
		mContainer.removeAll();
		init();
		mContainer.validate();
		mContainer.repaint();
	}

	static void playVideo() {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(main));
		emp.setVideoSurface(mpf.newVideoSurface(c));
		emp.setEnableMouseInputHandling(false);
		emp.setEnableKeyInputHandling(false);
		emp.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void finished(MediaPlayer mediaPlayer) {
				main.remove(p);
				init();
				main.validate();
				mContainer.repaint();
			}
			@Override
			public void error(MediaPlayer mediaPlayer) {
				main.remove(p);
				init();
				main.validate();
				mContainer.repaint();
			}
		});
		String file = "open.wmv";
		emp.prepareMedia(file);
		emp.play();
	}

	static void stopVideo()
	{
		emp.stop();
		emp.release();
	}

	public static void main(String[] args) {

		//
		player = new Player();
		// 要先 init player
		main = new Gui("Test");
		main.setVisible(true);
		
		c = new Canvas();
		c.setBackground(Color.BLACK);
		c.setFocusable(true);
		c.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				stopVideo();
				main.remove(p);
				main.validate();			
				init();
				main.validate();
				mContainer.repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(c);
		main.add(p);
		playVideo();		
	}

}
