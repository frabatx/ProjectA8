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
	private boolean optimization=false;
	private Hashtable<String, Integer> visited = new Hashtable<>();  
	
	public ArrayList<Node> search(Problem prob, Strategy strategy, int depthMax, int incDepth)
			throws CloneNotSupportedException {
		int currentDepth = incDepth;
		ArrayList<Node> nodeSolution = new ArrayList<Node>();
		while (nodeSolution.isEmpty() && currentDepth <= depthMax) {
			nodeSolution = limitedSearch(prob, strategy, currentDepth);
			currentDepth += incDepth;
		}

		return nodeSolution;
	}

	private ArrayList<Node> limitedSearch(Problem prob, Strategy strategy, int prof_max)
			throws CloneNotSupportedException {
		Frontier frontier = new Frontier();
		Node InitialNode = new Node(null, prob.getInitialState(), 0, 0, 0);
		frontier.createFrontier();
		frontier.insert(InitialNode);
		boolean solution = false;
		Node actualNode = null;
		ArrayList<Node> nodeList = new ArrayList<Node>();
		while (!solution && !frontier.isEmpty()) {
			actualNode = frontier.removeFirst();
			spatialComplexity++;
			if (prob.getSpaceState().isGoal(actualNode.getState())) {
				solution = true;
			} else {
				HashMap<Action, State> stateList = prob.getSpaceState().successor(actualNode.getState());
				nodeList = Node.createNodesList(stateList, actualNode, prof_max, strategy);

				for (Node node : nodeList) {
					frontier.insert(node);
				}
			}
		}
		if (solution) {
			return createSolution(actualNode);
		} else {
			return new ArrayList<Node>();
		}
	}

	private ArrayList<Node> createSolution(Node actualNode) {
		ArrayList<Node> solution = new ArrayList<Node>();
		solution.add(actualNode);
		Node node = actualNode.getParent();
		while (node != null) {
			solution.add(node);
			node = node.getParent();
		}
		Collections.sort(solution,new ReverseOrderComparator());
		return solution;
	}
	
	public long getSpatialComplexity() {
		return spatialComplexity;
	}
	public void setSpatialComplexity(long i) {
		spatialComplexity=i;
	}

	public void setOptimization(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
