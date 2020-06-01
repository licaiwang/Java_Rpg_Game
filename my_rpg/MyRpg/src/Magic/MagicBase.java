package Magic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Basic.ResReader;
import Gui.Town.Storage;
import au.com.bytecode.opencsv.CSVReader;

public class MagicBase {

    public static List<String[]> Magic_list;
    public static String[] in_use_name = { "", "", "", "" };
    public static Integer[] in_use_magic = { 0, 0, 0, 0 };
    public static String[] in_use_intro = { "", "", "", "" };
    public static Integer[] in_use_cost = { 0, 0, 0, 0 };
    public static Integer[] in_use_rarity = { 0, 0, 0, 0 };

    public static List<Integer> all_lock = new ArrayList<Integer>();
    public static List<String> unlock_Magic_name = new ArrayList<String>();
    public static List<String> Basic_Magic_name = new ArrayList<String>();
    public static List<String> Advance_Magic_name = new ArrayList<String>();
    public static List<String> Master_Magic_name = new ArrayList<String>();

    public static Boolean IsDamage = true;
    public static Boolean IsEnhance = false;
    public static Boolean IsStatus = false;

    public static void readAllData() throws IOException {
        // 加 "MS950" 才讀得懂中文
        CSVReader reader = new CSVReader(new InputStreamReader(
                new FileInputStream(ResReader.Current_Dic+("/res/data/magic.csv")), "MS950"));
        Magic_list = reader.readAll();
        for (int i = 1; i < Magic_list.size(); i++) {
            int rarity = Integer.valueOf(Magic_list.get(i)[4].toString());
            all_lock.add(Integer.valueOf(Magic_list.get(i)[5].toString()));
            switch (rarity) {
                case 2:
                    Master_Magic_name.add(Magic_list.get(i)[1].toString());
                    break;
                case 1:
                    Advance_Magic_name.add(Magic_list.get(i)[1].toString());
                    break;
                case 0:
                    Basic_Magic_name.add(Magic_list.get(i)[1].toString());
                    break;
            }
        }
        checkIfUnlock();
    }

    public static void checkIfUnlock() {
        unlock_Magic_name.clear();
        for (int i = 1; i < Magic_list.size(); i++) {
            if (all_lock.get(i - 1) == 0) {
                unlock_Magic_name.add((Magic_list.get(i)[1].toString()));
            }
        }
    }

    public static int getIndexByName(String name) {
        int i = 0;
        for (i = 1; i < Magic_list.size(); i++) {
            if (Magic_list.get(i)[1].toString() == name) {
                return i;
            }
        }
        return i;
    }

    public static void unlockMagic(String name) {
        int index = getIndexByName(name);
        all_lock.set(index - 1, 0);
        checkIfUnlock();
        Storage.list_2.setListData(unlock_Magic_name.stream().toArray(String[]::new));
        Storage.list_2.repaint();
    }

    public static String getRarity(int rarity) {
        switch (rarity) {
            case 0:
                return "普通";
            case 1:
                return "大師";
            case 2:
                return "冠位";
        }
        return null;
    }

    public static void setMagicByIndex(int i) {
        for (int j = 0; j < 3; j++) {
            in_use_name[j] = in_use_name[j + 1];
            in_use_magic[j] = in_use_magic[j + 1];
            in_use_intro[j] = in_use_intro[j + 1];
            in_use_cost[j] = in_use_cost[ j + 1 ];
            in_use_rarity[j] = in_use_rarity[j + 1];
        }
        in_use_magic[3] = Integer.valueOf(Magic_list.get(i)[0]);
        in_use_name[3] = Magic_list.get(i)[1].toString();
        in_use_intro[3] = Magic_list.get(i)[2].toString();
        in_use_cost[3] = Integer.valueOf(Magic_list.get(i)[3].toString());
        in_use_rarity[3] = Integer.valueOf(Magic_list.get(i)[4].toString());
    }

    // 一開始會 4 個技能
    public static void initMagic() throws IOException {
        for (int i = 1; i < 5; i++) {
            in_use_magic[i - 1] = Integer.valueOf(Magic_list.get(i)[0].toString());
            in_use_name[i - 1] = Magic_list.get(i)[1].toString();
            in_use_intro[i - 1] = Magic_list.get(i)[2].toString();
            in_use_cost[i - 1] = Integer.valueOf(Magic_list.get(i)[3].toString());
            in_use_rarity[i - 1] = Integer.valueOf(Magic_list.get(i)[4].toString());
        }
    }

    public static int getMagic(int id) {

        /*
         * 這裡的 id 為 按鈕 1,2,3,4 取得 X 號按鈕的技能稀有度 根據技能稀有度,再傳 X 號按鈕的技能 id
         */
        
        int current_rarity = in_use_rarity[id];
        switch (current_rarity) {
            case 0:
                return Magic.CommonMagic(in_use_magic[id]);
            case 1:
                return Magic.UnCommonMagic(in_use_magic[id]);
            case 2:
                return Magic.MasterMagic(in_use_magic[id]);
        }
        return 0;
    }



}