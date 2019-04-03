package kdTree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSTTest_Students {
	Point2D[] treePoints = {new Point2D(0,0), new Point2D(1,1), new Point2D(2,2), new Point2D(1,-1), new Point2D(2,-2),
			new Point2D(-1,1), new Point2D(-2,2), new Point2D(-1,-1), new Point2D(-2,-2)};
	PointST<String> tree;
	PointST<String> emptyTree;	

	
	@Before
	public void setUp() throws Exception {
		emptyTree = new PointST<>();
		
		tree = new PointST<>();
		for (Point2D point : treePoints) {
			tree.put(point, point.toString());
		}
	}
	
	@Test
	public void testPointST() {
		PointST<Integer> newTree = new PointST<>();
		assertEquals(0, newTree.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, emptyTree.isEmpty());
		assertEquals(false, tree.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyTree.size());
		assertEquals(9, tree.size());
	}

	@Test
	public void testPut() {
		Point2D p = new Point2D(5,5);
		
		// add a new key
		tree.put(p, "value");
		assertEquals(10, tree.size());
		assertEquals("value", tree.get(p)); 
		
		// update value of an existing key
		tree.put(p, "newValue");
		assertEquals(10, tree.size());
		assertEquals("newValue", tree.get(p));
	}
	
	@Test (expected = NullPointerException.class)
	public void testPutNull() {
		tree.put(null, null);
		tree.put(null, "value");
		tree.put(new Point2D(1,5), null);
	}

	@Test
	public void testGet() {
		// return appropriate values for existing keys
		for (Point2D point : treePoints) 
			assertEquals(point.toString(), tree.get(point));
		
		// return null if keys are not part of the symbol table
		assertEquals(null, tree.get(new Point2D(Double.MAX_VALUE, Double.MAX_VALUE)));
		assertEquals(null, tree.get(new Point2D(5,5)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetNull() {
		tree.get(null);
	}

	@Test
	public void testContains() {
		// true for existing keys
		for (Point2D point : treePoints) 
			assertEquals(true, tree.contains(point));
		
		// false if keys are not part of the symbol table
		assertEquals(false, tree.contains(new Point2D(Double.MAX_VALUE, Double.MAX_VALUE)));
		assertEquals(false, tree.contains(new Point2D(5,5)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsNull() {
		tree.contains(null);
	}

	@Test
	public void testPoints() {
		// no points are found in an empty symbol table
		Iterable<Point2D> noPoints = emptyTree.points();
		for (Point2D point : noPoints) 
			fail("Queue was not empty and contained " + point + ". Queue was " + noPoints.toString());
		
		// all points are returned but in no specific order
		List<Point2D> treePointList = new ArrayList<>(Arrays.asList(treePoints));
		for (Point2D point: tree.points()) {
			assertEquals(true, treePointList.contains(point));
			treePointList.remove(point);
		}
	}

	@Test
	public void testRange() {
		Iterable<Point2D> noPoints = tree.range(new RectHV(10,10,11,11));		
		for (Point2D point : noPoints) 
			fail("Queue was not empty and contained " + point + ". Queue was " + noPoints.toString());
		
		Iterable<Point2D> onePoint = tree.range(new RectHV(-0.5, -0.5, 0.5, 0.5));
		List<Point2D> onePointsAnswer = new ArrayList<>(Arrays.asList(new Point2D(0,0)));
		for (Point2D point : onePoint) {
			assertEquals(true, onePointsAnswer.contains(point));
			onePointsAnswer.remove(point);
		}
		
		Iterable<Point2D> fivePoints = tree.range(new RectHV(-1, -1, 1, 1));
		List<Point2D> fivePointsAnswer = new ArrayList<>(Arrays.asList(new Point2D(0,0), 
				new Point2D(-1,1), new Point2D(-1,-1), new Point2D(1,1), new Point2D(1,-1)));
		for (Point2D point : fivePoints) {
			assertEquals(true, fivePointsAnswer.contains(point));
			fivePointsAnswer.remove(point);
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testRangeNull() {
		tree.range(null);
	}

	@Test
	public void testNearest() {
		assertEquals(new Point2D(0,0), tree.nearest(new Point2D(0,0)));
		assertEquals(new Point2D(0,0), tree.nearest(new Point2D(0.0001, 0.0001)));
		assertEquals(new Point2D(2,2), tree.nearest(new Point2D(1000000, 1000000)));
		assertEquals(new Point2D(2,2), tree.nearest(new Point2D(3, 3)));
		assertEquals(new Point2D(-2,-2), tree.nearest(new Point2D(-2, -2)));
		assertEquals(new Point2D(-2,-2), tree.nearest(new Point2D(-1000000, -1000000)));
		assertEquals(new Point2D(-2, 2), tree.nearest(new Point2D(-1000000, 1000000)));
		assertEquals(new Point2D(2, -2), tree.nearest(new Point2D(1000000, -1000000)));
		assertEquals(new Point2D(0,0), tree.nearest(new Point2D(0.4, 0.6)));
		assertEquals(new Point2D(1,1), tree.nearest(new Point2D(0.4000001, 0.6)));
		assertEquals(new Point2D(1,1), tree.nearest(new Point2D(0.4, 0.6000001)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testNearestNull() {
		tree.nearest(null);
	}

}
