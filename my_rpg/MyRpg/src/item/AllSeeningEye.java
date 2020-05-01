package item;

import Basic.Player;
import Gui.BottomPanel;
import Gui.Advanture.BattleSidePanel;
import monster.CreateMonster;
import phase.BattleTemp;

public class AllSeeningEye extends Item {

    public AllSeeningEye(String name, Integer id, String rarity, boolean isattack) {
        super(name, id, rarity, isattack);
    }

    @Override
    public void ability() {
        if (BattleTemp.MP > 0) {
            BattleTemp.MP -= 1;
            BattleSidePanel.resetMp();
            int result = Player.HP - (CreateMonster.Movement() - BattleTemp.DEF);
            if (result < 0) {
                BottomPanel.content = "真知晶球燙得不得了！";
            } else if (result <= Player.HP/2) {
                BottomPanel.content = "真知晶球微微發燙";
            } else if (result >= Player.HP/2){
                BottomPanel.content = "真知晶球摸起來冰冰的";
            }
        } else {
            BottomPanel.content = "真知晶球似乎沒有魔力了．．．";
        }
        BottomPanel.resetTextArea();
    }

}