package Gui.Helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Basic.ResReader;



public class FileReaderHelper {
    /*

    讀取 txt 文本

    */
    public static String readTextFromTxt(String filename) throws IOException {
        String res =   ResReader.Current_Dic+("/res/intro/"+filename+".txt");
        InputStreamReader fr = new InputStreamReader(new FileInputStream(res), "UTF-8");
        BufferedReader br = new BufferedReader(fr);
        String words = "";
		while (br.ready()) {
            words += br.readLine();
		}
        fr.close();
        return words.replace("﻿","");
    }
  
}
