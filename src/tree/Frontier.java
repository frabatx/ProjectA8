package tree;

import java.util.PriorityQueue;

/**
 * Frontier is a class that rappresents the tree of nodes, it is implemented by a PriorityQueue 
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class Frontier{

	private PriorityQueue<Node> frontier;

	/**
	 * Returns a PriorityQueue of nodes that make up Tree
	 * 
	 * @return
	 */
	public PriorityQueue<Node> getFrontier() {
		return frontier;
	}
	
	/**
	 * This method set frontier
	 * @param frontier
	 * PriorityQueue
	 */
	public void setFrontier(PriorityQueue<Node> frontier) {
		this.frontier = frontier;
	}

	/**
	 * This method create a new frontier
	 */
	public void createFrontier() {
		frontier = new PriorityQueue<Node>();
	}
	
	/**
	 * This method insert a new node in frontier
	 * @param newNode
	 */
	public void insert(Node newNode) {
		frontier.add(newNode);
	}

	/**
	 * This method remove first node by tree
	 * @return
	 * a node
	 */
	public Node removeFirst() {
		return frontier.poll();
	}
	
	/**
	 * This method return true if frontier is empty, else false.
	 * @return
	 * true if frontier is empty, else false
	 */
	public boolean isEmpty() {
		boolean c=frontier.isEmpty();
		return c;
	}

}
