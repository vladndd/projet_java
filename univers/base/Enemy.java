package univers.base;

public class Enemy {

    private String name;
    private int health;
    private int attack;

    private boolean isAlive = true;
    private boolean isBoss = false;

    public Enemy(String name, int health, int attack, boolean isBoss) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.isBoss = isBoss;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            isAlive = false;
        }
    }

    public int attack() {
        return attack;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isBoss() {
        return isBoss;
    }
}
