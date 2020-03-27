package phase;

import java.util.Random;

import Basic.Player;
import monster.Monster;

public class BattleMain extends BattlePhase {
    private static Boolean IsCritical;
    private static Boolean IsDodge;
    private static Integer CriticalRate;
    private static Integer DodgeRate;
    
    // 0 代表玩家 1 代表怪物
    public static void PlayerTurn(Player player, Monster monster, Boolean IsSpatk) {
        Integer rate = 0;
        Random r = new Random();
        rate = r.nextInt(101);
        countSpeed(player, monster);
        CriticalRate = countCritical(player, monster, 0);
        if (CriticalRate - rate > 0) {
            IsCritical = true;
        } else {
            IsCritical = false;
        }
        // 看怪物是否迴避
        DodgeRate = countDodge(player, monster, 1);
        if (DodgeRate - rate > 0) {
            IsDodge = true;
        } else {
            IsDodge = false;
        }
        if (!IsDodge) {
            // 扣怪物的血
            subHp(player, monster, 1, countDamage(player, monster, 0, IsSpatk), IsCritical);
        }else{
            System.out.println("玩家攻擊，但史萊姆迴避了！");
        }
        if (monster.HP <= 0) {
            return;
        }
    }

    public static void MonsterTurn(Player player, Monster monster, Boolean IsSpatk) {
        Random r = new Random();
        Integer rate = r.nextInt(101);
        countSpeed(player, monster);
        CriticalRate = countCritical(player, monster, 1);
        if (CriticalRate - rate > 0) {
            IsCritical = true;
        } else {
            IsCritical = false;
        }
        // 看玩家是否迴避
        DodgeRate = countDodge(player, monster, 0);
        if (DodgeRate - rate > 0) {
            IsDodge = true;
        } else {
            IsDodge = false;
        }
        if (!IsDodge) {
            // 扣玩家的血
            subHp(player, monster, 0, countDamage(player, monster, 1, IsSpatk), IsCritical);
        }
        if (player.HP <= 0) {
            return;
        }
    }

}