# Space Adventure Game

## Description

Space Adventure is an interactive "choose your own adventure" game set in a sprawling universe where decisions and random events determine the outcome of the story. Players navigate through a series of decision nodes, chance nodes, and terminal nodes which collectively shape the narrative and conclusion of the game.

## Features

- **Dynamic Story Paths:** Each choice leads to a new path, with different outcomes based on decisions and chance events.
- **Character Customization:** Players can choose their character's name, race, and starting planet, each affecting the game's progression.
- **Multimedia Enhancements:** The game includes sound and image decorations at certain nodes to enhance player immersion.
- **Persistent Game State:** Players can save their game state to a file and load it later to continue their adventure.

## Prerequisites

Before you start playing the game, ensure you have Java installed on your machine. The game is compatible with Java 11 and above.

## Setup

1. Clone the repository to your local machine or download the ZIP file and extract it.
2. Navigate to the directory containing the game files.

## How to Play

1. Start the game by running the `Main` class.
2. Follow the on-screen prompts to create your character and begin your adventure.
3. Make choices at decision points to determine the direction of your story.
4. Save your game at any point to continue later.

## Game Structure

- **Nodes:** The game uses various types of nodes to construct the gameplay:
  - **DecisionNode:** Where the player must make a choice.
  - **ChanceNode:** Where the outcome is determined by a random event.
  - **TerminalNode:** Marks the conclusion of the game path.
  - **InnerNode:** Represents a non-terminal node that connects other nodes.
- **Game:** The game class manages the
- **Character:** The character class stores characters information.
- **Planet:** Each planet has unique characteristics that influence the game.
- **Event:** Represents a random event that can occur at chance nodes.
- **BaseCharacter:** An abstract class that defines the base character attributes.
- **Explorer:** A subclass of BaseCharacter that represents the explorer character.
- **Warior:** A subclass of BaseCharacter that represents the warrior character.
- **Interact:** A utility interface that describes user input.
- **Race:** An enum class that defines the races

## Development

This project was developed using Java. It utilizes object-oriented principles such as inheritance, polymorphism, and encapsulation to manage the game logic and structure.
