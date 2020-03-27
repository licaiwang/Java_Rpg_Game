package monster;

import java.util.Random;

public class Slime extends Monster {
    public Slime(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp, int level, int rarity)
    {
        super(5,5,5,5,5,70,5,0,5,5,0,0);
        // 最大等級 5 以下 , 稀有度 - 普通
    }

}