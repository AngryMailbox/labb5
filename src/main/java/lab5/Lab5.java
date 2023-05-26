// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab5;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab5 {

  /**
   * Computes the maximum flow for a flow network.
   *
   * @param g
   *          a graph with edge capacities and a source and sink
   * @return shortest distance between start and end
   */
  public static int maxFlow(FlowGraph g, int source, int sink) {
    int[][] flow = new int[g.vertexCount()][g.vertexCount()];

    while (true) {
      int[] parent = new int[g.vertexCount()];
      Arrays.fill(parent, -1);
      Queue<Integer> queue = new LinkedList<>();
      queue.add(source);
      parent[source] = -1;
      while (!queue.isEmpty()) {
        int u = queue.poll();
        for (int v = 0; v < g.vertexCount(); v++) {
          if (parent[v] == -1 && g.getCapacity(u, v) > flow[u][v]) {
            parent[v] = u;
            queue.add(v);
          }
        }
      }
      if (parent[sink] == -1) {
        break;
      }
      int pathFlow = (g.getCapacity(parent[sink], sink) - flow[parent[sink]][sink]);
      for (int v = sink; v != source; v = parent[v]) {
          int u = parent[v];
          pathFlow = Math.min(pathFlow, g.getCapacity(u, v) - flow[u][v]);
      }
      for (int v = sink; v != source; v = parent[v]) {
        int u = parent[v];
        flow[u][v] += pathFlow;
        flow[v][u] -= pathFlow;
      }
    }
    int maxFlow = 0;
    for (int v = 0; v < g.vertexCount(); v++) {
      maxFlow += flow[source][v];
    }
    return maxFlow;
  }

  /**
   * Read a flowgraph from a file.
   */
  public static FlowGraph loadFlowGraph(Path path) throws IOException {
    ArrayList<FlowEdge> edges = new ArrayList<>();

    try (Reader reader = Files.newBufferedReader(path)) {
      try (Scanner scanner = new Scanner(reader)) {
        int n = scanner.nextInt(); // number of nodes
        FlowGraph g = new FlowGraph(n);
        int m = scanner.nextInt(); // number of edges
        for (int i = 0; i < m; i++) {
          int u = scanner.nextInt();
          int v = scanner.nextInt();
          int c = scanner.nextInt();
          edges.add(new FlowEdge(u, v, c));
        }
        return new FlowGraph(n, edges.toArray(FlowEdge[]::new));
      }
    }
  }
}
