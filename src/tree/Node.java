package tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import components.Position;
import components.State;
import movements.Action;
import problem.Strategy;

/**
 * Node is the unique element of tree Frontier.
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class Node implements Comparable<Node>{

	private Node parent;
	private State state;
	private int cost;
	private int depth;
	private double value;
	private Action action;
	private Strategy strategy;
	
	//COSTRUCTOR
	/**
	 * A node is composed by : 
	 * Node parent,
	 * State,
	 * Cost,
	 * Value,
	 * Depth,
	 * Action,
	 * Strategy.
	 * 
	 * Strategy decides what kind of value to assign to "value"
	 * 
	 * @param parent
	 * @param state
	 * @param action
	 * @param s
	 * strategy
	 */
	public Node(Node parent, State state, Action action, Strategy s) {
		this.parent = parent;
		this.state = state;
		this.cost = parent.getCost()+action.getCost()+1;
		this.depth = parent.getDepth()+1;
		this.action = action;
		this.strategy = s;
		
		switch (s) {
		case BFS:
			value = depth;
			break;
		case DFS:
			value = -(depth);
			break;
		case DLS:
			value = -(depth);
			break;
		case IDS:
			value = -depth;
			break;
		case UCS:
			value = cost;
			break;
		case A:
			value = cost + state.getHeuristic(); 
			break;
		case Avariant:
		    	value = (cost*0.3) + (state.getHeuristic()*0.7);
		default:
			break;
		}
	}
	
	/**
	 * A node can be created starting from a single state
	 * @param state
	 */
	public Node(State state) {
			this.state=state;
		}
	// GETTER

	public Node getParent() {
		return parent;
	}

	public State getState() {
		return state;
	}

	public int getCost() {
		return cost;
	}

	public Action getAction() {
		return action;
	}

	public int getDepth() {
		return depth;
	}

	public double getValue() {
		return value;
	}

	// SETTER

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * The method return a list of nodes. The sort depends by strategy.
	 * 
	 * @param stateList
	 *            arrayList of states
	 * @param parentNode
	 *            the parent node of all new nodes
	 * @param max_depth
	 *            only for some strategy max depth is the limit of tree
	 * @param strategy
	 *            is the type of strategy that decide the type of order in tree
	 * @return list of nodes
	 */
	public static ArrayList<Node> createNodesList(HashMap<Action, State> stateList, Node parentNode, int max_depth, Strategy strategy) {
		
		ArrayList<Node> nodeList = new ArrayList<>();

		if ((parentNode.getDepth() + 1) > max_depth) 
			return nodeList;
		
		for (Action s : stateList.keySet()) {
			Node son = new Node(parentNode, stateList.get(s), s, strategy);
			nodeList.add(son);
		}
		return nodeList;
	}
	
	/**
	 * This method return an unique string that represents the node
	 * @return
	 * unique string 
	 * @throws CloneNotSupportedException
	 */
	public String getPrimaryKey() throws CloneNotSupportedException {
		String key="";
		for (int i = 0; i < this.state.getSizeCol(); i++) {
			for (int j = 0; j < this.state.getSizeRow(); j++) {
				key+=this.state.getValue(new Position(i,j));
			}
		}
		key+="?"+this.state.getTractor().getPosition().toString();
		return key;
	}
	
	/**
	 * Starting by strategy can be reached a value of node
	 * @param strategy
	 * @return
	 */
	public double getValueHash(Strategy strategy) {
		if(strategy == Strategy.BFS || strategy == Strategy.DFS || strategy == Strategy.DLS || strategy == Strategy.IDS)
			return cost;
		else 
			return value;
	}
	
	@Override
	public Node clone() throws CloneNotSupportedException {
		return new Node(this.parent, this.state.clone(), this.action, this.strategy);
	}
	
	/**
	 * This method return a comparator to revers order in solution
	 * @return
	 */
	public static Comparator<Node> getValueOrder(){
		return new ReverseOrderComparator();
	}

	@Override
	public int compareTo(Node n) {
		return ((Double) this.value).compareTo((Double) n.value);
	}

	@Override
	public String toString() {
		return "Node [cost=" + cost + ", depth=" + depth + ", value=" + value + "]";
	}

	
}
