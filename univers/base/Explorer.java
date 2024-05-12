package univers.base;

// Implémentation concrète d'un Explorateur
public class Explorer extends Character {
    private int intelligence;

    public Explorer(String nom, int health, int force, Race race, int intelligence, Planet planet) {
        super(nom, health, force, race, planet);
        this.intelligence = intelligence;
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
    public void fight() {
        System.out.println(nom + ": Je ne suis pas le meilleur combattant, mais je vais me défendre !");
    }

    public int getIntelligence() {
        return intelligence;
    }
}