package game;

import helpers.*;

/***************************************
 * Created by regula on 11.04.18.
 ***************************************/
public class Cell {
	private CellProperty property;
	private Coordinates coordinates = new Coordinates();
	private CellStatus status;

	public Cell() {
		setCoordinates(1, 1);
		setProperty("0");
	}

	private void setCoordinates(int x, int y) {
		coordinates.set(x, y);
	}

	public Cell(int x, int y) {
		setCoordinates(x, y);
		setProperty("0");
	}

	public CellProperty getProperty() {
		return property;
	}

	public void setProperty(String property) {
		if (property.equals("pit")) {
			this.property = CellProperty.PIT;
		} else {
			this.property = CellProperty.EMPTY;
		}

	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	CellStatus getStatus() {
		return status;
	}

	void setStatus(String status) {
		this.status = CellStatus.OPEN;

	}
}
