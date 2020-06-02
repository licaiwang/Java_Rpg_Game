package Gui.Helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Basic.ResReader;
 /** 
     * 
     * @author  Rorschach
     * 
     *  
     * 讀取故事文本的地方
     *  
     **
     */
public class FileReaderHelper {
    public static String readTextFromTxt(String filename) throws IOException {
        String res =   ResReader.Current_Dic+("/res/intro/"+filename+".txt");
        InputStreamReader fr = new InputStreamReader(new FileInputStream(res), "UTF-8");
        BufferedReader br = new BufferedReader(fr);
        String words = "";
		while (br.ready()) {
            words += br.readLine();
		}
        fr.close();
        // TODO: Find the answer
        // 就算用 "UTF-8" 解碼，偶爾還是會出現這個亂碼，目前找不出原因
        return words.replace("﻿","");
    }
  
}
