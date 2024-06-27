package univers;

/**
 * Race enum represents different races in the game, each with specific health
 * and force bonuses.
 */
public enum Race {
    Human(0, 0),
    Martian(10, 5),
    Venusian(5, 10),
    Jovian(15, 0),
    Saturnian(10, 10),
    Uranian(5, 5),
    Neptunian(0, 15),
    Plutonian(5, 5);

    private final int healthBonus;
    private final int forceBonus;

    /**
     * Constructs a Race enum with specified health and force bonuses.
     *
     * @param healthBonus The health bonus for the race.
     * @param forceBonus  The force (strength) bonus for the race.
     */
    Race(int healthBonus, int forceBonus) {
        this.healthBonus = healthBonus;
        this.forceBonus = forceBonus;
    }

    /**
     * Gets the health bonus for the race.
     *
     * @return The health bonus.
     */
    public int getHealthBonus() {
        return healthBonus;
    }

    /**
     * Gets the force (strength) bonus for the race.
     *
     * @return The force bonus.
     */
    public int getForceBonus() {
        return forceBonus;
    }
}
