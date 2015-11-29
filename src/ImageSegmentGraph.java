import java.util.ArrayList;

/**
 * A graph representation of image segmentation data
 * @author Kurt Marcinkiewicz
 */
public class ImageSegmentGraph {
  private ArrayList<ImageSegmentNode> imageSegmentNodes = new ArrayList<ImageSegmentNode>();
  private ArrayList<ImageSegmentEdge> imageSegmentEdges = new ArrayList<ImageSegmentEdge>();

  public ImageSegmentGraph() { }

  /**
   * Adds a node to the graph. Creates an edge between every the new node and every existing node
   * in the graph.
   * @param node the node to be added
   * @return true if the node is added successfully
   */
  public boolean addImageSegmentNode(ImageSegmentNode node) {
    if (!imageSegmentNodes.add(node)) {
      return false;
    }
    else {
      for (int i = 0; i < imageSegmentNodes.size() - 1; i++) {
        ImageSegmentEdge edge = new ImageSegmentEdge(imageSegmentNodes.size() - 1, i,
                                                     setWeight(node, imageSegmentNodes.get(i)));
        if (!addImageSegmentEdge(edge)) {
          return false;
        }
      }
      System.out.println(imageSegmentNodes.contains(node));
      return true;
    }
  }

  /**
   * @return the nodes
   */
  public ArrayList<ImageSegmentNode> getImageSegmentNodes() {
    return imageSegmentNodes;
  }

  /**
   * @return the edges
   */
  public ArrayList<ImageSegmentEdge> getImageSegmentEdges() {
    return imageSegmentEdges;
  }

  /**
   * Adds an edge to the graph.
   * @param edge the edge to be added
   * @return true if the edge is added successfully
   */
  private boolean addImageSegmentEdge(ImageSegmentEdge edge) {
    return imageSegmentEdges.add(edge);
  }

  /**
   * Creates a weight between two nodes by calculating the distance between them from all of their
   * attributes.
   * @param a the first node
   * @param b the second node
   * @return the weight of the edge
   */
  private double setWeight(ImageSegmentNode a, ImageSegmentNode b) {
    return Math.sqrt(
        Math.pow(a.getRegionCentroidCol() - b.getRegionCentroidCol(), 2) +
        Math.pow(a.getRegionCentroidRow() - b.getRegionCentroidRow(), 2) +
        Math.pow(a.getRegionPixelCount() - b.getRegionPixelCount(), 2) +
        Math.pow(a.getShortLineDensity5() - b.getShortLineDensity5(), 2) +
        Math.pow(a.getShortLineDensity2() - b.getShortLineDensity2(), 2) +
        Math.pow(a.getvEdgeMean() - b.getvEdgeMean(), 2) +
        Math.pow(a.getvEdgeSd() - b.getvEdgeSd(), 2) +
        Math.pow(a.getHedgeMean() - b.getHedgeMean(), 2) +
        Math.pow(a.getHedgeSd() - b.getHedgeSd(), 2) +
        Math.pow(a.getIntensityMean() - b.getIntensityMean(), 2) +
        Math.pow(a.getRawRedMean() - b.getRawRedMean(), 2) +
        Math.pow(a.getRawBlueMean() - b.getRawBlueMean(), 2) +
        Math.pow(a.getRawGreenMean() - b.getRawGreenMean(), 2) +
        Math.pow(a.getExRedMean() - b.getExRedMean(), 2) +
        Math.pow(a.getExBlueMean() - b.getExBlueMean(), 2) +
        Math.pow(a.getExGreenMean() - b.getExGreenMean(), 2) +
        Math.pow(a.getValueMean() - b.getValueMean(), 2) +
        Math.pow(a.getSaturationMean() - b.getSaturationMean(), 2) +
        Math.pow(a.getHueMean() - b.getHueMean(), 2)
    );
  }
}
