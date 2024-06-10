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
        // Simulation d'une interaction utilisateur pour choisir un nœud suivant
        System.out.println("Choisissez une option:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ". " + options.get(i).description);
        }

        int choice = Utility.scanner.nextInt();

        // Scanner sc = new Scanner(System.in);

        return options.get(choice - 1);
    }

    public Node checkNext() {
        return options.get(0);
    }

    public List<Node> getOptions() {
        return options;
    }
}
