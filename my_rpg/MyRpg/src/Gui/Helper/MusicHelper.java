package Gui.Helper;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import Basic.ResReader;

import java.io.File;
import java.io.IOException;



public class MusicHelper extends Thread {
    private String effect;
    private static Clip clip;

    public MusicHelper(String soundEffect) {
        // 音樂的部分額外抽出
        this.effect = ResReader.Current_Dic+("/res/soundEffect/"+soundEffect);
    }
    public void run() {
        play(effect);
    }

    public void play(String filePath) {
        final File file = new File(filePath);
        try {
            final AudioInputStream in = AudioSystem.getAudioInputStream(file);
            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, outFormat);
            final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            if (line != null) {
                line.open(outFormat);
                line.start();
                stream(AudioSystem.getAudioInputStream(outFormat, in), line);
                line.drain();
                line.stop();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        final byte[] buffer = new byte[65536];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }

    public static void playBackgroundMusic(String name) {
        // 背景音樂
        try {
            AudioInputStream audioIn = AudioSystem
                    .getAudioInputStream(new File(ResReader.Current_Dic+("/res/backgroundMusic/"+name+".wav")));
            clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
    }
    public static void stopBackgroundMusic()
    {
        clip.close();
    }


}
