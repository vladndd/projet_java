package core;

import representation.*;
import univers.base.*;
import univers.base.Character;
import utility.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game implements Serializable {
    private Node currentNode;
    public static final List<Planet> PLANETS_LIST = new ArrayList<>();
    private List<City> cities = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();

    private Random random = new Random();
    private List<ChanceNode> randomEvents = new ArrayList<>();

    private Planet currentPlanet;
    private City currentCity;

    private Set<Planet> visitedPlanets = new HashSet<>();
    private Set<City> visitedCities = new HashSet<>();



    public Game() {
        initializePlanets();
        initializeCitiesOnPlanets();
        initializeCharacter();
        initializeBosses();
        createNodePool();
    }

    private Node randomEvent() {
        return randomEvents.get(random.nextInt(randomEvents.size()));
    }

    private int getValidInput(String prompt, int max) {
        int choice = 0;
        do {
            System.out.println(prompt);
            while (!Utility.scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                Utility.scanner.next(); // Move scanner cursor to next to avoid infinite loop
            }
            choice = Utility.scanner.nextInt();
            if (choice < 1 || choice > max) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice < 1 || choice > max);
        return choice;
    }

    public void initializeCharacter() {
        // Initialize characters here
        System.out.println("Welcome to the Space Adventure Game!");
        System.out.println("Let's create your character!");

        // Displaying planets with indices
        System.out.println("Available planets:");
        for (int i = 0; i < PLANETS_LIST.size(); i++) {
            System.out.println((i + 1) + ": " + PLANETS_LIST.get(i).getName());
        }

        // Displaying races with indices
        Race[] races = Race.values();
        System.out.println("Available races:");
        for (int i = 0; i < races.length; i++) {
            System.out.println((i + 1) + ": " + races[i].name());
        }
        System.out.println("Your adventure starts here!");

        System.out.println("Enter your name: ");
        String name = Utility.scanner.nextLine();

        int raceChoice = getValidInput("Choose your race: (1-" + races.length + ")", races.length);

        int planetChoice = getValidInput("Choose your starting planet: (1-" + PLANETS_LIST.size() + ")", PLANETS_LIST.size());

        // Create character with validated choices
        Race race = races[raceChoice - 1];
        Planet planet = PLANETS_LIST.get(planetChoice - 1);
        Character character = new BaseCharacter(name, 100, 10, race, planet);

        characters.add(character);

        System.out.println(
                "Your character, " + character.getName() + " " + character.getRace() + ", has been created on planet "
                        + planet.getName() + "!");
    }

    public void play() {
        while (!(currentNode instanceof TerminalNode)) {
            currentNode.display();

            if (currentNode instanceof DecisionNode || currentNode instanceof InnerNode) {

                Node node = currentNode;
//                 Random event with 50% chance
                if (random.nextBoolean()) {
                    if (! randomEvents.isEmpty()) {
                        Node randomEvent = randomEvent();
                        randomEvent.display();
                        Node outcome = randomEvent.chooseNext();

                        outcome.display();
                    }
                }

                if (currentNode instanceof  InnerNode) {
                    InnerNode innerNode = (InnerNode) currentNode;
                    if (innerNode.isPlanetSelector()) {
                        Planet planet = innerNode.choosePlanet();
                        currentPlanet = planet;
                        visitedPlanets.add(planet);

                    }

                    if (innerNode.isCitySelector()) {
                        City city = innerNode.chooseCity(this.currentPlanet);
                        currentCity = city;
                        visitedCities.add(city);
                    }
                }


                currentNode = node.chooseNext();

                if (visitedPlanets.containsAll(PLANETS_LIST) && visitedCities.containsAll(cities)) {
                    System.out.println("You have explored all planets and cities!");
                    currentNode = new TerminalNode(99, "Congratulations! You have explored the entire galaxy!");
                }

                if (currentNode instanceof TerminalNode) {
                    currentNode.display();
                    break;
                }

            }
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
        // Initialize bosses here
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



    // TODO: ADD RANDOM MAP GENERATION, ADD NODE TYPES (ATTACK , TRADE ETC..)

    private List<Node> createNodePool() {
        List<Node> nodePool = new ArrayList<>();

        // Create decision nodes based on event names and descriptions
        DecisionNode decisionNode0 = new DecisionNode(0, "Your home planet is being invaded.");

        InnerNode innerNode1 = new InnerNode(1, "You escape to open space.");
        InnerNode innerNode2 = new InnerNode(2, "You find a spaceship ready for departure.");


        // Adding options to decision nodes logically
        decisionNode0.addOption(innerNode1); // Escape to space
        decisionNode0.addOption(innerNode2); // Find spaceship

        InnerNode planetNode =  new InnerNode(3, "Select a planet to land on.", true, false);

        innerNode1.addNextNode(planetNode);

        InnerNode cityNode = new InnerNode(4, "Select a city to land in.", false, true);

        planetNode.addNextNode(cityNode);

        cityNode.addNextNode(new TerminalNode(5, "You find a new city on new home planet."));
        innerNode2.addNextNode(new TerminalNode(4, "You find a new home planet."));


        // Populate randomEvents with chance nodes (example)
        ChanceNode alienEncounter = new ChanceNode(6, "You encounter an alien.");
        alienEncounter.addOutcome(new DecisionNode(7, "The alien is hostile and attacks!"));
        alienEncounter.addOutcome(new DecisionNode(8, "The alien is friendly and offers to trade."));


        randomEvents.add(alienEncounter);


        this.currentNode = decisionNode0;
        return nodePool;
    }

}