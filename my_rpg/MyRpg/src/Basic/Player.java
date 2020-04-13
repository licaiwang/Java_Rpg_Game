package Basic;

public class Player extends Power {
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
    public Integer TP;
    public Integer memoryShard;
    public Integer memoryCd;
    public Player() {
        super(20, 10, 10, 10, 10, 10, 10, 10, 0, 1);
        HP = 20;
        ATK = 10;
        DEF = 10;
        SP_ATK = 10;
        SP_DEF = 10;
        SPEED = 100;
        LUCK = 10;
        MP = 10;
        EXP = 0;
        LEVEL = 1;
        TP = 15;
        COIN = 1000;
        memoryShard = 0;
        memoryCd = 0;
    }

    // HP, ATK, DEF, SP_ATK, SP_DEF, SPEED, LUCK, MP, COIN, EXP, LEVEL
    public void upHp(Integer amount) {
        HP += amount;
    }

    public void upAtk(Integer amount) {
        ATK += amount;
    }

    public void upDef(Integer amount) {
        DEF += amount;
    }

    public void upSpAtk(Integer amount) {
        SP_ATK += amount;
    }

    public void upSpDef(Integer amount) {
        SP_DEF += amount;
    }

    public void upSpeed(Integer amount) {
        SPEED += amount;
    }

    public void upLuck(Integer amount) {
        LUCK += amount;
    }

    public void upGrade(Integer amount) {
        LEVEL += amount;
    }

    public void gainCoin(Integer amount) {
        COIN += amount;
    }

    public void gainExp(Integer amount) {
       EXP += amount;
    }

    public void gainMp(Integer amount) {
       MP += amount;
    }
    public void gainCD(Integer amount) {
        memoryCd += amount;
    }
    public void gainShard(Integer amount) {
       memoryShard += amount;
    }


    public Integer getHp() {
        return HP;
    }

    public Integer getAtk() {
        return ATK;
    }

    public Integer getDef() {
        return DEF;
    }

    public Integer getSpatk() {
        return SP_ATK;
    }

    public Integer getSpdef() {
        return SP_DEF;
    }

    public Integer getSpeed() {
        return SPEED;
    }

    public Integer getLuck() {
        return LUCK;
    }

    public Integer getMp() {
        return MP;
    }

    public Integer getCoin() {
        return COIN;
    }

    public Integer getExp() {
        return EXP;
    }

    public Integer getLevel() {
        return LEVEL;
    }

    public Integer getTP()
    {
        return TP;
    }
    public Integer getShard()
    {
        return memoryShard;
    }
    public Integer getCd()
    {
        return memoryCd;
    }
    public static Integer[] getAll() {
        Integer[] all = { Player.HP, Player.ATK, Player.DEF, Player.SP_ATK, Player.SP_DEF, Player.SPEED, Player.LUCK, Player.MP, Player.COIN, Player.EXP, Player.LEVEL };
        return all;
    }
}

