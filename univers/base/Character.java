package univers.base;

import java.util.HashMap;

public abstract class Character implements Interact {
    protected String nom;
    protected int health;
    protected int force;
    protected Race race;
    protected Planet planet;
    protected Item equipedWeapon;

    protected HashMap<String, Item> inventory = new HashMap<>();

    public Character(String nom, int health, int force, Race race, Planet planet) {
        this.nom = nom;
        this.health = health + race.getHealthBonus();
        this.force = force + race.getForceBonus();
        this.race = race;
        this.planet = planet;
        this.equipedWeapon = new Item("Sword", 5, 10, 60);

        this.inventory.put("Weapon", this.equipedWeapon);
    }

    // Define the methods declared in the Interact interface
    public abstract void interact();

    public abstract void speak();

    public abstract void trade(Item item);

    public abstract void fight(int intakeDamage);

    public String[] getInventory() {
        String[] inventoryArray = new String[inventory.size()];
        int i = 0;
        for (Item item : inventory.values()) {
            inventoryArray[i++] = "Name: " + item.getName() + ", Damage: " + item.getDamage() +
                    ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity();
        }
        return inventoryArray;
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

    public void setEquipedWeapon(Item equipedWeapon) {
        this.equipedWeapon = equipedWeapon;
    }

    public void setCurrentPlanet(Planet newPlanet) {
        this.planet = newPlanet;
    }

    public String getName() {
        return nom;
    }

    public Item getEquipedWeapon() {
        return equipedWeapon;
    }
}