package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;

import java.util.TreeMap;

public class KdTreeST<Value> {
    private Node root;
    private int size;

    // construct an empty symbol table of points
    public KdTreeST() {
        //TODO
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points
    public int size() {
        return size;
    }

    //associate Value val with point p
    public void put(Point2D p, Value val) {
        if(p == null || val == null)
            throw new NullPointerException("Arguments cannot be null");
        //TODO

        if (root == null)
            root = new Node(p, val, new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                    Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), true, 1);
        else {
            //TODO calculate rectangle

        }
        size++;
    }

    private Node put(Node prev, Node current, Point2D p, Value val) {
        if (current == null) {
            //FIXME do putting
            return new Node(p, val, null, null, null, !prev.vertical, 1);

        } else {
            if (current.vertical) {
                //compare X values
                if (Point2D.X_ORDER.compare(current.p, p) < 0) {
                    put(current, current.lb, p, val);
                } else {
                    put(current, current.rt, p, val);
                }
            } else {
                //compare Y values
                if (Point2D.Y_ORDER.compare(current.p, p) < 0) {
                    put(current, current.lb, p, val);
                } else {
                    put(current, current.rt, p, val);
                }
            }
        }
    }

    //get Value associated with point p
    public Value get(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        //TODO
        return null;
    }

    //Does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

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
        if(rect == null)
            throw new NullPointerException("Arguments cannot be null");

        //TODO
        return null;
    }

    //a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");


        //TODO
        return null;
    }

    private class Node {
        Point2D p;      // the point
        Value value;    // the symbol table maps the point to this value
        RectHV rect;    // the axis-aligned rectangle corresponding to this node
        Node lb;        // the left/bottom subtree
        Node rt;        // the right/top subtree
        int size;       // number of nodes in subtree
        boolean vertical; //is the node split vertically? if false, the node is split horizontally

        Node(Point2D p, Value value, RectHV rect, Node lb, Node rt, boolean vertical, int size) {
            if(p == null || value == null)
                throw new NullPointerException("Node's cannot have null Point or Value fields");

            this.p = p;
            this.value = value;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.vertical = vertical;
            this.size = size;
        }

        Node(Point2D p, Value value, RectHV rect, boolean vertical, int size) {
            this(p, value, rect, null, null, vertical, size);
        }

        Node(Point2D p, Value value, boolean vertical, int size) {
            this(p, value, null, null, null, vertical, size);
        }
    }
}