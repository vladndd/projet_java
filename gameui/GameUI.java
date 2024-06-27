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
import univers.Assassin;
import univers.BaseCharacter;
import univers.Character;
import univers.Explorer;
import univers.Item;
import univers.Planet;
import univers.Race;
import univers.Warrior;
import utility.GameUIutilities;
import utility.SoundManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * GameUI class provides the graphical user interface for the Space Adventure
 * Game.
 * It handles the display of game content, interaction with the user, and
 * updates to the game state.
 */
public class GameUI extends JFrame implements ActionListener {
    private Game game;
    private JTextArea descriptionArea;
    private JPanel optionsPanel;
    private JLabel backgroundLabel;
    private JTextArea statsArea;
    private JPanel mainContentPanel; // Store main content panel reference
    private SoundManager soundManager; // SoundManager instance

    /**
     * Constructor for GameUI.
     *
     * @param game The Game instance to be associated with this UI.
     */
    public GameUI(Game game) {
        this.game = game;
        this.soundManager = new SoundManager();

        this.setupFrame();
        this.initializeComponents();

        setVisible(true);
    }

    /**
     * Initializes the main components of the UI.
     */
    private void initializeComponents() {
        // Main content panel
        mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false);
        add(mainContentPanel, BorderLayout.CENTER);

        // Background label initialization and setup
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setHorizontalAlignment(JLabel.CENTER); // Horizontally center the content
        backgroundLabel.setVerticalAlignment(JLabel.CENTER); // Vertically center the content
        mainContentPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Stats and buttons panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Stats area
        statsArea = new JTextArea(5, 20);
        statsArea.setEditable(false);
        statsArea.setOpaque(false);
        statsArea.setForeground(Color.BLACK);
        topPanel.add(statsArea, BorderLayout.CENTER); // Add to the center

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Adding buttons
        JButton saveButton = createButton("Save Game", "save_game");
        buttonPanel.add(saveButton);

        JButton newGameButton = createButton("New Game", "new_game");
        buttonPanel.add(newGameButton);

        JButton loadGameButton = createButton("Load Game", "load_game");
        buttonPanel.add(loadGameButton);

        JButton exitButton = createButton("Exit", "exit");
        buttonPanel.add(exitButton);

        // Add button panel to the right
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Add top panel to the north of the main content panel
        mainContentPanel.add(topPanel, BorderLayout.NORTH);

        // Description area setup
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

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);
        descriptionPanel.add(scrollPane, BorderLayout.CENTER);

        optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new GridLayout(0, 1));
        descriptionPanel.add(optionsPanel, BorderLayout.SOUTH);

        mainContentPanel.add(descriptionPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the main frame of the UI.
     */
    private void setupFrame() {
        setTitle("Space Adventure Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    /**
     * Updates the stats area with the character's current stats.
     *
     * @param character The character whose stats are to be displayed.
     */
    private void updateStats(Character character) {
        StringBuilder inventoryString = new StringBuilder();
        for (String item : character.getInventory()) {
            inventoryString.append(item).append("\n");
        }

        String statsText = "Name: " + character.getName() + "\n" +
                "Race: " + character.getRace() + "\n" +
                "Character Type: " + character.getCharacterType() + "\n" +
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

    /**
     * Initializes a new character for the game.
     */
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

        game.setCharacter(character);

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

    /**
     * Updates the display area based on the current node.
     */
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

        // Play the associated sound for the current node
        String soundFilePath = currentNode.getSoundFilePath(); // Assuming each node has a sound file path
        if (soundFilePath != null) {
            soundManager.playSound(soundFilePath);
        }

        if (currentNode instanceof TerminalNode) {
            optionsPanel.add(createButton("Game over, restart", "restart"));
        } else if (currentNode instanceof ChanceNode) {
            optionsPanel.add(createButton("See what's next..", "advance"));
        } else if (currentNode instanceof DecisionNode) {
            List<Node> options = ((Optionable) currentNode).getOptions();
            for (Node option : options) {
                JButton button;
                if (currentNode.getId() == 99) {
                    // Add buttons for character type selection
                    button = GameUIutilities.createCharacterSelectionButton(option.getId());
                } else {
                    boolean isHomePlanet = false;
                    isHomePlanet = GameUIutilities.isHomePlanet(game, option.getId());

                    if (isHomePlanet) {
                        continue;
                    }

                    button = new JButton(option.getDescription());
                    button.setActionCommand(String.valueOf(option.getId()));
                }
                button.addActionListener(this);
                optionsPanel.add(button);
            }
        } else if (currentNode instanceof BattleNode) {
            optionsPanel.add(createButton("Fight " + ((BattleNode) currentNode).getEnemyName(), "battle"));
        } else if (currentNode instanceof TradeNode) {
            TradeNode tradeNode = (TradeNode) currentNode;
            for (Item item : tradeNode.getItemsForSale()) {
                JButton button = createButton("Trade for " + item.getName() + " " + item.getPrice(), "trade");
                button.putClientProperty("item", item); // Storing the item object directly on the button
                optionsPanel.add(button);
            }
        } else {
            optionsPanel.add(createButton("Continue", "advance"));
        }

        Character character = game.getCurrentCharacter();

        // Access the inventory HashMap and iterate over its entries
        for (Map.Entry<String, Item> entry : character.getInventoryItems().entrySet()) {
            String itemName = entry.getKey();
            Item item = entry.getValue();

            // Create a new JButton for each item in the inventory
            JButton button = new JButton("Equip " + itemName);
            button.putClientProperty("item", item); // Attach the item object directly to the button
            button.setActionCommand("equip");
            button.addActionListener(this); // Assuming 'this' is an ActionListener that handles the button actions
            optionsPanel.add(button); // Add the button to your panel
        }

        if (character.getEquipedWeapon() != null) {
            JButton button = new JButton("Unequip " + character.getEquipedWeapon()
                    .getName());
            button.setActionCommand("unequip");
            button.addActionListener(this);
            optionsPanel.add(button);
        }

        updateStats(game.getCurrentCharacter());

        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    /**
     * Replaces the current character with a new character.
     *
     * @param newCharacter The new character to be set.
     */
    private void replaceCharacterWith(Character newCharacter) {
        game.setCharacter(newCharacter);
        updateStats(newCharacter);
    }

    /**
     * Creates a button with the specified text and action command.
     *
     * @param text          The text to be displayed on the button.
     * @param actionCommand The action command to be set for the button.
     * @return The created JButton.
     */
    private JButton createButton(String text, String actionCommand) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        return button;
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
                Item item = (Item) sourceButton.getClientProperty("item");
                TradeNode tradeNode = (TradeNode) game.getCurrentNode();
                tradeNode.setItem(item);
                Node nextNode_ = tradeNode.chooseNext();
                game.advanceToNode(nextNode_.getId());
                break;
            case "equip":
                JButton sourceButton_ = (JButton) e.getSource();
                Item item_ = (Item) sourceButton_.getClientProperty("item");
                game.getCurrentCharacter().equipItem(item_);
                break;
            case "unequip":
                game.getCurrentCharacter().unequipItem();
                break;
            case "select_warrior":
                Character currentCharacter = game.getCurrentCharacter();

                replaceCharacterWith(new Warrior(currentCharacter.getName(),
                        currentCharacter.getHealth(),
                        currentCharacter.getForce(), currentCharacter.getRace(), 40,
                        currentCharacter.getStartPlanet()));
                game.advanceToNode(100); // Advance to the node for choosing warrior
                break;
            case "select_assassin":
                Character _currentCharacter = game.getCurrentCharacter();

                replaceCharacterWith(new Assassin(_currentCharacter.getName(),
                        _currentCharacter.getHealth(),
                        _currentCharacter.getForce(), _currentCharacter.getRace(), 40,
                        _currentCharacter.getStartPlanet()));
                game.advanceToNode(101); // Advance to the node for choosing assassin
                break;
            case "select_explorer":
                Character __currentCharacter = game.getCurrentCharacter();

                replaceCharacterWith(new Explorer(__currentCharacter.getName(),
                        __currentCharacter.getHealth(),
                        __currentCharacter.getForce(), __currentCharacter.getRace(), 40,
                        __currentCharacter.getStartPlanet()));
                game.advanceToNode(102); // Advance to the node for choosing explorer
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
            case "save_game":
                saveGame();
                return;
            case "exit":
                System.exit(0);
                return;
            default:
                int nodeId = Integer.parseInt(command);
                GameUIutilities.updatePlanet(game, nodeId);
                game.advanceToNode(nodeId);
                break;
        }

        updateDisplay();
    }

    /**
     * Displays the main menu.
     */
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

    /**
     * Loads a saved game.
     */
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

    /**
     * Saves the current game.
     */
    private void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                game.saveGame(selectedFile.getPath());
                JOptionPane.showMessageDialog(this, "Game saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving game: " + ex.getMessage());
            }
        }
    }

    /**
     * Main method to launch the game UI.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            GameUI gameUI = new GameUI(game);
            gameUI.showMainMenu(); // Show the main menu when the application starts
        });
    }
}
