package kdTree;

import edu.princeton.cs.algs4.Point2D;

public class test {
	public static void main(String args[]) {
		KdTreeST<String> test = new KdTreeST<>();
		
		
		
		test.put(new Point2D(5,5), "First Node");
		test.put(new Point2D(5,4), "second");
		test.put(new Point2D(5,6), "third");
		test.put(new Point2D(4,6), "fourth");
		
		System.out.println(test.get(new Point2D(4,6)));
	}
}
