package univers.base;

// Implémentation concrète d'un base character
public class BaseCharacter extends Character {

    public BaseCharacter(String nom, int health, int force, Race race, Planet startPlanet) {
        super(nom, health, force, race, startPlanet);
    }

    @Override
    public void interact() {
        System.out.println(nom + ": Explorons les mystères de cette planète !");
    }

    @Override
    public void speak() {
        System.out.println(nom + ": Observons notre environnement pour mieux comprendre où nous sommes.");
    }

    @Override
    public void trade(Item item) {
        this.inventory.put(item.getName(), item);
    }

    @Override
    public void fight(int intakeDamage) {
        this.health -= intakeDamage;
    }

}