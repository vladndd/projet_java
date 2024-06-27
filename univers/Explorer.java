package univers;

/**
 * Explorer class represents a character with intelligence-based attributes.
 * It extends the Character class and adds unique attributes and behavior
 * related to intelligence.
 */
public class Explorer extends Character {
    private int intelligence;

    /**
     * Constructs an Explorer instance with the specified parameters.
     *
     * @param nom          The name of the explorer.
     * @param health       The health of the explorer.
     * @param force        The force (strength) of the explorer.
     * @param race         The race of the explorer.
     * @param intelligence The intelligence of the explorer.
     * @param planet       The starting planet of the explorer.
     */
    public Explorer(String nom, int health, int force, Race race, int intelligence, Planet planet) {
        super(nom, health, force, race, planet);
        this.intelligence = intelligence;
    }

    /**
     * Gets the intelligence of the explorer.
     *
     * @return The intelligence value.
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Gets the specific attribute of the explorer.
     *
     * @return A string representing the intelligence attribute of the explorer.
     */
    public String getSpecificAttribute() {
        return "Intelligence: " + this.intelligence;
    }

    /**
     * Gets the specific damage of the explorer, which is intelligence-based.
     *
     * @return The intelligence value as specific damage.
     */
    @Override
    public int specificDamage() {
        return this.intelligence;
    }

    /**
     * Gets the character type of the explorer.
     *
     * @return A string representing the character type.
     */
    public String getCharacterType() {
        return "Explorer";
    }
}
