package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public final class KdTreeST<Value> {
    private Node root;
    private int size;

    // construct an empty symbol table of points
    public KdTreeST() {
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
        if (p == null || val == null)
            throw new NullPointerException("Arguments cannot be null");

        root = put(null, root, p, val);
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
        if (compareByAxis(current, p) < 0)
            current.lb = put(current, current.lb, p, val);
        else
            current.rt = put(current, current.rt, p, val);


        return current;
    }

    private int compareByAxis(Node prev, Point2D other) {
        if (prev.vertical)
            return Point2D.X_ORDER.compare(other, prev.p);
        else
            return Point2D.Y_ORDER.compare(other, prev.p);
    }

    private RectHV calculateRect(Node parent, Point2D p) {
        if (parent == null)
            return rootRectangle();

        int compareResult = parent.vertical ?
                Point2D.X_ORDER.compare(p, parent.p) : Point2D.Y_ORDER.compare(p, parent.p);
        RectHV parentRect = parent.rect;


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


    //get Value associated with point p
    public Value get(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        Node result = get(root, p);
        return result == null ? null : result.value;
    }

    private Node get(Node current, Point2D p) {
        if (current == null || current.p.equals(p))
            return current;
        else if (compareByAxis(current, p) < 0) {
            return get(current.lb, p);
        } else
            return get(current.rt, p);
    }

    //Does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");

        return get(p) != null;
    }

    //Iterable of all points in the symbol table in level order
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

    //all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rectHV) {
        if (rectHV == null)
            throw new NullPointerException("Arguments cannot be null");

        Queue<Point2D> nodesInRange = new Queue<>();
        check(nodesInRange, root, rectHV);

        return nodesInRange;
    }

    private void check(Queue<Point2D> q, Node node, RectHV rect) {
        if(node != null) {
//        if(node != null && node.rect.intersects(rect)) {
            if(rect.contains(node.p))
                q.enqueue(node.p);

            for(Node child : getIntersections(node, rect))
                check(q, child, rect);
        }
    }

    private Queue<Node> getIntersections(Node node, RectHV rect) {
        Queue<Node> intersections = new Queue<>();
        if(node.lb != null) {
            if (rect.intersects(node.rect))
                intersections.enqueue(node.lb);
        }
        if (node.rt != null) {
            if (rect.intersects(node.rect))
                intersections.enqueue(node.rt);
        }

        return intersections;
    }

    //a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");
        Node champion = nearestNode(p, root, root);
        
        return champion.p;
    }
    
    private Node nearestNode(Point2D p, Node champion, Node current) {
    	
    	if(current.p.distanceTo(p) < champion.p.distanceTo(p)) {
    		champion = current;
    	}
    	   
		if (current.lb != null && compareByAxis(current, p) < 0 && current.lb.rect.intersects(nearestQueryRect(p, champion))) {
				return nearestNode(p, champion, current.lb);
		}
		else if (current.rt != null && compareByAxis(current, p) > 0 && current.rt.rect.intersects(nearestQueryRect(p, champion))) {
				return nearestNode(p, champion, current.rt);
		}
    	return champion;
    }
    
    private RectHV nearestQueryRect(Point2D p, Node champion) {
    	return new RectHV(
    			p.x() - p.distanceTo(champion.p),
    			p.y() - p.distanceTo(champion.p),
    			p.x() + p.distanceTo(champion.p),
    			p.y() + p.distanceTo(champion.p)
    			);
    }
    
    private RectHV rootRectangle() {
        return new RectHV(
                Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY
        );
    }

    //FIXME calculate rectangles correctly
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
