/**
 * An 'edge' between two image segments
 * @author Kurt Marcinkiewicz
 */
public class ImageSegmentEdge implements Comparable<ImageSegmentEdge> {
  private final int a;
  private final int b;
  private final double weight;

  /**
   * @param a the first int
   * @param b the second int
   * @param weight the edge weight
   */
  public ImageSegmentEdge(int a, int b, double weight) {
    this.a = a;
    this.b = b;
    this.weight = weight;
  }

  /**
   * @return the first node
   */
  public int getA() {
    return a;
  }

  /**
   * @return the second node
   */
  public int getB() {
    return b;
  }

  /**
   *
   * @return the weight of the edge
   */
  public double getWeight() {
    return weight;
  }

  @Override
  public int compareTo(ImageSegmentEdge e) {
    if (getWeight() > e.getWeight()) {
      return 1;
    }
    else if (getWeight() < e.getWeight()) {
      return -1;
    }
    else {
      return 0;
    }
  }

  public String toString() {
    return Double.toString(weight);
  }
}
