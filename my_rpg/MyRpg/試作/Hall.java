package Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;

public class Hall extends JPanel {
    private static final long serialVersionUID = 1L;
    static String DEFALT_PATH = "D:/JavaWorkSpace/my_rpg/MyRpg/src/res/";
    static int i = 90;
    static boolean IsChossing;
    static boolean IsClockwise;
    static boolean goSleep = false;
    
    public Hall() {
        super();
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    IsChossing = true;
                    IsClockwise = true;
                    Thread playMusic = new MusicHelper("clock.wav");
                    playMusic.start();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
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
      
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        // Main
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
            } else {
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
        AffineTransform at = AffineTransform.getTranslateInstance(size.getWidth() / 2 - 35, size.getHeight() / 2 - 35);
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

    public static void pause(int time) {
        try {
            Thread.sleep(time);// 在此處睡眠一會，要不運動太快
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}