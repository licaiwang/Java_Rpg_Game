package Magic;

import Basic.Player;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.MusicHelper;
import phase.BattleTemp;

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

    // TODO:　實作

    public static int UnCommonMagic(Integer integer) {
        return 20;
	}

	public static int MasterMagic(Integer integer) {
        return 50;
	}



	private static int armorExplode() {
        if(checkMp(Player.MP))
        {
            MagicBase.IsDamage = true;
            MagicBase.IsEnhance = false;
            BattleTemp.M_DEF -= (Player.Max_MP/2);
        }
        return Player.MP;
    }

    private static int peaceWalk() {
        if(checkMp(7))
        {
            MagicBase.IsDamage = true;
            MagicBase.IsEnhance = false;
            Thread playMusic = new MusicHelper("/magic/hell.wav");
            playMusic.start();
            BattleTemp.M_SPEED -= 10;
        }
        return 0;
    }

    private static int thunderSpire() {
        if(checkMp(7))
        {
            MagicBase.IsDamage = true;
            MagicBase.IsEnhance = false;
            Thread playMusic = new MusicHelper("/magic/lightning.wav");
            playMusic.start();
            BattleTemp.M_SP_DEF -= 3;
            return Player.SP_ATK ;
        }
        return 0;
    }

    private static int raiseFire() {
        if(checkMp(5))
        {
            MagicBase.IsDamage = false;
            MagicBase.IsEnhance = true;
            Thread playMusic = new MusicHelper("/magic/fireball.wav");
            playMusic.start();
            BattleTemp.startCounter("atk",3);
            BattleTemp.ATK = (int) (BattleTemp.ATK * 1.2);
        }
        return 0;
    }

    private static int heal() {
        if(checkMp(5))
        {   
            MagicBase.IsDamage = false;
            MagicBase.IsEnhance = true;
            if(Player.HP < Player.Max_HP - 6)
            {   
                Player.HP += 7;
                BattleSidePanel.resetHp();
            }else{
                Player.HP = Player.Max_HP;
            }
            Thread playMusic = new MusicHelper("/magic/hell.wav");
            playMusic.start();
        }
        return 0;
    }

    private static int Self_Combustion() {
        MagicBase.IsDamage = false;
        MagicBase.IsEnhance = true;
        Thread playMusic = new MusicHelper("/magic/fireball.wav");
        playMusic.start();
        Player.Max_HP -= 2;
        Player.HP = Player.Max_HP;
        BattleSidePanel.resetHp();
        BattleTemp.startCounter("atk",2);
        BattleTemp.ATK = (int) (BattleTemp.ATK * 1.5);
        return 0;
    }

    private static int meditation() {
        if(Player.MP < Player.Max_MP)
        {  
            MagicBase.IsDamage = false;
            MagicBase.IsEnhance = true;
            Thread playMusic = new MusicHelper("/magic/psy_up.wav");
            playMusic.start();
            Player.MP += 15;
            BattleSidePanel.resetMp();
        }
        return 0;
    }

    private static int fireBall() {
        if(checkMp(5))
        {
            MagicBase.IsDamage = true;
            MagicBase.IsEnhance = false;
            Thread playMusic = new MusicHelper("/magic/fireball.wav");
            playMusic.start();
            return Player.SP_ATK - BattleTemp.M_SP_DEF;
        }
        return 0;
    }

    private static boolean checkMp(int cost) {
        if( Player.MP >= cost)
        {
            Player.MP -= cost;
            BattleSidePanel.resetMp();
            return true;
        }return false;
    }


}