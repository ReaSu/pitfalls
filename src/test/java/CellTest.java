import game.Cell;
import helpers.CellProperty;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/***************************************
 * Created by regula on 15.04.18.
 ***************************************/
class CellTest {

	private Cell cell;

	@Test
	void cellCanBeInitiated() {
		Cell cell = new Cell();
		assertNotNull(cell);
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

}
