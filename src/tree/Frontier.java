package tree;

import java.util.PriorityQueue;

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

	public void setFrontier(PriorityQueue<Node> frontier) {
		this.frontier = frontier;
	}

	public void createFrontier() {
		frontier = new PriorityQueue<Node>();
	}

	public void insert(Node newNode) {
		frontier.add(newNode);
	}

	public Node removeFirst() {
		return frontier.poll();
	}

	public boolean isEmpty() {
		boolean c=frontier.isEmpty();
		return c;
	}

	/*@Override
	public Iterator<Node> iterator() {
		ArrayList<Node> a = new ArrayList<Node>();
		a.addAll(frontier);
		Collections.sort(a,new ReverseOrderComparator());
		return a.iterator();
	}*/


}
