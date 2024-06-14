package gameui;

import javax.swing.*;

import core.Game;
import representation.BattleNode;
import representation.ChanceNode;
import representation.Node;
import representation.Optionable;
import representation.TerminalNode;
import representation.TradeNode;
import univers.base.BaseCharacter;
import univers.base.Planet;
import univers.base.Race;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GameUI extends JFrame implements ActionListener {
    private Game game;
    private JTextArea descriptionArea;
    private JPanel optionsPanel;
    private JLabel backgroundLabel;
    private JTextArea statsArea;

    public GameUI(Game game) {
        this.game = game;

        setTitle("Space Adventure Game");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        // Create a main content panel that will hold the scene image and other
        // components
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);
        add(mainContentPanel, BorderLayout.CENTER);

        // Add the scene image to the center of the main content panel
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        mainContentPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Create stats panel at the top
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statsPanel.setOpaque(false);
        statsArea = new JTextArea(5, 20); // Adjust rows and columns as needed
        statsArea.setEditable(false);
        statsArea.setOpaque(false);
        statsArea.setForeground(Color.BLACK);
        statsPanel.add(statsArea);
        mainContentPanel.add(statsPanel, BorderLayout.NORTH); // Add statsPanel to top of mainContentPanel

        // Create description panel at the bottom
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        descriptionPanel.add(scrollPane, BorderLayout.CENTER);

        optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new GridLayout(0, 1));
        descriptionPanel.add(optionsPanel, BorderLayout.SOUTH);

        mainContentPanel.add(descriptionPanel, BorderLayout.SOUTH); // Add descriptionPanel to bottom of
                                                                    // mainContentPanel

        initializeCharacter();

        setVisible(true);
    }

    private void updateStats(BaseCharacter character) {
        String statsText = "Name: " + character.getName() + "\n" +
                "Race: " + character.getRace() + "\n" +
                "Current Planet: " + character.getStartPlanetName() + "\n" +
                "Health: " + character.getHealth() + "\n" +
                "Force: " + character.getForce() + "\n" +
                "Inventory: " + character.getInventory();
        statsArea.setText(statsText);
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
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize the image
            ImageIcon resizedIcon = new ImageIcon(resizedImg);

            JPanel planetButtonPanel = new JPanel();
            planetButtonPanel.setLayout(new BorderLayout());

            JRadioButton planetButton = new JRadioButton(resizedIcon);
            planetButton.setActionCommand(String.valueOf(i));
            planetGroup.add(planetButton);

            JLabel planetLabel = new JLabel(planet.getName(), JLabel.CENTER);

            planetButtonPanel.add(planetButton, BorderLayout.CENTER);
            planetButtonPanel.add(planetLabel, BorderLayout.SOUTH);
            planetPanel.add(planetButtonPanel);
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

        updateStats(character);

        try {
            game.createNodePool();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error creating node pool");
            e.printStackTrace();
        }

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
            System.out.println(currentNode.getDescription());
            List<Node> options = ((Optionable) currentNode).getOptions();
            for (Node option : options) {
                JButton button = new JButton(option.getDescription());
                button.setActionCommand(String.valueOf(option.getId()));
                button.addActionListener(this);
                optionsPanel.add(button);
            }
        } else if (currentNode instanceof BattleNode) {
            JButton button = new JButton("Fight " + ((BattleNode) currentNode).getEnemyName());
            Node next = currentNode.chooseNext();
            button.setActionCommand(String.valueOf(next.getId()));
            button.addActionListener(this);
            optionsPanel.add(button);
        } else if (currentNode instanceof TradeNode) {
            TradeNode tradeNode = (TradeNode) currentNode;
            for (String item : tradeNode.getItemsForSale()) {
                JButton button = new JButton("Trade for " + item);
                Node next = currentNode.chooseNext();
                button.setActionCommand(String.valueOf(next.getId()));
                button.addActionListener(this);
                optionsPanel.add(button);
            }
        } else if (currentNode instanceof ChanceNode) {
            JButton button = new JButton("See whats next..");
            Node next = currentNode.chooseNext();
            button.setActionCommand(String.valueOf(next.getId()));
            button.addActionListener(this);
            optionsPanel.add(button);
        } else {
            JButton button = new JButton("Continue");
            button.setActionCommand(String.valueOf(currentNode.checkNext().getId()));
            button.addActionListener(this);
            optionsPanel.add(button);
        }

        if (currentNode.getBackgroundImage() != null) {
            backgroundLabel.setIcon(currentNode.getBackgroundImage());
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