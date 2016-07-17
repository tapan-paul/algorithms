import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by l071882 on 6/05/2016.
 */
public class Solver {

    private MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
    private boolean isSolveable = false;
    private int noMoves = 0;
    private Stack<Board> camefrom;

    private class SearchNode implements Comparable<SearchNode>{
        int noMoves;
        SearchNode previousNode;
        Board board;
        int priority;

        SearchNode(int noMoves, SearchNode previousNode, Board current) {
            this.noMoves = noMoves;
            this.previousNode = previousNode;
            this.board = current;
            this.priority = noMoves + current.manhattan();
        }

        @Override
        public int compareTo(SearchNode o) {
            if (priority > o.priority) {
                return 1;
            }
            if (priority < o.priority) {
                return -1;
            }
            return 0;
        }
    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        if (initial == null) {
            throw new NullPointerException();
        }
        noMoves = 0;
        SearchNode root = new SearchNode(noMoves, null, initial);
        queue.insert(root);
        camefrom = new Stack<Board>();

        while (!queue.isEmpty()) {

            SearchNode currentNode = queue.delMin();
            Board currentBoard = currentNode.board;
            if (currentBoard.isGoal()) {
                isSolveable = true;
                break;
            }

            for(Board neighbour : currentBoard.neighbors()) {
                SearchNode previousNode = currentNode.previousNode;
                if (!neighbour.equals(previousNode.board)) {
                    SearchNode neighbourNode = new SearchNode(noMoves, currentNode, neighbour);
                    queue.insert(neighbourNode);
                    camefrom.push(currentBoard);
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable(){
        return isSolveable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return noMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        return camefrom;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
