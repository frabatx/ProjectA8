package movements;

import java.util.HashMap;
import components.Position;

/**
 * Action is the class that represents an action performed by the tractor in State
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class Action {

	private Position nextPosition;
	private HashMap<Position, Integer> sandMovement;
	public int cost=0;
	
	//COSTRUCTOR
	/**
	 * Action is the action of the Tractor on the Field.
	 * It is represented by the next position of the Tractor and the displacement of the sand on 
	 * the possible positions it can take
	 * 
	 * @param next
	 * a next position of tractor
	 */
	public Action(Position next) {
		this.nextPosition = next;
		this.sandMovement = new HashMap<>();
	}
	
	//GETTER
	/**
	 * This method return next position of tractor
	 * @return
	 * next position
	 */
	public Position getNextPosition() {
		return nextPosition;
	}
	
	/**
	 * This method return the cost of an action. 
	 * The value is the resuld of sum between old cost and sand that have to be moved by action 
	 * @return
	 * cost of action
	 */
	public void setCost() {
		for (Position p : sandMovement.keySet()) {
			this.cost+=sandMovement.get(p);
		}
		this.cost++;
	}
	
	public int getCost() {
	    return this.cost;
	}
	
	/**
	 * This method return an HashMap of Position and Integer. 
	 * Position is composed by all possible next position thet tractor can reach.
	 * Integer are value of sand can be moved in each position  
	 * @return
	 */
	public HashMap<Position, Integer> getSandMovement() {
		return sandMovement;
	}
	
	//SETTER
	/**
	 * This method update a new nex position.
	 * @param nextPosition
	 */
	public void setNextPosition(Position nextPosition) {
		this.nextPosition = nextPosition;
	}
	
	/**
	 * This method set new HashMap of sand that can be moved by tractor
	 * @param sandMovement
	 * new HashMap
	 */
	public void setSandMovement(HashMap<Position, Integer> sandMovement) {
		this.sandMovement = sandMovement;
	}
	
	//OTHER
	/**
	 * This method add a new element in HashMap of Position and Integer
	 * @param p
	 * Position
	 * @param i
	 * value of sand
	 */
	public void addElement(Position p, Integer i) {
		this.sandMovement.put(p, i);
	}
	
	@Override
	public String toString() {
		String ref="";
		ref+="("+this.nextPosition+") [";
		
		for(Position p:sandMovement.keySet()) {
			ref+=sandMovement.get(p).toString()+p.toString()+",";
		}
		ref+=this.cost+"]";
		return ref;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((nextPosition == null) ? 0 : nextPosition.hashCode());
		result = prime * result + ((sandMovement == null) ? 0 : sandMovement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Action))
			return false;
		Action other = (Action) obj;
		if (cost != other.cost)
			return false;
		if (nextPosition == null) {
			if (other.nextPosition != null)
				return false;
		} else if (!nextPosition.equals(other.nextPosition))
			return false;
		if (sandMovement == null) {
			if (other.sandMovement != null)
				return false;
		} else if (!sandMovement.equals(other.sandMovement))
			return false;
		return true;
	}
}
