package test;

/*import components.Tractor;
import tree.Frontier;
import java.util.ArrayList;
import java.util.LinkedList;*/
import java.io.FileNotFoundException;
import java.util.ArrayList;

import components.State;
import movements.Action;

public class Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		//Prova
		
		State stato = new State("Test.0.txt");
		
		try {
			stato.printFild("Generato.0.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		int i=0;
		ArrayList<Action> listaAzioni= stato.generateActions();
		for(Action a:listaAzioni) {
			//non aggiorna stato
			State s=stato.newState(stato, a);
			try {
				s.printFild("Successore."+i+".txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			i++;
		}
		
	}

	/*
	 * public static void main(String[] args) { State stato = new
	 * State("Test.0.txt"); Successor successor = new Successor(stato); Frontier
	 * frontier = new Frontier(); frontier.createFrontier(); ArrayList<Node>
	 * sucNodes = null; try { sucNodes = successor.newSuccessors(); } catch
	 * (CloneNotSupportedException e) { 
	 * e.printStackTrace(); } // TIME 1 for (Node node : sucNodes) {
	 * frontier.insert(node); } //TIME 2 }
	 */
}
