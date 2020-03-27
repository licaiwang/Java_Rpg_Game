package phase;

import Basic.Player;
import monster.Monster;

public class BattlePhase {
    static Integer damage = 0;

    public static Integer countSpeed(Player player, Monster monster) {
        if (player.SPEED > monster.SPEED) {
            return 0;
        } else {
            return 1;
        }
    }

    public static Integer countDamage(Player player, Monster monster, Integer whoAtk, boolean isSpatk) {
        if (isSpatk) {
            switch (whoAtk) {
                case 0:
                    damage = (player.SP_ATK - monster.SP_DEF);
                    damage = isDamage(damage);
                    break;
                case 1:
                    damage = (monster.SP_ATK - player.SP_DEF);
                    damage = isDamage(damage);
                    break;
            }

        } else {
            switch (whoAtk) {
                case 0:
                    damage = (player.ATK - monster.DEF);
                    damage = isDamage(damage);
                    break;
                case 1:

                    damage = (monster.ATK - player.DEF) * (1 + ((monster.LUCK - player.LUCK) / 100));
                    damage = isDamage(damage);
                    break;
            }
        }

        return damage;
    }

    public static Integer countCritical(Player player, Monster monster, Integer whoLuck) {
        switch (whoLuck) {
            case 0:
                if (player.LUCK - monster.LUCK > 0) {
                    return (player.LUCK - monster.LUCK);
                } else {
                    return 1;
                }
            case 1:
                if (monster.LUCK - player.LUCK > 0) {
                    return (monster.LUCK - player.LUCK);
                } else {
                    return 1;
                }
        }
        return 0;
    }

    public static Integer countDodge(Player player, Monster monster, Integer whoDodge) {
        switch (whoDodge) {
            case 0:
                if (player.SPEED - monster.SPEED > 0) {
                    return (player.SPEED - monster.SPEED);
                } else {
                    return 1;
                }
            case 1:
                if (monster.SPEED - player.SPEED > 0) {
                    return (monster.SPEED - player.SPEED);
                } else {
                    return 1;
                }
        }
        return 0;
    }

    public static Integer subHp(Player player, Monster monster, Integer whoHp, Integer damage, Boolean IsCritical) {
        switch (whoHp) {
            case 0:
                if (IsCritical) {

                    return (player.HP - 2 * damage);
                } else

                    return (player.HP - damage);
            case 1:
                if (IsCritical) {
                    System.out.println("玩家發出了暴擊，對史萊姆造成了");
                    System.out.println(2 * damage);
                    System.out.println("點傷害");
                    return (monster.HP - (2 * damage));
                } else
                    System.out.println("玩家攻擊，對史萊姆造成了");
                    System.out.println(damage);
                    System.out.println("點傷害");
                    return (monster.HP - damage);
        }
        return 0;
    }

    public static Integer isDamage(Integer damage) {
        if (damage > 0) {
            return damage;
        } else {
            return 1;
        }
    }

}