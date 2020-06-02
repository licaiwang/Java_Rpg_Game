package monster;

import Basic.Power;
import java.util.Random;

/**
 * 
 * @author Rorschach
 * 
 * 怪物的基本資訊與生成方法
 * 
 **
 */
public class Monster extends Power {
    public static Integer HP;
    public static Integer ATK;
    public static Integer DEF;
    public static Integer SP_ATK;
    public static Integer SP_DEF;
    public static Integer SPEED;
    public static Integer LUCK;
    public static Integer MP;
    public static Integer COIN;
    public static Integer EXP;
    public static Integer LEVEL;
    public Monster(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp)
    {
        // 這個是拿來建 Boss 的，數據是固定的
        HP = hp;
        ATK = atk;
        DEF = def;
        SP_ATK = sp_atk;
        SP_DEF = sp_def;
        SPEED = speed;
        LUCK = luck;
        MP = mp; 
        COIN = coin;
        EXP = exp; 
    }



    public Monster(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp,
            int level, int rarity) {
        // 這個是拿來建 一般的怪物，數據是根據稀有度宇等級雜湊的
        Random r = new Random();
        LEVEL = r.nextInt(level);
        COIN = ((r.nextInt(10) + 2*LEVEL) * (1 + (rarity / 10)));
        EXP = ((r.nextInt(5) + 2*LEVEL) * (1 + (rarity / 10)));
        HP = ((hp + LEVEL) * (1 + (rarity / 100)));
        ATK = ((atk + LEVEL) * (1 + (rarity / 100)));
        DEF = ((def + LEVEL) * (1 + (rarity / 100)));
        SP_ATK = ((sp_atk + LEVEL) * (1 + (rarity / 100)));
        SP_DEF = ((sp_def + LEVEL) * (1 + (rarity / 100)));
        SPEED = ((speed + LEVEL) * (1 + (rarity / 100)));
        LUCK = ((luck + LEVEL) * (1 + (rarity / 100)));
        MP = ((mp + LEVEL) * (1 + (rarity / 100)));
    }


    public static Integer DropCoin(Integer level) {
        //TODO：看能不能擴充成計算掉落物
        //另外一種計算掉落錢的方法
        Random r = new Random();
        if (level < 5) {
            Integer money = r.nextInt(10);
            return money;
        }
        if (level >= 5 && level < 10) {
            Integer money = r.nextInt(50) + 5;
            return money;
        }
        if (level >= 10 && level < 15) {
            Integer money = r.nextInt(300) + 75;
            return money;
        }
        if (level >= 15 && level < 20) {
            Integer money = r.nextInt(600) + 150;
            return money;
        }
        if (level >= 20 && level < 25) {
            Integer money = r.nextInt(1200) + 300;
            return money;
        }
        if (level >= 25 && level < 30) {
            Integer money = r.nextInt(2400) + 600;
            return money;
        }
        return 0;
    }


    public static Integer getHp()
    {
        return HP;
    }
    public static Integer[] getAll() {
        Integer[] all = { Monster.HP, Monster.ATK, Monster.DEF, Monster.SP_ATK, Monster.SP_DEF, Monster.SPEED,
                Monster.LUCK, Monster.MP, Monster.COIN, Monster.EXP, Monster.LEVEL };
        return all;
    }

}