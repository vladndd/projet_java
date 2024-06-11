package gameui;

import javax.swing.*;
import core.Game;
import representation.Node;
import representation.Optionable;
import representation.TerminalNode;
import univers.base.BaseCharacter;
import univers.base.Planet;
import univers.base.Race;

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
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.NORTH); // Add the backgroundLabel to the top

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        add(scrollPane, BorderLayout.CENTER); // Add the descriptionArea to the center

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0, 1));
        add(optionsPanel, BorderLayout.SOUTH); // Add the optionsPanel to the bottom

        initializeCharacter();

        setVisible(true);
    }

    private void initializeCharacter() {
        JOptionPane.showMessageDialog(this, "Welcome to the Space Adventure Game! Let's create your character!");

        // Displaying planets with buttons and images
        JPanel planetPanel = new JPanel();
        planetPanel.setLayout(new GridLayout(3, 3));
        ButtonGroup planetGroup = new ButtonGroup();

        for (int i = 0; i < Game.PLANETS_LIST.size(); i++) {
            Planet planet = Game.PLANETS_LIST.get(i);
            ImageIcon icon = new ImageIcon("./images/planets/" + planet.getName().toLowerCase() + ".jpg"); // Adjust
            // path
            JRadioButton planetButton = new JRadioButton(planet.getName(), icon);
            planetButton.setActionCommand(String.valueOf(i));
            planetGroup.add(planetButton);
            planetPanel.add(planetButton);
        }

        JOptionPane.showMessageDialog(this, planetPanel, "Choose your starting planet", JOptionPane.QUESTION_MESSAGE);

        int planetChoice = Integer.parseInt(planetGroup.getSelection().getActionCommand());

        // Displaying races with buttons
        JPanel racePanel = new JPanel();
        racePanel.setLayout(new GridLayout(2, 3));
        ButtonGroup raceGroup = new ButtonGroup();
        Race[] races = Race.values();

        for (int i = 0; i < races.length; i++) {
            JRadioButton raceButton = new JRadioButton(races[i].name());
            raceButton.setActionCommand(String.valueOf(i));
            raceGroup.add(raceButton);
            racePanel.add(raceButton);
        }

        JOptionPane.showMessageDialog(this, racePanel, "Choose your race", JOptionPane.QUESTION_MESSAGE);

        int raceChoice = Integer.parseInt(raceGroup.getSelection().getActionCommand());

        String name = JOptionPane.showInputDialog(this, "Enter your name:");

        // Create character with validated choices
        Race race = races[raceChoice];
        Planet planet = Game.PLANETS_LIST.get(planetChoice);
        BaseCharacter character = new BaseCharacter(name, 100, 10, race, planet);

        game.addCharacter(character);

        JOptionPane.showMessageDialog(this,
                "Your character, " + character.getName() + " " + character.getRace() + " from "
                        + character.getStartPlanetName());

        updateDisplay();
    }

    private void updateDisplay() {
        Node currentNode = game.getCurrentNode();

        descriptionArea.setText(currentNode.getDescription());

        optionsPanel.removeAll();

        if (currentNode instanceof TerminalNode) {
            JButton button = new JButton("Game over, restart");
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