package components;

public class Position implements Comparable<Position>  {
	private int y;
	private int x;

	/*
	 *Position combines the coordinates within Field and allows easy access to them.
	 *It implements two integers (y, x) that represent columns and rows of the matrix
	 * @param y
	 * @param x
	 */
	public Position(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	// return new point, sum the coordinates in the argument
	public Position addCoordinates(Position p) {
		return new Position(this.getY() + p.getY(),this.getX() + p.getX());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Position))
			return false;
		if(o instanceof Position) {
			Position p = (Position) o;
			return (this.getX() == p.getX()) && (this.getY() == p.getY());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public int compareTo(Position p) {
		return ((Integer) p.getX()).compareTo((Integer) this.getX());
	}

	@Override
	public String toString() {
		return "(" + y + "," + x + ")";
	}
	
	
	
	public Position clone() {
		return new Position(y, x);
	}
}
