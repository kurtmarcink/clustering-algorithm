import java.util.HashMap;

/**
 * An image segment as represented by an .arff file
 * @author Kurt Marcinkiewicz
 */
public class ImageSegmentNode {
  private final double regionCentroidCol;
  private final double regionCentroidRow;
  private final double regionPixelCount;
  private final double shortLineDensity5;
  private final double shortLineDensity2;
  private final double vEdgeMean;
  private final double vEdgeSd;
  private final double hedgeMean;
  private final double hedgeSd;
  private final double intensityMean;
  private final double rawRedMean;
  private final double rawBlueMean;
  private final double rawGreenMean;
  private final double exRedMean;
  private final double exBlueMean;
  private final double exGreenMean;
  private final double valueMean;
  private final double saturationMean;
  private final double hueMean;
  private SegmentClass segmentClass;

  /**
   *
   * @param regionCentroidCol the column of the center pixel of the region
   * @param regionCentroidRow the row of the center pixel of the region
   * @param regionPixelCount the number of pixels in a region
   * @param shortLineDensity5 the results of a line extractoin algorithm that counts how many lines
   *                          of length 5 (any orientation) with low contrast, less than or equal to
   *                          5, go through the region
   * @param shortLineDensity2 same as short-line-density-5 but counts lines of high contrast,
   *                          greater than 5
   * @param vEdgeMean measure the contrast of horizontally adjacent pixels in the region. There are
   *                  6, the mean and standard deviation are given.  This attribute is used as a
   *                  vertical edge detector
   * @param vEdgeSd (see vEdgeMean)
   * @param hedgeMean measures the contrast of vertically adjacent pixels. Used for horizontal line
   *                  detection
   * @param hedgeSd (see hedgeMean)
   * @param intensityMean the average over the region of (R + G + B)/3
   * @param rawRedMean the average over the region of the R value
   * @param rawBlueMean the average over the region of the B value
   * @param rawGreenMean the average over the region of the G value
   * @param exRedMean measure the excess red:  (2R - (G + B))
   * @param exBlueMean measure the excess blue:  (2B - (G + R))
   * @param exGreenMean measure the excess green:  (2G - (R + B))
   * @param valueMean 3-d nonlinear transformation of RGB. (Algorithm can be found in Foley and
   *                  VanDam, Fundamentals of Interactive Computer Graphics)
   * @param saturationMean (see valueMean)
   * @param hueMean (see valueMean)
   * @param segmentClass the class of the image segment
   */
  public ImageSegmentNode(double regionCentroidCol, double regionCentroidRow, double regionPixelCount,
                          double shortLineDensity5, double shortLineDensity2, double vEdgeMean,
                          double vEdgeSd, double hedgeMean, double hedgeSd, double intensityMean,
                          double rawRedMean, double rawBlueMean, double rawGreenMean, double exRedMean,
                          double exBlueMean, double exGreenMean, double valueMean,
                          double saturationMean,
                          double hueMean, String segmentClass) {
    this.regionCentroidCol = regionCentroidCol;
    this.regionCentroidRow = regionCentroidRow;
    this.regionPixelCount = regionPixelCount;
    this.shortLineDensity5 = shortLineDensity5;
    this.shortLineDensity2 = shortLineDensity2;
    this.vEdgeMean = vEdgeMean;
    this.vEdgeSd = vEdgeSd;
    this.hedgeMean = hedgeMean;
    this.hedgeSd = hedgeSd;
    this.intensityMean = intensityMean;
    this.rawRedMean = rawRedMean;
    this.rawBlueMean = rawBlueMean;
    this.rawGreenMean = rawGreenMean;
    this.exRedMean = exRedMean;
    this.exBlueMean = exBlueMean;
    this.exGreenMean = exGreenMean;
    this.valueMean = valueMean;
    this.saturationMean = saturationMean;
    this.hueMean = hueMean;
    this.segmentClass = SegmentClass.get(segmentClass);
  }

  /**
   * @return the column of the center pixel of the region
   */
  public double getRegionCentroidCol() {
    return regionCentroidCol;
  }

  /**
   * @return the row of the center pixel of the region
   */
  public double getRegionCentroidRow() {
    return regionCentroidRow;
  }

  /**
   * @return the number of pixels in a region
   */
  public double getRegionPixelCount() {
    return regionPixelCount;
  }

  /**
   * @return the results of a line extractoin algorithm that counts how many lines of length 5
   * (any orientation) with low contrast, less than or equal to 5, go through the region
   */
  public double getShortLineDensity5() {
    return shortLineDensity5;
  }

  /**
   * @return same as short-line-density-5 but counts lines of high contrast, greater than 5
   */
  public double getShortLineDensity2() {
    return shortLineDensity2;
  }

  /**
   * @return the mean of the contrast of horizontally adjacent pixels in the region
   */
  public double getvEdgeMean() {
    return vEdgeMean;
  }

  /**
   * @return the standard deviation of the contrast of horizontally adjacent pixels in the region
   */
  public double getvEdgeSd() {
    return vEdgeSd;
  }

  /**
   * @return the mean of the contrast of vertically adjacent pixels in the region
   */
  public double getHedgeMean() {
    return hedgeMean;
  }

  /**
   * @return the standard deviation of the contrast of vertically adjacent pixels in the region
   */
  public double getHedgeSd() {
    return hedgeSd;
  }

  /**
   * @return the average intensity of the region
   */
  public double getIntensityMean() {
    return intensityMean;
  }

  /**
   * @return the average R value of the region
   */
  public double getRawRedMean() {
    return rawRedMean;
  }

  /**
   * @return the average B value of the region
   */
  public double getRawBlueMean() {
    return rawBlueMean;
  }

  /**
   * @return the average G value of the region
   */
  public double getRawGreenMean() {
    return rawGreenMean;
  }

  /**
   * @return the measure of excess red in the region
   */
  public double getExRedMean() {
    return exRedMean;
  }

  /**
   * @return the measure of excess blue in the region
   */
  public double getExBlueMean() {
    return exBlueMean;
  }

  /**
   * @return the measure of excess green in the region
   */
  public double getExGreenMean() {
    return exGreenMean;
  }

  /**
   * @return the 3-d nonlinear transformation of RGB
   */
  public double getValueMean() {
    return valueMean;
  }

  /**
   * @return the 3-d nonlinear transformation of RGB
   */
  public double getSaturationMean() {
    return saturationMean;
  }

  /**
   * @return the 3-d nonlinear transformation of RGB
   */
  public double getHueMean() {
    return hueMean;
  }

  /**
   * @return the class of the image segment
   */
  public SegmentClass getSegmentClass() {
    return segmentClass;
  }

  @Override
  public String toString() {
    return "ImageSegment{" +
           "regionCentroidCol=" + regionCentroidCol +
           ", regionCentroidRow=" + regionCentroidRow +
           ", regionPixelCount=" + regionPixelCount +
           ", shortLineDensity5=" + shortLineDensity5 +
           ", shortLineDensity2=" + shortLineDensity2 +
           ", vEdgeMean=" + vEdgeMean +
           ", vEdgeSd=" + vEdgeSd +
           ", hedgeMean=" + hedgeMean +
           ", hedgeSd=" + hedgeSd +
           ", intensityMean=" + intensityMean +
           ", rawRedMean=" + rawRedMean +
           ", rawBlueMean=" + rawBlueMean +
           ", rawGreenMean=" + rawGreenMean +
           ", exRedMean=" + exRedMean +
           ", exBlueMean=" + exBlueMean +
           ", exGreenMean=" + exGreenMean +
           ", valueMean=" + valueMean +
           ", saturationMean=" + saturationMean +
           ", hueMean=" + hueMean +
           ", segmentClass=" + segmentClass +
           '}';
  }

  /**
   * the different image segment classes
   */
  public enum SegmentClass {
    BRICKFACE("brickface"), SKY("sky"), FOLIAGE("foliage"), CEMENT("cement"), WINDOW("window"),
    PATH("path"), GRASS("grass");

    private String value;
    private static final HashMap<String, SegmentClass> lookup = new HashMap<>();

    /**
     * allow constant-time constant lookup by its string value
     */
    static {
      for (SegmentClass s : SegmentClass.values()) {
        lookup.put(s.value, s);
      }
    }

    SegmentClass(String value) {
      this.value = value;
    }

    public static SegmentClass get(String value) {
      return lookup.get(value);
    }
  }
}
