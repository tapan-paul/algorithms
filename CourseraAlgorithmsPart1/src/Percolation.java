import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by l071882 on 20/07/2015.
 */
public class Percolation {


    private WeightedQuickUnionUF uf;
    private int[][] sites;
    private int topVirtualSite;
    private int bottomVirtualSite;
    private int N;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {

        if (N<=0){
            throw new IllegalArgumentException();
        }
        this.N = N;
        int size = N*N;
        uf = new WeightedQuickUnionUF(size+2);
        sites = new int[N][N];
        topVirtualSite = size;
        bottomVirtualSite = size+1;

    }

    private void idxCheck(int i, int j){
        if (i-1<0 || j-1<0 || i-1>=N || j-1>=N){
            throw new IndexOutOfBoundsException();
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {

        idxCheck(i,j);

        if (!isOpen(i,j)){
            i=i-1;
            j=j-1;

            int idx = N*i +j;

            // open site
            sites[i][j] = 1;

            // check site above
            if (i-1>=0 && sites[i-1][j] == 1) {
                uf.union(idx, (N * (i - 1)) + j); // connect with site above
            }
            // check site below
            if (i+1<N && sites[i+1][j] == 1) {
                uf.union(idx, (N * (i + 1)) + j); // connect with site below
            }
            // check site left
            if (j-1>=0 && sites[i][j-1] == 1) {
                uf.union(idx, (N * i + (j - 1))); // connect with site left
            }
            // check site right
            if (j+1<N && sites[i][j+1] == 1){
                uf.union(idx, (N * i + (j + 1))); // connect with site right
            }

            // top row of site
            if (i==0) {
                uf.union(idx, topVirtualSite);
            }

            if (i==N-1) {
                uf.union(idx, bottomVirtualSite);
            }

        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        idxCheck(i,j);
        return (sites[(i-1)][(j-1)] == 1);
    }
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        idxCheck(i,j);
        return uf.connected(N*(i-1)+(j-1), topVirtualSite);
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }


    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);
        percolation.isFull(6,10);
    }



}
