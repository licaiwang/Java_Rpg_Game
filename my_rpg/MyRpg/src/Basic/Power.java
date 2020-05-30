package Basic;

import java.util.Random;

public class Power {
    // 最基礎的能力紀錄
    private Random r = new Random();
    public Integer HP;
    public Integer ATK;
    public Integer DEF;
    public Integer SP_ATK;
    public Integer SP_DEF;
    public Integer SPEED;
    public Integer LUCK;
    public Integer MP;
    public Integer COIN;
    public Integer EXP;
    public Integer LEVEL;
    
    public Power() {
    }
    public Power(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp, int level, int rarity) {
        switch (rarity) {
            case 0:
                montserPower(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp, level,rarity);
                break;

            case 1:
                rarity = r.nextInt(5)+10;
                montserPower(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp, level,rarity);
                break;
            case 2:
                rarity = r.nextInt(30)+20;
                montserPower(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp, level,rarity);
                break;
            case 3:
                rarity = r.nextInt(70)+30;
                montserPower(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp, level,rarity);
                break;
        }
    }

    
    // 讓怪物產生隨機亂數能力
    public void montserPower(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp, int level, int rarity) {
        if (level <= 5) {
            this.LEVEL = r.nextInt(5);
            this.COIN = ((r.nextInt(10) + LEVEL)*(1+(rarity/100)));
            this.EXP = ((r.nextInt(3)+LEVEL)*(1+(rarity/100)));
            this.HP = ((hp + LEVEL)*(1+(rarity/100)));
            this.ATK = ((atk + LEVEL)*(1+(rarity/100)));
            this.DEF = ((def + LEVEL)*(1+(rarity/100)));
            this.SP_ATK = ((sp_atk + LEVEL)*(1+(rarity/100)));
            this.SP_DEF = ((sp_def + LEVEL)*(1+(rarity/100)));
            this.SPEED = ((speed + LEVEL)*(1+(rarity/100)));
            this.LUCK = ((luck + LEVEL)*(1+(rarity/100)));
            this.MP = ((mp + LEVEL)*(1+(rarity/100)));
        }
        if (level > 5 && level <= 10) {
            this.LEVEL = r.nextInt(5);
            this.COIN = r.nextInt(10) + LEVEL;
            this.EXP = r.nextInt(3)+LEVEL;
            this.HP = hp + LEVEL;
            this.ATK = atk + LEVEL;
            this.DEF = def + LEVEL;
            this.SP_ATK = sp_atk + LEVEL;
            this.SP_DEF = sp_def + LEVEL;
            this.SPEED = speed + LEVEL;
            this.LUCK = luck + LEVEL;
            this.MP = mp + LEVEL;
        }
        if (level > 10 && level <= 15) {
            this.LEVEL = r.nextInt(5);
            this.COIN = r.nextInt(10) + LEVEL;
            this.EXP = r.nextInt(3)+LEVEL;
            this.HP = hp + LEVEL;
            this.ATK = atk + LEVEL;
            this.DEF = def + LEVEL;
            this.SP_ATK = sp_atk + LEVEL;
            this.SP_DEF = sp_def + LEVEL;
            this.SPEED = speed + LEVEL;
            this.LUCK = luck + LEVEL;
            this.MP = mp + LEVEL;
        }
        if (level > 15 && level <= 20) {
            this.LEVEL = r.nextInt(5);
            this.COIN = r.nextInt(10) + LEVEL;
            this.EXP = r.nextInt(3)+LEVEL;
            this.HP = hp + LEVEL;
            this.ATK = atk + LEVEL;
            this.DEF = def + LEVEL;
            this.SP_ATK = sp_atk + LEVEL;
            this.SP_DEF = sp_def + LEVEL;
            this.SPEED = speed + LEVEL;
            this.LUCK = luck + LEVEL;
            this.MP = mp + LEVEL;
        }
        if (level > 20 && level <= 25) {
            this.LEVEL = r.nextInt(5);
            this.COIN = r.nextInt(10) + LEVEL;
            this.EXP = r.nextInt(3)+LEVEL;
            this.HP = hp + LEVEL;
            this.ATK = atk + LEVEL;
            this.DEF = def + LEVEL;
            this.SP_ATK = sp_atk + LEVEL;
            this.SP_DEF = sp_def + LEVEL;
            this.SPEED = speed + LEVEL;
            this.LUCK = luck + LEVEL;
            this.MP = mp + LEVEL;
        }
        if (level > 25 && level <= 30) {
            this.LEVEL = r.nextInt(5);
            this.COIN = r.nextInt(10) + LEVEL;
            this.EXP = r.nextInt(3)+LEVEL;
            this.HP = hp + LEVEL;
            this.ATK = atk + LEVEL;
            this.DEF = def + LEVEL;
            this.SP_ATK = sp_atk + LEVEL;
            this.SP_DEF = sp_def + LEVEL;
            this.SPEED = speed + LEVEL;
            this.LUCK = luck + LEVEL;
            this.MP = mp + LEVEL;
        }
    }
}