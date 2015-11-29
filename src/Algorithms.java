import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Functional Class wrapping the algorithms used in the single-link cluster algorithm
 * @author Kurt Marcinkiewicz
 */
public class Algorithms {

  /**
   * Creates single-link clusters in a graph. Uses Kruskal's algorithm backed by a Union-Find
   * structure to find the MST of the graph, then removes k-1 most expensive edges to create k
   * trees.
   * @param edges the edges to create the MST from
   * @param nodes the number of nodes in the graph
   * @param k the desired number of clusters
   * @return the MST
   */
  public static ArrayList<ImageSegmentEdge> singleLinkCluster(ArrayList<ImageSegmentEdge> edges,
                                                              int nodes, int k) {
    Collections.sort(edges);
    int[] nodeIndexes = IntStream.range(0, nodes).toArray();
    UnionFind uf = new UnionFind(nodeIndexes);

    return edges.stream()
        .filter(edge -> uf.union(edge.getA(), edge.getB()))
        .limit(nodes - k)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Identifies the trees developed in the single-link cluster algorithm. Uses a modified breadth-
   * first search to find the tree that every node belongs to.
   * @param nodes the nodes in the graph
   * @param edges the edges connecting the trees within the graph
   * @param k the number of clusters within the graph
   * @return the list of clusters
   */
  public static ArrayList<HashSet<Integer>> clustersFromEdges(ArrayList<ImageSegmentNode> nodes,
                                                              ArrayList<ImageSegmentEdge> edges,
                                                              int k) {
    boolean[] inCluster = new boolean[nodes.size()];
    Arrays.fill(inCluster, false);

    ArrayList<HashSet<Integer>> clusters = Stream.generate(HashSet<Integer>::new)
        .limit(k)
        .collect(Collectors.toCollection(ArrayList::new));


    @SuppressWarnings("unchecked")
    ArrayList<ImageSegmentEdge> edgeList = (ArrayList<ImageSegmentEdge>) edges.clone();

    int i = 0;
    for (int j = 0; j < nodes.size(); j++) {
      boolean incrementI = false;
      ArrayList<Integer> queue = new ArrayList<>();

      if (!inCluster[j]) {
        queue.add(j);
        incrementI = true;
      }

      while (!queue.isEmpty()) {
        int node = queue.remove(0);

        if (!inCluster[node]) {
          clusters.get(i).add(node);
          inCluster[node] = true;

          for (ImageSegmentEdge edgeTwo : edgeList) {
            if (edgeTwo.getA() == node && !inCluster[edgeTwo.getB()]) {
              queue.add(edgeTwo.getB());
            } else if (edgeTwo.getB() == node && !inCluster[edgeTwo.getA()]) {
              queue.add(edgeTwo.getA());
            }
          }
        }
      }
      if (incrementI) {
        i++;
      }
    }
    return clusters;
  }

  /**
   * Calculates the purity of the clustering algorithm. Finds the majority class in each cluster
   * and divides the sum of the majority of each cluster by the total number of nodes.
   * @param clusters the list of clusters
   * @param nodes the nodes in the graph
   * @return the clustering purity
   */
  public static double purityFromClusters(ArrayList<HashSet<Integer>> clusters,
                                          ArrayList<ImageSegmentNode> nodes) {
    int[] majorities = new int[clusters.size()];

    for (int i = 0; i < clusters.size(); i++) {
      HashSet<Integer> cluster = clusters.get(i);

      ArrayList<Integer> frequencies =
          new ArrayList<>(Collections.nCopies(ImageSegmentNode.SegmentClass.values().length, 0));

      for (Integer nodeIndex : cluster) {
        ImageSegmentNode node = nodes.get(nodeIndex);
        frequencies.set(node.getSegmentClass().ordinal(),
                        frequencies.get(node.getSegmentClass().ordinal()) + 1);
      }
      majorities[i] = Collections.max(frequencies);
    }

    return (double) IntStream.of(majorities).sum() / nodes.size();
  }
}
