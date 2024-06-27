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
    private boolean isMuted = false;
    private String currentSoundFilePath;

    /**
     * Plays the sound file located at the specified file path.
     *
     * @param filePath The file path of the sound file.
     */
    public void playSound(String filePath) {
        if (isMuted) {
            currentSoundFilePath = filePath;
            return;
        }

        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            currentSoundFilePath = filePath;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing sound.
     */
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Loops the sound file located at the specified file path.
     *
     * @param filePath The file path of the sound file.
     */
    public void loopSound(String filePath) {
        if (isMuted) {
            currentSoundFilePath = filePath;
            return;
        }

        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            currentSoundFilePath = filePath;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Toggles the mute state of the sound manager.
     */
    public void toggleMute() {
        isMuted = !isMuted;
        if (isMuted) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        } else {
            if (currentSoundFilePath != null) {
                playSound(currentSoundFilePath);
            }
        }
    }
}