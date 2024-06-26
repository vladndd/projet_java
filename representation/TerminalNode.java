package representation;

public class TerminalNode extends Node {
    public TerminalNode(int id, String description) {
        super(id, description);
    }

    @Override
    public Node chooseNext() {
        return this;
    }

    public Node checkNext() {
        return this;
    }
}
