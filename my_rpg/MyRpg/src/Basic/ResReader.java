package Basic;

import javax.swing.ImageIcon;

import Gui.Avatar;

import java.awt.Image;

public class ResReader {
    /*
     *
     *  這裡存放所有照片的資源，都是固定不會改變的
     * 
     * 
    */
    public static final String path = ResReader.class.getClassLoader().getResource(".").getPath();

    // Avatar
    public static final Image avatar = new ImageIcon(path + "res/avatar.png").getImage();
    // ButtonFram 
    public static final Image buttonFrame = new ImageIcon(path +"res/btnFrame.png").getImage();
    // event 1
    public static final Image event1_1 = new ImageIcon(path+"res/battlePanel/event/event1_1.jpg").getImage();
    public static final Image event1_1_1 = new ImageIcon(path+"res/battlePanel/event/event1_1_1.jpg").getImage();
    public static final Image event1_1_2 = new ImageIcon(path+"res/battlePanel/event/event1_1_2.jpg").getImage();
    public static final Image event1_1_3 = new ImageIcon(path+"res/battlePanel/event/event1_1_3.jpg").getImage();
    public static final Image event1_1_2_1 = new ImageIcon(path+"res/battlePanel/event/event1_1_2_1.jpg").getImage();
    public static final Image event1_1_2_2 = new ImageIcon(path+"res/battlePanel/event/event1_1_2_2.jpg").getImage();
    // event 2
    public static final Image event1_2 = new ImageIcon(path+"res/battlePanel/event/event1_2.jpg").getImage();
    public static final Image event1_2_1 = new ImageIcon(path+"res/battlePanel/event/event1_2_1.jpg").getImage();
    public static final Image event1_2_2 = new ImageIcon(path+"res/battlePanel/event/event1_2_2.jpg").getImage();
    // event 3
    public static final Image event1_3 = new ImageIcon(path+"res/battlePanel/event/event1_3.jpg").getImage();
    public static final Image event1_3_1 = new ImageIcon(path+"res/battlePanel/event/event1_3_1.jpg").getImage();
    public static final Image event1_3_2 = new ImageIcon(path+"res/battlePanel/event/event1_3_2.jpg").getImage();
    // Treasure
    public static final Image treasure = new ImageIcon(path+"res/battlePanel/event/treasure.jpg").getImage();
    public static final Image treasure_1 = new ImageIcon(path+"res/battlePanel/event/treasure_1.jpg").getImage();
    public static final Image treasure_2 = new ImageIcon(path+"res/battlePanel/event/treasure_2.jpg").getImage();
    public static final Image treasure_3 = new ImageIcon(path+"res/battlePanel/event/treasure_3.jpg").getImage();
    // Battle
    public static final Image battleBase = new ImageIcon(path+"res/battlePanel/battleBase.jpg").getImage();
    // Side
    public static final Image side = new ImageIcon(path+"res/side.png").getImage();
    // Died 
    public static final Image youDied = new ImageIcon(path+"res/battlePanel/you_died.jpg").getImage();
    // Gate
    public static final Image gate = new ImageIcon(path+"res/battlePanel/gate.jpg").getImage();
    // Road
    public static final Image road = new ImageIcon(path+"res/battlePanel/road.jpg").getImage();
     // BackTown
    public static final Image compass = new ImageIcon(path+"res/battlePanel/back.jpg").getImage();
    // First Town
    public static final Image firstTown = new ImageIcon(path+"res/first_town.jpg").getImage();
    //  Inn 
    public static final Image inn = new ImageIcon(path+"res/sleep.gif").getImage();
    //  Market 
    public static final Image market =new ImageIcon(path+"res/market.jpg").getImage();
    // School
    public static final Image school = new ImageIcon(path+"res/school.jpg").getImage();
    // Storage
    public static final Image storage = new ImageIcon(path+"res/storage.jpg").getImage();

    // End
    public static final Image end = new ImageIcon(path+"res/end.jpg").getImage();

    // BattleSide - icon
    public static final ImageIcon heartIcon = new ImageIcon(path + "res/battlePanel/heart.png");
    public static final ImageIcon mpIcon = new ImageIcon(path + "res/battlePanel/mp.png");
    public static final ImageIcon expIcon = new ImageIcon(path + "res/battlePanel/exp.png");
    public static final ImageIcon travelIcon = new ImageIcon(path + "res/battlePanel/travel.png");
    public static final ImageIcon lvIcon = new ImageIcon(path + "res/battlePanel/lv.png");
    public static final ImageIcon CoinIcon = new ImageIcon(path + "res/battlePanel/coin.png");

    
    // 這個頭像也不會改變
    public static final Avatar topInsidePanel = new Avatar();
     
    // CampFire
    public static final Image campFire = new ImageIcon(path+"res/battlePanel/event/campfire.gif").getImage();
    // Fortess
    public static final Image fortress = new ImageIcon(path+"res/battlePanel/event/puzzle/frame.jpg").getImage();

    // Boss_1
    public static final Image boss_init = new ImageIcon(path+"res/battlePanel/boss/init.gif").getImage();
    public static final Image boss_1_background = new ImageIcon(path + "res/battlePanel/boss/boss_1_background.jpg").getImage();

}
    