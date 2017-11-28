package test;

import java.util.ArrayList;

import algorithm.UninformedSearchStrategy;
import components.Position;
import components.State;
import problem.Problem;
import problem.Strategy;
import tree.Node;

public class testFrontier {
	public static final int DEPTH_MAX = 500;
	public static final int INC_DEPTH = 1;

	public static void main(String[] args) throws Exception {
		State initialState = new State("Test.0.txt");
		Problem prob = new Problem(initialState);
		UninformedSearchStrategy uniformedAlgorithm = new UninformedSearchStrategy();
		for (Strategy s : Strategy.values()) {
			ArrayList<Node> nodeSolution = uniformedAlgorithm.search(prob, s, DEPTH_MAX, INC_DEPTH);
			int i = 0;
			System.out.println("////////////////////////////////////");
			System.out.println(s);
			for (Node node : nodeSolution) {
				System.out.println(i + node.toString() + " " + node.getAction());
				int[][] matrix = node.getState().getMatrix();
				for (int j = 0; j < node.getState().getSizeRow(); j++) {
					for (int j2 = 0; j2 < node.getState().getSizeCol(); j2++) {
						if (node.getState().getTractor().getPosition().equals(new Position(j2, j))) {
							System.out.print(matrix[j2][j] + "*");
						} else {
							System.out.print(matrix[j2][j]);
						}

					}
					System.out.println();
				}
				System.out.println();
				i++;
			} 

		}

	}
} 
