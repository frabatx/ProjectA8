package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

import components.State;
import movements.Action;
import problem.Problem;
import problem.Strategy;
import tree.Frontier;
import tree.Node;
import tree.ReverseOrderComparator;

public class UninformedSearchStrategy {

	private long spatialComplexity;
	private boolean optimization = false;
	private Hashtable<String, Double> visited = new Hashtable<>();

	public ArrayList<Node> search(Problem prob, Strategy strategy, int depthMax, int incDepth)
			throws CloneNotSupportedException {
		int currentDepth = incDepth;
		ArrayList<Node> nodeSolution = new ArrayList<Node>();
		while (nodeSolution.isEmpty() && currentDepth <= depthMax) {
			this.spatialComplexity=0;
			nodeSolution = limitedSearch(prob, strategy, currentDepth);
			this.visited.clear();
			currentDepth += incDepth;
		}

		return nodeSolution;
	}

	private ArrayList<Node> limitedSearch(Problem prob, Strategy strategy, int prof_max) throws CloneNotSupportedException {
		Frontier frontier = new Frontier();
		
		Node InitialNode = new Node(prob.getInitialState());
		
		frontier.createFrontier();
		frontier.insert(InitialNode);
		boolean solution = false;
		Node actualNode = null;
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		while (!solution && !frontier.isEmpty()) {
			actualNode = frontier.removeFirst();
			if (prob.getSpaceState().isGoal(actualNode.getState())) {
				solution = true;
			} else {
				HashMap<Action, State> stateList = prob.getSpaceState().successor(actualNode.getState());
				nodeList = Node.createNodesList(stateList, actualNode, prof_max, strategy);
				spatialComplexity+=nodeList.size();
				for (Node node : nodeList) {
					if (optimization == true) {
						if (isVisited(node, strategy))
							frontier.insert(node);
					} else {
						frontier.insert(node);
					}
				}
			}
		}
		if (solution) {
			return createSolution(actualNode);
		} else {
			return new ArrayList<Node>();
		}
	}
	
	/**
	 * Method that check if the node is visited before or not yet.
	 * If is visited, it control his value and replace with previous node
	 * @param node the node to control
	 * @param strategy the strategy of search
	 * @return boolean value
	 * @throws CloneNotSupportedException
	 */
	private boolean isVisited(Node node, Strategy strategy) throws CloneNotSupportedException {
		String hash=node.getPrimaryKey();
		if(!visited.containsKey(hash)) {
			visited.put(hash, node.getValueHash(strategy));
			return true;
		}else {
			if(visited.get(hash) > node.getValueHash(strategy)) {
				visited.replace(hash, node.getValueHash(strategy));
				return true;
			}else {
				spatialComplexity--;
				return false;
			}
		}
	}

	
	/**
	 * Create Solution is a method that return the solution of frontier. From solution state to start state
	 * @param actualNode is solution node at the end of search
	 * @return
	 */
	private ArrayList<Node> createSolution(Node actualNode) {
		ArrayList<Node> solution = new ArrayList<Node>();
		solution.add(actualNode);
		Node node = actualNode.getParent();
		while (node != null) {
			solution.add(node);
			node = node.getParent();
		}
		Collections.sort(solution, new ReverseOrderComparator());
		return solution;
	}
	
	/**
	 * This method returns the spatial complexity of search
	 * @return
	 * spatial complexity
	 */
	public long getSpatialComplexity() {
		return spatialComplexity;
	}
	/**
	 * This method set spatial complexity
	 * @param i
	 * number of nodes
	 */
	public void setSpatialComplexity(long i) {
		spatialComplexity = i;
	}

	/**
	 * This method can set optimization
	 * @param b
	 * boolean value, true if optimization is activated, else false
	 */
	public void setOptimization(boolean b) {
		this.optimization=b;
	}
}
