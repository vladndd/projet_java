package representation;

public class TerminalNode extends Node {
    public TerminalNode(int id, String description) {
        super(id, description);
    }

    @Override
    public Node chooseNext() {
        return this; // Retourne lui-même, car c'est un nœud terminal.
    }

    public Node checkNext() {
        return this; // Retourne lui-même, car c'est un nœud terminal.
    }
}
