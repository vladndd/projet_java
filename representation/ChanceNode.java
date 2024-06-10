package representation;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// Nœud de chance où un événement aléatoire détermine le prochain nœud
public class ChanceNode extends Node {
    private List<Node> outcomes;
    private Random random;

    public ChanceNode(int id, String description) {
        super(id, description);
        this.outcomes = new ArrayList<>();
        this.random = new Random();
    }

    public void addOutcome(Node node) {
        outcomes.add(node);
    }

    @Override
    public Node chooseNext() {
        int index = random.nextInt(outcomes.size());
        return outcomes.get(index);
    }

    public Node checkNext() {
        int index = random.nextInt(outcomes.size());
        return outcomes.get(index);
    }

    public List<Node> getOutcomes() {
        return outcomes;
    }
}
