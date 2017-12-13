package components;

/**
 * Position combines the coordinates within Field and allows easy access to them
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class Position implements Comparable<Position> {
    private int y;
    private int x;

    // COSTRUCTOR
    /**
     * It implements two integers (y, x) that represent columns and rows of the
     * matrix
     * 
     * @param y
     * represents the columns of the matrix
     * @param x
     * represents the rows of the matrix
     */
    public Position(int y, int x) {
	this.y = y;
	this.x = x;
    }

    // GETTER
    /**
     * @return the coordinate x of Position
     */
    public int getX() {
	return this.x;
    }

    /**
     * @return the coordinate y of Position
     */
    public int getY() {
	return this.y;
    }

    // SETTER
    /**
     * The method sets parameter x with an integer that is passed to it
     * 
     * @param x
     */
    public void setX(int x) {
	this.x = x;
    }

    /**
     * the method sets parameter y with an integer that is passed to it
     * 
     * @param y
     */
    public void setY(int y) {
	this.y = y;
    }

    // OTHER
    /**
     * The method returns a new point which is the result of the sum between the old
     * point and another that is passed to it as a parameter
     * 
     * @param p
     * a new coordinate
     * @return a new Position
     */
    public Position addCoordinates(Position p) {
	return new Position(this.getY() + p.getY(), this.getX() + p.getX());
    }

    /**
     * This method create a clone, copy of point
     * 
     * @return 
     * a copy of Point
     */
    public Position clone() {
	return new Position(y, x);
    }

    @Override
    public boolean equals(Object o) {
	if (o == null || !(o instanceof Position))
	    return false;
	if (o instanceof Position) {
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

}
