package univers.base;

import java.util.ArrayList;
import java.util.List;

// Classe représentant une planète
public class Planet {
    private String name;
    private String typeClimat;
    private List<Race> racesIndigenes;

    public Planet(String name, String typeClimat) {
        this.name = name;
        this.typeClimat = typeClimat;
        this.racesIndigenes = new ArrayList<>();
    }

    public void ajouterRaceIndigene(Race race) {
        racesIndigenes.add(race);
    }

    public String getName() {
        return name;
    }

    public String getTypeClimat() {
        return typeClimat;
    }

    public List<Race> getRacesIndigenes() {
        return racesIndigenes;
    }
}