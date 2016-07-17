import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l071882 on 17/03/2016.
 */
public class BruteCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<LineSegment>();

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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null) {
                throw new NullPointerException();
            }

            for (int j = i+1; j < points.length; j++) {
                Point q = points[j];
                if (q == null) {
                    throw new NullPointerException();
                }
                if (q.compareTo(p) == 0) {
                    throw new IllegalArgumentException();
                }

                for (int k = j+1; k < points.length; k++) {
                    Point r = points[k];
                    if (r == null) {
                        throw new NullPointerException();
                    }
                    if (r.compareTo(p) == 0 || r.compareTo(q) == 0) {
                        throw new IllegalArgumentException();
                    }

                    for (int l = k+1; l < points.length; l++) {
                        Point s = points[l];
                        if (s == null) {
                            throw new NullPointerException();
                        }
                        if (s.compareTo(q) == 0 || s.compareTo(r) == 0 || s.compareTo(p) == 0) {
                            throw new IllegalArgumentException();
                        }

                        Double referenceSlope = p.slopeTo(q);
                        Double rSlope = p.slopeTo(r);
                        Double sSlope = p.slopeTo(s);
                        Point min=p;
                        Point max=p;
                        if (rSlope.compareTo(referenceSlope) == 0 && sSlope.compareTo(referenceSlope) == 0) {
                            if (q.compareTo(min) == -1) {
                                min = q;
                            }
                            if (q.compareTo(max) == 1) {
                                max = q;
                            }
                            if (r.compareTo(min) == -1) {
                                min = r;
                            }
                            if (r.compareTo(max) == 1) {
                                max = r;
                            }
                            if (s.compareTo(min) == -1) {
                                min = s;
                            }
                            if (s.compareTo(max) == 1) {
                                max = s;
                            }
                            lineSegments.add(new LineSegment(min, max));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments(){
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

}
