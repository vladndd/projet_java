package core;

import representation.ChanceNode;
import representation.DecisionNode;
import representation.Node;
import representation.NodeImage;
import representation.NodeSound;
import representation.TerminalNode;
import univers.base.BaseCharacter;
import univers.base.Planet;
import univers.base.Race;
import utility.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game implements Serializable {
    private Node currentNode;
    private List<Planet> planets = new ArrayList<>();
    private List<BaseCharacter> characters = new ArrayList<>();

    public Game() {
        initializePlanets();
        initializeCharacter();

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
        BaseCharacter character = new BaseCharacter(name, 100, 10, race, planet);

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
            currentNode = currentNode.chooseNext();
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

    public void initializeGame() {
        // Initialize the game here

        DecisionNode startNode = new DecisionNode(1, "Welcome to the Space Adventure! Choose your path:");
        this.currentNode = startNode;

        ChanceNode randomEvent = new ChanceNode(2, "A strange anomaly has appeared. Chance will decide your fate.");
        TerminalNode endNode = new TerminalNode(3,
                "You've successfully navigated the anomalies and discovered a new habitable planet!");
        TerminalNode lostNode = new TerminalNode(4, "The anomaly was too much; your ship is lost in space...");

        // Adding options to the decision node
        startNode.addOption(randomEvent);

        // Adding outcomes to the chance node
        randomEvent.addOutcome(endNode);
        randomEvent.addOutcome(lostNode);

        // // Add a visual or sound to the start using a decorator
        // NodeImage startNodeWithImage = new NodeImage(startNode,
        // "path/to/startImage.jpg");
        // NodeSound startNodeWithSound = new NodeSound(startNode,
        // "path/to/startSound.wav");

    }
}
