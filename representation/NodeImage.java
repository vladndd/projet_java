package representation;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Décorateur concret pour afficher une image
public class NodeImage extends NodeDecorator {
    private String imagePath;

    public NodeImage(Node noeud, String imagePath) {
        super(noeud);
        this.imagePath = imagePath;
    }

    @Override
    public void display() {
        super.display();
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(null, label, "Image du Noeud", JOptionPane.PLAIN_MESSAGE);
    }
}