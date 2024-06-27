package representation;

import java.util.List;

/**
 * Optionable interface represents an entity that can provide a list of options.
 * It is used to define objects that have multiple possible next nodes to choose
 * from.
 */
public interface Optionable {

    /**
     * Gets the list of options (nodes) available.
     *
     * @return A list of Node objects representing the options.
     */
    List<Node> getOptions();
}
