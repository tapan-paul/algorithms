package abnamro;

import java.util.LinkedList;
import java.util.List;

/**
 * Directed graph
 * @tapan .
 */
public final class DirectedGraph {


    private List<DirectedEdge>[] adj;    // adj[v] = adjacency list for vertex v
    private int E;
    private int V;

    public DirectedGraph(int V)  {

        adj = new LinkedList[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        this.V = V;
        this.E = 0;
    }


    public DirectedGraph(In in) {


        this(in.readInt());
        int E = in.readInt();

        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            in.readChar();
            char a = in.readChar();
            in.readChar();
            char b = in.readChar();
            int v = Station.valueOf(Character.toString(a)).getVertex();
            int w = Station.valueOf(Character.toString(b)).getVertex();

            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            int weight = in.readInt();
            addEdge(new DirectedEdge(v, w, weight));
        }

    }


    /**
     * Adds the directed edge v->w to this digraph.
     *
     * @param  edge  contain the two vertices and weight
     * @throws IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(DirectedEdge edge) {

        int v = edge.from();
        int w = edge.to();

        validateVertex(v);
        validateVertex(w);
        adj[v].add(edge);
        E++;

    }

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int w) {

        if (w >= V || w < 0) {
            throw new IllegalArgumentException("vertex " + w + " is not between 0 and " + (V-1));
        }

    }


    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() {
        return E;
    }


    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the vertices adjacent from vertex <tt>v</tt> in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex <tt>v</tt> in this digraph, as an iterable
     * @throws IndexOutOfBoundsException unless 0 <= v < V
     */
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
}
