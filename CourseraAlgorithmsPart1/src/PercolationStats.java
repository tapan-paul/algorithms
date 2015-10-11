import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by l071882 on 21/07/2015.
 */
public class PercolationStats {

    private int T,N;
    private double avgOpenSites[];
    private int noOpenSites;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T)     {

        if (N<=0 || T<=0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        avgOpenSites = new double[T];


        for (int k = 0; k < T; k++) {
            Percolation percolation = new Percolation(N);
            while(!percolation.percolates()) {

                int i = StdRandom.uniform(N)+ 1;
                int j = StdRandom.uniform(N)+ 1;
                if (!percolation.isOpen(i,j)) {
                    percolation.open(i,j);
                    noOpenSites++;
                }
            }

            avgOpenSites[k] = (double)noOpenSites /(N*N);
            noOpenSites = 0;
        }

    }

    // sample mean of percolation threshold
    public double mean()                   {
        return StdStats.mean(avgOpenSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev()                    {
        return StdStats.stddev(avgOpenSites);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96*stddev())/(Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()  {
        return mean() + ((1.96*stddev())/(Math.sqrt(T)));
    }

    public static void main(String[] args) {

        int N = StdIn.readInt();
        int T = StdIn.readInt();

        PercolationStats percolationStats = new PercolationStats(N, T);
        StdOut.println("mean                    =" + percolationStats.mean());
        StdOut.println("stddev                  =" + percolationStats.stddev());
        StdOut.println("95% confidence interval =" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
