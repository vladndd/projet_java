package utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * SoundManager class provides methods to manage sound playback, including
 * playing, stopping, and looping sounds.
 */
public class SoundManager {
    private Clip clip;

    /**
     * Plays a sound from the specified file path.
     *
     * @param filePath The path to the sound file.
     */
    public void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing sound if it is running.
     */
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Loops a sound continuously from the specified file path.
     *
     * @param filePath The path to the sound file.
     */
    public void loopSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
