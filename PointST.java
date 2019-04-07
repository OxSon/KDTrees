
package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;

import java.util.TreeMap;

/**
 * Creates a generic Symbol Table using Point2D as keys.
 *
 * @param <Value> type parameter for values in symbol table
 * @author Alec Mills
 * @author Kenneth Salguero
 */
public class PointST<Value> {
    private TreeMap<Point2D, Value> tree;

    /**
     * Construct an empty symbol table of points.
     */
    public PointST() {
        tree = new TreeMap<>();
    }

    /**
     * Is the symbol table empty?
     *
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * Number of points in tree.
     *
     * @return size of the tree
     */
    public int size() {
        return tree.size();
    }

    /**
     * Associate Value 'val' with point 'p'.
     *
     * @param p   used as key for symbol table
     * @param val value associated with key 'p'
     */
    public void put(Point2D p, Value val) {
        if (p == null || val == null)
            throw new NullPointerException("Arguments cannot be null");

        tree.put(p, val);
    }

    /**
     * Get value associated with point 'p'.
     *
     * @param p key to get value associated with
     * @return value of a given key
     */
    public Value get(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        return tree.get(p);
    }

    /**
     * Does the symbol table contain point 'p'?
     *
     * @param p the key to check for
     * @return returns true if point is in tree, false otherwise
     */
    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        return tree.containsKey(p);
    }

    /**
     * Iterable of all points in the symbol table.
     *
     * @return an Iterable of points in tree
     */
    public Iterable<Point2D> points() {
        return tree.navigableKeySet();
    }

    /**
     * All points that are inside the rectangle queried.
     *
     * @param rectHV the query rectangle
     * @return Iterable every point in range of rectHV
     */
    public Iterable<Point2D> range(RectHV rectHV) {
        Queue<Point2D> hits = new Queue<Point2D>();

        for (Point2D point : tree.keySet())
            if (rectHV.contains(point))
                hits.enqueue(point);

        return hits;
    }

    /**
     * A nearest neighbor to point p; null if the symbol table is emptA
     *
     * @param p the query point
     * @return a nearest point in tree to p
     */
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");
        System.out.println(p);
        Point2D nearest = tree.firstKey();

        for (Point2D point : tree.keySet())
            if (p.distanceSquaredTo(point) < p.distanceSquaredTo(nearest))
                nearest = point;
        return nearest;
    }

    //for testing (ungraded)
    public static void main(String[] args) {
        //TODO
    }
}
