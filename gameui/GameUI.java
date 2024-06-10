package gameui;

import javax.swing.*;

import core.Game;
import representation.Node;
import representation.Optionable;
import representation.TerminalNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameUI extends JFrame implements ActionListener {
    private Game game;
    private JTextArea descriptionArea;
    private JPanel optionsPanel;
    private JLabel backgroundLabel;

    public GameUI(Game game) {
        this.game = game;

        setTitle("Space Adventure Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        backgroundLabel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0, 1));
        backgroundLabel.add(optionsPanel, BorderLayout.SOUTH);

        updateDisplay();

        setVisible(true);
    }

    private void updateDisplay() {
        Node currentNode = game.getCurrentNode();

        descriptionArea.setText(currentNode.getDescription());

        optionsPanel.removeAll();

        optionsPanel.removeAll();

        if (currentNode instanceof TerminalNode) {

            JButton button = new JButton("Game over , restart");

            button.setActionCommand(String.valueOf(1));
            button.addActionListener(this);
            optionsPanel.add(button);

        } else if (currentNode instanceof Optionable) {
            List<Node> options = ((Optionable) currentNode).getOptions();
            for (Node option : options) {
                JButton button = new JButton(option.getDescription());
                button.setActionCommand(String.valueOf(option.getId()));
                button.addActionListener(this);
                optionsPanel.add(button);
            }
        } else {
            JButton button = new JButton("Continue");
            button.setActionCommand(String.valueOf(currentNode.checkNext().getId()));
            button.addActionListener(this);
            optionsPanel.add(button);
        }

        ImageIcon backgroundImage = currentNode.getBackgroundImage();

        System.out.println("Background image: " + backgroundImage);
        if (backgroundImage != null) {
            backgroundLabel.setIcon(backgroundImage);
        } else {
            backgroundLabel.setIcon(null);
        }

        optionsPanel.revalidate();
        optionsPanel.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int nodeId = Integer.parseInt(e.getActionCommand());
        game.advanceToNode(nodeId);
        updateDisplay();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            new GameUI(game);
        });
    }
}