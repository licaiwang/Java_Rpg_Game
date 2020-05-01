package item;

import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Item {

    String Name;
    String Rarity;
    Integer Id;
    boolean IsAttack;

    public static List<String[]> item_list;
    static DeathAngle deathAngle;
    static AllSeeningEye allSeeningEye;
    public static String[] in_use_item = { "", "", "" };
    public static boolean[] in_use_isAttack = { false, false, false };
    public static List<String> unlock_item = new ArrayList<String>();
    public static List<String> item = new ArrayList<String>();
  


    public Item(String name, Integer id, String rarity, boolean isattack) {
        Name = name;
        Id = id;
        Rarity = rarity;
        IsAttack = isattack;
        item.add(name);
    }

    public abstract void ability();

    public static void readAllData() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(
                new FileInputStream("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/data/item.csv"), "MS950"));
        item_list = reader.readAll();
        init();
    }

    public static void init() {
        deathAngle = new DeathAngle(item_list.get(6)[0],0,item_list.get(6)[1], true);
        allSeeningEye = new AllSeeningEye(item_list.get(7)[0],1,item_list.get(7)[1], true);
        unlock(6);
        unlock(7);
    }

    public static void unlock(int index) {
        unlock_item.add(item_list.get(index)[0]);
    }
    
    public static void setItemByName(String name) {
        for (int i = 0; i <= 2; i++) {
            if (i < 3) {
                if (in_use_item[i].equals("")) {
                    in_use_item[i] = name;
                    in_use_isAttack[i] = getIsAttack(name);
                    break;
                }
            }
            if (!in_use_item[2].equals("")) {

                if (i == 2) {
                    in_use_item[2] = name;
                    in_use_isAttack[2] = getIsAttack(name);
                } else {
                    in_use_item[i] = in_use_item[i + 1];
                    in_use_isAttack[i] = in_use_isAttack[i + 1];
                }
            }
        }
    }



    public static boolean getIsAttack(String name) {
        if (name.equals("死告天使"))
            return deathAngle.IsAttack;
        if (name.equals("真知晶球"))
            return  allSeeningEye.IsAttack;
        return false;
    }


    public static void getAbility(String name) {
        // 這邊只會放有啟動性的寶具，因為上一步再選擇的時候已經把被動的過濾掉了
        if (name.equals("死告天使"))
            deathAngle.ability();
        if (name.equals("真知晶球"))
            allSeeningEye.ability();
    }

	public static int getIndexByName(String text) {
        int i = 0;
        for (i = 1; i < item_list.size(); i++) {
            if (text.equals(item_list.get(i)[0].toString())) {
                return i;
            }
        }
        return i;
	}

}