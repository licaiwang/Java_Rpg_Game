package Skill;

import java.util.Random;

import Basic.Player;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.MusicHelper;
import phase.BattleTemp;

public class Skill {


    /*
     *   回傳技能傷害並執行技能
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
            case 9:
                return Basic_Flying_Knife();     
        /*
        向下擴充
        */ 
        }
        return 0;
    }

	public static int UnCommonSkill(Integer id) {

        switch (id) {
            case 10:
                return UnCommon_Bubba_Cut();
            case 11:
                return UnCommon_BiteBlood_Sword();
            case 12:
                return UnCommon_Lightning_Slash();      
            case 13:
                return UnCommon_Never_Give_UP();    
        /*
        向下擴充
        */ 
        }
        return 0;
	}


    public static int MasterSkill(Integer id) {
        switch (id) {
            case 14:
                return Master_Sword_Dance();
            case 15:
                return Master_Goust_Cut();
        /*
        向下擴充
        */ 
        }
        return 0;
	}
    private static int Basic_Flying_Knife() {
        return BattleTemp.SPEED - BattleTemp.M_DEF;
    }

    private static int Basic_Iai_Slash() {
        Thread playMusic = new MusicHelper("/skill/slash.wav");
        playMusic.start();
        BattleTemp.LUCK += BattleTemp.SPEED;
        return (int) Math.round(BattleTemp.ATK) - BattleTemp.M_DEF;  
    }

    private static int Basic_Roll() {
        Thread playMusic = new MusicHelper("/skill/psy_up.wav");
        playMusic.start();
        BattleSkillBase.IsDamage = false;
        BattleSkillBase.IsEnhance = true;
        BattleTemp.startCounter("speed",2);
        BattleTemp.SPEED = (int) (BattleTemp.SPEED * 1.8);
        return 0;
    }

    private static int Basic_Focus() {
        Thread playMusic = new MusicHelper("/skill/psy_up.wav");
        playMusic.start();
        BattleSkillBase.IsDamage = false;
        BattleSkillBase.IsEnhance = true;
        BattleTemp.startCounter("luck",2);
        BattleTemp.LUCK = (int) (BattleTemp.LUCK * 1.25);
        return 0;
    }

    private static int Basic_Shuriken() {
        Thread playMusic = new MusicHelper("/skill/shuriken.wav");
        playMusic.start();
        BattleSkillBase.Strik = 2;
        return (int) Math.round(BattleTemp.ATK * 0.35) - BattleTemp.M_DEF;  
    }

    public static int Basic_Atk()
    {
        Thread playMusic = new MusicHelper("/skill/katana1.wav");
        playMusic.start();
        BattleSkillBase.Strik = 0;
        return BattleTemp.ATK - BattleTemp.M_DEF;     
    }
    public static int Basic_Double_Atk()
    {
        Thread playMusic = new MusicHelper("/skill/double_strike.wav");
        playMusic.start();
        BattleSkillBase.Strik = 1;
        return (int) Math.round(BattleTemp.ATK * 0.85) - BattleTemp.M_DEF;   
    }
    public static int Basic_Penetrate()
    {
        Thread playMusic = new MusicHelper("/skill/penetrate.wav");
        playMusic.start();
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

    private static int UnCommon_Never_Give_UP() {
        BattleTemp.startCounter("def",3);
        BattleTemp.startCounter("sp_def",3);
        BattleSkillBase.IsDamage = false;
        BattleSkillBase.IsEnhance = true;
        return 0;
    }

    private static int UnCommon_Lightning_Slash() {
        Thread playMusic = new MusicHelper("/skill/thunder.wav");
        playMusic.start();
        BattleTemp.startCounter("speed",1);
        BattleTemp.SPEED = (int) (BattleTemp.SPEED * 1.75);
        return BattleTemp.ATK * 2 - BattleTemp.M_DEF;
    }

    private static int UnCommon_BiteBlood_Sword() {
        if(Player.MP>0)
        {
            Player.MP -= 5;
            Player.HP += (BattleTemp.ATK / 4);
            BattleSidePanel.resetHp();
            BattleSidePanel.resetMp();
        }
        return BattleTemp.ATK - BattleTemp.M_DEF;  
    }

    private static int UnCommon_Bubba_Cut() {
        BattleSkillBase.Strik = 2;
        return (int) Math.round(BattleTemp.ATK * 0.75) - BattleTemp.M_DEF;  
    }


    private static int Master_Sword_Dance() {
        BattleSkillBase.Strik = 4;
        return (BattleTemp.ATK+BattleTemp.SPEED)/2 - BattleTemp.M_DEF;
    }

    private static int Master_Goust_Cut() {
        Random r = new Random();
        int opt = r.nextInt(2);
        int o_1[] = {0,1};
        switch (o_1[opt]) {
            case 0:
                return BattleTemp.ATK * 2 - BattleTemp.M_DEF;
            case 1:
                opt = r.nextInt(4);
                int o_2[] = {0,1,2,3};
                switch (o_2[opt]) {
                    case 3:
                        return BattleTemp.ATK * 2 + BattleTemp.ATK * 3 + BattleTemp.ATK * 5 - BattleTemp.M_DEF;           
                    default:
                        return BattleTemp.ATK * 2 + BattleTemp.ATK * 3 - BattleTemp.M_DEF;
                }            
        }
        return 0;
    }


}

