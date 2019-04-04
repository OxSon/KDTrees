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
    }
    

    //get Value associated with point p
    public Value get(Point2D p) {
        if(p == null)
            throw new NullPointerException("Arguments cannot be null");
        Node getNode = get(root, p);
        return getNode != null ? getNode.value : null;
    }

    private Node get(Node current, Point2D p) {
        if (current == null || current.p.compareTo(p) == 0) 
        	return current;
        else if (compareByAxis(current, p) < 0) {
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
        RectHV rect;    // the axis-aligned rectangle corresponding to this node
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
        }
    }
}