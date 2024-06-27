package representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import core.Game;
import univers.Character;
import univers.Item;

/**
 * TradeNode class represents a node where the player can trade items.
 * It manages a list of items for sale and possible outcomes after trading.
 */
public class TradeNode extends Node {
    private List<Item> itemsForSale;
    private List<Node> options;
    private Game game;
    private Item item;
    private Random random;

    /**
     * Constructs a TradeNode instance with the specified parameters.
     *
     * @param id           The ID of the node.
     * @param description  The description of the node.
     * @param itemsForSale The list of items available for sale.
     * @param game         The Game instance associated with this node.
     */
    public TradeNode(int id, String description, List<Item> itemsForSale, Game game) {
        super(id, description);
        this.itemsForSale = itemsForSale;
        this.options = new ArrayList<>();
        this.game = game;
        this.random = new Random();
    }

    /**
     * Adds a possible outcome node to the list of options.
     *
     * @param node The Node to be added as a possible outcome.
     */
    public void addChanceOption(Node node) {
        this.options.add(node);
    }

    /**
     * Gets the list of items available for sale.
     *
     * @return The list of items for sale.
     */
    public List<Item> getItemsForSale() {
        return itemsForSale;
    }

    /**
     * Sets the item to be traded.
     *
     * @param item The Item to be traded.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Executes the trade for the specified item.
     *
     * @param item The Item to be traded.
     */
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

    /**
     * Chooses the next node after executing the trade.
     *
     * @return The next Node to advance to.
     */
    @Override
    public Node chooseNext() {
        this.tradeItem(this.item);
        int index = random.nextInt(options.size());
        return this.options.get(index);
    }

    /**
     * Checks the next node. This method is not implemented for TradeNode.
     *
     * @throws UnsupportedOperationException If the method is called.
     */
    @Override
    public Node checkNext() {
        throw new UnsupportedOperationException("Unimplemented method 'checkNext'");
    }
}
