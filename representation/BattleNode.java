package representation;

import java.util.ArrayList;
import java.util.List;

import core.Game;
import univers.Character;

/**
 * BattleNode class represents a battle scenario in the game.
 * It manages the details of the battle, including the enemy's stats and the
 * possible outcomes.
 */
public class BattleNode extends Node {
    private String enemyName;
    private int enemyHealth;
    private int enemyAttack;
    private List<Node> options;
    private Game game;

    /**
     * Constructs a BattleNode instance with the specified parameters.
     *
     * @param id          The ID of the node.
     * @param description The description of the node.
     * @param enemyName   The name of the enemy.
     * @param enemyHealth The health of the enemy.
     * @param enemyAttack The attack power of the enemy.
     * @param game        The Game instance associated with this node.
     */
    public BattleNode(int id, String description, String enemyName, int enemyHealth, int enemyAttack, Game game) {
        super(id, description);
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.enemyAttack = enemyAttack;
        this.options = new ArrayList<>();
        this.game = game;
    }

    /**
     * Gets the name of the enemy.
     *
     * @return The name of the enemy.
     */
    public String getEnemyName() {
        return enemyName;
    }

    /**
     * Gets the health of the enemy.
     *
     * @return The health of the enemy.
     */
    public int getEnemyHealth() {
        return enemyHealth;
    }

    /**
     * Gets the attack power of the enemy.
     *
     * @return The attack power of the enemy.
     */
    public int getEnemyAttack() {
        return enemyAttack;
    }

    /**
     * Adds a possible outcome node to the list of options.
     *
     * @param node The Node to be added as a possible outcome.
     */
    public void addChanceOption(Node node) {
        this.options.add(node);
    }

    /**
     * Chooses the next node based on the outcome of the battle.
     *
     * @return The next Node to advance to.
     */
    @Override
    public Node chooseNext() {
        Character character = game.getCurrentCharacter();
        int enemyAttackYou = character.getHealth() - this.enemyAttack;
        int yourAttackOnEnemy = this.enemyHealth - character.getForce();

        character.fight(enemyAttack);

        if (enemyAttackYou <= 0) {
            return this.options.get(1); // You died
        } else if (yourAttackOnEnemy <= 0) {
            return this.options.get(0); // You won and killed the enemy
        } else {
            return this.options.get(2); // You won but the enemy is still alive
        }
    }

    /**
     * Method to check the next node. Currently not implemented.
     *
     * @throws UnsupportedOperationException If the method is not implemented.
     */
    @Override
    public Node checkNext() {
        throw new UnsupportedOperationException("Unimplemented method 'checkNext'");
    }
}
