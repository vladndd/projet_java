package univers.base;

// Implémentation concrète d'un Guerrier
public class Warrior extends Character {
    private int combatPower;

    public Warrior(String nom, int health, int force, Race race, int combatPower, Planet planet) {
        super(nom, health, force, race, planet);
        this.combatPower = combatPower;
    }

    @Override
    public void interact() {
        System.out.println(nom + ": En garde ! Prépare-toi au combat.");
    }

    @Override
    public void speak() {
        System.out.println(nom + ": Prépare-toi à la bataille, ennemi approchant !");
    }

    @Override
    public void fight(int intakeDamage) {
        System.out.println(nom + ": À l'attaque ! Mon épée est prête.");
    }

    @Override
    public void trade(Item item) {
        this.inventory.put(item.getName(), item);
    }

    public int getCombatPower() {
        return combatPower;
    }
}