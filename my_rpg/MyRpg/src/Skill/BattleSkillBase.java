package Skill;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Gui.Town.Storage;
import au.com.bytecode.opencsv.CSVReader;

public class BattleSkillBase {
    /*
    
    
    */
    public static List<String[]> skill_list;
    public static String[] in_use_name = { "", "", "", "" };
    public static Integer[] in_use_skill = { 0, 0, 0, 0 };
    public static String[] in_use_intro = { "", "", "", "" };
    public static String[] in_use_effect = { "", "", "", "" };
    public static Integer[] in_use_delay = { 0, 0, 0, 0 };
    public static Integer[] in_use_rarity = { 0, 0, 0, 0 };

    public static List<Integer> all_lock = new ArrayList<Integer>();
    public static List<String> unlock_skill_name = new ArrayList<String>();
    public static List<String> Basic_skill_name = new ArrayList<String>();
    public static List<String> Advance_skill_name = new ArrayList<String>();
    public static List<String> Master_skill_name = new ArrayList<String>();




    // 默認技能為純一次傷害
    public static Boolean IsDamage = true;
    public static Integer Strik = 0;
    public static Boolean IsEnhance = false;
    public static Boolean IsStatus = false;

    public static void readAllData() throws IOException {
        //  加 "MS950" 才讀得懂中文 
        CSVReader reader = new CSVReader(new InputStreamReader(
                new FileInputStream("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/data/skill.csv"), "MS950"));
        skill_list = reader.readAll();
        for (int i = 1; i < skill_list.size(); i++) {
            int rarity = Integer.valueOf(skill_list.get(i)[5].toString());
            all_lock.add(Integer.valueOf(skill_list.get(i)[6].toString()));
            switch (rarity) {
                case 2:
                    Master_skill_name.add(skill_list.get(i)[1].toString());
                    break;
                case 1:
                    Advance_skill_name.add(skill_list.get(i)[1].toString());
                    break;
                case 0:
                    Basic_skill_name.add(skill_list.get(i)[1].toString());
                    break;
            }
        }
        checkIfUnlock();
    }

    public static void checkIfUnlock() {
        unlock_skill_name.clear();
        for (int i = 1; i < skill_list.size(); i++) {
            if (all_lock.get(i-1) == 0) {
                unlock_skill_name.add((skill_list.get(i)[1].toString()));
            }
        }
    }

    public static int getIndexByName(String name) {
        int i = 0;
        for (i = 1; i < skill_list.size(); i++) {
            if (name.equals(skill_list.get(i)[1].toString())) {
                return i;
            }
        }
        return i;
    }

    public static void unlockSkill(String name) {
        int index = getIndexByName(name);
        all_lock.set(index-1, 0);
        checkIfUnlock();
        Storage.list_1.setListData(unlock_skill_name.stream().toArray(String[]::new));
        Storage.list_1.repaint();
    }


    public static String getRarity(int rarity)
    {
        switch (rarity) {
            case 0:           
                return "普通";
            case 1:
                return "進階";            
            case 2:         
              return "大師";
        }
        return null;
    } 

    public static void setSkillByIndex(int i) {
        for (int j = 0; j < 3; j++) {
            in_use_name[j] = in_use_name[j + 1];
            in_use_skill[j] = in_use_skill[j + 1];
            in_use_intro[j] = in_use_intro[j + 1];
            in_use_effect[j] = in_use_effect[j + 1];
            in_use_delay[j] = in_use_delay[j + 1];
            in_use_rarity[j] = in_use_rarity[j + 1];
        }
        in_use_name[3] = skill_list.get(i)[1].toString();
        in_use_skill[3] = Integer.valueOf(skill_list.get(i)[0]);
        in_use_intro[3] = skill_list.get(i)[2].toString();
        in_use_effect[3] = skill_list.get(i)[3].toString();
        in_use_delay[3] = Integer.valueOf(skill_list.get(i)[4].toString());
        in_use_rarity[3] = Integer.valueOf(skill_list.get(i)[5].toString());
    }


    // 一開始會 4 個技能
    public static void initSkill() throws IOException {
        for (int i = 1; i < 5; i++) {
            in_use_name[i - 1] = skill_list.get(i)[1].toString();
            in_use_skill[i - 1] = Integer.valueOf(skill_list.get(i)[0].toString());
            in_use_intro[i - 1] = skill_list.get(i)[2].toString();
            in_use_effect[i - 1] = skill_list.get(i)[3].toString();
            in_use_delay[i - 1] = Integer.valueOf(skill_list.get(i)[4].toString());
            in_use_rarity[i - 1] = Integer.valueOf(skill_list.get(i)[5].toString());
        }
    }

    public static int getSkill(int id) {

        /*
         * 這裡的 id 為 按鈕 1,2,3,4 取得 X 號按鈕的技能稀有度 根據技能稀有度,再傳 X 號按鈕的技能 id
         */
        resetFlag();
        int current_rarity = in_use_rarity[id];
        switch (current_rarity) {
            case 0:
                return Skill.CommonSkill(in_use_skill[id]);
            case 1:
                return Skill.UnCommonSkill(in_use_skill[id]);
            case 2:
                return Skill.MasterSkill(in_use_skill[id]);
        }
        return 0;
    }

    public static void resetFlag() {
        IsDamage = true;
        Strik = 0;
        IsEnhance = false;
        IsStatus = false;
    }

}