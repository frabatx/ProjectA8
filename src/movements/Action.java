package movements;

import java.util.HashMap;
import java.util.Random;

import components.Position;

public class Action {

	private Position nextPosition;
	private HashMap<Position, Integer> sandMovement;
	Random random = new Random();
	private int cost=random.nextInt(100)+1;
	/**
	 * Action is the action of the Tractor on the Field.
	 * It is represented by the next position of the Tractor and the displacement of the sand on 
	 * the possible positions it can take
	 * 
	 * @param next
	 */
	public Action(Position next) {
		this.nextPosition = next;
		this.sandMovement = new HashMap<>();
	}
	
	public Position getNextPosition() {
		return nextPosition;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public void setNextPosition(Position nextPosition) {
		this.nextPosition = nextPosition;
	}
	
	public HashMap<Position, Integer> getSandMovement() {
		return sandMovement;
	}
	
	public void setSandMovement(HashMap<Position, Integer> sandMovement) {
		this.sandMovement = sandMovement;
	}
	
	public void addElement(Position p, Integer i) {
		this.sandMovement.put(p, i);
	}
	
	public String toString() {
		String ref="";
		ref+="("+this.nextPosition+") [";
		
		for(Position p:sandMovement.keySet()) {
			ref+=sandMovement.get(p).toString()+p.toString()+",";
		}
		ref+=this.cost+"]";
		return ref;
	}
}
