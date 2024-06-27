package representation;

import java.util.List;
import java.util.ArrayList;

import utility.Utility;

/**
 * DecisionNode class represents a node where the player must make a choice.
 * It manages a list of possible options (nodes) that the player can choose
 * from.
 */
public class DecisionNode extends Node implements Optionable {
    private List<Node> options;

    /**
     * Constructs a DecisionNode instance with the specified ID and description.
     *
     * @param id          The ID of the node.
     * @param description The description of the node.
     */
    public DecisionNode(int id, String description) {
        super(id, description);
        this.options = new ArrayList<>();
    }

    /**
     * Adds a possible option node to the list of options.
     *
     * @param node The Node to be added as a possible option.
     */
    public void addOption(Node node) {
        options.add(node);
    }

    /**
     * Chooses the next node based on the player's input.
     *
     * @return The next Node to advance to, chosen by the player.
     */
    @Override
    public Node chooseNext() {
        int choice = Utility.scanner.nextInt();
        return options.get(choice - 1);
    }

    /**
     * Checks the next node in the list of options.
     *
     * @return The first Node in the list of options.
     */
    public Node checkNext() {
        return options.get(0);
    }

    /**
     * Gets the list of possible option nodes.
     *
     * @return The list of possible option nodes.
     */
    public List<Node> getOptions() {
        return options;
    }
}
