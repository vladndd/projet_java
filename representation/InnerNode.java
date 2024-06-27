package representation;

import java.util.Stack;

/**
 * InnerNode class represents an intermediate node in the game.
 * It manages a stack of next nodes and allows for sequential traversal through
 * them.
 */
public class InnerNode extends Node {
    private Stack<Node> nextNodes;

    /**
     * Constructs an InnerNode instance with the specified ID and description.
     *
     * @param id          The ID of the node.
     * @param description The description of the node.
     */
    public InnerNode(int id, String description) {
        super(id, description);
        this.nextNodes = new Stack<>();
    }

    /**
     * Adds a next node to the stack of next nodes.
     *
     * @param node The Node to be added to the stack.
     */
    public void addNextNode(Node node) {
        nextNodes.push(node);
    }

    /**
     * Chooses the next node by popping from the stack.
     *
     * @return The next Node to advance to, or null if the stack is empty.
     */
    @Override
    public Node chooseNext() {
        if (nextNodes.isEmpty()) {
            return null;
        }
        return nextNodes.pop();
    }

    /**
     * Checks the next node by peeking at the stack without removing it.
     *
     * @return The next Node to advance to, or null if the stack is empty.
     */
    public Node checkNext() {
        if (nextNodes.isEmpty()) {
            return null;
        }
        return nextNodes.peek();
    }
}
