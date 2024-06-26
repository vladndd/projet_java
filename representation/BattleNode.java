package representation;

import java.util.ArrayList;
import java.util.List;

import core.Game;
import univers.Character;

public class BattleNode extends Node {
    private String enemyName;
    private int enemyHealth;
    private int enemyAttack;
    private List<Node> options;
    private Game game;

    public BattleNode(int id, String description, String enemyName, int enemyHealth, int enemyAttack, Game game) {
        super(id, description);
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.enemyAttack = enemyAttack;
        this.options = new ArrayList<>();
        this.game = game;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public int getEnemyAttack() {
        return enemyAttack;
    }

    public void addChanceOption(Node node) {
        this.options.add(node);
    }

    @Override
    public Node chooseNext() {
        // Logic for choosing the next node after the battle
        // Placeholder: assuming battle outcome determines the next node

        Character character = game.getCurrentCharacter();
        int enemyAttackYou = character.getHealth() - this.enemyAttack;

        int yourAttackOnEnemy = this.enemyHealth
                - character.getForce();

        System.out.println("You have " + enemyAttackYou + " health left.");

        character.fight(enemyAttack);
        System.out.println("You have " + this.options);

        if (enemyAttackYou <= 0) {
            return this.options.get(1); // You died
        } else if (yourAttackOnEnemy <= 0) {
            return this.options.get(0); // You won and killed the enemy
        } else {
            return this.options.get(2); // You won but the enemy is still alive
        }

    }

    @Override
    public Node checkNext() {

        throw new UnsupportedOperationException("Unimplemented method 'checkNext'");
    }
}