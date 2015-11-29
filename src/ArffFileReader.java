import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Capable of reading an .arff file and deserializing it into an ArrayList of ImageSegment objects
 * @author Kurt Marcinkiewicz
 */
public class ArffFileReader {
  private final File file;

  /**
   * @param file the .arff file to use
   */
  public ArffFileReader(File file) {
    this.file = file;
  }

  /**
   * Processes an .arff file and builds a graph based off of the image segments and their relative
   * distances.
   * @return the graph
   * @throws IOException
   */
  public ImageSegmentGraph processLineByLine() throws IOException {
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine() && sc.hasNext("(%.*)|(@.*)")) {
      sc.nextLine();
    }

    ImageSegmentGraph graph = new ImageSegmentGraph();

    while (sc.hasNextLine()) {
      ImageSegmentNode node = processLine(sc.nextLine());
      graph.addImageSegmentNode(node);
    }
    System.out.println(graph.getImageSegmentNodes().size());
    System.out.println(graph.getImageSegmentEdges().size());

    System.out.println("");

    return graph;
  }

  /**
   * Creates an ImageSegmentNode based off of the image segment information from a line in .arff
   * file.
   * @param line the line to parse
   * @return the ImageSegmentNode generated from the parsed data
   */
  private ImageSegmentNode processLine(String line){
    Scanner scanner = new Scanner(line);
    scanner.useDelimiter(",");
    double regionCentroidCol = scanner.nextDouble();
    double regionCentroidRow = scanner.nextDouble();
    double regionPixelCount = scanner.nextDouble();
    double shortLineDensity5 = scanner.nextDouble();
    double shortLineDensity2 = scanner.nextDouble();
    double vEdgeMean = scanner.nextDouble();
    double vEdgeSd = scanner.nextDouble();
    double hedgeMean = scanner.nextDouble();
    double hedgeSd = scanner.nextDouble();
    double intensityMean = scanner.nextDouble();
    double rawRedMean = scanner.nextDouble();
    double rawBlueMean = scanner.nextDouble();
    double rawGreenMean = scanner.nextDouble();
    double exRedMean = scanner.nextDouble();
    double exBlueMean = scanner.nextDouble();
    double exGreenMean = scanner.nextDouble();
    double valueMean = scanner.nextDouble();
    double saturationMean = scanner.nextDouble();
    double hueMean = scanner.nextDouble();
    String segmentClass = scanner.next();

    return new ImageSegmentNode(regionCentroidCol, regionCentroidRow, regionPixelCount,
                                shortLineDensity5, shortLineDensity2, vEdgeMean, vEdgeSd,
                                hedgeMean, hedgeSd, intensityMean, rawRedMean, rawBlueMean,
                                rawGreenMean, exRedMean, exBlueMean, exGreenMean, valueMean,
                                saturationMean, hueMean, segmentClass);
  }

}
