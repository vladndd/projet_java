package representation;

import java.util.ArrayList;
import java.util.List;

import univers.base.Character;

public class BattleNode extends Node {
    private String enemyName;
    private int enemyHealth;
    private int enemyAttack;
    private Character character;
    private List<Node> options;

    public BattleNode(int id, String description, String enemyName, int enemyHealth, int enemyAttack,
            Character character) {
        super(id, description);
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.enemyAttack = enemyAttack;
        this.character = character;
        this.options = new ArrayList<>();
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

        int enemyAttackYou = this.character.getHealth() - this.enemyAttack;

        int yourAttackOnEnemy = this.enemyHealth
                - this.character.getForce();

        System.out.println("You have " + enemyAttackYou + " health left.");

        this.character.fight(enemyAttack);
        System.out.println("You have " + this.options);

        if (yourAttackOnEnemy <= 0) {
            return this.options.get(0); // You won and killed the enemy
        } else if (enemyAttackYou <= 0) {
            return this.options.get(1); // You died
        } else {
            return this.options.get(2); // You escaped
        }
    }

    @Override
    public Node checkNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkNext'");
    }
}