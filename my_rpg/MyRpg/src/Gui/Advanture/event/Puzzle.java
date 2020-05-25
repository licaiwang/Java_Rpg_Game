package Gui.Advanture.event;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Basic.ResReader;
import Gui.Advanture.AdvantureBackground;
import Gui.Advanture.BattleSidePanel;
import Gui.Helper.MusicHelper;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class Puzzle extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Image puzzle;
    public Integer Id;
    public Integer degree = 0;
    public boolean isRotate;
    public BufferedImage rotated;
    public BufferedImage temp_puzzle;
    public static final Integer pic_size = 130;

    public Puzzle(Integer id) {
        super();
        Id = id;
        isRotate = false;
        setOpaque(false);
        setPreferredSize(new Dimension(pic_size, pic_size));
        puzzle = new ImageIcon(ResReader.path + "res/battlePanel/event/puzzle/" + id + ".png").getImage();
        temp_puzzle = convertToBufferedImage(puzzle);
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isRotate = true;
                Thread playMusic = new MusicHelper("puzzle/puzzle.wav");
				playMusic.start();
                validate();
                repaint();
            }
        });
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        int x = w / 2;
        int y = h / 2;
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, this);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();
        return rotated;
    }

    public static BufferedImage convertToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        // 把 bufferImage 畫上去
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!isRotate) {
            temp_puzzle = rotateImageByDegrees(temp_puzzle, Fortress.degree[Id - 1]);
            g2.drawImage(temp_puzzle, 0, 0, pic_size, pic_size, this);
        } else {
            degree = Fortress.degree[Id - 1] + 1;
            BufferedImage temp = convertToBufferedImage(puzzle);
            temp = rotateImageByDegrees(temp, degree);
            g2.drawImage(temp, 0, 0, pic_size, pic_size, this);
            switch (degree) {
                case 90:
                    isRotate = false;
                    Fortress.degree[Id - 1] = 90;
                    checkAnswer();
                    break;
                case 180:
                    isRotate = false;
                    Fortress.degree[Id - 1] = 180;
                    checkAnswer();
                    break;
                case 270:
                    isRotate = false;
                    Fortress.degree[Id - 1] = 270;
                    checkAnswer();
                    break;
                case 360:
                    isRotate = false;
                    Fortress.degree[Id - 1] = 0;
                    checkAnswer();
                    break;
                default:
                    Fortress.degree[Id - 1] = Fortress.degree[Id - 1] + 1;
                    validate();
                    repaint();
            }
        }
    }

    private void checkAnswer() {
        int rightAns = 0;
        if (Fortress.answer[Id - 1] == 3) {
            Fortress.answer[Id - 1] = 0;
        } else {
            Fortress.answer[Id - 1] += 1;
        }
        for (int i = 0; i < Fortress.answer.length; i++) {
            if (Fortress.answer[i] == 0) {
                rightAns += 1;
            }
        }
        if(rightAns == 9)
        {
            Fortress.btn_back.setVisible(false);
            Fortress.removePuzzle();
            BattleSidePanel.setBackButtonEnable();
            Thread playMusic = new MusicHelper("puzzle/open.wav");
            playMusic.start();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    BattleSidePanel.setBackButtonAble();
                    AdvantureBackground.showCampFire();
                }
            }, 4000);        
        }
    }
}