package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import univers.base.Assassin;
import univers.base.Item;
import univers.base.Planet;
import univers.base.Race;

import static org.junit.jupiter.api.Assertions.*;

class AssassinTest {
    private Assassin assassin;
    private Race mockRace;
    private Planet mockPlanet;
    private Item mockItem;

    @BeforeEach
    void setUp() {
        mockRace = Race.Jovian; // Assuming a constructor like Race(name, healthBonus, forceBonus)
        mockPlanet = new Planet("Mercury", "Very hot planet with no atmosphere."); // Assuming a constructor like
                                                                                   // Planet(name)
        assassin = new Assassin("Vega", 100, 10, mockRace, 20, mockPlanet);
        mockItem = new Item("Dagger", 2, 10, 1, 50, 1);
    }

    @Test
    void testInteract() {
        assassin.interact();
        // Check the output or behavior if specific implementations are required
    }

    @Test
    void testSpeak() {
        assassin.speak();
        // Check the output or behavior if specific implementations are required
    }

    @Test
    void testFight() {
        assassin.fight(10);
        assertEquals(105, assassin.getHealth());
    }

    @Test
    void testTrade() {
        assassin.trade(mockItem);
        assertNotNull(assassin.getInventory());
        assertTrue(assassin.getInventory().length > 0);
        assertEquals("Name: Dagger, Attack: 50, Price: 2, Health: 1, Quantity: 1, Weight: 10",
                assassin.getInventory()[0]);
    }

    @Test
    void testSpecificAttribute() {
        String expected = "Agile Damage: 20";
        assertEquals(expected, assassin.getSpecificAttribute());
    }

    @Test
    void testSpecificDamage() {
        assertEquals(20, assassin.specificDamage());
    }

    @Test
    void testCharacterType() {
        assertEquals("Assassin", assassin.getCharacterType());
    }
}