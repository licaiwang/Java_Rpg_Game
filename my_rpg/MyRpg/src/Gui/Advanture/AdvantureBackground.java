package Gui.Advanture;

import javax.swing.*;

import Gui.Advanture.event.BackTown;
import Gui.Advanture.event.Event1;
import Gui.Advanture.event.Event2;
import Gui.Advanture.event.Event3;
import Gui.Advanture.event.Treasure;

public class AdvantureBackground extends JPanel {
    /*
     * 
     * 註冊以及控制旅行時候主畫面
     * 
     * 事件 - 30%   寶物  - 10%   怪物 - 60%
     * 
     */
    private static final long serialVersionUID = 1L;
    public static JPanel advPanel;
    static Gate gate;
    static Road road;
    static Event1 event1;
    static Event2 event2;
    static Event3 event3;
    static Treasure treasure;
    static BackTown backTown;
    static Battle battle;
    static Dead dead;

    public AdvantureBackground() {
        super();
    }

    public AdvantureBackground(int width, int height) {
        super();
        advPanel = new JPanel();
        gate = new Gate();
        event1 = new Event1();
        event2 = new Event2();
        event3 = new Event3();
        treasure = new Treasure();
        dead = new Dead();
       
        advPanel.setLayout(new BoxLayout(advPanel, BoxLayout.Y_AXIS));
        advPanel.add(gate);
    }

    public static void showRoad() {
        advPanel.removeAll();
        road = new Road();
        advPanel.add(road);
        advPanel.validate();
        road.repaint();
    }

    static void showBattle() {
        advPanel.removeAll();
        battle = new Battle();
        advPanel.add(battle);
        advPanel.validate();
        battle.repaint();
    }

    static void showCampus() {
        advPanel.removeAll();
        backTown = new BackTown();
        advPanel.add(backTown);
        advPanel.validate();
        backTown.repaint();
    }
    static void showDead()
    {
        advPanel.removeAll();
        advPanel.add(dead);
        advPanel.validate();
        dead.repaint();
    }
    static void showRandom(int id) {
        switch (id) {
            case 1:
                advPanel.removeAll();
                advPanel.add(event1);
                advPanel.validate();
                event1.repaint();
                break;
            case 2:
                advPanel.removeAll();
                advPanel.add(event2);
                advPanel.validate();
                event2.repaint();
                break;
            case 3:
                advPanel.removeAll();
                advPanel.add(event3);
                advPanel.validate();
                event3.repaint();
                break;
            case 4:
                advPanel.removeAll();
                advPanel.add(treasure);
                advPanel.validate();
                treasure.repaint();
                break;
            case 5:
                showBattle();
                break;
            case 6:
                showRoad();
                break;


        }
    }



}