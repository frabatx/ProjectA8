package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import components.State;
import movements.Action;
import problem.Strategy;

public class Node implements Comparable<Node> {

	private Node parent;
	private State state;
	private int cost;
	private Action action;
	private int depth;
	private int value;
	private Random random = new Random();

	public Node(Node parent, State state, int depth, int cost, int value) {
		super();
		this.parent = parent;
		this.state = state;
		this.cost = cost;
		this.depth = depth;
		this.value = value;
	}

	public Node(Node parent, State state, int depth, int cost) {
		super();
		this.parent = parent;
		this.state = state;
		this.cost = cost;
		this.depth = depth;
		this.value = random.nextInt(100) + 1;

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
	public static ArrayList<Node> createNodesList(HashMap<Action, State> stateList, Node parentNode, int max_depth,
			Strategy strategy) {

		ArrayList<Node> nodeList = new ArrayList<>();
		int depth = parentNode.getDepth() + 1;
		int cost = 0;
		int value = 0;

		switch (strategy) {
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
		default:
			break;
		}

		if((parentNode.getDepth()+1)>max_depth) {
			return nodeList;
		}
		for (Action s : stateList.keySet()) {
			Node son = new Node(parentNode, stateList.get(s), depth, cost, value);
			son.setAction(s); // setting action in the node
			son.setCost(son.getAction().getCost()+parentNode.getCost()); // setting cost by action in node

			if (strategy == Strategy.UCS) { // ucs is only strategy that value is egual to cost
				son.setValue(son.getAction().getCost());
			}
			nodeList.add(son);
		}
		return nodeList;
	}

	public Node clone() throws CloneNotSupportedException {
		return new Node(this.parent, this.state.clone(), this.depth, this.cost, this.value);
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
