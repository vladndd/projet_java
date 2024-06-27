package representation;

/**
 * TerminalNode class represents the final node in the game.
 * It indicates the end of a particular path or scenario in the game.
 */
public class TerminalNode extends Node {

    /**
     * Constructs a TerminalNode instance with the specified ID and description.
     *
     * @param id          The ID of the node.
     * @param description The description of the node.
     */
    public TerminalNode(int id, String description) {
        super(id, description);
    }

    /**
     * Chooses the next node, which is always the current node itself since this is
     * a terminal node.
     *
     * @return The current TerminalNode.
     */
    @Override
    public Node chooseNext() {
        return this;
    }

    /**
     * Checks the next node, which is always the current node itself since this is a
     * terminal node.
     *
     * @return The current TerminalNode.
     */
    public Node checkNext() {
        return this;
    }
}
