package Skill;

import Gui.Helper.MusicHelper;
import phase.BattleTemp;

public class Skill {


    /*
     *   回傳技能
     * 
     *   Type1 - 純粹計算傷害
     *   Type2 - 臨時增強數值
     *   Type3 - 造成傷害並附加狀態
     *   Type4 - 特殊 (目前想不到)
     * 
    */

    public static int CommonSkill(Integer id)
    {
        switch (id) {
            case 1:
                return Basic_Atk();
            case 2:
                return Basic_Double_Atk();
            case 3:
                return Basic_Penetrate();      
            case 4:
                return Basic_Defense();   
            case 5:
                return Basic_Shuriken();   
            case 6:
                return Basic_Focus();   
            case 7:
                return Basic_Roll();   
            case 8:
                return Basic_Iai_Slash();   
        /*
        向下擴充
        */ 
        }
        return 0;
    }

    private static int Basic_Iai_Slash() {
        Thread playMusic = new MusicHelper("/skill/slash.wav");
        playMusic.start();
        BattleTemp.LUCK += BattleTemp.SPEED;
        return (int) Math.round(BattleTemp.ATK);  
    }

    private static int Basic_Roll() {
        return 0;
    }

    private static int Basic_Focus() {
        return 0;
    }

    private static int Basic_Shuriken() {
        Thread playMusic = new MusicHelper("/skill/shuriken.wav");
        playMusic.start();
        BattleSkillBase.Strik = 2;
        return (int) Math.round(BattleTemp.ATK * 0.35 + BattleTemp.SPEED * 0.15);  
    }

    public static int Basic_Atk()
    {
        Thread playMusic = new MusicHelper("/skill/katana1.wav");
        playMusic.start();
        return BattleTemp.ATK - BattleTemp.M_DEF;     
    }
    public static int Basic_Double_Atk()
    {
        Thread playMusic = new MusicHelper("/skill/double_strike.wav");
        playMusic.start();
        BattleSkillBase.Strik = 1;
        return (int) Math.round(BattleTemp.ATK * 0.85);
    }
    public static int Basic_Penetrate()
    {
        return BattleTemp.ATK/2;     
    }

    public static int Basic_Defense()
    {
        Thread playMusic = new MusicHelper("/skill/psy_up.wav");
        playMusic.start();
        BattleSkillBase.IsDamage = false;
        BattleSkillBase.IsEnhance = true;
        BattleTemp.startCounter("def",2);
        BattleTemp.DEF = (int) (BattleTemp.DEF+BattleTemp.DEF * 0.25);
        return 0;
    }

	public static int UnCommonSkill(Integer integer) {
        return integer;
	}

	public static int MasterSkill(Integer integer) {
        return integer;
	}
}