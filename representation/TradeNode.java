package representation;

import java.util.ArrayList;
import java.util.List;

import univers.base.Character;
import univers.base.Item;

public class TradeNode extends Node {
    private String[] itemsForSale;
    private List<Node> options;
    private Character character;

    public TradeNode(int id, String description, String[] itemsForSale, Character character) {
        super(id, description);
        this.itemsForSale = itemsForSale;
        this.options = new ArrayList<>();
        this.character = character;

    }

    public void addChanceOption(Node node) {
        // Placeholder: adding options for trading
        this.options.add(node);
    }

    public String[] getItemsForSale() {
        return itemsForSale;
    }

    @Override
    public Node chooseNext() {

        Item i = new Item("Test item trade", 10);
        this.character.trade(i);
        // Logic for choosing the next node after trading
        // Placeholder: assuming trade completion determines the next node
        return this.options.get(0); // Trade completed
    }

    @Override
    public Node checkNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkNext'");
    }
}