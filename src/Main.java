import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int step = 100000000;
    int k;

    File file = getArffFile(sc);

    if (promptUser(sc, "Do you wish to loop over multiple cluster sizes?")) {
      step = chooseKStep(sc);
      k = step;
    }
    else {
      k = getClusterNumber(sc);
    }

    boolean printClusters = promptUser(sc, "Do you wish to print the clusters (WARNING: THIS COULD "
                                           + "GET BIG!)?");
    boolean printPurity = promptUser(sc, "Do you wish to print the cluster purity?");

    ImageSegmentGraph graph;

    try {
      ArffFileReader arffFileReader = new ArffFileReader(file);
      graph = arffFileReader.processLineByLine();

      System.out.println(graph);
    }
    catch(IOException e) {
      System.out.println("Could not parse file.");
      e.printStackTrace();
      return;
    }
    for (; k <= graph.getImageSegmentNodes().size(); k+= step) {
      ArrayList<ImageSegmentEdge> mst = Algorithms
          .singleLinkCluster(graph.getImageSegmentEdges(), graph.getImageSegmentNodes().size(), k);

      ArrayList<HashSet<Integer>> clusters = Algorithms.clustersFromEdges(graph.getImageSegmentNodes(),
                                                                          mst, k);

      if (printClusters) {
        printClusters(clusters, graph.getImageSegmentNodes());
      }

      if (printPurity) {
        System.out.println(k + ", " +
                           Algorithms.purityFromClusters(clusters, graph.getImageSegmentNodes()));
      }
    }
  }

  /**
   * Prompt the user to answer a question with 'Y' or 'N' and return the associated boolean
   * @param sc the scanner to use
   * @param question the question to ask the user
   * @return true if the user answers 'Y' to the question
   */
  private static boolean promptUser(Scanner sc, String question) {
    String ans;

    do {
      System.out.println(question);
      System.out.println("Please enter 'Y' or 'N': ");
      ans = sc.next();
    } while (!("Y".equals(ans) ||  "N".equals(ans)));

    return "Y".equals(ans);
  }

  /**
   * Prompt the user for the number of clusters to use.
   * @param sc the scanner to use
   * @return the number of clusters
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
   * Prompt the user for the step size for the value of k.
   * @param sc the scanner to use
   * @return the step size
   */
  private static int chooseKStep(Scanner sc) {
    int step;
    do {
      System.out.println("Please choose a step size for k (must be be between 1 and 500: ");
      while (!sc.hasNextInt()) {
        System.out.println("That's not a number!");
        sc.next();
      }
      step = sc.nextInt();
    } while (step <= 0 || step > 500);
    return step;
  }

  /**
   * Prompt the user for the .arff file to use.
   * @param sc the scanner to use
   * @return the file
   */
  private static File getArffFile(Scanner sc) {
    System.out.println("Please enter the path to the .arff file you wish to use: ");

    File file = new File(sc.nextLine());
    while (!file.exists()) {
      System.out.println("That's not a file!");
      file = new File(sc.nextLine());
      while (!file.getName().endsWith(".arff")) {
        System.out.println("That's not an .arff file!");
        file = new File(sc.nextLine());
      }
    }
    return file;
  }

  /**
   * Print the clusters in friendly format to the console
   * @param clusters the list of clusters
   * @param nodes the nodes in the graph
   */
  private static void printClusters(ArrayList<HashSet<Integer>> clusters,
                             ArrayList<ImageSegmentNode> nodes) {
    for (int i = 0; i < clusters.size(); i++) {
      System.out.println("CLUSTER " + (i + 1) + ":");
      for (Integer nodeIndex : clusters.get(i)) {
        System.out.println(nodes.get(nodeIndex));
      }
    }
  }
}
