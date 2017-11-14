package test;

import java.util.ArrayList;

import components.State;
import problem.SpaceState;
import problem.Strategy;
import tree.Frontier;
import tree.Node;

public class testFrontier 
{
	public static void main(String[] args) throws Exception {
		Frontier frontier = new Frontier();
		frontier.createFrontier();
		State initialState = new State("Test.0.txt");
		Node initialNode = new Node (null, initialState, 0, 0, 0 );
		
		//creo una lista di nodi parenti a InitialNode
		
		ArrayList<Node> nodeList = Node.createNodesList(SpaceState.successor(initialNode.getState()), initialNode, 100, Strategy.UCS);
		
		for (Node node : nodeList) {
			frontier.insert(node);
			
		}
		int i=0;
		while(!frontier.isEmpty()) {
			System.out.println(i+frontier.removeFirst().toString());
			i++;
		}
	}
	
}
