package univers.base;

import java.util.ArrayList;
import java.util.List;

// Classe représentant une planète
public class Planet {
    private String name;
    private String typeClimat;
    private List<Race> racesIndigenes;

    private List<City> cities;

    public Planet(String name, String typeClimat) {
        this.name = name;
        this.typeClimat = typeClimat;
        this.racesIndigenes = new ArrayList<>();
        this.cities = new ArrayList<>();
    }

    public void ajouterRaceIndigene(Race race) {
        racesIndigenes.add(race);
    }

    public String getName() {
        return name;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public String getTypeClimat() {
        return typeClimat;
    }

    public List<Race> getRacesIndigenes() {
        return racesIndigenes;
    }

    public List<City> getCities() {
        return cities;
    }
}