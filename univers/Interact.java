package univers;

/**
 * Interact interface defines common interactions for characters in the game.
 * It includes methods for fighting and trading.
 */
public interface Interact {

    /**
     * Reduces the health of the character by the specified damage intake.
     *
     * @param intakeDamage The damage to be subtracted from the health.
     */
    void fight(int intakeDamage);

    /**
     * Trades the specified item by adding it to the inventory.
     *
     * @param item The item to be traded.
     */
    void trade(Item item);
}
