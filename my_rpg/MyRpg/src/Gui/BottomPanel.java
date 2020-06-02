package Gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.Font;
import Gui.Helper.FileReaderHelper;
  /** 
     * 
     * @author  Rorschach
     * 
     * 底下提供文字訊息的地方
     *  
     **
     */
public class BottomPanel {

    static JPanel bottomPanel;
    static JTextArea jTextArea;
    static JScrollPane jScrollPane;
    public static String content;
  
    public BottomPanel() {
        super();
        bottomPanel = new JPanel();
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
        //重設文字區域
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
        // 讀取文本
        try {
            content = FileReaderHelper.readTextFromTxt(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}