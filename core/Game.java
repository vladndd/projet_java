package core;

import representation.*;
import univers.Character;
import univers.Planet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Game class represents the main game logic and state for the Space Adventure
 * Game.
 * It manages the current game node, character, and planet information.
 */
public class Game implements Serializable {
    private Node currentNode;
    public static final List<Planet> PLANETS_LIST = new ArrayList<>();
    private Character character;
    private NodeFactory nodeFactory;
    private static final long serialVersionUID = 1L; // Add serialVersionUID

    /**
     * Constructs a new Game instance and initializes the list of planets.
     */
    public Game() {
        initializePlanets();
    }

    /**
     * Gets the current node in the game.
     *
     * @return The current Node.
     */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**
     * Updates the current planet of the character.
     *
     * @param newPlanet The new Planet to be set as the current planet.
     */
    public void updateCurrentPlanet(Planet newPlanet) {
        getCurrentCharacter().setCurrentPlanet(newPlanet);
    }

    /**
     * Sets the character for the game.
     *
     * @param character The Character to be set.
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Gets the current character in the game.
     *
     * @return The current Character.
     */
    public Character getCurrentCharacter() {
        return character;
    }

    /**
     * Advances the game to the specified node.
     *
     * @param nodeId The ID of the node to advance to.
     */
    public void advanceToNode(int nodeId) {
        System.out.println("Advancing to node: " + nodeId);
        currentNode = this.nodeFactory.getNode(nodeId);

        if (currentNode instanceof TerminalNode) {
            System.out.println("Game over: " + currentNode.getDescription());
        }
    }

    /**
     * Saves the current game state to a file.
     *
     * @param filename The name of the file to save the game state to.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void saveGame(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(this);
        out.close();
    }

    /**
     * Loads a saved game state from a file.
     *
     * @param filename The name of the file to load the game state from.
     * @return The loaded Game instance.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of the serialized object cannot
     *                                be found.
     */
    public static Game loadGame(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        Game game = (Game) in.readObject();
        in.close();
        return game;
    }

    /**
     * Initializes the list of planets used in the game.
     */
    private void initializePlanets() {
        PLANETS_LIST.add(new Planet("Mercury", "Very hot planet with no atmosphere."));
        PLANETS_LIST.add(new Planet("Venus", "Thick atmosphere and volcanic activity."));
        PLANETS_LIST.add(new Planet("Earth", "Rich in life and diverse climates."));
        PLANETS_LIST.add(new Planet("Mars", "Red planet with potential for life."));
        PLANETS_LIST.add(new Planet("Jupiter", "Giant gas planet with a strong magnetic field."));
        PLANETS_LIST.add(new Planet("Saturn", "Known for its extensive ring system."));
        PLANETS_LIST.add(new Planet("Uranus", "Ice giant with a tilted axis."));
        PLANETS_LIST.add(new Planet("Neptune", "Cold blue planet with strong winds."));
        PLANETS_LIST.add(new Planet("Pluto", "Dwarf planet with a heart-shaped glacier."));
    }

    /**
     * Creates a pool of nodes for the game from a JSON file.
     *
     * @throws IOException If an I/O error occurs during node creation.
     */
    public void createNodePool() throws IOException {
        NodeFactory nodeFactory = new NodeFactory(this);
        this.nodeFactory = nodeFactory;
        Map<Integer, Node> nodes = nodeFactory.createNodes("./gameMap.json");
        currentNode = nodes.get(0); // Start at the initial node
    }
}
