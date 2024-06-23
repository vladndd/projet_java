package univers.base;

import java.util.HashMap;

import javax.swing.JOptionPane;

public abstract class Character implements Interact {
    protected String nom;
    protected int health;
    protected int force;
    protected Race race;
    protected Planet planet;
    protected Item equipedWeapon;
    protected int money;
    protected int maxweight;
    protected String characterType;

    protected HashMap<String, Item> inventory = new HashMap<>();

    public Character(String nom, int health, int force, Race race, Planet planet) {
        this.nom = nom;
        this.health = health + race.getHealthBonus();
        this.force = force + race.getForceBonus();
        this.race = race;
        this.planet = planet;
        this.equipedWeapon = new Item("Sword", 5, 10, 0, 50, 1);
        this.money = 100; // for all characters
        this.maxweight = 100; // for all characters

        // this.inventory.put("Weapon", this.equipedWeapon);
    }

    // Define the methods declared in the Interact interface
    public abstract void interact();

    public abstract void speak();

    public abstract void trade(Item item);

    public abstract void fight(int intakeDamage);

    public abstract String getSpecificAttribute();

    public abstract int specificDamage();

    public String[] getInventory() {
        String[] inventoryArray = new String[inventory.size()];
        int i = 0;
        for (Item item : inventory.values()) {
            inventoryArray[i++] = "Name: " + item.getName() + ", Attack: " + item.getAttack() +
                    ", Price: " + item.getPrice() + ", Health: " + item.getHealth() + ", Quantity: "
                    + item.getQuantity() + ", Weight: " + item.getWeight();
        }
        return inventoryArray;
    }

    public void addToInventory(Item item) {

        if (item.getWeight() + this.getCurrentWeight() > maxweight) {
            JOptionPane.showMessageDialog(null, "Item is too heavy to carry", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (inventory.containsKey(item.getName())) {
            inventory.get(item.getName()).addQuantity(item.getQuantity());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    public int getCurrentWeight() {
        int currentWeight = 0;
        for (Item item : inventory.values()) {
            currentWeight += item.getWeight() * item.getQuantity();
        }
        return currentWeight;
    }

    public abstract String getCharacterType();

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public int getHealth() {
        int weaponHealth = 0;
        if (this.equipedWeapon != null) {
            weaponHealth = this.equipedWeapon.getHealth();
        }
        return health + weaponHealth;
    }

    public int getForce() {
        int weaponForce = 0;
        if (this.equipedWeapon != null) {
            weaponForce = this.equipedWeapon.getAttack();
        }
        return force + weaponForce + this.specificDamage();
    }

    public void decreaseMoney(int amount) {
        money -= amount;
    }

    public boolean hasEnoughMoney(int amount) {
        return money >= amount;
    }

    public int getMoney() {
        return money;
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

    public void equipItem(String itemName) {
        Item item = inventory.get(itemName);
        if (item != null) {
            Item equippedItem = this.getEquipedWeapon();
            if (equippedItem != null) {
                equippedItem.setEquipped(false);
                this.inventory.put(equippedItem.getName(), equippedItem);
            }

            item.setEquipped(true);
            setEquipedWeapon(item);
            this.inventory.remove(itemName);
        }
    }

    public void unequipItem() {
        if (this.getEquipedWeapon() != null) {
            this.getEquipedWeapon().setEquipped(false);
            this.inventory.put(this.getEquipedWeapon().getName(), this.getEquipedWeapon());
            setEquipedWeapon(null);

        }
    }
}