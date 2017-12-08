package tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import components.Position;
import components.State;
import movements.Action;
import problem.Strategy;

public class Node implements Comparable<Node>{

	private Node parent;
	private State state;
	private int cost;
	private int depth;
	private int value;
	private Action action;
	private Strategy strategy;
	
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
		default:
			break;
		}
	}
	
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

	public int getValue() {
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

	public Integer getValueHash(Strategy strategy) {
		if(strategy == Strategy.BFS || strategy == Strategy.DFS || strategy == Strategy.DLS || strategy == Strategy.IDS)
			return cost;
		else 
			return value;
	}

	public Node clone() throws CloneNotSupportedException {
		return new Node(this.parent, this.state.clone(), this.action, this.strategy);
	}
	
	public static Comparator<Node> getValueOrder(){
		return new ReverseOrderComparator();
	}
	

	@Override
	public int compareTo(Node n) {
		return ((Integer) this.value).compareTo((Integer) n.value);
	}

	@Override
	public String toString() {
		return "Node [cost=" + cost + ", depth=" + depth + ", value=" + value + "]";
	}

	
}
