package univers.base;

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

    Race(int healthBonus, int forceBonus) {
        this.healthBonus = healthBonus;
        this.forceBonus = forceBonus;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public int getForceBonus() {
        return forceBonus;
    }

}