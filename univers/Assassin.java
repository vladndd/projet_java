package univers;

/**
 * Assassin class represents a character with specific agility-based damage.
 * It extends the Character class and adds unique attributes and behavior.
 */
public class Assassin extends Character {
    private int agileDamage;

    /**
     * Constructs an Assassin instance with the specified parameters.
     *
     * @param nom         The name of the assassin.
     * @param health      The health of the assassin.
     * @param force       The force (strength) of the assassin.
     * @param race        The race of the assassin.
     * @param agileDamage The agility-based damage of the assassin.
     * @param planet      The starting planet of the assassin.
     */
    public Assassin(String nom, int health, int force, Race race, int agileDamage, Planet planet) {
        super(nom, health, force, race, planet);
        this.agileDamage = agileDamage;
    }

    /**
     * Reduces the health of the assassin by the specified damage intake.
     * If health drops to zero or below, it is set to zero.
     *
     * @param intakeDamage The damage to be subtracted from the health.
     */
    @Override
    public void fight(int intakeDamage) {
        this.health -= intakeDamage;

        if (this.health <= 0) {
            this.health = 0;
        }
    }

    /**
     * Trades the specified item by adding it to the inventory.
     *
     * @param item The item to be traded.
     */
    @Override
    public void trade(Item item) {
        this.addToInventory(item);
    }

    /**
     * Gets the specific attribute of the assassin.
     *
     * @return A string representing the agility-based damage.
     */
    public String getSpecificAttribute() {
        return "Agile Damage: " + this.agileDamage;
    }

    /**
     * Gets the specific damage of the assassin, which is agility-based.
     *
     * @return The agility-based damage.
     */
    @Override
    public int specificDamage() {
        return this.agileDamage;
    }

    /**
     * Gets the character type of the assassin.
     *
     * @return A string representing the character type.
     */
    public String getCharacterType() {
        return "Assassin";
    }
}
