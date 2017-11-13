package components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Tractor {

	private int MAX_POSITION;
	private static int MIN_POSITION = 0;

	private Position position;
	private SetPosition set;

	/**
	 * Tractor is the class that represents the object that creates the actions 
	 * within our state.
	 * It has the task of moving sand in the North, South, East, West directions
	 * and has the ability to move in the same direction.
	 */
	public Tractor() {
		this.set = new SetPosition();
	}

	public Tractor(int x, int y, int maxPosition) {
		this.position = new Position(x, y);
		this.MAX_POSITION = maxPosition;
		this.set = new SetPosition();
	}
	

	public Tractor(int mAX_POSITION, Position position, SetPosition set) {
		super();
		MAX_POSITION = mAX_POSITION;
		this.position = position;
		this.set = set;
	}

	// getter setter tractor position
	public void setPosition(Position p) {
		this.position = p;
	}

	public Position getPosition() {
		return this.position;
	}

	// set maxPosition
	public void setLimit(int maxPosition) {
		this.MAX_POSITION = maxPosition;
	}

	// possibility of movement
	private boolean isIn(Position next) {
		return (next.getX() < MAX_POSITION && next.getX() >= MIN_POSITION && next.getY() < MAX_POSITION
				&& next.getY() >= MIN_POSITION);
	}

	// movimento in qualunque direzione
	public ArrayList<Position> getMovements() {
		ArrayList<Position> movements = new ArrayList<>();

		for (Position p : set) {
			Position next = this.position.addCoordinates(p);
			if (this.isIn(next)) {
				movements.add(next);
			}
		}
		return movements;
	}

	// *********************************************
	public void printPossibility() {
		for (Position p : set) {
			System.out.println(p.toString());
		}
	}
	// *********************************************

	/*
	 * inner class to define set of movement of tractor
	 */

	@Override
	public String toString() {
		return "("+ position +")";
	}
	private class SetPosition implements Iterable<Position> {
		private Position north = new Position(0, 1);
		private Position south = new Position(0, -1);
		private Position east = new Position(1, 0);
		private Position west = new Position(-1, 0);

		private ArrayList<Position> set;

		/**
		 * SetPosition creates a set of movements that the tractor can perform.
		 * Allows the tractor to go only in the directions that are specified 
		 * within the costractor
		 */
		public SetPosition() {
			set = new ArrayList<>();
			set.add(north);
			set.add(south);
			set.add(east);
			set.add(west);
		}

		@Override
		public Iterator<Position> iterator() {
			ArrayList<Position> e = new ArrayList<>();
			e.addAll(set);
			Collections.sort(e);
			return e.iterator();
		}
	}
	
	public Tractor clone(){
		return new Tractor(MAX_POSITION, position.clone(), set);
	}

}
