import game.Board;
import helpers.Coordinates;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/***************************************
 * Created by regula on 27.04.18.
 ***************************************/
class LogicTest {
	private Board board;

	@Test
	void openCellWithPitOpensOneCell() {
		board = new Board(3, 3, 1);
		forceCell(1, 1, "pit");
		forceCell(1, 2, "0");
		forceCell(1, 3, "0");
		forceCell(2, 1, "0");
		forceCell(2, 2, "0");
		forceCell(2, 3, "0");
		forceCell(3, 1, "0");
		forceCell(3, 2, "0");
		forceCell(3, 3, "0");
		board.open(new Coordinates(1, 1));
		assertThat(board.countNumberOfOpenCells(), is(1));
	}

	private void forceCell(int x, int y, String pit) {
		board.getCellAt(new Coordinates(x, y)).setProperty(pit);
	}

	@Test
	void openCellNextToPitOpensOneCell() {
		board = new Board(3, 3, 1);
		forceCell(1, 1, "pit");
		forceCell(1, 2, "0");
		forceCell(1, 3, "0");
		forceCell(2, 1, "0");
		forceCell(2, 2, "0");
		forceCell(2, 3, "0");
		forceCell(3, 1, "0");
		forceCell(3, 2, "0");
		forceCell(3, 3, "0");
		board.open(new Coordinates(1, 2));
		assertThat(board.countNumberOfOpenCells(), is(1));
	}

	@Test
	void openCellOppositeOfPitOpensSevenCells() {
		board = new Board(3, 3, 1);
		forceCell(1, 1, "pit");
		forceCell(1, 2, "0");
		forceCell(1, 3, "0");
		forceCell(2, 1, "0");
		forceCell(2, 2, "0");
		forceCell(2, 3, "0");
		forceCell(3, 1, "0");
		forceCell(3, 2, "0");
		forceCell(3, 3, "0");
		board.open(new Coordinates(3, 3));
		assertThat(board.countNumberOfOpenCells(), is(7));
	}

	@Test
	void openingCellShouldOpen4Cells() {
		board = new Board(3, 3, 2);
		forceCell(1, 1, "pit");
		forceCell(1, 2, "0");
		forceCell(1, 3, "0");
		forceCell(2, 1, "0");
		forceCell(2, 2, "0");
		forceCell(2, 3, "pit");
		forceCell(3, 1, "0");
		forceCell(3, 2, "0");
		forceCell(3, 3, "0");
		board.open(new Coordinates(3, 1));
		assertThat(board.countNumberOfOpenCells(), is(4));
	}

/*	@Test
	public void cellWithNoNeighbouringPitsChecksNextCells() {
		board = new Board(3, 3, 1);
		board.getCellAt(1, 1).setProperty("pit");
		board.getCellAt(1, 2).setProperty("0");
		board.getCellAt(1, 3).setProperty("0");
		board.getCellAt(2, 1).setProperty("0");
		board.getCellAt(2, 2).setProperty("0");
		board.getCellAt(2, 3).setProperty("0");
		board.getCellAt(3, 1).setProperty("0");
		board.getCellAt(3, 2).setProperty("0");
		board.getCellAt(3, 3).setProperty("0");
		assertThat(board.)
	} */
}
