package cs567;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class EnumerationScheme {

	/**
	 * Given a vertex a, return the next vertex of a preorder enumeration
	 * 
	 * Each element of the enumeration (i.e. vertex) is represented
	 * as a java.util.List of Integers. The root of the enumeration tree
	 * is represented as the empty list (a List of size 0), and each prefix node
	 * i.e. internal node has a size equal to the length of the prefix, where a.size()-1
	 * is the right-most value of the prefix. The left most index is 0.
	 * 
	 * If a is the last element in the enumeration, then the first element is returned.
	 * 
	 * @param a The vertex from which the next vertex is to be found
	 * @param L The total possible number of digits each vertex can have
	 * @param k A List of size L, where k.get(i) is the total possible number of values the ith digit can have
	 * @return the next vertex
	 */
	public static List<Integer> nextVertex(List<Integer> a, int L, List<Integer> k){
		int level=L;
		for (int i=0;i<L;i++ ) {
			if (a.get(i)==0) {
				level=i; //0-L levels
				break;
			}			
		}
		if (level<L) {
			a.set(level,1);
			return a;
		}else {
			for (int j=L-1; j>-1;j-- ) {
				if (a.get(j)<k.get(j)) {
					a.set(j,a.get(j)+1);
					return a;
				}
				a.set(j,0);
			}
			return a;
		}
	}
     /**
     * @param a The vertex from which the next vertex is to be found
	 * @param L The total possible number of digits each vertex can have
	 * @param k A List of size L, where k.get(i) is the total possible number of values the ith digit can have
	 * @param  i the level
	 * @return the next vertex
	 */

	public static List<Integer> byPass(List<Integer> a, int L, List<Integer> k,int i){	
		for (int j=i-1; j>-1;j-- ) {
			if (a.get(j)<k.get(j)) {
				a.set(j,a.get(j)+1);
				return a;
			}
				a.set(j,0);
		}
		return a;
		
	}
	
}
