import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by l071882 on 18/03/2016.
 */
public class FastCollinearPoints {


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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points){

        if (points == null){
            throw new NullPointerException();
        }


        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point sortedPoints[] = new Point[points.length-1];
            int k=0;
            for (int j = 0; j < points.length; j++) {
                Point q = points[j];
                if (p.compareTo(q) != 0){
                    sortedPoints[k++]=q;
                }
            }
            if (sortedPoints.length == 0) {
                return;
            }

            Arrays.sort(sortedPoints, p.slopeOrder());

            Point firstPoint = sortedPoints[0];
            double referenceSlope = p.slopeTo(firstPoint);
            int countPoints = 1;
            ArrayList<Point> tmp = new ArrayList<Point>();
            tmp.add(p);
            tmp.add(firstPoint);
            for (int j = 1; j < sortedPoints.length; j++) {
                Point q = sortedPoints[j];
                if (p.slopeTo(q) == referenceSlope) {
                    countPoints++;
                    tmp.add(q);
                }else {
                    if (countPoints >= 3) {
                        Collections.sort(tmp);
                        Point min = tmp.get(0);
                        Point max = tmp.get(tmp.size()-1);
                        if (p.compareTo(min) == 0) {
                            lineSegments.add(new LineSegment(min, max));
                        }
                    }
                    countPoints = 1;
                    tmp = new ArrayList<Point>();
                    tmp.add(p);
                    tmp.add(q);
                    referenceSlope = p.slopeTo(q);
                }
            }
            if (countPoints >= 3) {
                Collections.sort(tmp);
                Point min = tmp.get(0);
                Point max = tmp.get(tmp.size()-1);
                if (p.compareTo(min) == 0) {
                    lineSegments.add(new LineSegment(min, max));
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
