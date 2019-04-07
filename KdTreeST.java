package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates a generic symbol table that implements a 2D KD-Tree with Point2D as key
 *
 * @param <Value> type parameter for values in symbol table
 * @author Alec Mills
 * @author Kenneth Salguero
 */
public final class KdTreeST<Value> {
    private Node root;
    private int size;

    /**
     * Construct an empty symbol table of point.
     */
    public KdTreeST() {
    }

    /**
     * Is the symbol table empty?
     *
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Number of key-value association-pairs in symbol table.
     *
     * @return size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Associate Value 'val' with point 'p'.
     *
     * @param p   the key to associate with 'val'
     * @param val the value to associate with key 'p'
     */
    public void put(Point2D p, Value val) {
        if (p == null || val == null)
            throw new NullPointerException("Arguments cannot be null");

        root = put(null, root, p, val);
    }


    /**
     * Get Value associated with point 'p'.
     *
     * @param p the key to get the value associated with
     * @return value associated with key 'p'
     */
    public Value get(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        Node result = get(root, p);
        return result == null ? null : result.value;
    }


    /**
     * Does the symbol table contain point 'p'?
     *
     * @param p the key to check for
     * @return true if point is in tree, false otherwise
     */
    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        return get(p) != null;
    }

    /**
     * Iterable of all points in the symbol table in level order.
     *
     * @return iterable of all points in tree
     */
    public Iterable<Point2D> points() {
        Node temp = root; //node we are currently processing
        Queue<Point2D> levelOrder = new Queue<>(); //stores processed points in final level order
        Queue<Node> tempQ = new Queue<>(); //holds nodes to be processed

        while (temp != null) {
            //add the node we're processing's point to our final level order
            levelOrder.enqueue(temp.p);

            //add its children, if they exist
            if (temp.lb != null)
                tempQ.enqueue(temp.lb);
            if (temp.rt != null)
                tempQ.enqueue(temp.rt);

            //move to next node to be processed, set temp to null to indicate we're finished
            temp = tempQ.isEmpty() ? null : tempQ.dequeue();
        }

        return levelOrder;
    }

    /**
     * All points that are inside the query rectangle.
     *
     * @param queryRect query rectangle
     * @return points inside range of query rectangle
     */
    public Iterable<Point2D> range(RectHV queryRect) {
        if (queryRect == null)
            throw new NullPointerException("Arguments cannot be null");

        Queue<Point2D> nodesInRange = new Queue<>();
        check(nodesInRange, root, queryRect);

        return nodesInRange;
    }

    /**
     * A nearest neighbor to point p; null if the symbol table is empty.
     *
     * @param p the key to query for
     * @return a Point2D in tree nearest to p, arbitrary among possible candidates if more than one point are 'nearest'
     */
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        Node champion = root;
        champion = nearestNode(p, champion, root);

        return champion == null ? null : champion.p;
    }


    private Node get(Node current, Point2D p) {
        if (current == null || current.p.equals(p))
            return current;

        else if (compareByAxis(p, current) < 0) {
            return get(current.lb, p);
        } else
            return get(current.rt, p);
    }

    private void check(Queue<Point2D> q, Node node, RectHV queryRect) {
        if (node != null) {
            if (queryRect.contains(node.p))
                q.enqueue(node.p);

            for (Node child : getIntersections(node, queryRect))
                check(q, child, queryRect);
        }
    }

    private Queue<Node> getIntersections(Node node, RectHV rect) {
        Queue<Node> intersections = new Queue<>();

        if (node.lb != null) {
            if (rect.intersects(node.rect))
                intersections.enqueue(node.lb);
        }
        if (node.rt != null) {
            if (rect.intersects(node.rect))
                intersections.enqueue(node.rt);
        }

        return intersections;
    }

    private Node nearestNode(Point2D p, Node champion, Node current) {
        if (pathIsViable(current, champion, p)) {
            if (current.p.distanceSquaredTo(p) < champion.p.distanceSquaredTo(p))
                champion = current;

            for (Node path : choosePath(current, p))
                champion = nearestNode(p, champion, path);
        }

        return champion;
    }

    private List<Node> choosePath(Node current, Point2D queryPoint) {
        List<Node> nodesToVisit = new ArrayList<>();
        nodesToVisit.add(current.lb);
        nodesToVisit.add(current.rt);

        if (current.rt != null && current.rt.rect.contains(queryPoint))
            Collections.reverse(nodesToVisit);

        return nodesToVisit;
    }

    private boolean pathIsViable(Node path, Node champion, Point2D queryPoint) {
        if (path == null)
            return false;

        double distance = path.rect.distanceSquaredTo(queryPoint);

        if (distance == 0)
            return true;
        else return distance < champion.p.distanceSquaredTo(queryPoint);

    }

    private RectHV rootRectangle() {
        return new RectHV(
                Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY
        );
    }

    private Node put(Node prev, Node current, Point2D p, Value val) {
        if (current == null) { //base case
            size++;
            return new Node(p, val, prev);
        }

        //we are updating a value of an already present key
        if (current.p.equals(p)) {
            current.value = val;
            return current;
        }

        //we are adding a new key and still need to traverse the tree
        if (compareByAxis(p, current) < 0)
            current.lb = put(current, current.lb, p, val);
        else
            current.rt = put(current, current.rt, p, val);

        return current;
    }

    /**
     * Compares a node to a point relative to appropriate axis (x | y).
     * Used to determine which way to go in the tree.
     *
     * @param node  node we are processing
     * @param other point to compare current node to
     * @return negative, zero, or positive number as other point is lesser, equal, or greater relative to appropriate axis.
     */
    private int compareByAxis(Point2D other, Node node) {
        if (node.vertical)
            return Point2D.X_ORDER.compare(node.p, other);
        else
            return Point2D.Y_ORDER.compare(node.p, other);
    }

    /**
     * Calculates rectangle for a given node based on its parent and its associated point.
     *
     * @param parent parent node of node for which we are calculating the rectangle
     * @param p      point associated with node for which we are calculating the rectangle
     * @return rectangle for a new node based on its parent and its associated point
     */
    private RectHV calculateRect(Node parent, Point2D p) {
        if (parent == null)
            return rootRectangle();

        RectHV parentRect = parent.rect;
        int compareResult = parent.vertical ?
                Point2D.X_ORDER.compare(p, parent.p) : Point2D.Y_ORDER.compare(p, parent.p);

        /*Rectangle dimensions are evaluated as follows:
         * -If parent is vertical, only x values may change.
         * -If parent is horizontal, only y values may change.
         *
         * -If new node is left child of parent, only max values will change.
         * -If new node is right child of parent, only min values will change.
         */
        if (parent.vertical) {
            if (compareResult < 0)
                return new RectHV(parentRect.xmin(), parentRect.ymin(), parent.p.x(), parentRect.ymax());
            else
                return new RectHV(parent.p.x(), parentRect.ymin(), parentRect.xmax(), parentRect.ymax());
        } else {
            if (compareResult < 0)
                return new RectHV(parentRect.xmin(), parentRect.ymin(), parentRect.xmax(), parent.p.y());
            else
                return new RectHV(parentRect.xmin(), parent.p.y(), parentRect.xmax(), parentRect.ymax());
        }
    }

    private final class Node {
        final Point2D p;      // the point
        final boolean vertical; //is the node split vertically? if false, the node is split horizontally
        final RectHV rect;    // the axis-aligned rectangle corresponding to this node
        Value value;    // the symbol table maps the point to this value
        Node lb;        // the left/bottom subtree
        Node rt;        // the right/top subtree

        Node(Point2D p, Value val, RectHV rect, boolean vertical) {
            this.p = p;
            value = val;
            this.rect = rect;
            this.vertical = vertical;
        }

        Node(Point2D p, Value val, Node parent) {
            this(p, val, calculateRect(parent, p), parent == null || !parent.vertical);
        }
    }
}
