package core;

import representation.ChanceNode;
import representation.DecisionNode;
import representation.Node;
import representation.TerminalNode;
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
    private List<Planet> planets = new ArrayList<>();

    private List<City> cities = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();


    private Random random = new Random();
    private List<ChanceNode> randomEvents = new ArrayList<>();


    public Game() {
        initializePlanets();
        initializeCitiesOnPlanets();
        initializeCharacter();
        initializeBosses();

        this.currentNode = generateRandomGraph();


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
        for (int i = 0; i < planets.size(); i++) {
            System.out.println((i + 1) + ": " + planets.get(i).getName());
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

        int planetChoice = getValidInput("Choose your starting planet: (1-" + planets.size() + ")", planets.size());

        // Create character with validated choices
        Race race = races[raceChoice - 1];
        Planet planet = planets.get(planetChoice - 1);
        Character character = new BaseCharacter(name, 100, 10, race, planet);

        characters.add(character);

        System.out.println(
                "Your character, " + character.getName() + " " + character.getRace() + ", has been created on planet "
                        + planet.getName() + "!");

        // Close scanner here or elsewhere depending on the structure of your game
        // scanner.close();

    }

    public void play() {
        while (!(currentNode instanceof TerminalNode)) {
            currentNode.display();

            if (currentNode instanceof DecisionNode) {
                DecisionNode decisionNode = (DecisionNode) currentNode;

                // Random event with 50% chance
                if (random.nextBoolean()) {
                    Node randomEvent = randomEvent();
                    randomEvent.display();
                    currentNode = randomEvent.chooseNext();
                    continue;
                }

                currentNode = decisionNode.chooseNext();
            } else if (currentNode instanceof ChanceNode) {
                currentNode = currentNode.chooseNext();
            }

//            if (currentNode.getDescription().contains("boss")) {
//                System.out.println("You have found the boss!");
//                if (characters.get(0).getPower() > 50) {
//                    System.out.println("You have defeated the boss!");
//                    currentNode = new TerminalNode(99, "You win the game!");
//                } else {
//                    System.out.println("You are not powerful enough to defeat the boss.");
//                    currentNode = new TerminalNode(100, "You lose the game.");
//                }
//            }
        }
        currentNode.display(); // Display the final node
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
        planets.add(new Planet("Mercury", "Very hot planet with no atmosphere."));
        planets.add(new Planet("Venus", "Thick atmosphere and volcanic activity."));
        planets.add(new Planet("Earth", "Rich in life and diverse climates."));
        planets.add(new Planet("Mars", "Red planet with potential for life."));
        planets.add(new Planet("Jupiter", "Giant gas planet with a strong magnetic field."));
        planets.add(new Planet("Saturn", "Known for its extensive ring system."));
        planets.add(new Planet("Uranus", "Ice giant with a tilted axis."));
        planets.add(new Planet("Neptune", "Cold blue planet with strong winds."));
        planets.add(new Planet("Pluto", "Dwarf planet with a heart-shaped glacier."));
    }

    private void initializeBosses() {
        // Initialize bosses here
        /* create bosses */

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
        // Initialize cities on planets here
        /* create cities */

        for (Planet planet : planets) {
            int numCities = new Random().nextInt(3) + 1;
            for (int i = 0; i < numCities; i++) {

                String cityName = planet.getName() + " City " + (i + 1);
                City city = new City(cityName, planet);
                cities.add(city);
                planet.addCity(city);
            }
        }

    }

    private List<Node> createNodePool() {
           List<Node> nodePool = new ArrayList<>();

           // Create decision nodes based on event names and descriptions
           DecisionNode decisionNode0 = new DecisionNode(0, "Your home planet is being invaded.");
           DecisionNode decisionNode1 = new DecisionNode(1, "You escape to open space.");
           DecisionNode decisionNode2 = new DecisionNode(2, "You cannot escape and you are facing a choice.");
           DecisionNode decisionNode3 = new DecisionNode(3, "You are being recruited as a warrior/scientist at the enemy army.");
           DecisionNode decisionNode4 = new DecisionNode(4, "You choose a planet to go.");
           DecisionNode decisionNode5 = new DecisionNode(5, "You choose which city to go on this planet.");
           DecisionNode decisionNode6 = new DecisionNode(6, "You arrived at the city.");
           DecisionNode decisionNode7 = new DecisionNode(7, "You explore the city.");

           // Create chance nodes with outcomes
           ChanceNode alienEncounter = new ChanceNode(8, "You encounter an alien.");
           alienEncounter.addOutcome(new DecisionNode(9, "The alien is hostile and attacks!"));
           alienEncounter.addOutcome(new DecisionNode(10, "The alien is friendly and offers to trade."));

           ChanceNode findHiddenItem = new ChanceNode(11, "You found hidden items.");
           findHiddenItem.addOutcome(new DecisionNode(12, "You found a rare artifact."));
           findHiddenItem.addOutcome(new DecisionNode(13, "You found some useful supplies."));

           ChanceNode blackHoleAppears = new ChanceNode(14, "You encounter a black hole.");
           blackHoleAppears.addOutcome(new TerminalNode(15, "Your ship is sucked into the black hole and destroyed."));
           blackHoleAppears.addOutcome(new DecisionNode(16, "You narrowly escape the black hole's pull."));

           ChanceNode spacePirateAmbush = new ChanceNode(17, "Space pirates ambush your ship.");
           spacePirateAmbush.addOutcome(new DecisionNode(18, "You fight off the pirates."));
           spacePirateAmbush.addOutcome(new TerminalNode(19, "The pirates overpower you and steal your supplies."));

           ChanceNode mysticalSpaceAnomaly = new ChanceNode(20, "You come across a mystical space anomaly.");
           mysticalSpaceAnomaly.addOutcome(new DecisionNode(21, "You gain mysterious new powers."));
           mysticalSpaceAnomaly.addOutcome(new DecisionNode(22, "You are disoriented but eventually recover."));

           ChanceNode derelictShipDiscovery = new ChanceNode(23, "You discover a derelict ship floating in space.");
           derelictShipDiscovery.addOutcome(new DecisionNode(24, "You find valuable technology onboard."));
           derelictShipDiscovery.addOutcome(new DecisionNode(25, "The ship is haunted and you flee in terror."));

           ChanceNode asteroidFieldNavigation = new ChanceNode(26, "You need to navigate through a dense asteroid field.");
           asteroidFieldNavigation.addOutcome(new DecisionNode(27, "You successfully navigate through the asteroid field."));
           asteroidFieldNavigation.addOutcome(new TerminalNode(28, "Your ship is damaged by the asteroids."));

           ChanceNode distressSignal = new ChanceNode(29, "You receive a distress signal from a nearby ship.");
           distressSignal.addOutcome(new DecisionNode(30, "You rescue the ship's crew and gain new allies."));
           distressSignal.addOutcome(new DecisionNode(31, "It was a trap and you are ambushed."));

           ChanceNode energySurge = new ChanceNode(32, "Your ship experiences a sudden energy surge.");
           energySurge.addOutcome(new DecisionNode(33, "The surge enhances your ship's capabilities."));
           energySurge.addOutcome(new TerminalNode(34, "The surge causes severe damage to your ship."));

           ChanceNode wormholeEncounter = new ChanceNode(35, "You encounter a wormhole that could transport you to a distant part of the galaxy.");
           wormholeEncounter.addOutcome(new DecisionNode(36, "You successfully travel through the wormhole."));
           wormholeEncounter.addOutcome(new TerminalNode(37, "The wormhole destabilizes and collapses."));

           ChanceNode technologicalMalfunction = new ChanceNode(38, "A critical system on your ship malfunctions.");
           technologicalMalfunction.addOutcome(new DecisionNode(39, "You manage to repair the malfunction."));
           technologicalMalfunction.addOutcome(new TerminalNode(40, "The malfunction causes a catastrophic failure."));

           ChanceNode alienArtifactDiscovery = new ChanceNode(41, "You discover an ancient alien artifact.");
           alienArtifactDiscovery.addOutcome(new DecisionNode(42, "The artifact grants you advanced technology."));
           alienArtifactDiscovery.addOutcome(new DecisionNode(43, "The artifact is cursed and brings misfortune."));

           ChanceNode crewMutiny = new ChanceNode(44, "Part of your crew stages a mutiny.");
           crewMutiny.addOutcome(new DecisionNode(45, "You quash the mutiny and restore order."));
           crewMutiny.addOutcome(new TerminalNode(46, "The mutiny is successful and you are overthrown."));

           // Adding options to decision nodes
           decisionNode0.addOption(decisionNode1);
           decisionNode0.addOption(decisionNode2);

           decisionNode1.addOption(decisionNode4);
           decisionNode2.addOption(decisionNode3);
           decisionNode2.addOption(new TerminalNode(47, "You failed to escape and were captured."));

           decisionNode3.addOption(decisionNode4);
           decisionNode3.addOption(new TerminalNode(48, "You die while being recruited."));

           decisionNode4.addOption(alienEncounter);
           decisionNode4.addOption(findHiddenItem);
           decisionNode4.addOption(blackHoleAppears);

           decisionNode5.addOption(decisionNode6);
           decisionNode5.addOption(new TerminalNode(49, "You get lost and never find the city."));

           decisionNode6.addOption(decisionNode7);
           decisionNode6.addOption(new TerminalNode(50, "You are ambushed in the city and die."));

           decisionNode7.addOption(derelictShipDiscovery);
           decisionNode7.addOption(asteroidFieldNavigation);
           decisionNode7.addOption(distressSignal);

           // Add all nodes to the pool
           nodePool.add(decisionNode0);
           nodePool.add(decisionNode1);
           nodePool.add(decisionNode2);
           nodePool.add(decisionNode3);
           nodePool.add(decisionNode4);
           nodePool.add(decisionNode5);
           nodePool.add(decisionNode6);
           nodePool.add(decisionNode7);

           nodePool.add(alienEncounter);
           nodePool.add(findHiddenItem);
           nodePool.add(blackHoleAppears);
           nodePool.add(spacePirateAmbush);
           nodePool.add(mysticalSpaceAnomaly);
           nodePool.add(derelictShipDiscovery);
           nodePool.add(asteroidFieldNavigation);
           nodePool.add(distressSignal);
           nodePool.add(energySurge);
           nodePool.add(wormholeEncounter);
           nodePool.add(technologicalMalfunction);
           nodePool.add(alienArtifactDiscovery);
           nodePool.add(crewMutiny);

           randomEvents.add(alienEncounter);
           randomEvents.add(findHiddenItem);
           randomEvents.add(blackHoleAppears);
           randomEvents.add(spacePirateAmbush);
           randomEvents.add(mysticalSpaceAnomaly);
           randomEvents.add(derelictShipDiscovery);
           randomEvents.add(asteroidFieldNavigation);
           randomEvents.add(distressSignal);
           randomEvents.add(energySurge);
           randomEvents.add(wormholeEncounter);
           randomEvents.add(technologicalMalfunction);
           randomEvents.add(alienArtifactDiscovery);
           randomEvents.add(crewMutiny);

           return nodePool;

   }

    private Node generateRandomGraph() {
        List<Node> nodePool = createNodePool();
        Random random = new Random();

        // Ensure the graph is non-cyclic and leads to at least one terminal node
        Node startNode = nodePool.get(random.nextInt(nodePool.size()));
        Set<Node> visited = new HashSet<>();
        createGraphConnections(startNode, nodePool, visited, random);

        return startNode;
    }

    private void createGraphConnections(Node currentNode, List<Node> nodePool, Set<Node> visited, Random random) {
        if (visited.contains(currentNode) || currentNode instanceof TerminalNode) {
            return;
        }

        visited.add(currentNode);

        if (currentNode instanceof DecisionNode) {
            DecisionNode decisionNode = (DecisionNode) currentNode;
            int numberOfOptions = random.nextInt(3) + 1; // Each decision node can have 1 to 3 options
            for (int i = 0; i < numberOfOptions; i++) {
                Node nextNode = nodePool.get(random.nextInt(nodePool.size()));
                decisionNode.addOption(nextNode);
                createGraphConnections(nextNode, nodePool, visited, random);
            }
        } else if (currentNode instanceof ChanceNode) {
            ChanceNode chanceNode = (ChanceNode) currentNode;
            for (Node outcome : chanceNode.getOutcomes()) {
                createGraphConnections(outcome, nodePool, visited, random);
            }
        }
    }

}
