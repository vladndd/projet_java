package univers.base;

public abstract class Character implements Interact {
    protected String nom;
    protected int health;
    protected int force;
    protected Race race;
    protected Planet planet;

    public Character(String nom, int health, int force, Race race, Planet planet) {
        this.nom = nom;
        this.health = health;
        this.force = force;
        this.race = race;
        this.planet = planet;
    }

    // Define the methods declared in the Interact interface
    public abstract void interact();

    public abstract void speak();

    public abstract void fight();

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

    public String getName() {
        return nom;
    }
}