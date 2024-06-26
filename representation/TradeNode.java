package representation;

import java.util.ArrayList;
import java.util.List;

import core.Game;
import univers.Character;
import univers.Item;
import java.util.Random;

import javax.swing.JOptionPane;

public class TradeNode extends Node {
    private List<Item> itemsForSale;
    private List<Node> options;
    private Game game;
    private Item item;
    private Random random;

    public TradeNode(int id, String description, List<Item> itemsForSale, Game game) {
        super(id, description);
        this.itemsForSale = itemsForSale;
        this.options = new ArrayList<>();
        this.game = game;
        this.random = new Random();
    }

    public void addChanceOption(Node node) {
        this.options.add(node);
    }

    public List<Item> getItemsForSale() {
        return itemsForSale;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void tradeItem(Item item) {
        Item itemToTrade = item;
        Character character = game.getCurrentCharacter();

        if (itemToTrade != null && character.hasEnoughMoney(itemToTrade.getPrice())) {
            character.trade(itemToTrade);
            character.decreaseMoney(itemToTrade.getPrice());
            JOptionPane.showMessageDialog(null, "Trade successful!", "Trade Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Not enough money or item not found", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Node chooseNext() {
        this.tradeItem(this.item);
        int index = random.nextInt(options.size());
        return this.options.get(index);
    }

    @Override
    public Node checkNext() {
        throw new UnsupportedOperationException("Unimplemented method 'checkNext'");
    }
}