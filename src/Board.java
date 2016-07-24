import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by l071882 on 29/04/2016.
 */
public class Board {

    private int[][] tiles;
    private int[][] goal;
    private int N;

    // construct a board from an N-by-N array of tiles
    public Board(int[][] tiles) {
        if (tiles ==null){
            throw new NullPointerException();
        }
        this.tiles = tiles;
        N = tiles.length;
        this.goal = new int[N][N];
    }

    // (where tiles[i][j] = block in row i, column j)
    // board dimension N
    public int dimension(){
        return N;
    }

    // number of tiles out of place
    public int hamming(){

        int noBlocksNotInPosition = 0;
        int noCounter = 1;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                // exclude zero , noBlocksNotInPosition would incorrectly
                // return a value if all numbers were in correct position
                if (tiles[i][j] != noCounter++ && tiles[i][j] != 0) {
                    noBlocksNotInPosition++;
                }
            }
        }

        return noBlocksNotInPosition;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {

        int sum = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                int value = tiles[i][j];
                if (value != 0) {
                    int targetx = (value - 1) / N; // x coordinate
                    int targety = (value - 1) % N; // y coordinate
                    sum += Math.abs(targetx - i) + Math.abs(targety -j);
                }
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal(){

        int counter = 1;
        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal.length; j++) {
                if (counter != (N * N)) {
                    goal[i][j] = counter++;
                }
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0 && i+1<N && j+1<N && tiles[i+1][j+1] != 0) {
                    return swap(tiles, i, j, i+1, j+1);
                }
            }
        }

        return new Board(tiles);
    }

    // does this board equal y?
    public boolean equals(Object y) {

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (that.tiles.length != this.tiles.length) return false;

        return  Arrays.deepEquals(this.tiles, that.tiles);

    }

    private Board swap(int[][] board, int i , int j, int x, int y) {

        int ij_value = board[i][j];
        int xy_value = board[x][y];
        int[][] copy = new int[N][N];
        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board.length; l++) {
                copy[k][l] = board[k][l];
            }
        }
        copy[i][j] = xy_value;
        copy[x][y]= ij_value;
        return new Board(copy);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        int i_pos = 0;
        int j_pos = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    i_pos = i;
                    j_pos = j;
                    break;
                }
            }
        }


        Stack<Board> stack = new Stack<Board>();

        if (i_pos-1 >= 0){
            stack.push(swap(tiles, i_pos, j_pos, i_pos-1, j_pos));
        }
        if (j_pos-1 >= 0) {
            stack.push(swap(tiles, i_pos, j_pos, i_pos, j_pos-1));
        }
        if (i_pos+1 < N){
            stack.push(swap(tiles, i_pos, j_pos, i_pos+1, j_pos));
        }
        if (j_pos+1 < N) {
            stack.push(swap(tiles, i_pos, j_pos, i_pos, j_pos+1));
        }

        return stack;
    }

    // string representation of this board (in the output format specified below)
    public String toString(){

        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args){

        int[][] block1 = {{0,1,3},{4,2,5},{7,8,6}};
        int[][] block2 = {{1,0,3},{4,2,5},{7,8,6}};
        int[][] block3 = {{1,2,3},{4,0,5},{7,8,6}};
        int[][] block4 = {{1,2,3},{4,5,6},{7,8,0}};
        int[][] block5 = {{8,1,3},{4,0,2},{7,6,5}};
        int[][] block6 = {{1,0,3},{4,2,5},{7,8,6}};
        Board board = new Board(block6);

        StdOut.print(board);
        Iterable<Board> neighbours = board.neighbors();
        for (Board neighbour : neighbours) {
            StdOut.print(neighbour);
        }
    }
}