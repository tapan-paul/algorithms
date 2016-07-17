import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by l071882 on 19/08/2015.
 */
public class Point implements Comparable<Point> {

    private int x,y;

    // compare points by slope to this point
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            double s1 = Point.this.slopeTo(p1);
            double s2 = Point.this.slopeTo(p2);

            if (s1 < s2) {
                return -1;
            }

            if (s1 > s2) {
                return  1;
            }

            return 0;
        }
    };

    // construct the point (x, y)
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    // draw this point
    public void draw(){
        StdDraw.point(x, y);
    }

    // draw the line segment from this point to that point
    public void drawTo(Point that){
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    // is this point lexicographically smaller than that point?
    public int compareTo(Point that){

        if (this.y < that.y) {
            return -1;
        }

        if (this.y > that.y) {
            return 1;
        }

        if (this.x < that.x) {
            return -1;
        }

        if (this.x > that.x) {
            return 1;
        }

        return 0 ;
    }

    // the slope between this point and that point
    public double slopeTo(Point that){

        int ydiff = that.y - this.y;
        int xdiff = that.x - this.x;

        // degenerate line - negative infinity
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }

        // horizontal line , positive zero
        if (ydiff == 0) {
            return 0.0;
        }

        // vertical line , positive infinity
        if (xdiff == 0) {
            return Double.POSITIVE_INFINITY;
        }

        final double slope = (double)ydiff / xdiff;

        return slope;
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p0 = new Point(0,0);
        Point p1 = new Point(1,1);
        Point p2 = new Point(2,2);
        Point p3 = new Point(4,2);
        Point p4 = new Point(2,4);


        int c1 = p0.SLOPE_ORDER.compare(p1, p2);
        int c2 = p0.SLOPE_ORDER.compare(p2, p3);
        int c3 = p0.SLOPE_ORDER.compare(p3, p4);


        StdOut.println(c1);
        StdOut.println(c2);
        StdOut.println(c3);

        StdOut.println(p0.slopeTo(p1));
    }

    public Comparator<Point> slopeOrder() {
        return SLOPE_ORDER;
    }
}