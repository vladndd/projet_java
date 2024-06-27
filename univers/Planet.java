package univers;

import java.io.Serializable;

/**
 * Planet class represents a planet in the game.
 * It includes attributes such as name, climate type, and indigenous races.
 */
public class Planet implements Serializable {
    private String name;
    private String typeClimat;

    /**
     * Constructs a Planet instance with the specified parameters.
     *
     * @param name       The name of the planet.
     * @param typeClimat The climate type of the planet.
     */
    public Planet(String name, String typeClimat) {
        this.name = name;
        this.typeClimat = typeClimat;

    }

    /**
     * Gets the name of the planet.
     *
     * @return The name of the planet.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the climate type of the planet.
     *
     * @return The climate type of the planet.
     */
    public String getTypeClimat() {
        return typeClimat;
    }
}