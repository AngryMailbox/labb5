package lab3;

import graph.Graph;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordGraph implements Graph<String> {

  private final Map<String, Set<String>> graph = new HashMap<>();

  public WordGraph(Path wordfile, WordCriteria criteria) throws IOException {
    Reader file = Files.newBufferedReader(wordfile);
    Scanner scan = new Scanner(file);

    while (scan.hasNext()) {
      String word = scan.nextLine();
      graph.put(word, new HashSet<String>());
    }
    scan.close();

    Set<Map.Entry<String, Set<String>>> wordGraphSet = graph.entrySet();

    for (Map.Entry<String, Set<String>> entry : wordGraphSet) {
      Iterator<Map.Entry<String, Set<String>>> setIterator = wordGraphSet.iterator();
      while (setIterator.hasNext()) {
        String nextString = setIterator.next().getKey();
        if (criteria.adjacent(entry.getKey(), nextString)) {
          entry.getValue().add(nextString);
        }
      }
    }
  }

  @Override
  public int vertexCount() {
    return graph.size();
  }

  //TODO: implement this method
  @Override
  public Collection<String> vertexSet() {
    return graph.keySet();
  }

  @Override
  public Collection<String> neighbours(String v) {
    return graph.getOrDefault(v, Collections.emptySet());
  }
}
