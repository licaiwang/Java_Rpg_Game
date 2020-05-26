package monster;

import monster.level_1.Fanatic;
import monster.level_1.GaintRat;
import monster.level_1.Slime;

public class CreateMonster {
      static int damage = 0;

/*
hp, atk,  def, sp_atk,  sp_def, 
speed, luck,  mp,  coin,  exp,
level,  rarity
*/

    public static void createLevelOne(String name) {
        if (name.equals("slime")) {
            Slime slime = new Slime(5,20,0,1,1, 10,10,0,1,5,   5,0);
            // 最大等級 5 以下 , 稀有度 - 普通
            damage = slime.Atkskill();
        }
        else if (name.equals("giant_rat")) {
            GaintRat rat= new GaintRat(7,20,1,1,1, 20,10,0,3,7, 5,0);
            // 最大等級 5 以下 , 稀有度 - 普通 
            damage = rat.Atkskill();
        }
        else if(name.equals("fanatic"))
        {
            Fanatic fanatic = new Fanatic(50,20,0,0,0, 5,0,0,10,10, 5,1);
             // 最大等級 5 以下 , 稀有度 - 稀有
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