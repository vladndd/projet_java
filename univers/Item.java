package univers;

import java.io.Serializable;

/**
 * Item class represents an item in the game.
 * It includes attributes such as name, quantity, health, attack, weight, price,
 * and equipped status.
 */
public class Item implements Serializable {

    private String name;
    private int quantity;
    private int health;
    private int attack;
    private int weight;
    private int price;
    private boolean isEquipped;

    /**
     * Constructs an Item instance with the specified parameters.
     *
     * @param name     The name of the item.
     * @param price    The price of the item.
     * @param weight   The weight of the item.
     * @param health   The health value of the item.
     * @param attack   The attack value of the item.
     * @param quantity The quantity of the item.
     */
    public Item(String name, int price, int weight, int health, int attack, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.attack = attack;
        this.health = health;
        this.price = price;
        this.weight = weight;
        this.isEquipped = false;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Adds the specified quantity to the item.
     *
     * @param quantity The quantity to be added.
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Gets the attack value of the item.
     *
     * @return The attack value.
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Gets the health value of the item.
     *
     * @return The health value.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the weight of the item.
     *
     * @return The weight of the item.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Checks if the item is equipped.
     *
     * @return True if the item is equipped, false otherwise.
     */
    public boolean isEquipped() {
        return isEquipped;
    }

    /**
     * Sets the equipped status of the item.
     *
     * @param equipped The equipped status to set.
     */
    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }
}
