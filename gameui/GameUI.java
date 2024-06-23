package gameui;

import javax.swing.*;

import core.Game;
import representation.BattleNode;
import representation.ChanceNode;
import representation.DecisionNode;
import representation.Node;
import representation.Optionable;
import representation.TerminalNode;
import representation.TradeNode;
import univers.base.BaseCharacter;
import univers.base.Planet;
import univers.base.Race;
import univers.base.Character;
import univers.base.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameUI extends JFrame implements ActionListener {
    private Game game;
    private JTextArea descriptionArea;
    private JPanel optionsPanel;
    private JLabel backgroundLabel;
    private JTextArea statsArea;
    private JPanel mainContentPanel; // Store main content panel reference

    public GameUI(Game game) {
        this.game = game;

        setTitle("Space Adventure Game");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);
        add(mainContentPanel, BorderLayout.CENTER);

        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        mainContentPanel.add(backgroundLabel, BorderLayout.CENTER);

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statsPanel.setOpaque(false);
        statsArea = new JTextArea(5, 20);
        statsArea.setEditable(false);
        statsArea.setOpaque(false);
        statsArea.setForeground(Color.BLACK);
        statsPanel.add(statsArea);
        mainContentPanel.add(statsPanel, BorderLayout.NORTH);

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

        mainContentPanel.add(descriptionPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateStats(Character character) {
        StringBuilder inventoryString = new StringBuilder();
        for (String item : character.getInventory()) {
            inventoryString.append(item).append("\n");
        }

        String statsText = "Name: " + character.getName() + "\n" +
                "Race: " + character.getRace() + "\n" +
                "Character Type: " + character.getCharacteType() + "\n" +
                "Specific attribute " + character.getSpecificAttribute() + "\n" +
                "Current Planet: " + character.getStartPlanetName() + "\n" +
                "Health: " + character.getHealth() + "\n" +
                "Force: " + character.getForce() + "\n" +
                "Money: " + character.getMoney() + "\n" +
                "Weight: " + character.getCurrentWeight() + "\n" +
                "Equipped Weapon: "
                + (character.getEquipedWeapon() != null ? character.getEquipedWeapon().getName() : "None") + "\n" +
                "Inventory: " + (inventoryString.length() > 0 ? inventoryString.toString() : "Empty");
        statsArea.setText(statsText);
    }

    private void initializeCharacter() {
        JOptionPane.showMessageDialog(this, "Welcome to the Space Adventure Game! Let's create your character!");

        JPanel planetPanel = new JPanel();
        planetPanel.setLayout(new GridLayout(3, 3));
        ButtonGroup planetGroup = new ButtonGroup();

        for (int i = 0; i < Game.PLANETS_LIST.size(); i++) {
            Planet planet = Game.PLANETS_LIST.get(i);
            ImageIcon icon = new ImageIcon("./images/planets/" + planet.getName().toLowerCase() + ".jpg");
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
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
            System.out.println("Error creating node pool");
            e.printStackTrace();
        }

        setContentPane(mainContentPanel); // Switch back to the main content panel
        updateDisplay();
    }

    private void updateDisplay() {
        Node currentNode = game.getCurrentNode();

        if (currentNode == null) {
            return; // Main menu is displayed, no need to update display
        }

        descriptionArea.setText(currentNode.getDescription());

        optionsPanel.removeAll();

        if (currentNode.getBackgroundImage() != null) {
            backgroundLabel.setIcon(currentNode.getBackgroundImage());
        } else {
            backgroundLabel.setIcon(null);
        }

        if (currentNode instanceof TerminalNode) {
            JButton button = new JButton("Game over, restart");
            button.setActionCommand("restart");
            button.addActionListener(this);
            optionsPanel.add(button);
        } else if (currentNode instanceof ChanceNode) {
            JButton button = new JButton("See whats next..");
            button.setActionCommand("advance");
            button.addActionListener(this);
            optionsPanel.add(button);
        } else if (currentNode instanceof DecisionNode) {
            List<Node> options = ((Optionable) currentNode).getOptions();
            for (Node option : options) {
                boolean isHomePlanet = false;

                switch (option.getId()) {
                    case 5:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Mercury");
                        break;
                    case 6:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Venus");
                        break;
                    case 7:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Earth");
                        break;
                    case 8:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Mars");
                        break;
                    case 9:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Jupiter");
                        break;
                    case 10:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Saturn");
                        break;
                    case 11:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Uranus");
                        break;
                    case 12:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Neptune");
                        break;
                    case 13:
                        isHomePlanet = this.game.getCurrentCharacter().getStartPlanetName().equals("Pluto");
                        break;
                }

                if (!isHomePlanet) {
                    JButton button = new JButton(option.getDescription());
                    button.setActionCommand(String.valueOf(option.getId()));
                    button.addActionListener(this);
                    optionsPanel.add(button);
                }
            }
        } else if (currentNode instanceof BattleNode) {
            JButton button = new JButton("Fight " + ((BattleNode) currentNode).getEnemyName());
            button.setActionCommand("battle");
            button.addActionListener(this);
            optionsPanel.add(button);
        } else if (currentNode instanceof TradeNode) {
            TradeNode tradeNode = (TradeNode) currentNode;
            for (Item item : tradeNode.getItemsForSale()) {
                JButton button = new JButton("Trade for " + item.getName());
                button.setActionCommand("trade");
                button.addActionListener(this);
                optionsPanel.add(button);
            }
        } else {
            JButton button = new JButton("Continue");
            button.setActionCommand("advance");
            button.addActionListener(this);
            optionsPanel.add(button);
        }

        Character character = game.getCurrentCharacter();

        for (String itemName : character.getInventory()) {
            JButton button = new JButton("Equip " + itemName);
            button.setActionCommand("equip");
            button.addActionListener(this);
            optionsPanel.add(button);
        }

        if (character.getEquipedWeapon() != null) {
            JButton button = new JButton("Unequip " + character.getEquipedWeapon().getName());
            button.setActionCommand("unequip");
            button.addActionListener(this);
            optionsPanel.add(button);
        }
        updateStats(game.getCurrentCharacter());

        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "advance":
            case "battle":
                Node nextNode = game.getCurrentNode().chooseNext();
                game.advanceToNode(nextNode.getId());
                break;
            case "trade":
                JButton sourceButton = (JButton) e.getSource();
                String itemName = sourceButton.getText().substring(10); // "Trade for ".length() == 10
                TradeNode tradeNode = (TradeNode) game.getCurrentNode();
                tradeNode.tradeItem(itemName);
                Node nextNode_ = tradeNode.chooseNext();
                game.advanceToNode(nextNode_.getId());
                break;
            case "equip":
                JButton sourceButton_ = (JButton) e.getSource();
                String[] itemDetails = sourceButton_.getText().split(",")[0].split(" ");
                String equipItemName = itemDetails[2];
                game.getCurrentCharacter().equipItem(equipItemName);
                break;
            case "unequip":
                game.getCurrentCharacter().unequipItem();
                break;
            case "restart":
                showMainMenu();
                return;
            case "new_game":
                initializeCharacter();
                return;
            case "load_game":
                loadGame();
                return;
            case "exit":
                System.exit(0);
                return;
            default:
                int nodeId = Integer.parseInt(command);
                updatePlanet(nodeId);
                game.advanceToNode(nodeId);
                break;
        }

        updateDisplay();
    }

    private void updatePlanet(int nodeId) {
        switch (nodeId) {
            case 5:
                game.updateCurrentPlanet(new Planet("Mercury", "Very hot planet with no atmosphere."));
                break;
            case 6:
                game.updateCurrentPlanet(new Planet("Venus", "Thick atmosphere and volcanic activity."));
                break;
            case 7:
                game.updateCurrentPlanet(new Planet("Earth", "Rich in life and diverse climates."));
                break;
            case 8:
                game.updateCurrentPlanet(new Planet("Mars", "Red planet with potential for life."));
                break;
            case 9:
                game.updateCurrentPlanet(new Planet("Jupiter", "Giant gas planet with a strong magnetic field."));
                break;
            case 10:
                game.updateCurrentPlanet(new Planet("Saturn", "Known for its extensive ring system."));
                break;
            case 11:
                game.updateCurrentPlanet(new Planet("Uranus", "Ice giant with a tilted axis."));
                break;
            case 12:
                game.updateCurrentPlanet(new Planet("Neptune", "Cold blue planet with strong winds."));
                break;
            case 13:
                game.updateCurrentPlanet(new Planet("Pluto", "Dwarf planet with a heart-shaped glacier."));
                break;
        }
    }

    private void showMainMenu() {
        JPanel mainMenuPanel = new JPanel(new GridLayout(3, 1));

        JButton newGameButton = new JButton("New Game");
        newGameButton.setActionCommand("new_game");
        newGameButton.addActionListener(this);
        mainMenuPanel.add(newGameButton);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setActionCommand("load_game");
        loadGameButton.addActionListener(this);
        mainMenuPanel.add(loadGameButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this);
        mainMenuPanel.add(exitButton);

        setContentPane(mainMenuPanel);
        revalidate();
        repaint();
    }

    private void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                game = Game.loadGame(selectedFile.getPath());
                setContentPane(mainContentPanel); // Switch back to the main content panel
                updateDisplay();
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error loading game: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            GameUI gameUI = new GameUI(game);
            gameUI.showMainMenu(); // Show the main menu when the application starts
        });
    }
}