 package movements;

import java.util.ArrayList;
import java.util.TreeSet;

public class Partition {

	private static TreeSet<String> a = new TreeSet<>();

	private static void partition(int n, int k, String prefix) {
        if (k == 0 && n == 0) {
            a.add(prefix);
            return;
        }
        if (k < 0 || n < 0) return;

        for (int i = n; i >= 0; --i) {
            partition(n-i, k-1, prefix + "" + i);
        }
    }

	
	/**
	 * Returns an ArrayList containing all possible combinations of sand that the tractor can move
	 * 
	 * @param numPoint number of position that tractor can do
	 * @param sand quantity of sand tractor can move
	 * @return ArrayList of string that rappresented possible sand moving by tractor 
	 */
	public static ArrayList<String> generationSand(int sand, int numPoint) {
		partition(sand, numPoint, "");
		ArrayList<String> b = new ArrayList<>();
		b.addAll(a);
		Partition.a.clear();
		return b;
	}
	
}
