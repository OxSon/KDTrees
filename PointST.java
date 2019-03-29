package kdTree;

import edu.princeton.cs.algs4.Point2D;

public class PointST<Value> {
    // construct an empty symbol table of points
    public PointST() {
        //TODO
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        //TODO
        return false;
    }

    // number of points
    public int size() {
        //TODO
        return 0;
    }

    //associate Value val with point p
    public void put(Point2D p, Value val) {
        //TODO
    }

    //get Value associated with point p
    public Value get(Point2D p) {
        //TODO
        return null;
    }

    //Does the symbol table contain point p?
    public boolean contains(Point2D p) {
        //TODO
        return false;
    }

    //Iterable of all points in the symbol table
    public Iterable<Point2D> points() {
        //TODO
        return null;
    }

    //all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        //TODO
        return null;
    }

    //a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        //TODO
        return null;
    }

    //for testing (ungraded)
    public static void main(String[] args) {
        //TODO
    }
}
