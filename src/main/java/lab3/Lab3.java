// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab3;

import graph.Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lab3 {

  public static <T> int distance(Graph<T> graph, T source, T destination) {
    Map<T, Integer> distanceMap = new HashMap<>();
    Set<T> visitedNodeSet = new HashSet<>();
    List<T> queue = new ArrayList<>();

    distanceMap.put(source, 0);
    queue.add(source);
    visitedNodeSet.add(source);

    while (!queue.isEmpty()) {
      T currentNode = queue.remove(0);

      for (T neighbourNode : graph.neighbours(currentNode)) {
        if (!visitedNodeSet.contains(neighbourNode)) {
          queue.add(neighbourNode);
          distanceMap.put(neighbourNode, distanceMap.get(currentNode) + 1);
          visitedNodeSet.add(neighbourNode);
        }
      }
    }

    if (distanceMap.containsKey(destination)) {
      return distanceMap.get(destination);
    } else return -1;
  }
}
