package representation;

import java.util.List;
import java.util.ArrayList;

import utility.Utility;

// Nœud de décision où le joueur doit faire un choix
public class DecisionNode extends Node implements Optionable {
    private List<Node> options;

    public DecisionNode(int id, String description) {
        super(id, description);
        this.options = new ArrayList<>();
    }

    public void addOption(Node node) {
        options.add(node);
    }

    @Override
    public Node chooseNext() {

        int choice = Utility.scanner.nextInt();

        return options.get(choice - 1);
    }

    public Node checkNext() {
        return options.get(0);
    }

    public List<Node> getOptions() {
        return options;
    }
}
