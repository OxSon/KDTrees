/**
 * Creates a Symbol Table using Point2D as keys
 * 
 * @author Alec Mills & Kenneth Salguero
 */

package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;

import java.util.TreeMap;

public class PointST<Value> {
    private TreeMap<Point2D, Value> tree;

    /**
     * construct an empty symbol table of points
     */
    public PointST() {
        tree = new TreeMap<>();
    }

    /**
     * is the symbol table empty?
     * @return true if tree is empty
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * number of points
     * @return size of the tree
     */
    public int size() {
        return tree.size();
    }

    /**
     * associate Value val with point p
     * @param p
     * @param val
     */
    public void put(Point2D p, Value val) {
        if(p == null || val == null)
            throw new NullPointerException("Arguments cannot be null");

        tree.put(p, val);
    }

    /**
     * get Value associated with point p
     * @param p
     * @return value of a given key
     */
    public Value get(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        return tree.get(p);
    }

    /**
     * Does the symbol table contain point p?
     * @param p
     * @return returns true if point is in tree
     */
    public boolean contains(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        return tree.containsKey(p);
    }
    
    /**
     * Iterable of all points in the symbol table
     * @return an iterator of points in tree
     */
    public Iterable<Point2D> points() {
        return tree.navigableKeySet();
    }

    /**
     * all points that are inside the rectangle
     * @param rectHV
     * @return iterator every point in range of rectHV
     */
    public Iterable<Point2D> range(RectHV rectHV) {
        Queue<Point2D> hits = new Queue<Point2D>();

        for(Point2D point : tree.keySet())
            if (rectHV.contains(point))
                hits.enqueue(point);

        return hits;
    }

    /**
     * a nearest neighbor to point p; null if the symbol table is empty
     * @param p
     * @return nearest point in tree to p
     */
    public Point2D nearest(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");
        System.out.println(p);
        Point2D nearest = tree.firstKey();

        for(Point2D point : tree.keySet()) 
        	if(p.distanceSquaredTo(point) < p.distanceSquaredTo(nearest)) 
                nearest = point;
        return nearest;
    }

    //for testing (ungraded)
    public static void main(String[] args) {
        //TODO
    }
}
