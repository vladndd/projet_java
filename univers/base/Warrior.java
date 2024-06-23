package univers.base;

// sub classes add one specific attribute to your character
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
        this.health -= intakeDamage;
    }

    @Override
    public void trade(Item item) {
        this.addToInventory(item);
    }

    public int getCombatPower() {
        return combatPower;
    }

    public String getSpecificAttribute() {
        return "Combat Power: " + this.combatPower;
    }

    @Override
    public int specificDamage() {
        return this.combatPower;
    }

    public String getCharacterType() {
        return "Warrior";
    }
}