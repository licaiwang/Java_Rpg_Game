import Basic.*;
import monster.Slime;
import phase.BattleMain;
import phase.BattlePhase;
public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Slime slime = new Slime(5, 5, 5, 5, 5, 5, 5,0, 5, 5, 5, 0);
        //System.out.println(slime.HP);
        // System.out.println(player.HP);
        //BattlePhase.subHp(player, slime, 0 ,BattlePhase.countDamage(player, slime, 0));
        //player.upHp(30);
        //System.out.println(player.HP);
        BattleMain.PlayerTurn(player, slime, false);
        player.upLuck(70);
        BattleMain.PlayerTurn(player, slime, false);
        BattleMain.PlayerTurn(player, slime, false);
        BattleMain.PlayerTurn(player, slime, false);
        BattleMain.PlayerTurn(player, slime, false);
    }
}
