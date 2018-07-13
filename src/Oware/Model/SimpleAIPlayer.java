package Oware.Model;

import java.util.Random;

/**
 * A simple AI player.
 *
 * @author tyteen4a03
 */
public class SimpleAIPlayer extends Player {
	/**
	 * Makes a move.
	 *
	 * @return int The house to use.
	 */
	public int makeMove() {
		while (true) {
			int next = ((new Random()).nextInt(6)) + 6;
			if (board.getBoardSeeds()[next] == 0) {
				// This board has no seeds, continue
				continue;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return next;
		}
	}
}
