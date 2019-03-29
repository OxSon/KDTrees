package kdTree;

import edu.princeton.cs.algs4.Point2D;

/**
 * Represents an axis-aligned rectangle in a 2D plane.
 *
 * @author Alec Mills
 * @author Kenneth
 */
public class RectHV {


    /**
     * Construct the rectangle [xmin, xmax] X [ymin, ymax].
     *
     * @param xmin Left-most bound of rectangle on X-axis.
     * @param ymin Lower-most bound of rectangle on Y-axis.
     * @param xmax Right-most bound of rectangle on X-axis.
     * @param ymax Top-most bound of rectangle on Y-axis.
     */
    public RectHV(double xmin, double ymin,
                  double xmax, double ymax) {
        //TODO
    }

    /**
     * Minimum X-coordinate of rectangle.
     *
     * @return minimum X-coordinate.
     */
    public double xmin() {
        //TODO
        return 0D;

    }

    /**
     * Minimum Y-coordinate of rectangle.
     *
     * @return minimum Y-coordinate.
     */
    public double ymin() {
        //TODO
        return 0D;
    }

    /**
     * Maximum X-coordinate of rectangle.
     *
     * @return maximum X-coordinate.
     */
    public double xmax() {
        //TODO
        return 0D;
    }

    /**
     * Maximum X-coordinate of rectangle.
     *
     * @return maximum X-coordinate.
     */
    public double ymax() {
        //TODO
        return 0D;
    }

    /**
     * Does this rectangle contain the point p (either inside or on boundary)?
     *
     * @param p the point that may be contained in this rectangle.
     * @return true if the point is contained in the rectangle (including on boundary), false otherwise.
     */
    public boolean contains(Point2D p) {
        //TODO
        return false;
    }

    /**
     * Does this rectangle intersect that rectangle (at one or more points)?
     *
     * @param that rectangle that this rectangle may intersect with.
     * @return true if rectangles intersect, false otherwise.
     */
    public boolean intersects(RectHV that) {
        //TODO
        return false;
    }

    /**
     * Square of Euclidian distance from point p to closest point in rectangle.
     *
     * @param p the point from which distance is to be measured.
     * @return the square of the distance from point p to closest point in rectangle.
     */
    public double distanceSquaredTo(Point2D p) {
        //TODO
        return 0D;
    }

    @Override
    public boolean equals(Object that) {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        //TODO
        return null;
    }
}
