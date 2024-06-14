package univers.base;

import java.util.HashMap;

public abstract class Character implements Interact {
    protected String nom;
    protected int health;
    protected int force;
    protected Race race;
    protected Planet planet;

    protected HashMap<String, Item> inventory = new HashMap<>();

    public Character(String nom, int health, int force, Race race, Planet planet) {
        this.nom = nom;
        this.health = health;
        this.force = force;
        this.race = race;
        this.planet = planet;

        this.inventory.put("Potion", new Item("Potion", 5));
    }

    // Define the methods declared in the Interact interface
    public abstract void interact();

    public abstract void speak();

    public abstract void trade(Item item);

    public abstract void fight(int intakeDamage);

    public String[] getInventory() {
        return inventory.keySet().toArray(new String[0]);
    }

    public void addToInventory(Item item) {
        if (inventory.containsKey(item.getName())) {
            inventory.get(item.getName()).addQuantity(item.getQuantity());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    public int getHealth() {
        return health;
    }

    public int getForce() {
        return force;
    }

    public Race getRace() {
        return race;
    }

    public Planet getStartPlanet() {
        return planet;
    }

    public String getStartPlanetName() {
        return planet.getName();
    }

    public String getName() {
        return nom;
    }
}