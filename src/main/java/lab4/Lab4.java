package lab4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/** Helper class for the priority queue in Dijkstras algorithm. */
class PQElement {

  int node;
  int distance;

  public PQElement(int node, int dist) {
    this.node = node;
    this.distance = dist;
  }
}

public class Lab4 {

  /**
   * Computes the shortest distance between start and end in the graph g using
   * Dijkstra's
   * algorithm.
   * This version handles only graphs with integer edge distances.
   * 
   * @param g     a graph with distance information attached to the edges
   * @param start start vertex
   * @param end   end vertex
   * @return shortest distance between start and end
   */
  public static int distance(DistanceGraph g, int start, int end) {
    Comparator<PQElement> comp = (e1, e2) -> Integer.compare(e1.distance, e2.distance);
    PriorityQueue<PQElement> pq = new PriorityQueue<>(comp);
    Set<Integer> visitedNodes = new HashSet<>();
    Map<Integer, Integer> distanceMap = new HashMap<>();

    pq.offer(new PQElement(start, 0));
    for (int i = 0; i < g.vertexCount(); i++) {
      distanceMap.put(i, Integer.MAX_VALUE);
    }
    distanceMap.put(start, 0);

    while (!pq.isEmpty()) {
      PQElement current = pq.poll();
      if (current.node == end) {
        return current.distance;
      }
      if (visitedNodes.contains(current.node)) {
        continue;
      }
      visitedNodes.add(current.node);
      if (current.distance == distanceMap.get(current.node)) {
        for (Edge edge : g.edges(current.node)) {
          int dest = edge.destination;
          int newDist = current.distance + edge.distance;
          int existingDist = distanceMap.get(dest);

          if (newDist < existingDist && !visitedNodes.contains(dest)) {
            pq.offer(new PQElement(dest, newDist));
            distanceMap.put(dest, newDist);
          }
        }
      }
    }
    return -1;
  }

  /**
   * Finds a shortest path between start and end in a graph g Dijkstra's
   * algorithm.
   * The graph can have any distance unit.
   * 
   * @param g     a graph with distance information attached to the edges
   * @param start start vertex
   * @param end   end vertex
   * @return a list containing the nodes on the shortest path from start to end
   */
  public static List<Integer> shortestPath(DistanceGraph g, int start, int end)

  {
    Comparator<PQElement> pqComp = (e1, e2) -> Integer.compare(e1.distance, e2.distance);
    PriorityQueue<PQElement> pq = new PriorityQueue<>(pqComp);
    Map<Integer, Integer> prevNodes = new HashMap<>();
    Map<Integer, Integer> nodeDistances = new HashMap<>();
    Set<Integer> processedNodes = new HashSet<>();

    for (int i = 0; i < g.vertexCount(); i++) {
      nodeDistances.put(i, Integer.MAX_VALUE);
    }

    nodeDistances.put(start, 0);
    pq.offer(new PQElement(start, 0));

    while (!pq.isEmpty()) {
      PQElement curr = pq.poll();
      if (processedNodes.contains(curr.node)) {
        continue;
      }

      processedNodes.add(curr.node);
      for (Edge edge : g.edges(curr.node)) {
        int dest = edge.destination;
        int newDist = nodeDistances.get(curr.node) + edge.distance;
        int existingDist = nodeDistances.get(dest);

        if (newDist < existingDist) {
          nodeDistances.put(dest, newDist);
          prevNodes.put(dest, curr.node);
          pq.offer(new PQElement(dest, newDist));
        }
      }
    }

    LinkedList<Integer> path = new LinkedList<>();
    int currNode = end;
    while (currNode != start) {
      path.addFirst(currNode);
      currNode = prevNodes.get(currNode);
    }
    path.addFirst(start);

    return path;
  }
}
