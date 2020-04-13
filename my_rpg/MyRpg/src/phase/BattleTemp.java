package phase;

import java.util.Random;

import Basic.Player;
import monster.Monster;

public class BattleTemp extends Player {
    /*
     * 存旅行時戰鬥的數據，除了 HP 以外其他數值每次進入戰鬥皆會刷新
     */
    public static Integer Hp;
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
    public static Integer M_HP;
    public static Integer M_ATK;
    public static Integer M_DEF;
    public static Integer M_SP_ATK;
    public static Integer M_SP_DEF;
    public static Integer M_SPEED;
    public static Integer M_LUCK;
    public static Integer M_MP;
    public static Integer M_COIN;
    public static Integer M_EXP;
    public static Integer M_LEVEL;

    public static void init() {
        Integer[] Temp_P = Player.getAll();
        Integer[] Temp_M = Monster.getAll();
        HP = Player.HP;
        ATK = Temp_P[1];
        DEF = Temp_P[2];
        SP_ATK = Temp_P[3];
        SP_DEF = Temp_P[4];
        SPEED = Temp_P[5];
        LUCK = Temp_P[6];
        MP = Temp_P[7];
        COIN = Temp_P[8];
        EXP = Temp_P[9];
        LEVEL = Temp_P[10];
        M_HP = Temp_M[0];
        M_ATK = Temp_M[1];
        M_DEF = Temp_M[2];
        M_SP_ATK = Temp_M[3];
        M_SP_DEF = Temp_M[4];
        M_SPEED = Temp_M[5];
        M_LUCK = Temp_M[6];
        M_MP = Temp_M[7];
        M_COIN = Temp_M[8];
        M_EXP = Temp_M[9];
        M_LEVEL = Temp_M[10];
    }

    public static Integer countSpeed() {
        // 如果玩家速度大於怪物回傳 1 否則 0
        if (SPEED > M_SPEED) {
            return 1;
        } else {
            return 0;
        }
    }

    public static Integer countCritical() {
        // 如果玩家幸運高於怪物回傳 1 否則 0
        if (LUCK > M_LUCK) {
            return 1;
        } else {
            return 0;
        }
    }

    public static Boolean Critical() {
        Random r = new Random();
        int check = r.nextInt(100);
        int rate = 0;
        switch (countCritical()) {
            case 0:
                rate = M_LUCK - LUCK;
                break;
            case 1:
                // 玩家幸運大於怪物回傳 1
                rate = LUCK - M_LUCK;

                break;
        }
        if (rate > check) {
            return true;
        }
        return false;
    }

    public static Boolean Dodge() {
        Random r = new Random();
        int check = r.nextInt(100);
        int rate = 0;
        switch (countSpeed()) {
            case 0:
                rate = M_SPEED - SPEED;
                break;
            case 1:
                // 玩家速度大於怪物回傳 1
                rate = SPEED - M_SPEED;
                break;
        }
        if (rate > check) {
            return true;
        }
        return false;
    }

}