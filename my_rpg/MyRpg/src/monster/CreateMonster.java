package monster;

import java.util.Random;

import monster.level_1.Fanatic;
import monster.level_1.GaintRat;
import monster.level_1.Slime;
/**
 * 
 * @author Rorschach
 * 
 * 選擇要生成哪隻怪物，並決定生成的稀有度
 * 
 * 稀有度等級，每一級約差 10 % 的強度
 * 
 * 0 ~ 3 普通
 * 
 * 
 * 4 ~ 6 菁英
 * 
 * 
 * 7 ~ 9 史詩
 * 
 **
 */
public class CreateMonster {
      static int damage = 0;

/*
hp, atk,  def, sp_atk,  sp_def, 
speed, luck,  mp,  coin,  exp,
level,  rarity
*/

    public static void createLevelOne(String name) {
        if (name.equals("slime")) {
            Random r = new Random();
            int rarity = r.nextInt(3);
            Slime slime = new Slime(10,11,7,1,0, 10,10,0,1,5,   5,rarity);
            // 最大等級 5 以下 , 稀有度等級 - 普通
            damage = slime.Atkskill();
        }
        else if (name.equals("giant_rat")) {
            Random r = new Random();
            int rarity = r.nextInt(3);
            GaintRat rat= new GaintRat(10,13,2,0,3, 20,10,0,3,7, 5,rarity);
            // 最大等級 5 以下 , 稀有度等級 - 普通 
            damage = rat.Atkskill();
        }
        else if(name.equals("fanatic"))
        {
            Random r = new Random();
            int rarity = r.nextInt(6);
            Fanatic fanatic = new Fanatic(25,20,0,0,0, 5,0,0,10,10, 5,rarity);
             // 最大等級 5 以下 , 稀有度等級 - 普通 ~ 稀有
            damage = fanatic.Atkskill();
        }
    }


 public static Integer Movement()
    {
        return damage;
    }

	public static void clearall() {

	}
}