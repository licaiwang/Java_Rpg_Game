package Gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.Font;
import Gui.Helper.FileReaderHelper;
public class BottomPanel {
	/*

    底下顯示文字的地方
    
    */
    static JPanel bottomPanel;
    static JTextArea jTextArea;
    static JScrollPane jScrollPane;
    public static String content;
  
    public BottomPanel() {
        super();
        // buttom
        
        bottomPanel = new JPanel();
        // bottomPanel.setLayout(new FlowLayout(50, 50, 50));
        bottomPanel.setBackground(Color.BLACK);
        // init text on buttom
        jTextArea = new JTextArea();
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setOpaque(false);
        jScrollPane.setPreferredSize(new Dimension(1000, 80));
        readText("FirstTown");
        resetTextArea();
    }

    public static void resetTextArea() {
        jTextArea.setFont(new Font("Serif", Font.PLAIN, 20));
        jTextArea.setBackground(Color.BLACK);
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setColumns(50);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setText(content);
        jScrollPane.setOpaque(false);
        bottomPanel.add(jScrollPane);
    }


    public static void readText(String name) {
        try {
            content = FileReaderHelper.readTextFromTxt(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}