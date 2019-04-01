package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;

import java.util.TreeMap;

public class PointST<Value> {
    private TreeMap<Point2D, Value> tree;

    // construct an empty symbol table of points
    public PointST() {
        tree = new TreeMap<>();
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    // number of points
    public int size() {
        return tree.size();
    }

    //associate Value val with point p
    public void put(Point2D p, Value val) {
        if(p == null || val == null)
            throw new NullPointerException("Arguments cannot be null");

        tree.put(p, val);
    }

    //get Value associated with point p
    public Value get(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        return tree.get(p);
    }

    //Does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        return tree.containsKey(p);
    }

    //Iterable of all points in the symbol table
    public Iterable<Point2D> points() {
        return tree.navigableKeySet();
    }

    //all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        var hits = new Queue<Point2D>();

        for(var point : tree.keySet())
            if (rect.contains(point))
                hits.enqueue(point);

        return hits;
    }

    //a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        var nearest = tree.firstKey();

        for(var point : tree.keySet())
            if(p.distanceSquaredTo(point) < p.distanceSquaredTo(nearest))
                nearest = p;

        return nearest;
    }

    //for testing (ungraded)
    public static void main(String[] args) {
        //TODO
    }
}
