package game;

import helpers.*;

import java.util.*;

/***************************************
 * Created by regula on 11.04.18.
 ***************************************/
public class Board {

	private final int width;
	private final int height;
	private final int numberOfPits;
	private List<Cell> cells = new LinkedList<Cell>();

	public Board(int width, int height, int pits) {
		this.width = width;
		this.height = height;
		this.numberOfPits = pits;
		initialiseBoard(width, height);
		Set<Integer> randomNumbers = getRandomNumbers();
		setPits(randomNumbers);
	}

	private void initialiseBoard(int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				add(new Cell(j + 1, i + 1));
			}
		}
	}

	private Set<Integer> getRandomNumbers() {
		Set<Integer> randomNumbers = new HashSet<Integer>();
		while (randomNumbers.size() < numberOfPits) {
			randomNumbers.add((int) (Math.random() * width * height));
		}
		return randomNumbers;
	}

	private void setPits(Set<Integer> randomNumbers) {
		for (int num : randomNumbers) {
			cells.get(num).setProperty("pit");
		}
	}

	private void add(Cell cell) {
		cells.add(cell);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumberOfPits() {
		return numberOfPits;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public CellProperty getPropertyAt(Coordinates coordinates) {
		Cell cell = getCellAt(coordinates);
		return cell.getProperty();
	}

	public Cell getCellAt(Coordinates coordinates) {
		Cell targetCell = null;
		for (Cell cell : cells) {
			if (cell.getCoordinates().equals(coordinates)) {
				targetCell = cell;
				break;
			}
		}
		return targetCell;
	}

	public List<Cell> getNeighboursOf(Coordinates coordinates) {
		List<Cell> neighbours = new ArrayList<Cell>();
		//top row
		int x = coordinates.getX();
		int y = coordinates.getY();
		if (y > 1) {
			if (x > 1) {
				neighbours.add(getCellAt(new Coordinates(x - 1, y - 1)));
			}
			neighbours.add(getCellAt(new Coordinates(x, y - 1)));
			if (x < width) {
				neighbours.add(getCellAt(new Coordinates(x + 1, y - 1)));
			}
		}
		//middle row
		if (x > 1) {
			neighbours.add(getCellAt(new Coordinates(x - 1, y)));
		}
		if (x < width) {
			neighbours.add(getCellAt(new Coordinates(x + 1, y)));
		}
		//bottom row
		if (y < height) {
			if (x > 1) {
				neighbours.add(getCellAt(new Coordinates(x - 1, y + 1)));
			}
			neighbours.add(getCellAt(new Coordinates(x, y + 1)));
			if (x < width) {
				neighbours.add(getCellAt(new Coordinates(x + 1, y + 1)));
			}
		}
		return neighbours;
	}

	public int getNumberOfAdjacentPits(Coordinates coordinates) {
		short count = 0;
		for (Cell nb : getNeighboursOf(coordinates)) {
			if (nb.getProperty() == CellProperty.PIT) {
				count++;
			}
		}
		return count;
	}

	public void open(Coordinates coordinates) {
		System.out.println("opening " + coordinates.getX() + ", " + coordinates.getY());
		getCellAt(coordinates).setStatus("open");
		if (getNumberOfAdjacentPits(coordinates) == 0 && getCellAt(coordinates).getProperty() != CellProperty.PIT) {
			for (Cell nb : getNeighboursOf(coordinates)) {
				System.out.println("checking " + nb.getCoordinates().toString());
				if ((nb.getStatus() == CellStatus.OPEN)) {
					break;
				}
				open(nb.getCoordinates());
			}
		}
	}

	public int countNumberOfOpenCells() {
		int numberOfOpenCells = 0;
		for (Cell cell : getCells()) {
			if (cell.getStatus() == CellStatus.OPEN) {
				numberOfOpenCells++;
			}
		}
		return numberOfOpenCells;
	}
}
