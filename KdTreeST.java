package kdTree;

import edu.princeton.cs.algs4.Point2D;


public final class KdTreeST<Value> {
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

        if (root == null) {
            root = new Node(p, val, new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                    Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), true);
            size++;
        }
        else {
            put(root, new Node(p, val));
        }
    }

    private Node put(Node prev, Node putNode) {
        if (prev == null)
            throw new NullPointerException("Node 'prev' in private put method is null");
        if(compareByAxis(prev, putNode) < 0) {
            putLeft(prev, putNode);
        } else if (prev.p.compareTo(putNode.p) == 0) {
            prev = new Node(putNode.p, putNode.value, calculateRect(prev, true), !prev.vertical);
        } else {
            putRight(prev, putNode);
        }

        return prev;
    }
    
    private double compareByAxis(Node prev, Node putNode) {
		if(prev.vertical == true) {
			return prev.p.x() - putNode.p.x();
		}
		else {
			return prev.p.y() - putNode.p.y();
		}
    }

    private Node putLeft(Node prev, Node putNode) {
        if(prev.lb == null) {
            prev.lb = new Node(putNode.p, putNode.value, calculateRect(prev, true), !prev.vertical);
            size++;
        }
        else {
            prev.lb = put(prev.lb, putNode);
        }

        return prev;
    }

    private Node putRight(Node prev, Node putNode) {
        if(prev.rt == null) {
            prev.rt = new Node(putNode.p, putNode.value, calculateRect(prev, false), !prev.vertical);
            size++;
        }
        else {
            prev.rt = put(prev.rt, putNode);
        }

        return prev;
    }

    private RectHV calculateRect(Node prev, boolean left) {
        //FIXME
        return null;
    }

    //get Value associated with point p
    public Value get(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        return get(root, p).value;
    }

    private Node get(Node current, Point2D p) {
        if (current == null || current.p.equals(p)) 
        	return current;
        else if (current.p.compareTo(p) < 0) {
        	return get(current.lb, p); }
        else
            return get(current.rt, p);
    }

    //Does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");

        return get(p) != null;
    }

    //Iterable of all points in the symbol table
    public Iterable<Point2D> points() {
        //TODO
        return null;
    }

    //all points that are inside the rectangle
    public Iterable<Point2D> range(edu.princeton.cs.algs4.RectHV rectHV) {
        if(rectHV == null)
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

    //FIXME calculate rectangles correctly
    private final class Node implements Comparable<Node> {
        final Point2D p;      // the point
        Value value;    // the symbol table maps the point to this value
        final RectHV rect;    // the axis-aligned rectangle corresponding to this node
        Node lb;        // the left/bottom subtree
        Node rt;        // the right/top subtree
        final boolean vertical; //is the node split vertically? if false, the node is split horizontally

        Node(Point2D p, Value value, RectHV rect, Node lb, Node rt, boolean vertical) {
            if(p == null || value == null)
                throw new NullPointerException("Node's cannot have null Point or Value fields");

            this.p = p;
            this.value = value;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.vertical = vertical;
        }

        Node(Point2D p, Value value, RectHV rect, boolean vertical) {
            this(p, value, rect, null, null, vertical);
        }

        Node(Point2D p, Value val) {
            this(p, val, null, null, null, false);
        }

        @Override
        public int compareTo(Node other) {
            if(vertical)
                return Point2D.X_ORDER.compare(this.p, other.p);
            else
                return Point2D.Y_ORDER.compare(this.p, other.p);
        }
    }
}