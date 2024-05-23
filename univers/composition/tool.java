package univers.composition;

import univers.base.Character;

public class Tool {

    protected int health;

    protected String name;

    protected Character owner;

    public Tool(String name, Character owner) {
        this.health = 100;
        this.name = name;
        this.owner = owner;
    }

    public void useTool(int decreasePoints) {
        this.health -= decreasePoints;
    }

    public void fixTool(int increasePoints) {
        this.health += increasePoints;
    }

    public void changeOwner(Character newOwner) {
        this.owner = newOwner;
    }

}
