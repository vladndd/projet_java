package univers;

/**
 * BaseCharacter class represents a basic character in the game with no specific
 * attributes.
 * It extends the Character class and provides concrete implementations for
 * trade and fight behaviors.
 */
public class BaseCharacter extends Character {

    /**
     * Constructs a BaseCharacter instance with the specified parameters.
     *
     * @param nom         The name of the character.
     * @param health      The health of the character.
     * @param force       The force (strength) of the character.
     * @param race        The race of the character.
     * @param startPlanet The starting planet of the character.
     */
    public BaseCharacter(String nom, int health, int force, Race race, Planet startPlanet) {
        super(nom, health, force, race, startPlanet);
    }

    /**
     * Gets the specific attribute of the character.
     *
     * @return A string representing that the character has no specific attribute.
     */
    public String getSpecificAttribute() {
        return "No specific attribute";
    }

    /**
     * Gets the specific damage of the character, which is zero for a base
     * character.
     *
     * @return The specific damage, which is zero.
     */
    @Override
    public int specificDamage() {
        return 0;
    }

    /**
     * Gets the character type of the base character.
     *
     * @return A string representing the character type.
     */
    public String getCharacterType() {
        return "Base Character";
    }
}
