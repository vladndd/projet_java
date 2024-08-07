package utility;

import univers.Planet;
import core.Game;

import javax.swing.JButton;

/**
 * GameUIutilities class provides utility methods for updating game state and
 * creating UI components.
 */
public class GameUIutilities {

    /**
     * Updates the current planet in the game based on the specified node ID.
     *
     * @param game   The Game instance.
     * @param nodeId The node ID to determine the planet update.
     */
    static public void updatePlanet(Game game, int nodeId) {
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

    /**
     * Creates a JButton for character selection based on the specified option ID.
     *
     * @param optionId The option ID to determine the character selection button.
     * @return A JButton configured for character selection.
     */
    static public JButton createCharacterSelectionButton(int optionId) {
        JButton button = null;
        switch (optionId) {
            case 100:
                button = new JButton("Choose Warrior");
                button.setActionCommand("select_warrior");
                break;
            case 101:
                button = new JButton("Choose Assassin");
                button.setActionCommand("select_assassin");
                break;
            case 102:
                button = new JButton("Choose Explorer");
                button.setActionCommand("select_explorer");
                break;
        }
        return button;
    }

    /**
     * Checks if the specified option ID corresponds to the character's home planet.
     *
     * @param game     The Game instance.
     * @param optionId The option ID to check.
     * @return True if the option ID corresponds to the home planet, false
     *         otherwise.
     */
    static public boolean isHomePlanet(Game game, int optionId) {
        String currentPlanet = game.getCurrentCharacter().getStartPlanetName();
        switch (optionId) {
            case 5:
                return currentPlanet.equals("Mercury");
            case 6:
                return currentPlanet.equals("Venus");
            case 7:
                return currentPlanet.equals("Earth");
            case 8:
                return currentPlanet.equals("Mars");
            case 9:
                return currentPlanet.equals("Jupiter");
            case 10:
                return currentPlanet.equals("Saturn");
            case 11:
                return currentPlanet.equals("Uranus");
            case 12:
                return currentPlanet.equals("Neptune");
            case 13:
                return currentPlanet.equals("Pluto");
            default:
                return false;
        }
    }
}
