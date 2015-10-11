import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by l071882 on 19/08/2015.
 */
public class Brute {

    public static void main(String[] args) {

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
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

                        if ((slope_ij.compareTo(slope_ik) == 0) && (slope_ij.compareTo(slope_il) == 0)) {


                        }
                    }
                }
            }
        }
    }
}
 