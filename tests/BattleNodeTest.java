package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Game;
import representation.BattleNode;
import representation.InnerNode;
import representation.Node;
import univers.Race;

import static org.junit.jupiter.api.Assertions.*;

public class BattleNodeTest {
    private StubGame game;
    private StubCharacter character;
    private BattleNode battleNode;

    @BeforeEach
    public void setUp() {
        game = new StubGame();
        character = new StubCharacter("Jhon", 100, 50, Race.Human, Game.PLANETS_LIST.get(0));

        game.setCurrentCharacter(character);

        battleNode = new BattleNode(1, "A fierce battle", "Dragon", 100, 10, game);
        battleNode.addChanceOption(new InnerNode(2, "Victory"));
        battleNode.addChanceOption(new InnerNode(3, "Defeat"));
        battleNode.addChanceOption(new InnerNode(4, "Escape"));
    }

    @Test
    public void testWinScenario() {
        character.setHealth(50);
        character.setForce(120); // More than enemy health

        Node result = battleNode.chooseNext();
        assertEquals(2, result.getId()); // Assuming you can retrieve ID or modify Node class accordingly
    }

    @Test
    public void testLoseScenario() {
        character.setHealth(5); // Less than enemy attack
        character.setForce(50); // Less than enemy health

        Node result = battleNode.chooseNext();
        assertEquals(3, result.getId());
    }

    @Test
    public void testEscapeScenario() {
        character.setHealth(30);
        character.setForce(50); // Less than enemy health but survives attack

        Node result = battleNode.chooseNext();
        assertEquals(4, result.getId());
    }

    @Test
    public void testUnsupportedOperationExceptionForCheckNext() {
        assertThrows(UnsupportedOperationException.class, () -> battleNode.checkNext());
    }
}