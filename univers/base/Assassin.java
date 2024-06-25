package univers.base;

// sub classes add one specific attribute to your character
public class Assassin extends Character {
    private int agileDamage;

    public Assassin(String nom, int health, int force, Race race, int agileDamage, Planet planet) {
        super(nom, health, force, race, planet);
        this.agileDamage = agileDamage;
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

        if (this.health <= 0) {
            this.health = 0;
        }
    }

    @Override
    public void trade(Item item) {
        this.addToInventory(item);
    }

    public String getSpecificAttribute() {
        return "Agile Damage: " + this.agileDamage;
    }

    @Override
    public int specificDamage() {
        return this.agileDamage;
    }

    public String getCharacterType() {
        return "Assassin";
    }

}