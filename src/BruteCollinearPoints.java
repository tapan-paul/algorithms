import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BruteCollinearPoints {


    private List<LineSegment> segments = new ArrayList<LineSegment>();
    private HashMap<Double, List<Point>> slopeToPoint = new HashMap<Double, List<Point>>();


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
        edu.princeton.cs.algs4.StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)  {


        if (points == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < points.length; i++) {
            Point p_i = points[i];
            for (int j = i+1; j < points.length; j++) {
                Point p_j = points[j];
                for (int k = j+1; k < points.length; k++) {
                    Point p_k = points[k];
                    for (int l = k+1; l < points.length; l++) {
                        Point p_l = points[l];

                        Double slope_ij = p_i.slopeTo(p_j);
                        Double slope_ik = p_i.slopeTo(p_k);
                        Double slope_il = p_i.slopeTo(p_l);

                        if (isCollinear(slope_ij, slope_ik, slope_il)) {
                            List<Point> linePoints = new ArrayList<Point>();
                            linePoints.add(p_i);
                            linePoints.add(p_j);
                            linePoints.add(p_k);
                            linePoints.add(p_l);
                            Collections.sort(linePoints);
                            Point min = linePoints.get(0);
                            Point max = linePoints.get(3);
                            ArrayList<Point> minMax = new ArrayList<Point>();
                            minMax.add(min);
                            minMax.add(max);

                            LineSegment lineSegment = new LineSegment(min, max);

                            if (!slopeToPoint.containsKey(slope_ij)) {
                                slopeToPoint.put(slope_ij, minMax);
                                segments.add(lineSegment);
                            }else{
                                List<Point> existingPoint = slopeToPoint.get(slope_ij);
                                if ((min.compareTo(existingPoint.get(0)) != 0)) {
                                    slopeToPoint.put(slope_ij, minMax);
                                    segments.add(lineSegment);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isCollinear(Double slope_ij, Double slope_ik, Double slope_il) {
        return (slope_ij.compareTo(slope_ik) == 0) && (slope_ij.compareTo(slope_il) == 0);
    }

    // the number of line segments
    public int numberOfSegments(){
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments(){

    /*    for (Double aDouble : slopeToPoint.keySet()) {
            List<Point> tmp = slopeToPoint.get(aDouble);
            LineSegment lineSegment = new LineSegment(tmp.get(0), tmp.get(1));
            segments.add(lineSegment);

        }*/

        return segments.toArray(new LineSegment[segments.size()]);
    }
}