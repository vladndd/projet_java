package representation;

import univers.base.Planet;
import java.util.Stack;

public class InnerNode extends Node {
    private Stack<Node> nextNodes;

    private Planet planet;

    private boolean planetSelector;
    private boolean citySelector;

    public InnerNode(int id, String description) {
        super(id, description);
        this.nextNodes = new Stack<>();

    }

    public boolean isPlanetSelector() {
        return planetSelector;
    }

    public boolean isCitySelector() {
        return citySelector;
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

    public void display() {
        super.display();
        // Affiche des informations supplémentaires si nécessaire
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

}