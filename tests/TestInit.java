package tests;

import univers.base.Character;
import univers.base.Item;
import univers.base.Planet;
import univers.base.Race;
import core.Game;

// Stub implementation of the Game class
class StubGame extends Game {
    private Character currentCharacter;

    @Override
    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character character) {
        this.currentCharacter = character;
    }
}

// Stub implementation of the Character class
class StubCharacter extends Character {
    public StubCharacter(String nom, int health, int force, Race race, Planet planet) {
        super(nom, health, force, race, planet);
        // TODO Auto-generated constructor stub
    }

    private int health;
    private int force;

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    @Override
    public void fight(int damage) {
        this.health -= damage;
    }

    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public void speak() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'speak'");
    }

    @Override
    public void trade(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trade'");
    }

    @Override
    public String getSpecificAttribute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpecificAttribute'");
    }

    @Override
    public int specificDamage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'specificDamage'");
    }

    @Override
    public String getCharacterType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCharacterType'");
    }
}