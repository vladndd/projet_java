# Game concept

The game is a text-based adventure game where the player navigates through a series of nodes representing different scenarios. The player can choose different paths, make decisions, and engage in battles and trades to progress through the game. He also have an equippement that he can change. The game features multiple characters with unique attributes and abilities that affect the gameplay. The player's goal is to reach the end of the game by making strategic choices and overcoming challenges along the way.

# Core Classes

## Game

Manages the overall game state, including the current node, characters, and game progress.

- **Methods:**
  - advanceToNode(int nodeId): Advances the game to the specified node.
  - saveGame(String filename): Saves the current game state to a file.
  - loadGame(String filename): Loads a game state from a file.
  - createNodePool(): Initializes the nodes for the game from a configuration file.

## NodeFactory

Creates and links nodes based on a configuration file.

- **Methods:**
  - createNodes(String jsonFilePath): Creates nodes from a JSON configuration file.
  - getNode(int id): Retrieves a node by its ID.

# Representation Classes

## Node

An abstract class representing a point in the game.

- **Methods:**
  - chooseNext(): Determines the next node in the sequence.
  - checkNext(): Checks the next node without advancing.

## BattleNode

Represents a battle scenario.

- **Methods:**
  - chooseNext(): Determines the next node based on the battle outcome.

## ChanceNode

Represents a node with random outcomes.

- **Methods:**
  - chooseNext(): Randomly selects the next node from possible outcomes.
  - checkNext(): Randomly checks the next node without advancing.

## DecisionNode

Represents a node where the player must make a choice.

- **Methods:**
  - chooseNext(): Determines the next node based on player's choice.

## TradeNode

Represents a node where the player can trade items.

- **Methods:**
  - tradeItem(Item item): Handles trading an item.
  - chooseNext(): Determines the next node after trading.

## TerminalNode

Represents an end point in the game.

- **Methods:**
  - chooseNext(): Returns the current node as the next node since it's the end.

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

## Utility

Provides a static Scanner instance for user input.

- **Methods:**
  - closeScanner(): Closes the Scanner instance.

# Character Classes

## Character

An abstract class representing the player character.

- **Subclasses:** Warrior, Assassin, Explorer, BaseCharacter
- **Methods:**
  - fight(int intakeDamage): Reduces the health of the character by the specified damage intake.
  - trade(Item item): Trades the specified item by adding it to the inventory.
  - getSpecificAttribute(): Gets the specific attribute of the character.
  - specificDamage(): Gets the specific damage of the character.

## Warrior

A subclass of Character with combat power-based attributes.

- **Methods:**
  - getCombatPower(): Gets the combat power of the warrior.
  - getSpecificAttribute(): Gets the combat power attribute.
  - specificDamage(): Gets the combat power value as specific damage.

## Assassin

A subclass of Character with agile damage-based attributes.

- **Methods:**
  - getSpecificAttribute(): Gets the agile damage attribute.
  - specificDamage(): Gets the agile damage value as specific damage.

## Explorer

A subclass of Character with intelligence-based attributes.

- **Methods:**
  - getSpecificAttribute(): Gets the intelligence attribute.
  - specificDamage(): Gets the intelligence value as specific damage.
