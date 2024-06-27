package representation;

import javax.swing.JOptionPane;

import core.Game;
import univers.Character;

/**
 * PuzzleNode class represents a node where the player must solve a puzzle to
 * proceed.
 * It manages the puzzle question, correct answer, and the next node to go to.
 */
public class PuzzleNode extends Node {
    private String puzzleQuestion;
    private String correctAnswer;
    private Node nextNode;
    private Game game;

    /**
     * Constructs a PuzzleNode instance with the specified parameters.
     *
     * @param id             The ID of the node.
     * @param description    The description of the node.
     * @param puzzleQuestion The puzzle question.
     * @param correctAnswer  The correct answer to the puzzle.
     * @param game           The Game instance associated with this node.
     */
    public PuzzleNode(int id, String description, String puzzleQuestion, String correctAnswer, Game game) {
        super(id, description);
        this.puzzleQuestion = puzzleQuestion;
        this.correctAnswer = correctAnswer;
        this.game = game;
    }

    /**
     * Sets the next node to go to after solving the puzzle.
     *
     * @param node The next node to go to.
     */
    public void setCorrectNode(Node node) {
        this.nextNode = node;
    }

    /**
     * Chooses the next node based on the player's answer to the puzzle.
     *
     * @return The next node to go to.
     */
    @Override
    public Node chooseNext() {
        Character character = game.getCurrentCharacter();
        String answer = JOptionPane.showInputDialog(null, puzzleQuestion);
        if (answer != null && answer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(null, "Correct! You may proceed.");
            character.increaseMoney(50); // Reward the player for solving the puzzle
            return this.nextNode;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect. Try again.");
            return this;
        }
    }

    /**
     * Checks the next node to go to after solving the puzzle.
     *
     * @return The next node to go to.
     */
    @Override
    public Node checkNext() {
        return this.nextNode;
    }
}