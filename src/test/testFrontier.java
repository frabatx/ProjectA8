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
	static int maxDepth;
	static int incDepth;

	public static void main(String[] args) throws Exception {

		State initialState = new State("Test.0.txt");
		Problem prob = new Problem(initialState);
		UninformedSearchStrategy uniformedAlgorithm = new UninformedSearchStrategy();
		File solutions = new File("Solution.txt");
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(solutions)));
		Strategy s = null;

		Scanner tastiera = new Scanner(System.in);
		while (s == null) {
			System.out.println("MENU");
			System.out.println("***********************************");
			System.out.println("*1) BFS");
			System.out.println("*2) DFS");
			System.out.println("*3) DLS");
			System.out.println("*4) IDS");
			System.out.println("*5) UCS");
			System.out.println("*6) A*");
			System.out.println("***********************************");
			System.out.println("Choose the strategy");
			String choice = tastiera.next();
			System.out.println("Choose max depth: ");
			maxDepth = tastiera.nextInt();
			incDepth = maxDepth;
			
			switch (choice) {
			case "1":
				s = Strategy.BFS;
				break;
			case "2":
				s = Strategy.DFS;
				break;
			case "3":
				s = Strategy.DLS;
				break;
			case "4":
				s = Strategy.IDS;
				System.out.println("Insert Increment Depth: ");
				incDepth = tastiera.nextInt();
				break;
			case "5":
				s = Strategy.UCS;
				break;
			case "6":
				s = Strategy.A;
				break;
			default:
				break;
			}
		}
		System.out.println("Do you want optimization?");
		String opt = tastiera.next();
		boolean optim = (opt.equals("Y") || opt.equals("YES") || opt.equals("Yes") || opt.equals("yes") || opt.equals("y")) ? true : false;
		tastiera.close();

		double initialTime;
		double finalTime;
		//set spatial complexity and optimization
		uniformedAlgorithm.setSpatialComplexity(0);
		uniformedAlgorithm.setOptimization(optim);

		initialTime = System.currentTimeMillis();
		ArrayList<Node> nodeSolution = uniformedAlgorithm.search(prob, s, maxDepth, incDepth);
		finalTime = System.currentTimeMillis();

		out.println("////////////////////////////////////");
		out.println("Spatial Complexity: " + uniformedAlgorithm.getSpatialComplexity());
		out.println("Spatial Time: " + (finalTime - initialTime) + " ms");
		out.println(s.toString());
		out.println();

		System.out.println("Spatial Complexity: " + uniformedAlgorithm.getSpatialComplexity());
		System.out.println("Spatial Time: " + (finalTime - initialTime) + " ms");
		System.out.println(s.toString());
		System.out.println();

		int i = 0;
		for (Node node : nodeSolution) {
			out.println("Node n: " + i + " Action: " + node.getAction());
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
		out.close();
	}
}
