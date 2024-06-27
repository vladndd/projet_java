# Game concept

The game is a text-based adventure game where the player navigates through a series of nodes representing different scenarios. The player can choose different paths, make decisions, and engage in battles trades and puzzles to progress through the game. He also have an equippement that he can change. The game features multiple characters with unique attributes and abilities that affect the gameplay. The player's goal is to reach the end of the game by making strategic choices and overcoming challenges along the way.

# Game UI

## GameUI

Manages the game user interface.

- **Methods:**
  - initializeComponents(): Initializes the components of the game UI.
  - setupFrame(): Sets up the frame for the game UI.
  - updateStats(): Updates the player character's stats on the UI.
  - initializeCharacter(): Initializes the character selection screen.
  - updateDisplay(): Updates the display based on the current node.
  - replaceCharacterWith(): Replaces the character with new one.
  - createButton(String text, int actionCommand): Creates a JButton with the specified text and action command.
  - updateMuteButton(): Updates the mute button based on the sound state.
  - actionPerformed(ActionEvent e): Handles action events for the game UI.
  - showMainMenu(): Shows the main menu screen.
  - loadGame(): Loads a saved game state.
  - saveGame(): Saves the current game state.
  - main(String[] args): The main method to start the game.

# Core Classes

## Game

Manages the overall game state, including the current node, characters, and game progress.

- **Methods:**
  - advanceToNode(int nodeId): Advances the game to the specified node.
  - saveGame(String filename): Saves the current game state to a file.
  - loadGame(String filename): Loads a game state from a file.
  - createNodePool(): Initializes the nodes for the game from a configuration file.
  - getCurrentNode(): Gets the current node in the game.
  - updateCurrentPlanet(Planet planet): Updates the current planet in the game.
  - setCharacter(Character character): Sets the player character for the game.
  - getCurrentCharacter(): Gets the player character in the game.
  - initializePlanets(): Initializes the planets in the game.

## NodeFactory

Creates and links nodes based on a configuration file.

- **Methods:**
  - createNodes(String jsonFilePath): Creates nodes from a JSON configuration file.
  - createNodeFromJson(JSONObject nodeJson): Creates a node from a JSON object.
  - linkNodes(JSONObject nodeJson): Links nodes based on the JSON object.

# Representation Classes

## Node

An abstract class representing a point in the game.

- **Methods:**
  - chooseNext(): Determines the next node in the sequence.
  - checkNext(): Checks the next node without advancing.
  - getDescription(): Gets the description of the node.
  - getId(): Gets the ID of the node.
  - getSoundFilePath(): Gets the sound file path for the node.
  - setSoundFilePath(String soundFilePath): Sets the sound file path for the node.
  - getBackgroundImage(): Gets the background image for the node.
  - setBackgroundImage(String backgroundImage): Sets the background image for the node.

## PuzzleNode

Represents a node with a puzzle scenario.

- **Methods:**
  - setCorrectNode(Node n): Sets next node in case of puzzle solved.
  - chooseNext(): Determines the next node based on the puzzle outcome.
  - checkNext(): Checks the next node without advancing.

## BattleNode

Represents a battle scenario.

- **Methods:**
  - chooseNext(): unsupported operation.
  - checkNext(): Checks the next node without advancing.
  - getEnemyName(): Gets the name of the enemy in the battle.
  - getEnemyHealth(): Gets the health of the enemy in the battle.
  - getEnemyAttack(): Gets the damage of the enemy in the battle.
  - addChanceOption(Node node): Adds a chance node as an option for the battle.

## ChanceNode

Represents a node with random outcomes.

- **Methods:**
  - chooseNext(): Randomly selects the next node from possible outcomes.
  - checkNext(): Randomly checks the next node without advancing.
  - addOutcome(Node node): Adds a possible outcome with a probability.
  - getOutcomes(): Gets the possible outcomes with probabilities.

## DecisionNode

Represents a node where the player must make a choice.

- **Methods:**
  - chooseNext(): Determines the next node based on player's choice.
  - addOption(Node node): Adds an option for the player to choose.
  - getOptions(): Gets the available options for the player.
  - checkNext(): Checks the next node without advancing.

## TradeNode

Represents a node where the player can trade items.

- **Methods:**
  - tradeItem(Item item): Handles trading an item.
  - addChanceOption(Node node): Adds a chance node as an option for the trade.
  - getItemsForSale(): Gets the items available for trade.
  - checkNext(): unsupported operation.
  - chooseNext(): Determines the next node after trading.

## TerminalNode

Represents an end point in the game.

- **Methods:**
  - chooseNext(): Returns the current node as the next node since it's the end.
  - checkNext(): Checks the next node without advancing.

## InnerNode

Represents a node that is not a terminal node.

- **Methods:**
  - chooseNext(): Determines the next node based on player's choice.
  - checkNext(): Checks the next node without advancing.
  - addNextNode(Node node): Adds a next node

# Utility Classes

## GameUIutilities

Provides utility methods for the game UI.

- **Methods:**
  - updatePlanet(Game game, int nodeId): Updates the current planet in the game based on the node ID.
  - createCharacterSelectionButton(int optionId): Creates a JButton for character selection.
  - isHomePlanet(Game game, int optionId): Checks if the specified option ID corresponds to the character's home planet.

## SoundManager

Manages sound playback.

- **Methods:**
  - playSound(String filePath): Plays a sound from the specified file path.
  - stopSound(): Stops the currently playing sound.
  - loopSound(String filePath): Loops a sound continuously from the specified file path.
  - toggleMute(): Toggles the sound on/off.

## Utility

Provides a static Scanner instance for user input.

- **Methods:**
  - closeScanner(): Closes the Scanner instance.

# Univers Classes

## Character

An abstract class representing the player character.

- **Subclasses:** Warrior, Assassin, Explorer, BaseCharacter
- **Methods:**
  - fight(int intakeDamage): Reduces the health of the character by the specified damage intake.
  - trade(Item item): Trades the specified item by adding it to the inventory.
  - getSpecificAttribute(): Gets the specific attribute of the character.
  - specificDamage(): Gets the specific damage of the character.
  - getInventory(): Gets the inventory of the character.
  - getInventoryItems(): Gets the items in the inventory.
  - addToInventory(Item item): Adds an item to the inventory.
  - getCurrentWeight(): Gets the current weight of the inventory.
  - getMaximumWeight(): Gets the maximum weight the character can carry.
  - getHealth(): Gets the health of the character.
  - getCharacterType(): Gets the type of the character.
  - getForce(): Gets the force of the character.
  - decreaseMoney(int amount): Decreases the money of the character by the specified amount.
  - increaseMoney(int amount): Increases the money of the character by the specified amount.
  - hasEnoughMoney(int amount): Checks if the character has enough money.
  - getMoney(): Gets the money of the character.
  - getRace(): Gets character's race
  - getStartPlanet(): Gets the starting planet of the character.
  - getStartPlanetName(): Gets the name of the starting planet.
  - setEquipedWeapon(Weapon weapon): Sets the equipped weapon for the character.
  - setCurrentPlanet(Planet planet): Sets the current planet of the character.
  - getName(): Gets the name of the character.
  - getEquipedWeapon(): Gets the equipped weapon of the character.
  - equipItem(Item item): Equips the specified item.
  - unequipItem(): Unequips the specified item.

## Warrior

A subclass of Character with combat power-based attributes.

- **Methods:**
  - getCombatPower(): Gets the combat power of the warrior.
  - getSpecificAttribute(): Gets the combat power attribute.
  - specificDamage(): Gets the combat power value as specific damage.
  - getCharacterType(): Gets the type of the character.

## Assassin

A subclass of Character with agile damage-based attributes.

- **Methods:**
  - getSpecificAttribute(): Gets the agile damage attribute.
  - specificDamage(): Gets the agile damage value as specific damage.
  - getCharacterType(): Gets the type of the character.

## Explorer

A subclass of Character with intelligence-based attributes.

- **Methods:**
  - getSpecificAttribute(): Gets the intelligence attribute.
  - specificDamage(): Gets the intelligence value as specific damage.
  - getCharacterType(): Gets the type of the character.
  - getIntelligence(): Gets the intelligence of the explorer.

## BaseCharacter

A subclass of Character with basic attributes.

- **Methods:**
  - getSpecificAttribute(): Gets the intelligence attribute.
  - specificDamage(): Gets the intelligence value as specific damage.
  - getCharacterType(): Gets the type of the character.
