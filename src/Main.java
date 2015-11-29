import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int k = getClusterNumber(sc);
    System.out.println("Got: " + k);
    File file = getArffFile(sc);

    ImageSegmentGraph graph;

    try {
      ArffFileReader arffFileReader = new ArffFileReader(file);
      graph = arffFileReader.processLineByLine();
    }
    catch(IOException e) {
      System.out.println("Could not parse file.");
      e.printStackTrace();
      return;
    }

    ArrayList<ImageSegmentEdge> mst = Algorithms
        .singleLinkCluster(graph.getImageSegmentEdges(), graph.getImageSegmentNodes().size(), k);
  }

  /**
   * Prompt the user for the number of clusters to use.
   * @param sc the scanner to use
   * @return the number of clusters.
   */
  private static int getClusterNumber(Scanner sc) {
    int k;
    do {
      System.out.println("Please enter the number of clusters you wish to use: ");
      while (!sc.hasNextInt()) {
        System.out.println("That's not a number!");
        sc.next();
      }
      k = sc.nextInt();
    } while (k <= 0);
    return k;
  }

  /**
   * Prompt the user for the .arff file to use.
   * @param sc the scanner to use
   * @return the file
   */
  private static File getArffFile(Scanner sc) {
    System.out.println("Please enter the path of the .arff file you wish to use: ");
    sc.nextLine();

    File file = new File(sc.nextLine());
    while (!file.exists()) {
      System.out.println("That's not a file!");
      file = new File(sc.nextLine());
      while (file.getName().endsWith(".arff")) {
        System.out.println("That's not an .arff file!");
        file = new File(sc.nextLine());
      }
    }
    return file;
  }
}
