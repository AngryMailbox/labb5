// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab1;

import graph.Graph;
import graph.SimpleGraph;
import java.util.Collection;

public class Lab1 {

  /**
   * Returns the number of vertices in the graph g.
   */
  public static int vertexCount(Graph<Integer> g) {
    return g.vertexCount();
  }

  /**
   * Returns the number of edges in the graph g.
   */
  public static int edgeCount(Graph<Integer> g) {
    // TODO(D2): implement this!
    int edges = 0;
    for (int i = 0; i < g.vertexCount(); i++) {
      Collection<Integer> c = g.neighbours(i);
      edges = edges + c.size();
    }
    return edges;
  }

  /**
   * Returns true if there is an edge from vertex u to vertex v.
   * Returns false if u and v are not connected or if there is only an edge from v
   * to u.
   *
   * @param g a graph containing u and v
   * @param u index of the first vertex in g
   * @param v index of the second vertex in g
   */
  public static boolean edgeBetween(Graph<Integer> g, int u, int v) {
    // TODO(D3): implement this!
    Collection<Integer> c = g.neighbours(u);
    if (c.contains(v)) {
      return true;
    }
    return false;
  }

  /**
   * Returns a simple graph with at least 6 vertices and at least 10 edges.
   */
  public static Graph<Integer> buildGraph() {
    int[][] edges = new int[][] {
        { 0, 1 }, // 1
        { 1, 2 }, // 2
        { 2, 3 }, // 3
        { 3, 4 }, // 4
        { 4, 5 }, // 5
        //
        { 5, 4 }, // 6
        { 4, 3 }, // 7
        { 3, 2 }, // 8
        { 2, 1 }, // 9
        { 1, 0 }, // 10
    };

    Graph<Integer> g = new SimpleGraph(6, edges);
    return g;
  }
}
