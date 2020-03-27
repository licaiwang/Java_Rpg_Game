package monster;
import Basic.Power;
import java.util.Random;

public class Monster extends Power {


    public Monster(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp,
            int level) {
        super(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp, level);
        // ATK, DEF, SP_ATK, SP_DEF, SPEED, LUCK, MP, COIN, EXP, LEVEL
    }


    public Monster(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp, int level, int rarity)
    {
        super(hp, atk, def, sp_atk, sp_def,speed, luck, mp, coin, exp,  level, rarity);
    }

    private Random r = new Random();

    

    public void upAtk(Integer amount) {
        this.ATK += amount;
    }

    public void upDef(Integer amount) {
        this.DEF += amount;
    }

    public void upSpAtk(Integer amount) {
        this.SP_ATK += amount;
    }

    public void upSpDef(Integer amount) {
        this.SP_DEF += amount;
    }

    public void upSpeed(Integer amount) {
        this.SPEED += amount;
    }

    public void upLuck(Integer amount) {
        this.LUCK += amount;
    }

    public Integer DropCoin(Integer level) {

        if (level < 5) {
            Integer money = r.nextInt(10);
            return money;
        }
        if (level >= 5 && level < 10) {
            Integer money = r.nextInt(50)+5;
            return money;
        }
        if (level >= 10 && level < 15) {
            Integer money = r.nextInt(300)+75;
            return money;
        }
        if (level >= 15 && level < 20) {
            Integer money = r.nextInt(600)+150;
            return money;
        }
        if (level >= 20 && level < 25) {
            Integer money = r.nextInt(1200)+300;
            return money;
        }
        if (level >= 25 && level < 30) {
            Integer money = r.nextInt(2400)+600;
            return money;
        }
        return 0;
    }

    public void DropExp(Integer amount) {
        this.EXP += amount;

    }

    public Monster getMonster()
    {
        return this;
    }
}