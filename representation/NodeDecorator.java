package representation;

import java.util.Scanner;

// Décorateur abstrait
public abstract class NodeDecorator implements Event {
    protected Node node;

    public NodeDecorator(Node node) {
        this.node = node;
    }

    @Override
    public void display() {
        node.display();
    }

    @Override
    public Node chooseNext() {
        return node.chooseNext();
    }
}