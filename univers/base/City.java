package univers.base;

import java.io.Serializable;

public class City implements Serializable {

    private String name;

    private Planet planet;

    public City(String name, Planet planet) {
        this.name = name;
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public Planet getPlanet() {
        return planet;
    }

}
