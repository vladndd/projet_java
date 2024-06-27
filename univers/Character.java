package univers;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Character is an abstract class representing a character in the game.
 * It includes attributes such as name, health, force, race, planet, inventory,
 * and various methods to interact with these attributes.
 */
public abstract class Character implements Serializable, Interact {
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

    /**
     * Constructs a Character instance with the specified parameters.
     *
     * @param nom    The name of the character.
     * @param health The health of the character.
     * @param force  The force (strength) of the character.
     * @param race   The race of the character.
     * @param planet The starting planet of the character.
     */
    public Character(String nom, int health, int force, Race race, Planet planet) {
        this.nom = nom;
        this.health = health + race.getHealthBonus();
        this.force = force + race.getForceBonus();
        this.race = race;
        this.planet = planet;
        this.equipedWeapon = new Item("Sword", 5, 10, 0, 50, 1); // default weapon
        this.money = 100; // for all characters
        this.maxweight = 100; // for all characters
    }

    /**
     * Trades the specified item by adding it to the inventory.
     *
     * @param item The item to be traded.
     */
    @Override
    public void trade(Item item) {
        this.addToInventory(item);
    }

    /**
     * Reduces the health of the character by the specified damage intake.
     *
     * @param intakeDamage The damage to be subtracted from the health.
     */
    @Override
    public void fight(int intakeDamage) {
        this.health -= intakeDamage;
        if (this.health <= 0) {
            this.health = 0;
        }
    }

    /**
     * Gets the specific attribute of the character.
     *
     * @return A string representing the specific attribute of the character.
     */
    public abstract String getSpecificAttribute();

    /**
     * Gets the specific damage of the character.
     *
     * @return The specific damage value.
     */
    public abstract int specificDamage();

    /**
     * Gets the inventory of the character as an array of strings.
     *
     * @return An array of strings representing the inventory items.
     */
    public String[] getInventory() {
        String[] inventoryArray = new String[inventory.size()];
        int i = 0;
        for (Item item : inventory.values()) {
            inventoryArray[i++] = "Name: " + item.getName() + ",Attack: " + item.getAttack() +
                    ",Price: " + item.getPrice() + ",Health: " + item.getHealth() + ",Quantity: "
                    + item.getQuantity() + ",Weight: " + item.getWeight();
        }
        return inventoryArray;
    }

    /**
     * Gets the inventory items as a HashMap.
     *
     * @return A HashMap of inventory items.
     */
    public HashMap<String, Item> getInventoryItems() {
        return inventory;
    }

    /**
     * Adds the specified item to the inventory.
     *
     * @param item The item to be added to the inventory.
     */
    public void addToInventory(Item item) {
        if (inventory.containsKey(item.getName())) {
            inventory.get(item.getName()).addQuantity(item.getQuantity());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    /**
     * Gets the current weight of the inventory.
     *
     * @return The current weight of the inventory.
     */
    public int getCurrentWeight() {
        int currentWeight = 0;
        for (Item item : inventory.values()) {
            currentWeight += item.getWeight() * item.getQuantity();
        }
        return currentWeight;
    }

    /**
     * Gets the max weight of the character.
     *
     * @return The max weight of the character.
     */
    public int getMaxWeight() {
        return maxweight;
    }

    /**
     * Gets the character type.
     *
     * @return A string representing the character type.
     */
    public abstract String getCharacterType();

    /**
     * Sets the character type.
     *
     * @param characterType The character type to be set.
     */
    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    /**
     * Gets the health of the character.
     *
     * @return The health value.
     */
    public int getHealth() {
        int weaponHealth = 0;

        if (this.health <= 0) {
            this.health = 0;
            return 0;
        }

        if (this.equipedWeapon != null) {
            weaponHealth = this.equipedWeapon.getHealth();
        }
        return health + weaponHealth;
    }

    /**
     * Gets the force (strength) of the character.
     *
     * @return The force value.
     */
    public int getForce() {
        int weaponForce = 0;
        if (this.equipedWeapon != null) {
            weaponForce = this.equipedWeapon.getAttack();
        }
        return force + weaponForce + this.specificDamage();
    }

    /**
     * Decreases the money of the character by the specified amount.
     *
     * @param amount The amount to be subtracted from the money.
     */
    public void decreaseMoney(int amount) {
        money -= amount;
    }

    /**
     * Increases the money of the character by the specified amount.
     *
     * @param amount The amount to be added to the money.
     */
    public void increaseMoney(int amount) {
        money += amount;
    }

    /**
     * Checks if the character has enough money for a specified amount.
     *
     * @param amount The amount to check.
     * @return True if the character has enough money, false otherwise.
     */
    public boolean hasEnoughMoney(int amount) {
        return money >= amount;
    }

    /**
     * Gets the money of the character.
     *
     * @return The money value.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Gets the race of the character.
     *
     * @return The race of the character.
     */
    public Race getRace() {
        return race;
    }

    /**
     * Gets the starting planet of the character.
     *
     * @return The starting planet.
     */
    public Planet getStartPlanet() {
        return planet;
    }

    /**
     * Gets the name of the starting planet.
     *
     * @return The name of the starting planet.
     */
    public String getStartPlanetName() {
        return planet.getName();
    }

    /**
     * Sets the equipped weapon of the character.
     *
     * @param equipedWeapon The weapon to be equipped.
     */
    public void setEquipedWeapon(Item equipedWeapon) {
        this.equipedWeapon = equipedWeapon;
    }

    /**
     * Sets the current planet of the character.
     *
     * @param newPlanet The new planet to be set.
     */
    public void setCurrentPlanet(Planet newPlanet) {
        this.planet = newPlanet;
    }

    /**
     * Gets the name of the character.
     *
     * @return The name of the character.
     */
    public String getName() {
        return nom;
    }

    /**
     * Gets the equipped weapon of the character.
     *
     * @return The equipped weapon.
     */
    public Item getEquipedWeapon() {
        return equipedWeapon;
    }

    /**
     * Equips the specified item to the character.
     *
     * @param itemToEquip The item to be equipped.
     */
    public void equipItem(Item itemToEquip) {
        Item item = inventory.get(itemToEquip.getName());
        if (item != null) {
            Item equippedItem = this.getEquipedWeapon();
            if (equippedItem != null) {
                equippedItem.setEquipped(false);
                this.inventory.put(equippedItem.getName(), equippedItem);
            }

            item.setEquipped(true);
            setEquipedWeapon(item);
            this.inventory.remove(itemToEquip.getName());
        }
    }

    /**
     * Unequips the current equipped item from the character.
     */
    public void unequipItem() {
        if (this.getEquipedWeapon() != null) {
            this.getEquipedWeapon().setEquipped(false);
            this.inventory.put(this.getEquipedWeapon().getName(), this.getEquipedWeapon());
            setEquipedWeapon(null);
        }
    }
}
