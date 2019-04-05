package kdTree;

import edu.princeton.cs.algs4.Point2D;

public class test {
	public static void main(String args[]) {
		KdTreeST<String> test = new KdTreeST<>();
		
		
		
		test.put(new Point2D(5,5), "First Node");
		test.put(new Point2D(5,4), "second");
		test.put(new Point2D(5,6), "third");
		test.put(new Point2D(5,6), "replace");
		test.put(new Point2D(4,3), "fifth");
		
<<<<<<< HEAD
		System.out.println(test.get(new Point2D(4,6)));
		System.out.println(test.range(new RectHV(5, 3, 10, 10)));
		
=======
		
		System.out.println(test.get(new Point2D(4,3)));
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
	}
}
