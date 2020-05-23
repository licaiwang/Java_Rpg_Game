package Magic;

import Basic.Player;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.MusicHelper;
import monster.Monster;

public class Magic{


    /*
     *   回傳技能
     * 
     *   Type1 - 純粹計算傷害
     *   Type2 - 臨時增強數值
     *   Type3 - 造成傷害並附加狀態
     *   Type4 - 特殊 (目前想不到)
     * 
    */

    public static int CommonMagic(Integer id)
    {
        switch (id) {
            case 1:
                return fireBall();
            case 2:
                return meditation();
            case 3:
                return Self_Combustion();      
            case 4:
                return heal();   
            case 5:
                return raiseFire();
            case 6:
                return thunderSpire();   
            case 7:
                return peaceWalk();  
            case 8:
                return armorExplode();  
        } 
        return 0;
    }

	private static int armorExplode() {
        return 0;
    }

    private static int peaceWalk() {
        return 0;
    }

    private static int thunderSpire() {
        return 0;
    }

    private static int raiseFire() {
        return 0;
    }

    private static int heal() {
        return 0;
    }

    private static int Self_Combustion() {
        return 0;
    }

    private static int meditation() {
        return 0;
    }

    private static int fireBall() {

        if(checkMp(MagicBase.in_use_cost[0]))
        {
            Thread playMusic = new MusicHelper("/magic/fireball.wav");
            playMusic.start();
            BattleSidePanel.resetMp();
            return Player.SP_ATK - Monster.SP_DEF;
        }
        return 0;
          
    }

    private static boolean checkMp(int cost) {
        if( Player.MP >= cost)
        {
            Player.MP -= cost;
            return true;
        }return false;
    }

    public static int UnCommonMagic(Integer integer) {
        return integer;
	}

	public static int MasterMagic(Integer integer) {
        return integer;
	}
}