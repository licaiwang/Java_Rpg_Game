package Gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderHelper {

    public static String readTextFromTxt(String filename) throws IOException {
        FileReader fr = new FileReader("D:/JavaWorkSpace/my_rpg/MyRpg/src/res/intro/"+filename+".txt");
        BufferedReader br = new BufferedReader(fr);
        String words = "";
		while (br.ready()) {
            words += br.readLine();
		}
        fr.close();
        return words.replace("﻿","");
    }
  
}
