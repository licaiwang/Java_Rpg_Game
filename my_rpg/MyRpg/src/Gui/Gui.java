package Gui;

import javax.sound.sampled.AudioInputStream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import Basic.Player;
import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.MusicHelper;
import Gui.Town.FirstTown;
import Gui.Town.Inn;
import Gui.Town.Market;
import Gui.Town.School;
import Gui.Town.Storage;
import Gui.Town.TownSidePanel;
import Magic.MagicBase;
import Skill.BattleSkillBase;
import item.Item;
import phase.BattlePhase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

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

  /** 
		* 
		* @author  Rorschach
		* 
		* 程式的進入口
		* 
		* 顯示城鎮的地方
		*  
		**
		*/
public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Gui main;
	public static Player player;
	public static Container mContainer;
	static FirstTown mainPage;
	static Market market;
	static Storage storage;
	static Inn inn;
	static School school;
	static BattleSidePanel battleSidePanel;
	static AdvantureBackground advBackground;
	static BottomPanel bottomPanel;
	static TownSidePanel townSidePanel;
	static Canvas c;
	static JPanel p;
	static EmbeddedMediaPlayer emp;
	static AudioInputStream audioIn;
	static WelcomPage welcomPage;
	public Gui(final String title) {
		super(title);
		this.setSize(1280, 840);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
		this.setResizable(false);
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
		storage = new Storage();
		market = new Market();
		// bottom - init
		bottomPanel = new BottomPanel();
		// Setting
		mainPage.setFocusable(true);
		mainPage.setOpaque(true);
		mainPage.setBorder(new LineBorder(Color.BLACK, 3));
		// Add to Container
		mContainer.add(topPanel, BorderLayout.NORTH);
		mContainer.add(mainPage);
		mContainer.add(townSidePanel, BorderLayout.EAST);
		mContainer.add(BottomPanel.bottomPanel, BorderLayout.SOUTH);

	}

	public static void resetPannel(Integer which) {
		switch (which) {
			case 1:
				BottomPanel.readText("Gate");
				BottomPanel.resetTextArea();
				showBattleSidePanel();
				showBattleMainPanel();
				break;
			case 2:
				showInn();
				Thread playMusic = new MusicHelper("sleep.wav");
				playMusic.start();
				BottomPanel.readText("inn");
				BottomPanel.resetTextArea();
				break;
			case 3:
				showSchool();
				BottomPanel.readText("school");
				BottomPanel.resetTextArea();
				break;
			case 4:
				showStorage();
				BottomPanel.readText("storage");
				BottomPanel.resetTextArea();
				break;
			case 5:
				showMarket();
				BottomPanel.readText("market");
				BottomPanel.resetTextArea();
				break;
			case 6:
				BattlePhase.to_M_damage = -1;
				showTown();
				BottomPanel.readText("FirstTown");
				BottomPanel.resetTextArea();
				break;
		}
	}

	public static void showTownSidePanel() {
		townSidePanel = new TownSidePanel();
		townSidePanel.setVisible(true);
		townSidePanel.setFocusable(true);
		//
		BattleSidePanel.sidePanel.setVisible(false);
		//
		mContainer.remove(BattleSidePanel.sidePanel);
		//
		mContainer.add(townSidePanel, BorderLayout.EAST);
		mContainer.validate();
		townSidePanel.repaint();
	}

	static void showBattleSidePanel() {
		battleSidePanel = new BattleSidePanel();
		battleSidePanel.setVisible(true);
		//
		townSidePanel.setVisible(false);
		//
		mContainer.remove(townSidePanel);
		//
		mContainer.add(battleSidePanel, BorderLayout.EAST);
		mContainer.validate();
		battleSidePanel.repaint();
	}

	static void showBattleMainPanel() {
		advBackground = new AdvantureBackground(mContainer.getWidth(), mContainer.getHeight());
		AdvantureBackground.advPanel.setVisible(true);
		//
		mainPage.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		storage.setVisible(false);
		inn.setVisible(false);
		//
		mContainer.remove(storage);
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
		storage.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(storage);
		mContainer.remove(mainPage);
		mContainer.remove(school);
		//
		mContainer.add(inn);
		mContainer.invalidate();
		inn.repaint();
	}

	static void showSchool() {
		School.resetCdAmount();
		school.setVisible(true);
		school.setFocusable(true);
		//
		mainPage.setVisible(false);
		inn.setVisible(false);
		market.setVisible(false);
		storage.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(storage);
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
		storage.setVisible(false);
		//
		mContainer.remove(school);
		mContainer.remove(storage);
		mContainer.remove(mainPage);
		mContainer.remove(inn);
		//
		mContainer.add(market);
		mContainer.invalidate();
		market.repaint();
	}

	static void showStorage() {
		storage.setBackground(Color.YELLOW);
		storage.setVisible(true);
		storage.setFocusable(true);
		//
		mainPage.setVisible(false);
		inn.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(school);
		mContainer.remove(mainPage);
		mContainer.remove(inn);
		//
		mContainer.add(storage);
		mContainer.invalidate();
		storage.repaint();
	}

	static void showTown() {

		mainPage.setVisible(true);
		//
		inn.setVisible(false);
		school.setVisible(false);
		market.setVisible(false);
		storage.setVisible(false);
		//
		mContainer.remove(market);
		mContainer.remove(inn);
		mContainer.remove(school);
		mContainer.remove(storage);
		//
		mContainer.add(mainPage);
		mContainer.invalidate();
		mainPage.repaint();
	}

	public static void reset() throws IOException {
		Player.UpgradePlayer(Integer.valueOf(Player.LEVEL));
		MusicHelper.playBackgroundMusic("firstTown");
		AdvantureBackground.isBoss = false;
		mContainer.removeAll();
		init();
		mContainer.validate();
		mContainer.repaint();
		battleSidePanel.validate();
		battleSidePanel.repaint();
	}

	static void playVideo() {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), ResReader.Current_Dic+("/res/video/VideoLAN/VLC"));
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(main));
		emp.setVideoSurface(mpf.newVideoSurface(c));
		emp.setEnableMouseInputHandling(false);
		emp.setEnableKeyInputHandling(false);
		emp.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void finished(MediaPlayer mediaPlayer) {
				stopVideo();
				showMain();
			}

			@Override
			public void error(MediaPlayer mediaPlayer) {
				stopVideo();
				showMain();
			}		
		});
		try{
			emp.prepareMedia(ResReader.Current_Dic+("/res/video/open.wmv"));
			emp.play();
		}catch(Exception e)
		{
			e.printStackTrace();
			stopVideo();
			showMain();
		}
	}

	static void stopVideo() {
		emp.stop();
		emp.release();		
	}
	static void showMain()
	{
		main.remove(p);
		main.validate();
		main.repaint();
		init();
		mContainer.validate();
		mContainer.repaint();
		MusicHelper.playBackgroundMusic("firstTown");
	}

	static void createVideo()
	{

		MusicHelper.stopBackgroundMusic();
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
				showMain();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		// 把畫布加進 JPanel 裡
		p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(c);
		main.remove(welcomPage);
		main.add(p);
		main.validate();
		main.repaint();
		playVideo();
	}

	public static void main(String[] args) throws IOException {
		// 先 init player 跟遊戲的所有數據
		player = new Player();
		try {
			Player.readAllData();
			Item.readAllData();
			BattleSkillBase.readAllData();
			BattleSkillBase.initSkill();
			MagicBase.readAllData();
			MagicBase.initMagic();

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// 生成畫開頭動畫的畫布
	
		main = new Gui("泰格達");
		main.setVisible(true);
		MusicHelper.playBackgroundMusic("menu");
		welcomPage =  new WelcomPage();
		main.add(welcomPage);
		main.setFocusable(true);
		main.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
				{
					createVideo();	
					main.removeKeyListener(this);
					main.setFocusable(false);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		main.validate();
		main.repaint();
	}
}
