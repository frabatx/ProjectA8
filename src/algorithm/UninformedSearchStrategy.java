package algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import components.State;
import movements.Action;
import problem.Problem;
import problem.SpaceState;
import problem.Strategy;
import tree.Frontier;
import tree.Node;

public class UninformedSearchStrategy {

	public ArrayList<Node> search(Problem prob, Strategy strategy, int depthMax, int incDepth)
			throws CloneNotSupportedException {
		int currentDepth = incDepth;
		boolean solution = false;
		ArrayList<Node> nodeSolution = null;
		while (!solution && currentDepth <= depthMax) {
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
		while (!solution && !frontier.isEmpty()) {
			actualNode = frontier.removeFirst();
			if (prob.getSpaceState().isGoal(actualNode.getState())) {
				solution = true;
			} else {
				HashMap<Action, State> stateList = prob.getSpaceState().successor(actualNode.getState());
				ArrayList<Node> nodeList = Node.createNodesList(stateList, actualNode, prof_max, strategy);
				for (Node node : nodeList) {
					frontier.insert(node);

				}
			}
		}
		if (solution) {
			return createSolution(actualNode);
		} else {
			return null;
		}
	}

	private ArrayList<Node> createSolution(Node actualNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
