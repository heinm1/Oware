import Oware.Model.Board;
import Oware.Model.House;
import Oware.Model.HumanPlayer;
import Oware.Model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoardTest {
	Player player1;
	Player player2;

	@Before
	public void setUp() {
		player1 = new HumanPlayer();
		player2 = new HumanPlayer();
	}

	@Test
	public void testMakeMove() {
		Board board = new Board();
		board.setPlayer1(player1);
		board.setPlayer2(player2);
		board.assignHouses();
		board.makeMove(1, board.getPlayer1());
		House[] houses = board.getBoard();
		assertEquals(0, houses[1].getSeeds());
	}

	@Test
	public void testNumberOfSeeds() {
		Board board = new Board();
		board.setPlayer1(player1);
		board.setPlayer2(player2);
		board.assignHouses();
		int[] seeds = board.getBoardSeeds();
		int totalSeeds = 0;
		for (int seed : seeds) {
			totalSeeds += seed;
		}
		assertEquals(totalSeeds, 48);
	}
}



