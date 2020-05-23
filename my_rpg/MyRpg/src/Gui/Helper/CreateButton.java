package Gui.Helper;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import Basic.ResReader;

public class CreateButton extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CreateButton(String label) {
        super(label);
        Dimension size = getPreferredSize();
        size.width = 70;
        size.height = 40;
        setOpaque(false);
        setPreferredSize(size);
        // 這個呼叫使JButton不畫背景，而允許我們畫一個圓的背景。
        setContentAreaFilled(false);
    }

    // 畫圓的背景和標籤
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // 你可以選一個高亮的顏色作為圓形按鈕類的屬性
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        } 
        g.fillRect(0, 0, getSize().width-1, getSize().height-1);
      
        g.drawImage(ResReader.buttonFrame, 0, 0, getWidth(), getHeight(), this);
        //g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        // 這個呼叫會畫一個標籤和焦點矩形。
        super.paintComponent(g);
    }

    // 用簡單的弧畫按鈕的邊界。
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRect(0,0, getSize().width-1, getSize().height-1);
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

}
