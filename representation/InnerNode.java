package representation;

import core.Game;
import univers.base.City;
import univers.base.Planet;
import utility.Utility;

import java.security.PublicKey;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class InnerNode extends Node {
    private Stack<Node> nextNodes;

    private Planet planet;

    private boolean planetSelector;
    private boolean citySelector;

    public InnerNode(int id, String description) {
        super(id, description);
        this.nextNodes = new Stack<>();


    }
    public InnerNode(int id, String description, boolean planetSelector, boolean citySelector) {
        super(id, description);
        this.nextNodes = new Stack<>();

        this.planetSelector = planetSelector;
        this.citySelector = citySelector;
    }

    public boolean isPlanetSelector() {
        return planetSelector;
    }

    public boolean isCitySelector() {
        return citySelector;
    }




    public void addNextNode(Node node) {
        nextNodes.push(node);
    }

    @Override
    public Node chooseNext() {
        if (nextNodes.isEmpty()) {
            return null;
        }
        return nextNodes.pop();
    }

    public void display() {
        super.display();
        // Affiche des informations supplémentaires si nécessaire
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

    public City chooseCity(Planet planet) {
        List<City> cities = planet.getCities();
        for (int i = 0; i < cities.size(); i++) {
            System.out.println(i + 1 + ". " + cities.get(i).getName());
        }

        int choice = Utility.scanner.nextInt();

        // Scanner sc = new Scanner(System.in);

        return cities.get(choice - 1);
    }

    public Planet choosePlanet() {
        List<Planet> planets = Game.PLANETS_LIST;


        for (int i = 0; i < planets.size(); i++) {
            System.out.println(i + 1 + ". " + planets.get(i).getName());
        }

        int choice = Utility.scanner.nextInt();


        // Scanner sc = new Scanner(System.in);

        return planets.get(choice - 1);
    }

}