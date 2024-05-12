package representation;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class InnerNode extends Node {
    private List<Node> nextNodes;

    public InnerNode(int id, String description) {
        super(id, description);
        this.nextNodes = new ArrayList<>();
    }

    public void addNextNode(Node node) {
        nextNodes.add(node);
    }

    @Override
    public Node chooseNext() {
        if (nextNodes.isEmpty()) {
            return null;
        }
        return nextNodes.get(0); // Simplifié pour l'exemple. Peut être randomisé ou basé sur le choix de
                                 // l'utilisateur.
    }

    public void display() {
        super.display();
        // Affiche des informations supplémentaires si nécessaire
    }
}