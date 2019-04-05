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
<<<<<<< HEAD
        if (p == null || val == null)
=======
        if(root == null) {
        	root = new Node(p, val, calculateRect(root, false), null, null, true); // TODO calculateRect should do something with the root instead of passing true or false
        	size++;
        }
        root = put(root, p, val, root.vertical); //TODO Need to add RectHV to the nodes
    }
    
    private Node put(Node parent, Point2D p, Value val, Boolean vertical) {
    	if(parent == null) {
    		size++;
    		return new Node(p, val, vertical);
    	}
    	if(parent.p.compareTo(p) == 0) {
    		parent.value = val;
    	}
    	else if(compareByAxis(parent, p) < 0) {
    		parent.lb = put(parent.lb, p, val, !parent.vertical);
    	}
    	else {
    		parent.rt = put(parent.rt, p, val, !parent.vertical);
    	}
    	return parent;
    }
    
    private double compareByAxis(Node parent, Point2D p) {
		if(parent.vertical == true) {
			return Point2D.X_ORDER.compare(parent.p, p);
		}
		else {
			return Point2D.Y_ORDER.compare(parent.p, p);
		}
    }
    
    
    /*
     
     public void put(Point2D p, Value val) {
        if(p == null || val == null)
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
            throw new NullPointerException("Arguments cannot be null");

        root = put(null, root, p, val);
    }

    private Node put(Node prev, Node current, Point2D p, Value val) {
        if (current == null) { //base case
            size++;
            return new Node(p, val, prev);
        }
<<<<<<< HEAD

        //we are updating a value of an already present key
        if (current.p.equals(p)) {
            current.value = val;
            return current;
=======
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
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
        }

        //we are adding a new key and still need to traverse the tree
        if (compareByAxis(current, p) < 0)
            current.lb = put(current, current.lb, p, val);
        else
            current.rt = put(current, current.rt, p, val);


        return current;
    }
<<<<<<< HEAD
=======
     
     
    private Node putLeft(Node prev, Node putNode) {
        if(prev.lb == null) {
            prev.lb = new Node(putNode.p, putNode.value, calculateRect(prev, true), !prev.vertical);
            size++;
        }
        else {
            prev.lb = put(prev.lb, putNode);
        }
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b

    private int compareByAxis(Node prev, Point2D other) {
        if (prev.vertical)
            return Point2D.X_ORDER.compare(other, prev.p);
        else
            return Point2D.Y_ORDER.compare(other, prev.p);
    }

    private RectHV calculateRect(Node parent, Point2D p) {
        if (parent == null)
            return rootRectangle();

<<<<<<< HEAD
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
=======
        return prev;
    }
	*/
    
    private RectHV calculateRect(Node prev, boolean left) {
        if(root == null) {
        	return new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 
        			Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        } 
        
        if(prev.vertical) {
        	if(left) {
        		return new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 
            			prev.p.x(), Double.POSITIVE_INFINITY);
        	}
        	else {
        		return new RectHV(prev.p.x(), Double.NEGATIVE_INFINITY, 
            			Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        	}
        }
        
        else {
        	if(left) {
        		return new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 
            			Double.POSITIVE_INFINITY, prev.p.y());
        	}
        	else {
        		return new RectHV(Double.NEGATIVE_INFINITY, prev.p.y(), 
            			Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        	}
        }    
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
    }
    


    //get Value associated with point p
    public Value get(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");
<<<<<<< HEAD

        Node result = get(root, p);
        return result == null ? null : result.value;
    }

    private Node get(Node current, Point2D p) {
        if (current == null || current.p.equals(p))
            return current;
        else if (compareByAxis(current, p) < 0) {
            return get(current.lb, p);
        } else
=======
        Node getNode = get(root, p);
        return getNode != null ? getNode.value : null;
    }

    private Node get(Node current, Point2D p) {
        if (current == null || current.p.compareTo(p) == 0) 
        	return current;
        else if (compareByAxis(current, p) < 0) {
        	return get(current.lb, p); }
        else
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
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
<<<<<<< HEAD
        Queue<Point2D> nodesInRange = new Queue<>();
        check(nodesInRange, root, rectHV);
        
        return nodesInRange;
    }
    
    private void check(Queue<Point2D> q, Node node, RectHV rect) {
    	if(node != null && node.rect.intersects(rect)) {
    		if(rect.contains(node.p)) {
    			q.enqueue(node.p);
    		}
    		for(Node child : getIntersections(node, rect)) {
    			check(q, child, rect);
    		}
    	}
    }
    
    private Queue<Node> getIntersections(Node node, RectHV rect) {
    	Queue<Node> intersectQueue = new Queue<>();
    	if(node.lb != null) {
	    	if(rect.contains(node.lb.p)) {
	    		intersectQueue.enqueue(node.lb);
	    	}
    	}
    	if(node.rt != null) {
	    	if(rect.contains(node.rt.p)) {
	    		intersectQueue.enqueue(node.rt);
	    	}
    	}
    	return intersectQueue;
=======

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
>>>>>>> 357fa58aa6454c757dbda92d6eb71416458200e1
    }

    //a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException("Arguments cannot be null");
        //TODO
        return null;
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
<<<<<<< HEAD
        final boolean vertical; //is the node split vertically? if false, the node is split horizontally
        final RectHV rect;    // the axis-aligned rectangle corresponding to this node
        Value value;    // the symbol table maps the point to this value
=======
        Value value;    // the symbol table maps the point to this value
        RectHV rect;    // the axis-aligned rectangle corresponding to this node
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
        Node lb;        // the left/bottom subtree
        Node rt;        // the right/top subtree

        Node(Point2D p, Value val, RectHV rect, boolean vertical) {
            this.p = p;
            value = val;
            this.rect = rect;
            this.vertical = vertical;
        }

<<<<<<< HEAD
        Node(Point2D p, Value val, Node parent) {
            this(p, val, calculateRect(parent, p), parent == null || !parent.vertical);
=======
        Node(Point2D p, Value value, RectHV rect, boolean vertical) {
            this(p, value, rect, null, null, vertical);
        }

        Node(Point2D p, Value val) {
            this(p, val, null, null, null, false);
        }

        public Node(Point2D p, Value val, boolean vertical) {
        	this.p = p;
            this.value = val;
            this.vertical = vertical; //TODO
		}

		@Override
        public int compareTo(Node other) {
            if(vertical)
                return Point2D.X_ORDER.compare(this.p, other.p);
            else
                return Point2D.Y_ORDER.compare(this.p, other.p);
>>>>>>> b9b84a1096a9d2f44a09141322057f1b78ac5c2b
        }
    }
}
