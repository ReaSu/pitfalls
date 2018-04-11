import game.Board;
import game.Cell;
import helpers.CellProperty;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/***************************************
 * Created by regula on 11.04.18.
 ***************************************/
class gameTest {

	private Board board;
	private Cell cell;

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
	void cellCanBeInitiated() {
		Cell cell = new Cell();
		assertNotNull(cell);
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
	void cellHasCoordinatesOneOne() {
		cell = new Cell(1, 1);
		assertThat(cell.getCoordinates().getX(), is(1));
		assertThat(cell.getCoordinates().getY(), is(1));
	}

	@Test
	void cellHasCoordinatesOneTwo() {
		cell = new Cell(1, 2);
		assertThat(cell.getCoordinates().getX(), is(1));
		assertThat(cell.getCoordinates().getY(), is(2));
	}

	@Test
	void cellReturnsPitAsProperty() {
		cell = new Cell();
		cell.setProperty("pit");
		assertThat(cell.getProperty(), is(CellProperty.PIT));
	}

	@Test
	void cellReturnsEmptyAsProperty() {
		cell = new Cell();
		cell.setProperty("0");
		assertThat(cell.getProperty(), is(CellProperty.EMPTY));
	}

	@Test
	void boardGivesPropertyOfCellAtOneOneAsPit() {
		board = new Board(2, 2, 1);
		board.getCellAt(1, 1).setProperty("pit");
		assertThat(board.getPropertyAt(1, 1), is(CellProperty.PIT));
	}

	@Test
	void boardGivesPropertyOfCellAtOneOneAsEmpty() {
		board = new Board(2, 2, 1);
		board.getCellAt(1, 1).setProperty("0");
		assertThat(board.getPropertyAt(1, 1), is(CellProperty.EMPTY));
	}

	@Test
	void boardIsInitialisedWithFourCells() {
		board = new Board(2, 2, 1);
		assertNotNull(board.getCellAt(1, 1));
		assertNotNull(board.getCellAt(1, 2));
		assertNotNull(board.getCellAt(2, 1));
		assertNotNull(board.getCellAt(2, 2));
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
		assertThat(board.getPropertyAt(1, 1), is(CellProperty.PIT));
	}

	@Test
	void bothCellsArePits() {
		board = new Board(1, 2, 2);
		assertThat(board.getPropertyAt(1, 1), is(CellProperty.PIT));
		assertThat(board.getPropertyAt(1, 2), is(CellProperty.PIT));
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
				if (board.getPropertyAt(j + 1, i + 1) == CellProperty.PIT) {
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
		assertThat(board.getNeighboursOf(1, 1).size(), is(0));
	}

	@Test
	void oneCellOfTwoHasOneNeighbour() {
		board = new Board(1, 2, 0);
		assertThat(board.getNeighboursOf(1, 1).size(), is(1));
	}

	@Test
	void middleCellOfNineHasEightNeighbours() {
		board = new Board(3, 3, 0);
		assertThat(board.getNeighboursOf(2, 2).size(), is(8));
	}

	@Test
	void bottomMiddleCellOfNineHasFiveNeighbours() {
		board = new Board(3, 3, 0);
		assertThat(board.getNeighboursOf(2, 3).size(), is(5));
	}

	@Test
	void cornerCellOfNineHasThreeNeighbours() {
		board = new Board(3, 3, 0);
		assertThat(board.getNeighboursOf(3, 3).size(), is(3));
	}
}
