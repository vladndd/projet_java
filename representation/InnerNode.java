package representation;

import java.util.Stack;

public class InnerNode extends Node {
    private Stack<Node> nextNodes;

    public InnerNode(int id, String description) {
        super(id, description);
        this.nextNodes = new Stack<>();

    }

    public void addNextNode(Node node) {
        nextNodes.push(node);
    }

    @Override
    public Node chooseNext() {
        if (nextNodes.isEmpty()) {
            return null;
        }
        return nextNodes.pop();
    }

    public Node checkNext() {
        if (nextNodes.isEmpty()) {
            return null;
        }
        return nextNodes.peek();
    }
}