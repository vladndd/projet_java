package representation;

import javax.swing.JOptionPane;

public class PuzzleNode extends Node {
    private String puzzleQuestion;
    private String correctAnswer;
    private Node nextNode;

    public PuzzleNode(int id, String description, String puzzleQuestion, String correctAnswer) {
        super(id, description);
        this.puzzleQuestion = puzzleQuestion;
        this.correctAnswer = correctAnswer;
    }

    public void setCorrectNode(Node node) {
        this.nextNode = node;
    }

    @Override
    public Node chooseNext() {
        String answer = JOptionPane.showInputDialog(null, puzzleQuestion);
        if (answer != null && answer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(null, "Correct! You may proceed.");
            return this.nextNode;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect. Try again.");
            return this;
        }
    }

    @Override
    public Node checkNext() {
        return this.nextNode;
    }
}