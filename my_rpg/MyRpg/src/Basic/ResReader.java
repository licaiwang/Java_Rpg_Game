package Basic;
import javax.swing.ImageIcon;
import Gui.Advanture.Battle;
import Gui.Advanture.BattleSidePanel;
import Gui.Advanture.Dead;
import Gui.Advanture.Gate;
import Gui.Advanture.Road;
import Gui.Advanture.event.BackTown;
import Gui.Advanture.event.CampFire;
import Gui.Advanture.event.Event1;
import Gui.Advanture.event.Event2;
import Gui.Advanture.event.Event3;
import Gui.Advanture.event.Fortress;
import Gui.Advanture.event.Treasure;
import monster.Boss.Boss_1;

import java.awt.Image;
import java.io.File;
    /**
     * 
     * 
     * @author  Rorschach
     * 
     * 這裡存放所有照片的資源，都是固定不會改變的
     * 
     **
     */
public class ResReader {
    public static final String Current_Dic = getCurrentDirectory();
    public static final String DEFAULT_PATH = "res/battlePanel/";
    public static final ClassLoader loader = ResReader.class.getClassLoader();

    // Avatar
    public static final Image avatar = new ImageIcon(loader.getResource("res/avatar.png")).getImage();

    // ButtonFram
    public static final Image buttonFrame = new ImageIcon(loader.getResource("res/btnFrame.png")).getImage();

    // event 1
    public static final Image event1_1 = new ImageIcon(
            Event1.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_1.jpg")).getImage();
    public static final Image event1_1_1 = new ImageIcon(
            Event1.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_2.jpg")).getImage();
    public static final Image event1_1_2 = new ImageIcon(
            Event1.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_3.jpg")).getImage();
    public static final Image event1_1_3 = new ImageIcon(
            Event1.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_1.jpg")).getImage();
    public static final Image event1_1_2_1 = new ImageIcon(
            Event1.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_2_1.jpg")).getImage();
    public static final Image event1_1_2_2 = new ImageIcon(
            Event1.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_2_2.jpg")).getImage();
    // event 2
    public static final Image event1_2 = new ImageIcon(
            Event2.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_2.jpg")).getImage();
    public static final Image event1_2_1 = new ImageIcon(
            Event2.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_2_1.jpg")).getImage();
    public static final Image event1_2_2 = new ImageIcon(
            Event2.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_2_2.jpg")).getImage();
    // event 3
    public static final Image event1_3 = new ImageIcon(
            Event3.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_3.jpg")).getImage();
    public static final Image event1_3_1 = new ImageIcon(
            Event3.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_3_1.jpg")).getImage();
    public static final Image event1_3_2 = new ImageIcon(
            Event3.class.getClassLoader().getResource(DEFAULT_PATH + "event/event1_3_2.jpg")).getImage();
    // Treasure

    public static final Image treasure = new ImageIcon(
            Treasure.class.getClassLoader().getResource(DEFAULT_PATH + "event/treasure.jpg")).getImage();
    public static final Image treasure_1 = new ImageIcon(
            Treasure.class.getClassLoader().getResource(DEFAULT_PATH + "event/treasure_1.jpg")).getImage();
    public static final Image treasure_2 = new ImageIcon(
            Treasure.class.getClassLoader().getResource(DEFAULT_PATH + "event/treasure_2.jpg")).getImage();
    public static final Image treasure_3 = new ImageIcon(
            Treasure.class.getClassLoader().getResource(DEFAULT_PATH + "event/treasure_3.jpg")).getImage();
    // Battle

    public static final Image battleBase = new ImageIcon(
            Battle.class.getClassLoader().getResource(DEFAULT_PATH + "battleBase.jpg")).getImage();
    // Side
    // TODO: check if work
    public static final Image side = new ImageIcon(BattleSidePanel.class.getClassLoader().getResource("res/side.png"))
            .getImage();

    // Died
    public static final Image youDied = new ImageIcon(
            Dead.class.getClassLoader().getResource(DEFAULT_PATH + "you_died.jpg")).getImage();
    // Gate
    public static final Image gate = new ImageIcon(Gate.class.getClassLoader().getResource(DEFAULT_PATH + "gate.jpg"))
            .getImage();
    // Road
    public static final Image road = new ImageIcon(Road.class.getClassLoader().getResource(DEFAULT_PATH + "road.jpg"))
            .getImage();
    // BackTown
    public static final Image compass = new ImageIcon(
            BackTown.class.getClassLoader().getResource(DEFAULT_PATH + "back.jpg")).getImage();

    // First Town
    public static final Image firstTown = new ImageIcon(loader.getResource("res/first_town.jpg")).getImage();
    // Inn
    public static final Image inn = new ImageIcon(loader.getResource("res/sleep.gif")).getImage();
    // Market
    public static final Image market = new ImageIcon(loader.getResource("res/market.jpg")).getImage();
    // School
    public static final Image school = new ImageIcon(loader.getResource("res/school.jpg")).getImage();
    // Storage
    public static final Image storage = new ImageIcon(loader.getResource("res/storage.jpg")).getImage();

    // End
    public static final Image end = new ImageIcon(loader.getResource("res/end.jpg")).getImage();

    // BattleSide - icon
    public static final ImageIcon heartIcon = new ImageIcon(
            BattleSidePanel.class.getClassLoader().getResource(DEFAULT_PATH + "heart.png"));
    public static final ImageIcon mpIcon = new ImageIcon(
            BattleSidePanel.class.getClassLoader().getResource(DEFAULT_PATH + "mp.png"));
    public static final ImageIcon expIcon = new ImageIcon(
            BattleSidePanel.class.getClassLoader().getResource(DEFAULT_PATH + "exp.png"));
    public static final ImageIcon travelIcon = new ImageIcon(
            BattleSidePanel.class.getClassLoader().getResource(DEFAULT_PATH + "travel.png"));
    public static final ImageIcon lvIcon = new ImageIcon(
            BattleSidePanel.class.getClassLoader().getResource(DEFAULT_PATH + "lv.png"));
    public static final ImageIcon CoinIcon = new ImageIcon(
            BattleSidePanel.class.getClassLoader().getResource(DEFAULT_PATH + "coin.png"));

    // CampFire
    public static final Image campFire = new ImageIcon(
            CampFire.class.getClassLoader().getResource(DEFAULT_PATH + "event/campfire.gif")).getImage();
    // Fortess
    public static final Image fortress = new ImageIcon(
            Fortress.class.getClassLoader().getResource(DEFAULT_PATH + "event/puzzle/frame.jpg"))
            .getImage();

    // Boss_1
    public static final Image boss_init = new ImageIcon(Boss_1.class.getClassLoader().getResource(DEFAULT_PATH + "boss/init.gif")).getImage();
    public static final Image boss_1_background = new ImageIcon(
            Boss_1.class.getClassLoader().getResource(DEFAULT_PATH + "boss/boss_1_background.jpg")).getImage();


    public static String getCurrentDirectory() {
        // 取得當前目錄
        File now_directory = new File(".");
        String current_path = now_directory.getAbsolutePath().replaceAll("\\.", "");
        return current_path;
    }
}
