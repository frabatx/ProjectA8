package movements;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This class contains the method that generates all possible permutations 
 * of the sand starting from a point in the field
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class Partition {

    private static TreeSet<String> a = new TreeSet<>();

    /**
     * This method generate all possible permutation and possibility of a value in a
     * String
     * @param n
     * value of sand that can be moved (value in point - K)
     * @param k
     * number of posizions can be reached by tractor
     * @param prefix
     * spatial between values in output
     */
    private static void partition(int n, int k, String prefix) {
	if (k == 0 && n == 0) {
	    a.add(prefix);
	    return;
	}
	if (k < 0 || n < 0)
	    return;

	for (int i = n; i >= 0; --i) {
	    partition(n - i, k - 1, prefix + "" + i);
	}
    }

    /**
     * Returns an ArrayList containing all possible combinations of sand that the
     * tractor can move
     * 
     * @param numPoint
     * number of position that tractor can do
     * @param sand
     * quantity of sand tractor can move
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
