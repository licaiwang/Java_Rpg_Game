package Gui.Helper;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import Basic.ResReader;
 /** 
     * 
     * @author  Rorschach
     * 
     *  
     * 生成自定義按紐的地方
     *  
     **
     */
public class CreateButton extends JButton {
    private static final long serialVersionUID = 1L;

    public CreateButton(String label) {
        super(label);
        Dimension size = getPreferredSize();
        size.width = 70;
        size.height = 40;
        setOpaque(false);
        setPreferredSize(size);
        // 這個呼叫使 JButton 不畫背景，而允許我們畫一個自己的背景。
        setContentAreaFilled(false);
    }

    // 畫背景
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        } 
        g.fillRect(0, 0, getSize().width-1, getSize().height-1);
    
        g.drawImage(ResReader.buttonFrame, 0, 0, getWidth(), getHeight(), this);
        // 這個呼叫會畫出一個圓的按紐。
        //g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        
        super.paintComponent(g);
    }

    
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRect(0,0, getSize().width-1, getSize().height-1);
        // 用簡單的弧畫按鈕的邊界。
        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // 偵測點選事件
    Shape shape;

    public boolean contains(int x, int y) {
        // 如果按鈕改變大小，產生一個新的形狀物件。
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

    public static void clickSound()
    {
        //按下按鈕的音效
        Thread playButton = new MusicHelper("button.wav");
        playButton.start();
    }

}
