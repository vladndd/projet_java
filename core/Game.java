package core;

import representation.*;
import univers.base.*;
import univers.base.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game implements Serializable {
    private Node currentNode;
    public static final List<Planet> PLANETS_LIST = new ArrayList<>();
    private List<City> cities = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();
    private NodeFactory nodeFactory;

    public Game() {
        initializePlanets();
        initializeCitiesOnPlanets();
        initializeBosses();
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void updateCurrentPlanet(Planet newPlanet) {
        getCurrentCharacter().setCurrentPlanet(newPlanet);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public Character getCurrentCharacter() {
        return characters.get(0);
    }

    public void advanceToNode(int nodeId) {

        System.out.println("Advancing to node: " + nodeId);

        currentNode = this.nodeFactory.getNode(nodeId);

        if (currentNode instanceof TerminalNode) {
            System.out.println("Game over: " + currentNode.getDescription());
        }
    }

    public void saveGame(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(this);
        out.close();
    }

    public static Game loadGame(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        Game game = (Game) in.readObject();
        in.close();
        return game;
    }

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

    private void initializeBosses() {
        for (City city : cities) {
            int numBosses = new Random().nextInt(2) + 1;
            for (int i = 0; i < numBosses; i++) {
                String bossName = city.getPlanet() + " Boss " + (i + 1);
                Enemy boss = new Enemy(bossName, 100, 20, true);
                city.setBoss(boss);
            }
        }
    }

    private void initializeCitiesOnPlanets() {
        for (Planet planet : PLANETS_LIST) {
            int numCities = new Random().nextInt(3) + 1;
            for (int i = 0; i < numCities; i++) {
                String cityName = planet.getName() + " City " + (i + 1);
                City city = new City(cityName, planet);
                cities.add(city);
                planet.addCity(city);
            }
        }
    }

    public void createNodePool() throws IOException {
        NodeFactory nodeFactory = new NodeFactory(characters);
        this.nodeFactory = nodeFactory;
        Map<Integer, Node> nodes = nodeFactory.createNodes("./gameMap.json");
        currentNode = nodes.get(0); // Start at the initial node
    }
}
