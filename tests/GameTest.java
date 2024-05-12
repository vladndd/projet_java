package tests;

import core.Game;
import representation.DecisionNode;
import representation.Node;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {
    @Test
    public void testGameCreation() {
        Node startNode = new DecisionNode(1, "Start");
        Game game = new Game();
        assertNotNull(game);
    }
}
