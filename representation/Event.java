package representation;

/**
 * Event interface represents an event in the game.
 * It defines methods for choosing the next node and checking the next node.
 */
public interface Event {

    /**
     * Chooses the next node based on the event's logic.
     *
     * @return The next Node to advance to.
     */
    Node chooseNext();

    /**
     * Checks the next node based on the event's logic.
     *
     * @return The next Node to advance to.
     */
    Node checkNext();
}
