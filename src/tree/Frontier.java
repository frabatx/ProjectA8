package tree;
/*
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Frontier implements Iterable<Node> {

	private LinkedList<Node> frontier;

	*//**
	 * Returns a LinkedList of nodes that make up Tree
	 * 
	 * @return
	 *//*
	public LinkedList<Node> getFrontier() {
		return frontier;
	}

	public void setFrontier(LinkedList<Node> frontier) {
		this.frontier = frontier;
	}

	public void createFrontier() {
		frontier = new LinkedList<Node>();
	}

	public void insert(Node newNode) {
		frontier.add(newNode);
	}

	public Node removeFirst() {
		return frontier.removeFirst();
	}

	public boolean isEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public Iterator<Node> iterator() {
		LinkedList<Node> l = new LinkedList<Node>();
		l.addAll(this.frontier);
		Collections.sort(l);
		return l.iterator();
	}

}
*/