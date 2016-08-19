package abnamro;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @tapan .
 */
public class RouteCalculator {

    private DirectedGraph graph ;
    private boolean visited[];
    private int dist[];
    private Set<String> permutations;

    public RouteCalculator(String fileName) {
        In in = new In(fileName);
        graph = new DirectedGraph(in);
        dist = new int[graph.V()];
        permutations = new HashSet<>();
    }

    public int shortestRoute(Station start, Station end) {

        for (int i=0; i<dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        visited = new boolean[graph.V()];
        dist[start.getVertex()] = 0;
        for (int i = 0; i < dist.length; i++) {

            int min = minDirectedEdge(dist, visited);
            visited[min] = true;

            for (DirectedEdge edge : graph.adj(min)) {
                int weight = dist[min] + edge.weight();
                if (weight < dist[edge.to()] || edge.to()==end.getVertex()) {
                    dist[edge.to()] = weight;
                }
                if (edge.to()==end.getVertex()) {
                    return dist[end.getVertex()];
                }
            }

        }

        return dist[end.getVertex()];
    }

    private int minDirectedEdge(int[] dist, boolean[] visited) {

        int max = Integer.MAX_VALUE;
        int min = 0;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && max > dist[i]) {
                max = dist[i];
                min = i;
            }

        }
        return min;
    }


    public int findAllPermutations(Station start, Station end, int max) {


        Iterable<DirectedEdge> edges = graph.adj(start.getVertex());
        int count=0;
        int edgeTo[]= new int[graph.E()];
        int sumWeight = 0;
        int next=0;

        while (sumWeight < max) {

            for (DirectedEdge edge : edges) {
                if (edge.to() == end.getVertex()) {

                    String s = getPath(start, edgeTo, edge);

                    if (!permutations.contains(s)) {
                        count++;
                        permutations.add(s);
                    }

                    edgeTo = new int[graph.E()];
                    sumWeight = 0;
                    next = start.getVertex();
                    break;
                }
                sumWeight+=edge.weight();
                edgeTo[edge.to()] = edge.from();
                next = edge.to();
                break;
            }
            edges = graph.adj(next);
        }

        return count;
    }

    private String getPath(Station start, int[] edgeTo, DirectedEdge edge) {
        String s = Station.values()[edge.from()].toString();
        for (int i = edge.from(); start.getVertex() != edgeTo[i]; i = edgeTo[i]) {
            s += edgeTo[i];
        }
        s += start.toString();
        s= start.toString() + s;
        return s;
    }

    public int noRoutes(Station start, Station end, int max) {
        return paths(start.getVertex(), end.getVertex(), 0, max);
    }

    private int paths(int start, int end, int total, int max) {

        Iterable<DirectedEdge> edges = graph.adj(start);

        for (DirectedEdge edge : edges) {
            if (total>=max ||edge.to() == end) {
                return ++total;
            }
            total = paths(edge.to(), end, total, max);
        }

        return total;

    }


    public int distance(Map<Station,Station> route, Station start) {

        int sum = 0;
        boolean edgeFound = false;

        for (int i = 0; i < route.keySet().size(); i++) {
            Station nextStation = route.get(start);
            Iterable<DirectedEdge> edges = graph.adj(start.getVertex());
            for (DirectedEdge edge : edges) {
                if (edge.to() == nextStation.getVertex()) {
                    sum += edge.weight();
                    edgeFound = true;
                }
            }
            if (!edgeFound) {
                return -1;
            }

            edgeFound = false;
            start = nextStation;
        }


        return sum;
    }


    public static void main(String[] args) {

        RouteCalculator routeCalulator = new RouteCalculator("stations.txt");
        Map<Station, Station> route = new HashMap<>();
        route.put(Station.A, Station.B);
        route.put(Station.B, Station.C);
        int distance = routeCalulator.distance(route, Station.A);
        StdOut.println("Output #1: " + distance);

        route.clear();

        route.put(Station.A, Station.D);
        distance = routeCalulator.distance(route, Station.A);
        StdOut.println("Output #2: " + distance);

        route.clear();

        route.put(Station.A, Station.D);
        route.put(Station.D, Station.C);
        distance = routeCalulator.distance(route, Station.A);
        StdOut.println("Output #3: " + distance);

        route.clear();

        route.put(Station.A, Station.E);
        route.put(Station.E, Station.B);
        route.put(Station.B, Station.C);
        route.put(Station.C, Station.D);
        distance = routeCalulator.distance(route, Station.A);
        StdOut.println("Output #4: " + distance);

        route.clear();

        route.put(Station.A, Station.E);
        route.put(Station.E, Station.D);
        distance = routeCalulator.distance(route, Station.A);
        StdOut.println("Output #5: " + ((distance == -1) ? "NO SUCH ROUTE" : distance));

        int noTrips = routeCalulator.noRoutes(Station.C, Station.C, 3);
        StdOut.println("Output #6: " + noTrips);

        noTrips = routeCalulator.noRoutes(Station.A, Station.C, 4);
        StdOut.println("Output #7: " + noTrips);

        int shortestRoute = routeCalulator.shortestRoute(Station.A, Station.C);
        StdOut.println("Output #8: " + shortestRoute);

        shortestRoute = routeCalulator.shortestRoute(Station.B, Station.B);
        StdOut.println("Output #9: " + shortestRoute);

        noTrips = routeCalulator.findAllPermutations(Station.C, Station.C, 30);
        StdOut.println("Output #10: " + noTrips);

    }

}
