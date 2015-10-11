import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @tapan .
 */
public class FastCollinearPoints {


    private List<LineSegment> segments = new ArrayList<LineSegment>();
    private HashMap<Double, Point> slopeToPoint = new HashMap<Double, Point>();


    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point currPoints[] = new Point[points.length-1];
            int k=0;
            for (int j = 0; j < points.length; j++) {
                Point q = points[j];
                if (p.compareTo(q) != 0) {
                    currPoints[k++] = q;
                }
            }
            Arrays.sort(currPoints, p.SLOPE_ORDER);
            Double slope_i0 = p.slopeTo(currPoints[0]);
            Double slope_i1 = p.slopeTo(currPoints[1]);
            Double slope_i2 = p.slopeTo(currPoints[2]);

            if (slope_i0.compareTo(slope_i1) == 00 && slope_i0.compareTo(slope_i2) == 0) {
                Arrays.sort(currPoints, 0, 3);
                if (slopeToPoint.containsKey(slope_i0)){
                    if (currPoints[0].compareTo(slopeToPoint.get(slope_i0)) != 0){
                        LineSegment lineSegment = new LineSegment(currPoints[0], currPoints[2]);
                        segments.add(lineSegment);

                    }
                }else{
                    LineSegment lineSegment = new LineSegment(currPoints[0], currPoints[2]);
                    segments.add(lineSegment);
                }
            }

        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments(){
        return segments.toArray(new LineSegment[segments.size()]);

    }
}
