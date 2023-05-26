// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab2;

import graph.Graph;
import graph.SimpleGraph;

public class GraphFactory {

  /** Returns a connected graph of at least 5 vertices. */
  public static Graph<Integer> buildConnected() {
    // TODO(D1): implement this!
    return new SimpleGraph(
      5,
      new int[][] {
        { 0, 1 }, // 1
        { 1, 2 }, // 2
        { 2, 3 }, // 3
        { 3, 4 }, // 4
        // -- //
        { 4, 3 }, // 5
        { 3, 2 }, // 6
        { 2, 1 }, // 7
        { 1, 0 }, // 8
      }
    );
  }

  /** Returns a disconnected graph of at least 5 vertices. */
  public static Graph<Integer> buildDisconnected() {
    // TODO(D1): implement this!
    return new SimpleGraph(5, new int[][] { { 0, 1 }, { 1, 2 } });
  }
}
