package univers;

/**
 * Warrior class represents a character with combat power-based attributes.
 * It extends the Character class and adds unique attributes and behavior
 * related to combat power.
 */
public class Warrior extends Character {
    private int combatPower;

    /**
     * Constructs a Warrior instance with the specified parameters.
     *
     * @param nom         The name of the warrior.
     * @param health      The health of the warrior.
     * @param force       The force (strength) of the warrior.
     * @param race        The race of the warrior.
     * @param combatPower The combat power of the warrior.
     * @param planet      The starting planet of the warrior.
     */
    public Warrior(String nom, int health, int force, Race race, int combatPower, Planet planet) {
        super(nom, health, force, race, planet);
        this.combatPower = combatPower;
    }

    /**
     * Gets the combat power of the warrior.
     *
     * @return The combat power value.
     */
    public int getCombatPower() {
        return combatPower;
    }

    /**
     * Gets the specific attribute of the warrior.
     *
     * @return A string representing the combat power attribute of the warrior.
     */
    public String getSpecificAttribute() {
        return "Combat Power: " + this.combatPower;
    }

    /**
     * Gets the specific damage of the warrior, which is combat power-based.
     *
     * @return The combat power value as specific damage.
     */
    @Override
    public int specificDamage() {
        return this.combatPower;
    }

    /**
     * Gets the character type of the warrior.
     *
     * @return A string representing the character type.
     */
    public String getCharacterType() {
        return "Warrior";
    }
}
