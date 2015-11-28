import java.util.ArrayList;

/**
 * A graph representation of image segmentation data
 * @author Kurt Marcinkiewicz
 */
public class ImageSegmentGraph {
  public ArrayList<ImageSegmentNode> imageSegmentNodes = new ArrayList<ImageSegmentNode>();
  public ArrayList<ImageSegmentEdge> imageSegmentEdges = new ArrayList<ImageSegmentEdge>();

  public ImageSegmentGraph() { }

  public boolean addImageSegmentNode(ImageSegmentNode node) {
    for (int i = 0; i < imageSegmentNodes.size(); i++) {
      ImageSegmentEdge edge = new ImageSegmentEdge(imageSegmentNodes.size(), i, setWeight(node, imageSegmentNodes.get(i)));
      addImageSegmentEdge(edge);
    }

    System.out.println(imageSegmentNodes.contains(node));
    return imageSegmentNodes.add(node);
  }

  protected boolean addImageSegmentEdge(ImageSegmentEdge edge) {
    return imageSegmentEdges.add(edge);
  }

  public boolean removeImageSegmentEdge(ImageSegmentEdge edge) {
    return imageSegmentEdges.remove(edge);
  }

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
