package univers;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private int quantity;
    private int health;
    private int attack;
    private int weight;
    private int price;
    private boolean isEquipped;

    public Item(String name, int price, int weight, int health, int attack, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.attack = attack;
        this.health = health;
        this.price = price;
        this.weight = weight;
        this.isEquipped = false;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getHealth() {
        return this.health;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }

}
