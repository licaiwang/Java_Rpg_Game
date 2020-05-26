package monster.Boss;

import java.util.Random;

import monster.Monster;

public class Boss_1 extends Monster {
    /*
     *
     *      Boss_1 - 泰格達不死隊
     * 
     *      招式：
     * 
     *          1. 普通攻擊   - 40 %
     *          2. 二連擊     - 30 %
     *          3. 提升攻擊力(中) - 10 %
     *          4. 提升速度 (小)，防禦力 (小) - 20 %
     * 
     *      特殊：　在未持有死告天使的情況下，這個 Boss 不會死
     *              且每被玩家擊倒一次會再生成一個不死隊隊員，然後攻擊力 * 2
     * 
     *              在持有死告天使且發動的時候必發動成功，但玩家還是必須再擊倒" "正常版" 的不死隊
     * 
     * 
    */
    public static Boolean isStrike = false;
    public static String Name = "UndeadLengend";

    public Boss_1(int hp, int atk, int def, int sp_atk, int sp_def, int speed, int luck, int mp, int coin, int exp) {
        super(hp, atk, def, sp_atk, sp_def, speed, luck, mp, coin, exp);
    }
    public Integer Atkskill()
    {
        Random r = new Random();
        int opt = r.nextInt(3) + 1;
        switch (opt) {
            case 1:
                return normalAtk();
            case 2:
                return doubleStrike();
            case 3:
                return enhanceAtk();
            case 4:
                return enhanceDefSpeed();
        }
        return 0;
    }

    private Integer enhanceAtk() {
        // 提升攻擊力
        Monster.ATK += 5;
        return 0;
    }
    private Integer enhanceDefSpeed() {
        // 提升防禦，速度
        Monster.DEF += 3;
        Monster.SPEED += 3;
        return 0;
    }
    private Integer doubleStrike() {
        // 攻擊兩次
        isStrike = true;
        return Monster.ATK;
    }

    private Integer normalAtk() {
        // 普攻
        return  Monster.ATK;
    }
}