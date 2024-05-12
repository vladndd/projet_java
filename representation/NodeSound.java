package representation;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// Décorateur concret pour jouer un son
public class NodeSound extends NodeDecorator {
    private String soundPath;

    public NodeSound(Node node, String soundPath) {
        super(node);
        this.soundPath = soundPath;
    }

    @Override
    public void display() {
        super.display();
        playSound(soundPath);
    }

    private void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du son: " + e.getMessage());
        }
    }
}