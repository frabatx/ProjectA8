package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

import components.Position;
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
			
		/*	Scanner tastiera = new Scanner(System.in);
			System.out.println("Padre" + actualNode.toString() + " " + actualNode.getAction());
			
			int[][] matrix = actualNode.getState().getMatrix();
			for (int j = 0; j < actualNode.getState().getSizeRow(); j++) {
				for (int j2 = 0; j2 < actualNode.getState().getSizeCol(); j2++) {
					if (actualNode.getState().getTractor().getPosition().equals(new Position(j2, j))) {
						System.out.print(matrix[j2][j] + "*");
					} else {
						System.out.print(matrix[j2][j]);
					}

				}
				System.out.println();
			}
			int c = tastiera.nextInt();*/
			if (prob.getSpaceState().isGoal(actualNode.getState())) {
				solution = true;
			} else {
				HashMap<Action, State> stateList = prob.getSpaceState().successor(actualNode.getState());
				nodeList = Node.createNodesList(stateList, actualNode, prof_max, strategy);
			/*	if(nodeList.isEmpty()) {
					System.out.println("Non mi sposto");
				}
				for (Node node : nodeList) {
					System.out.println("Figlio" + node.toString() + " " + node.getAction());
					int[][] matrix2 = node.getState().getMatrix();
					for (int j = 0; j < node.getState().getSizeRow(); j++) {
						for (int j2 = 0; j2 < node.getState().getSizeCol(); j2++) {
							if (node.getState().getTractor().getPosition().equals(new Position(j2, j))) {
								System.out.print(matrix2[j2][j] + "*");
							} else {
								System.out.print(matrix2[j2][j]);
							}

						}
						System.out.println();
					}
					System.out.println();
				}
				c = tastiera.nextInt();*/
				
				
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
		return solution;
	}

}
