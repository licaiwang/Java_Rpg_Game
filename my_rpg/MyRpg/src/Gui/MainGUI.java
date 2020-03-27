package Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import Gui.MusicHelper;

public class MainGUI {
    static String DEFALT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/";
    static int i = 90;
    static boolean IsChossing;
    static boolean IsClockwise;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.setSize(1200, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

    public static class MyFrame extends JFrame {
        private static final long serialVersionUID = 1L;
        public static final String TITLE = "泰格達傳說 - 武漢肺炎";

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            setTitle(TITLE);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            MyPanel panel = new MyPanel(this);
            panel.add(new JLabel("泰格達"));
            panel.setFocusable(true);
            panel.requestFocusInWindow();
            panel.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        IsChossing = true;
                        IsClockwise = true;
                        Thread playMusic = new MusicHelper("clock.wav");
                        playMusic.start();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D) {
                        IsChossing = true;
                        IsClockwise = false;
                        Thread playMusic = new MusicHelper("clock.wav");
                        playMusic.start();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub

                }
            });
            setContentPane(panel);
        }

    }

    public static class MyPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public MyPanel(MyFrame frame) {
            super();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBackground(g);
            if (IsChossing) {
                if (!IsClockwise) {
                    rotateClockwise(g, i++, getSize());
                     switch (i) {   
                        case 135:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 215:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 315:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 405:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 450:
                            i = 90;
                            pause(1000);
                            IsChossing = false;
                            break;
                    }
                }
                else{
                    rotateClockwise(g, i--, getSize());
                    switch (i) {
                        case 45:
                            i = 405;
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 90:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 135:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 215:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 315:
                            pause(1000);
                            IsChossing = false;
                            break;
                        case 405:
                            pause(1000);
                            IsChossing = false;
                            break;
                    }
                }   
            } else {
                rotateClockwise(g, i, getSize());
            }
        }

        private void drawBackground(Graphics g) {
            Image image = new ImageIcon(DEFALT_PATH + "background.png").getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }

        public void rotateClockwise(Graphics g, Integer rads, Dimension size) {
            BufferedImage clock = LoadImage(DEFALT_PATH + "clock.png");
            AffineTransform at = AffineTransform.getTranslateInstance(size.getWidth() / 2 - 35,
                    size.getHeight() / 2 - 35);
            at.rotate(Math.toRadians(rads), 41, clock.getHeight() / 2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(clock, at, null);
            repaint();
        }


        BufferedImage LoadImage(String Filename) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(Filename));
            } catch (IOException e) {
            }
            return img;
        }

        private void drawString(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(new Font(null, Font.PLAIN, 25));
            g2d.drawString("Hello World!", 20, 60);
            g2d.drawString("你好, 世界!", 20, 120);
            g2d.dispose();
        }
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);// 在此處睡眠一會，要不運動太快
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
