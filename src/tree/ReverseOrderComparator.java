package tree;

import java.util.Comparator;

public class ReverseOrderComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		return (o1.getDepth()-o2.getDepth());
	}

}
