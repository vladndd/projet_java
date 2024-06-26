package univers;

// Implémentation concrète d'un base character
public class BaseCharacter extends Character {

    public BaseCharacter(String nom, int health, int force, Race race, Planet startPlanet) {
        super(nom, health, force, race, startPlanet);
    }

    @Override
    public void trade(Item item) {
        this.addToInventory(item);
    }

    @Override
    public void fight(int intakeDamage) {
        this.health -= intakeDamage;

        if (this.health <= 0) {
            this.health = 0;
        }
    }

    public String getSpecificAttribute() {
        return "No specific attribute";
    }

    @Override
    public int specificDamage() {
        return 0;
    }

    public String getCharacterType() {
        return "Base Character";
    }

}