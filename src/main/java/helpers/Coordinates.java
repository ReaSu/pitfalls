package helpers;

/***************************************
 * Created by regula on 11.04.18.
 ***************************************/
public class Coordinates {
	private int x;
	private int y;

	public Coordinates() {
	}

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Coordinates coordinates) {
		return coordinates.getX() == x && coordinates.getY() == y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
