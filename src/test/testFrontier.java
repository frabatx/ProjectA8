package test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import algorithm.UninformedSearchStrategy;
import components.Position;
import components.State;
import problem.Problem;
import problem.Strategy;
import tree.Node;

public class testFrontier {
	public static final int DEPTH_MAX = 20;
	public static final int INC_DEPTH = 1;

	public static void main(String[] args) throws Exception {
		File solutions = new File("Solutions.txt");
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(solutions)));
		State initialState = new State("Test.0.txt");
		Problem prob = new Problem(initialState);
		UninformedSearchStrategy uniformedAlgorithm = new UninformedSearchStrategy();

		for (Strategy s : Strategy.values()) {
			Scanner tastiera = new Scanner(System.in);
			
			String choice;
			/*do {
				System.out.println("Do you want to use optimization? (Y / N)");
				choice = (String)tastiera.nextLine();
			} while (!choice.equals("Y"));*/

			uniformedAlgorithm.setSpatialComplexity(0);
			double initialTime;
			double finalTime;

			initialTime = System.currentTimeMillis();
			if (true) {
				uniformedAlgorithm.setOptimization(true);
			}
			ArrayList<Node> nodeSolution = uniformedAlgorithm.search(prob, s, DEPTH_MAX, INC_DEPTH);
			finalTime = System.currentTimeMillis();
			int i = 0;
			out.println("////////////////////////////////////");
			out.println("Spatial Complexity: " + uniformedAlgorithm.getSpatialComplexity());
			out.println("Spatial Time: " + (finalTime - initialTime) + " MilliSecond");
			out.println(s.toString());
			for (Node node : nodeSolution) {
				out.println("Node n: " + i);
				out.println(node.getAction());
				int[][] matrix = node.getState().getMatrix();
				for (int j = 0; j < node.getState().getSizeRow(); j++) {
					for (int j2 = 0; j2 < node.getState().getSizeCol(); j2++) {
						if (node.getState().getTractor().getPosition().equals(new Position(j2, j))) {
							out.print(matrix[j2][j] + "*");
						} else {
							out.print(matrix[j2][j]);
						}
					}
					out.println();
				}
				i++;
			}
			nodeSolution.clear();
			tastiera.close();
		}
		out.close();
	}
}
