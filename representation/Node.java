package representation;

import java.awt.MediaTracker;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Node is an abstract class that represents a point in the game.
 * It contains basic information such as ID, description, background image, and
 * sound file path.
 * Subclasses must implement the methods for choosing and checking the next
 * node.
 */
public abstract class Node implements Serializable, Event {
    protected int id;
    protected String description;
    private ImageIcon backgroundImage;
    private String soundFilePath; // Sound file path for the node

    /**
     * Constructs a Node instance with the specified ID and description.
     *
     * @param id          The ID of the node.
     * @param description The description of the node.
     */
    public Node(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Gets the description of the node.
     *
     * @return The description of the node.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the ID of the node.
     *
     * @return The ID of the node.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the sound file path associated with the node.
     *
     * @return The sound file path.
     */
    public String getSoundFilePath() {
        return soundFilePath;
    }

    /**
     * Sets the sound file path associated with the node.
     *
     * @param soundFilePath The sound file path to set.
     */
    public void setSoundFilePath(String soundFilePath) {
        this.soundFilePath = soundFilePath;
    }

    /**
     * Abstract method to choose the next node.
     * Must be implemented by subclasses.
     *
     * @return The next Node to advance to.
     */
    public abstract Node chooseNext();

    /**
     * Abstract method to check the next node.
     * Must be implemented by subclasses.
     *
     * @return The next Node to check.
     */
    public abstract Node checkNext();

    /**
     * Gets the background image of the node.
     *
     * @return The background image as an ImageIcon.
     */
    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Sets the background image of the node from a given image path.
     *
     * @param imagePath The path to the background image file.
     * @throws IllegalArgumentException If the image path is invalid.
     */
    public void setBackgroundImage(String imagePath) throws IllegalArgumentException {
        this.backgroundImage = new ImageIcon(imagePath);
        if (backgroundImage.getImageLoadStatus() != MediaTracker.COMPLETE) {
            throw new IllegalArgumentException("Invalid image path: " + imagePath);
        }
    }
}
