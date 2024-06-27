package representation;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * ChanceNode class represents a node where a random event determines the next
 * node.
 * It manages a list of possible outcome nodes and uses a random number
 * generator to select one.
 */
public class ChanceNode extends Node {
    private List<Node> outcomes;
    private Random random;

    /**
     * Constructs a ChanceNode instance with the specified ID and description.
     *
     * @param id          The ID of the node.
     * @param description The description of the node.
     */
    public ChanceNode(int id, String description) {
        super(id, description);
        this.outcomes = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Adds a possible outcome node to the list of outcomes.
     *
     * @param node The Node to be added as a possible outcome.
     */
    public void addOutcome(Node node) {
        outcomes.add(node);
    }

    /**
     * Chooses the next node randomly from the list of possible outcomes.
     *
     * @return The next Node to advance to.
     */
    @Override
    public Node chooseNext() {
        int index = random.nextInt(outcomes.size());
        return outcomes.get(index);
    }

    /**
     * Checks the next node randomly from the list of possible outcomes.
     *
     * @return The next Node to advance to.
     */
    public Node checkNext() {
        int index = random.nextInt(outcomes.size());
        return outcomes.get(index);
    }

    /**
     * Gets the list of possible outcome nodes.
     *
     * @return The list of possible outcome nodes.
     */
    public List<Node> getOutcomes() {
        return outcomes;
    }
}
