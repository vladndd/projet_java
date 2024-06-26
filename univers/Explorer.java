package univers;

// Implémentation concrète d'un Explorateur
public class Explorer extends Character {
    private int intelligence;

    public Explorer(String nom, int health, int force, Race race, int intelligence, Planet planet) {
        super(nom, health, force, race, planet);
        this.intelligence = intelligence;
    }

    @Override
    public void fight(int intakeDamage) {
        this.health -= intakeDamage;
    }

    @Override
    public void trade(Item item) {
        this.addToInventory(item);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getSpecificAttribute() {
        return "Intelligence: " + this.intelligence;
    }

    @Override
    public int specificDamage() {
        return this.intelligence;
    }

    public String getCharacterType() {
        return "Explorer";
    }
}