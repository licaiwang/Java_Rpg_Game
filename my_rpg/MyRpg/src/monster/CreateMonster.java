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
    public static void create(String name) {
        if (name.equals("slime")) {
            Slime slime = new Slime(5,3,3,3,3, 10,50,0,5,5, 5, 0);
            // 最大等級 5 以下 , 稀有度 - 普通
            damage = slime.Atkskill();
        }
        else if (name.equals("giant_rat")) {
            GaintRat rat= new GaintRat(7,1,3,3,3, 30,50,0,5,5, 5, 1);
            // 最大等級 5 以下 , 稀有度 - 普通 ~ 稀有
            damage = rat.Atkskill();
        }
        else if(name.equals("fanatic"))
        {
            Fanatic fanatic = new Fanatic(10,5,5,7,5, 5,50,0,5,5, 5,0);
             // 最大等級 5 以下 , 稀有度 - 普通
            damage = fanatic.Atkskill();
        }


    }
    public static Integer Movement()
    {
        return damage;
    }
}