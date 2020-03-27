package Basic;

public class Player extends Power {

    public Player() {
        super(20, 10, 10, 10, 10, 10, 10, 10, 100, 0, 1);
    }

    // HP, ATK, DEF, SP_ATK, SP_DEF, SPEED, LUCK, MP, COIN, EXP, LEVEL
    public void upHp(Integer amount) {
        this.HP += amount;
    }

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

    public void upGrade(Integer amount) {
        this.LEVEL += amount;
    }

    public void gainCoin(Integer amount) {
        this.COIN += amount;
    }

    public void gainExp(Integer amount) {
        this.EXP += amount;
    }

    public void gainMp(Integer amount) {
        this.MP += amount;
    }

    public Player getPlayer() {
        return this;
    }

}