package test;

import components.State;
import problem.SpaceState;
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
		
		for(State s: SpaceState.successor(initialState)) {
			Node parent = new Node(initialNode,s,0,0);
			frontier.insert(parent);
		}
		
		while(!frontier.isEmpty()) {
			System.out.println(frontier.removeFirst().getValue());
		}
	}
	
}
