package Basic;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Player extends Power {
    public static List<String[]> Upgrade;
    public static Integer Max_HP;
    public static Integer HP;
    public static Integer ATK;
    public static Integer DEF;
    public static Integer SP_ATK;
    public static Integer SP_DEF;
    public static Integer SPEED;
    public static Integer LUCK;
    public static Integer MP;
    public static Integer Max_MP;
    public static Integer COIN;
    public static Integer EXP;
    public static Integer LEVEL;
    public static Integer TP;
    public static Integer memoryShard;
    public static Integer memoryCd;
    public static Integer stop_pain;
    public static Integer magic_box;
    public static Integer godness_bless;
    public static Boolean isDead;
    public Player() throws IOException {
        super();
        readAllData();
        LEVEL = Integer.valueOf(Upgrade.get(1)[0]);
        EXP = Integer.valueOf(Upgrade.get(1)[1]);
        HP = Integer.valueOf(Upgrade.get(1)[2]);
        Max_HP = HP;
        ATK = Integer.valueOf(Upgrade.get(1)[3]);
        DEF = Integer.valueOf(Upgrade.get(1)[4]);
        SP_ATK = Integer.valueOf(Upgrade.get(1)[5]);
        SP_DEF = Integer.valueOf(Upgrade.get(1)[6]);
        SPEED = Integer.valueOf(Upgrade.get(1)[7]);
        LUCK = Integer.valueOf(Upgrade.get(1)[8]);
        MP = Integer.valueOf(Upgrade.get(1)[9]);
        Max_MP = MP;
        TP = 15;
        COIN = 1000;
        memoryShard = 1000;
        memoryCd = 0;
        stop_pain = 0;
        magic_box = 0;
        godness_bless = 0;
        isDead = false;
    }

    // HP, ATK, DEF, SP_ATK, SP_DEF, SPEED, LUCK, MP, COIN, EXP, LEVEL
    public void upHp( Integer amount) {
        HP += amount;
    }

    public void upAtk( Integer amount) {
        ATK += amount;
    }

    public void upDef( Integer amount) {
        DEF += amount;
    }

    public void upSpAtk( Integer amount) {
        SP_ATK += amount;
    }

    public void upSpDef( Integer amount) {
        SP_DEF += amount;
    }

    public void upSpeed( Integer amount) {
        SPEED += amount;
    }

    public void upLuck( Integer amount) {
        LUCK += amount;
    }

    public void upGrade( Integer amount) {
        LEVEL += amount;
    }

    public void gainCoin( Integer amount) {
        COIN += amount;
    }

    public void gainExp( Integer amount) {
        EXP += amount;
    }

    public void gainMp( Integer amount) {
        MP += amount;
    }

    public void gainCD( Integer amount) {
        memoryCd += amount;
    }

    public void gainShard( Integer amount) {
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

    public Integer getTP() {
        return TP;
    }

    public Integer getShard() {
        return memoryShard;
    }

    public Integer getCd() {
        return memoryCd;
    }

    public static Integer[] getAll() {
        Integer[] all = { Player.HP, Player.ATK, Player.DEF, Player.SP_ATK, Player.SP_DEF, Player.SPEED,
                Player.LUCK, Player.MP, Player.COIN, Player.EXP, Player.LEVEL };
        return all;
    }

    public static void readAllData() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/data/player.csv"));
        Upgrade = reader.readAll();
        // System.out.println(Upgrade.get(1)[1]); 
    }

	public static void UpgradePlayer(int i) {
        EXP = Integer.valueOf(Upgrade.get(i)[1]);
        HP = Integer.valueOf(Upgrade.get(i)[2]);
        Max_HP = HP;
        ATK = Integer.valueOf(Upgrade.get(i)[3]);
        DEF = Integer.valueOf(Upgrade.get(i)[4]);
        SP_ATK = Integer.valueOf(Upgrade.get(i)[5]);
        SP_DEF = Integer.valueOf(Upgrade.get(i)[6]);
        SPEED = Integer.valueOf(Upgrade.get(i)[7]);
        LUCK = Integer.valueOf(Upgrade.get(i)[8]);
        MP = Integer.valueOf(Upgrade.get(i)[9]);
        Max_MP = MP;
	}
}

