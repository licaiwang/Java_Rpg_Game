package Gui;
import javax.swing.*;
import java.awt.*;

public class MainGUI {

    static int which = 0;
    static Hall hall;
    static Inn inn;

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final MyFrame frame = new MyFrame();
                hall = new Hall();
                frame.add(hall);
                frame.setSize(1200, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setTitle("泰格達傳說 - 武漢肺炎");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
            }

        });
    }
    public static class MyFrame extends JFrame {
        public MyFrame() {
            super();
        }
    }

}
