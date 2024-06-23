package representation;

import java.util.ArrayList;
import java.util.List;

import core.Game;
import univers.base.Character;
import univers.base.Item;

public class TradeNode extends Node {
    private List<Item> itemsForSale;
    private List<Node> options;

    public TradeNode(int id, String description, List<Item> itemsForSale) {
        super(id, description);
        this.itemsForSale = itemsForSale;
        this.options = new ArrayList<>();

    }

    public void addChanceOption(Node node) {
        // Placeholder: adding options for trading
        this.options.add(node);
    }

    public List<Item> getItemsForSale() {
        return itemsForSale;
    }

    public void tradeItem(String itemKey) {
        Item itemToTrade = null;
        Character character = Game.getCurrentCharacter();
        for (Item item : itemsForSale) {
            if (item.getName().equals(itemKey)) {
                itemToTrade = item;
                break;
            }
        }

        if (itemToTrade != null && character.hasEnoughMoney(itemToTrade.getPrice())) {
            character.trade(itemToTrade);
            character.decreaseMoney(itemToTrade.getPrice());
        } else {
            System.out.println("Not enough money or item not found");
        }
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