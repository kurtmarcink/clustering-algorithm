import java.util.ArrayList;
import java.util.Collections;

/**
 * Functional Class wrapping the algorithms used in the single-link cluster algorithm
 * @author Kurt Marcinkiewicz
 */
public class Algorithms {

  /**
   * Creates single-link clusters in a graph.
   * @param edges the edges to create the MST from
   * @param nodes the number of nodes in the graph
   * @param k the desired number of clusters
   * @return the MST
   */
  public static ArrayList<ImageSegmentEdge> singleLinkCluster(ArrayList<ImageSegmentEdge> edges,
                                                              int nodes, int k) {
    Collections.sort(edges);
    ArrayList<ImageSegmentEdge> mst = new ArrayList<ImageSegmentEdge>(nodes - 1);

    int[] anArray = new int[edges.size()];

    for(int i = 0; i < edges.size(); i++) {
      anArray[i] = i;
    }

    UnionFind uf = new UnionFind(anArray);

    for(ImageSegmentEdge edge : edges) {
      if (uf.union(edge.getA(), edge.getB())) {
        mst.add(edge);
      }
    }
    System.out.println(mst.size());
    mst.subList(mst.size() - k + 1, mst.size()).clear();
    System.out.println(mst.size());

    return mst;
  }
}
