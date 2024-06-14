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

    public void tradeItem(String itemKey) {
        // Placeholder: logic for trading
        // Assuming trade completion determines the next node

        Item item = new Item(itemKey, 5, 10, 20);
        this.character.trade(item);
    }

    @Override
    public Node chooseNext() {
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