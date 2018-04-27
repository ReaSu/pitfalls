import game.Board;
import helpers.CellProperty;
import helpers.Coordinates;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/***************************************
 * Created by regula on 11.04.18.
 ***************************************/
class BoardTest {

	private Board board;

	@Test
	void boardCanBeInitiatedWithThreParams() {
		board = new Board(10, 10, 3);
		assertNotNull(board);
		assertThat(board.getHeight(), is(10));
		assertThat(board.getWidth(), is(10));
		assertThat(board.getNumberOfPits(), is(3));
	}

	@Test
	void parametersAreSetCorrectly() {
		board = new Board(30, 16, 99);
		assertThat(board.getHeight(), is(16));
		assertThat(board.getWidth(), is(30));
		assertThat(board.getNumberOfPits(), is(99));
	}

	@Test
	void cellsCanBeAddedToBoard() {
		board = new Board(10, 10, 3);
		assertThat(board.getCells().size(), is(100));
	}

	@Test
	void boardHasSixCells() {
		board = new Board(2, 3, 1);
		assertThat(board.getCells().size(), is(6));
	}

	@Test
	void boardHasEightCells() {
		board = new Board(4, 2, 1);
		assertThat(board.getCells().size(), is(8));
	}

	@Test
	void boardGivesPropertyOfCellAtOneOneAsPit() {
		board = new Board(2, 2, 1);
		board.getCellAt(new Coordinates(1, 1)).setProperty("pit");
		assertThat(board.getPropertyAt(new Coordinates(1, 1)), is(CellProperty.PIT));
	}

	@Test
	void boardGivesPropertyOfCellAtOneOneAsEmpty() {
		board = new Board(2, 2, 1);
		board.getCellAt(new Coordinates(1, 1)).setProperty("0");
		assertThat(board.getPropertyAt(new Coordinates(1, 1)), is(CellProperty.EMPTY));
	}

	@Test
	void boardIsInitialisedWithFourCells() {
		board = new Board(2, 2, 1);
		assertNotNull(board.getCellAt(new Coordinates(1, 1)));
		assertNotNull(board.getCellAt(new Coordinates(1, 2)));
		assertNotNull(board.getCellAt(new Coordinates(2, 1)));
		assertNotNull(board.getCellAt(new Coordinates(2, 2)));
	}

	@Test
	void boardIsInitialisedWith100Cells() {
		board = new Board(10, 10, 1);
		assertThat(board.getCells().size(), is(100));
	}

	@Test
	void boardIsInitialisedWithOnePit() {
		board = new Board(1, 1, 1);
		assertThat(board.getNumberOfPits(), is(1));
	}

	@Test
	void singleCellIsPit() {
		board = new Board(1, 1, 1);
		assertThat(board.getPropertyAt(new Coordinates(1, 1)), is(CellProperty.PIT));
	}

	@Test
	void bothCellsArePits() {
		board = new Board(1, 2, 2);
		assertThat(board.getPropertyAt(new Coordinates(1, 1)), is(CellProperty.PIT));
		assertThat(board.getPropertyAt(new Coordinates(1, 2)), is(CellProperty.PIT));
	}

	@Test
	void twoCellsArePits() {
		board = new Board(2, 2, 2);
		assertThat(getPitcount(2, 2), is(2));
	}

	private int getPitcount(int width, int height) {
		int pitcount = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (board.getPropertyAt(new Coordinates(j + 1, i + 1)) == CellProperty.PIT) {
					pitcount++;
				}
			}
		}
		return pitcount;
	}

	@Test
	void ninetyninePitsOnBoard() {
		board = new Board(30, 16, 99);
		assertThat(getPitcount(30, 16), is(99));
	}

	@Test
	void singleCellHasNoNeighbours() {
		board = new Board(1, 1, 0);
		assertThat(board.getNeighboursOf(new Coordinates(1, 1)).size(), is(0));
	}

	@Test
	void oneCellOfTwoHasOneNeighbour() {
		board = new Board(1, 2, 0);
		assertThat(board.getNeighboursOf(new Coordinates(1, 1)).size(), is(1));
	}

	@Test
	void middleCellOfNineHasEightNeighbours() {
		board = new Board(3, 3, 0);
		assertThat(board.getNeighboursOf(new Coordinates(2, 2)).size(), is(8));
	}

	@Test
	void bottomMiddleCellOfNineHasFiveNeighbours() {
		board = new Board(3, 3, 0);
		assertThat(board.getNeighboursOf(new Coordinates(2, 3)).size(), is(5));
	}

	@Test
	void cornerCellOfNineHasThreeNeighbours() {
		board = new Board(3, 3, 0);
		assertThat(board.getNeighboursOf(new Coordinates(3, 3)).size(), is(3));
	}

	@Test
	void cellHasOneNeighbouringPit() {
		board = new Board(2, 1, 1);
		board.getCellAt(new Coordinates(1, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(2, 1)).setProperty("0");
		assertThat(board.getNumberOfAdjacentPits(new Coordinates(2, 1)), is(1));
	}

	@Test
	void cellHasTwoNeighbouringPits() {
		board = new Board(2, 2, 2);
		board.getCellAt(new Coordinates(1, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(1, 2)).setProperty("pit");
		board.getCellAt(new Coordinates(2, 1)).setProperty("0");
		board.getCellAt(new Coordinates(2, 2)).setProperty("0");
		assertThat(board.getNumberOfAdjacentPits(new Coordinates(2, 1)), is(2));
	}

	@Test
	void cellHasEightNeighbouringPits() {
		board = new Board(3, 3, 8);
		board.getCellAt(new Coordinates(1, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(1, 2)).setProperty("pit");
		board.getCellAt(new Coordinates(1, 3)).setProperty("pit");
		board.getCellAt(new Coordinates(2, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(2, 2)).setProperty("0");
		board.getCellAt(new Coordinates(2, 3)).setProperty("pit");
		board.getCellAt(new Coordinates(3, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(3, 2)).setProperty("pit");
		board.getCellAt(new Coordinates(3, 3)).setProperty("pit");
		assertThat(board.getNumberOfAdjacentPits(new Coordinates(2, 2)), is(8));
	}

	@Test
	void cellHasFourNeighbouringPits() {
		board = new Board(3, 3, 8);
		board.getCellAt(new Coordinates(1, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(1, 2)).setProperty("0");
		board.getCellAt(new Coordinates(1, 3)).setProperty("pit");
		board.getCellAt(new Coordinates(2, 1)).setProperty("0");
		board.getCellAt(new Coordinates(2, 2)).setProperty("0");
		board.getCellAt(new Coordinates(2, 3)).setProperty("");
		board.getCellAt(new Coordinates(3, 1)).setProperty("pit");
		board.getCellAt(new Coordinates(3, 2)).setProperty("0");
		board.getCellAt(new Coordinates(3, 3)).setProperty("pit");
		assertThat(board.getNumberOfAdjacentPits(new Coordinates(2, 2)), is(4));
	}

	@Test
	void cellHasNoNeighbouringPits() {
		board = new Board(3, 3, 0);
		assertThat(board.getNumberOfAdjacentPits(new Coordinates(2, 2)), is(0));
	}

}
