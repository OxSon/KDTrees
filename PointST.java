package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value> {
	RedBlackBST<Point2D, Value> table = new RedBlackBST<>();
	
	
	// construct an empty symbol table of points
    public PointST() {
        table = new RedBlackBST<>();
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return table.isEmpty();
    }

    // number of points
    public int size() {
        return table.size();
    }

    //associate Value val with point p
    public void put(Point2D p, Value val) {
    	table.put(p, val);
    }

    //get Value associated with point p
    public Value get(Point2D p) {
        return table.get(p);
    }

    //Does the symbol table contain point p?
    public boolean contains(Point2D p) {
        return table.contains(p);
    }

    //Iterable of all points in the symbol table
    public Iterable<Point2D> points() {
        return table.keys();
    }

    //all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> pointQueue = new Queue<>(); 
    	for(Point2D el : points()) {
        	if(rect.contains(el)) pointQueue.enqueue(el);
        }
        return pointQueue;
    }

    //a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (table.isEmpty()) return null;
        Point2D closest = null;
        
        for(Point2D el : points()) {
        	// adds first element to list
        	if(closest == null && !el.equals(p)) closest = el;
        	
        	// compares el with current closest Point2D
        	// el distance to p is less closest to p closest get assign value of el
        	if(!el.equals(p)) {
        		if(p.distanceTo(el) < p.distanceTo(closest)) closest = el;
        	}
        }
        
        return closest;
    }

    //for testing (ungraded)
    public static void main(String[] args) {
    	PointST<String> test = new PointST<>();
    	
    	test.put(new Point2D(1,1), "");
    	test.put(new Point2D(2,1), "");
    	test.put(new Point2D(3,2), "");
    	test.put(new Point2D(7,8), "test closest");
    	test.put(new Point2D(7,9), "should be closest");
    	test.put(new Point2D(11,12), "");
    	test.put(new Point2D(3,4), "");
    	test.put(new Point2D(4,4), "");
    	
    	System.out.println(test.contains(new Point2D(7,10)));
    	System.out.println(test.get(new Point2D(7,8)));
    	System.out.println(test.points());
    	System.out.print(test.nearest(new Point2D(11,12)));
    	System.out.println(test.range(new RectHV(5, 5, 12, 12)));
    }
}
