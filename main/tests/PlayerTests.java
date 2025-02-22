package main.tests;
import main.Game;
import main.Player;
import main.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    private Player player;

    @BeforeEach
    public void setUp(){
        player = new Player("JDoe", 6, 4, 3, 2);
    }

    @Test
    public void testPlayer(){
        assertNotNull(player);

        assertEquals("JDoe", player.getUsername());
        assertEquals( 6, player.getNumberOfBulls());
        assertEquals( 4, player.getNumberOfCows());
        assertEquals(3, player.getCodesAttempted());
        assertEquals(2, player.getCodesDeciphered());
    }

    @Test
    public void testIncrementAttempts(){
        assertEquals(3, player.getCodesAttempted());
        player.incrementCodesAttempted();
        assertEquals(4, player.getCodesAttempted());
    }

    @Test
    public void testIncrementDeciphered(){
        assertEquals(2, player.getCodesDeciphered());
        player.incrementCodesDeciphered();
        player.incrementCodesDeciphered();
        assertEquals(4, player.getCodesDeciphered());
    }

    @Test
    public void testFindPlayer_Exists() {
        Players players = new Players();
        Player player = new Player("TestPlayer");
        players.addPlayer(player);
        Player foundPlayer = players.findPlayer("TestPlayer");
        assertNotNull(foundPlayer);
        assertEquals("TestPlayer", foundPlayer.getUsername());
    }

    @Test
    public void testFindPlayer_NotExist() {
        Players players = new Players();
        Player foundPlayer = players.findPlayer("NonExistentPlayer");
        assertNotNull(foundPlayer);
        assertEquals("NonExistentPlayer", foundPlayer.getUsername());
    }

    @Test
    public void testAddPlayer() {
        Players players = new Players();
        Player player = new Player("TestPlayer");
        players.addPlayer(player);
        assertTrue(players.getPlayers().contains(player));
    }

    @Test
    public void testSetNumberOfCows() {
        Player player = new Player("TestPlayer");
        player.setNumberOfCows(3);
        assertEquals(3, player.getNumberOfCows());
    }

    @Test
    public void testToString() {
        Player player = new Player("TestPlayer");
        assertEquals("TestPlayer", player.toString());
    }

    @Test
    public void testGetStats() {
        Player currentPlayer = new Player("Alice", 2, 2, 5, 3);
        Game game = new Game(new Players(), currentPlayer);
        String stats = game.loadPlayer().getStats();
        String expectedStats = String.format(
                """
                Number of Codes Attempted   :   %d
                Number of Codes Deciphered  :   %d
                Percentage of Success       :   %.2f%%
                """,
                currentPlayer.getCodesAttempted(), currentPlayer.getCodesDeciphered(), (double) currentPlayer.getCodesDeciphered() / currentPlayer.getCodesAttempted() * 100
        );

        assertEquals(expectedStats, stats);
    }
}
