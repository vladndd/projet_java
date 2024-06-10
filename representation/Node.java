package representation;

import javax.swing.ImageIcon;

public abstract class Node implements Event {
    protected int id;
    protected String description;
    private ImageIcon backgroundImage;

    public Node(int id, String description) {
        this.id = id;
        this.description = description;

    }

    public void display() {
        System.out.println(description);
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public abstract Node chooseNext();

    public abstract Node checkNext();

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

}
