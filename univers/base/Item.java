package univers.base;

public class Item {

    private String name;

    private int quantity;

    private int damage;
    private int price;

    public Item(String name, int quantity, int damage, int price) {
        this.name = name;
        this.quantity = quantity;
        this.damage = damage;
        this.price = price;
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

    public int getDamage() {
        return this.damage;
    }

    public int getPrice() {
        return this.price;
    }

}
