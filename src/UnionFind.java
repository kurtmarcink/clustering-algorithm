import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * A representation of a union-find data structure for use with Kruskal's algorithm
 * @author Kurt Marcinkiewicz
 */
public class UnionFind {
  private HashMap<Integer, ArrayList<Integer>> items;
  private int[] setsArray;

  /**
   * @param items the array of 'items,' to be managed by Union-Find, represented by ints
   */
  public UnionFind(int[] items) {
    this.items = generateHashMap(items);
    this.setsArray = generateSetsArray(items);
  }

  /**
   * Retrieves the parent of the given item.
   * @param i the item to retrieve the parent of.
   * @return the parent of the given item
   */
  public int find(int i) {
    return setsArray[i];
  }

  /**
   * Unionizes two components together if the items are not already in the same component. Combines
   * the two components by allowing the smaller of the two to be engulfed by the other.
   * @param a the item of the first component
   * @param b the item of the second component
   * @return true if the items are not in the same component and the union is successful
   */
  public boolean union(int a, int b) {
    if (inSameComponent(a, b)) {
      return false;
    }
    else {
      int componentA = find(a);
      int componentB = find(b);

      if(items.get(componentA).size() >= items.get(componentB).size()) {
        return appendTo(componentA, componentB);
      }
      else {
        return appendTo(componentB, componentA);
      }
    }
  }

  /**
   * Appends one component to another. Updates the parent of every item in the first component to be
   * the parent of the second component and adds all the members of the second component to the
   * first component in the hash map.
   * @param to the component to receive the new members
   * @param from the component to be usurped
   * @return true if the appending was successful
   */
  private boolean appendTo(int to, int from) {
    for(int item : items.get(from)) {
      setsArray[item] = to;
    }
    items.get(to).addAll(0, items.get(from));
    items.remove(from);
    return true;
  }

  /**
   * Determines whether or not two 'items' are in the same component by comparing their parents.
   * @param a the first item
   * @param b the second item
   * @return true if both items are in the same component
   */
  private boolean inSameComponent(int a, int b) {
    return setsArray[a] == setsArray[b];
  }

  /**
   * Generates the initialized HashMap, used to determine the 'items' in each 'component' in
   * constant time. Initially, each item points to a list consisting of just itself.
   * @param items the array of 'items' managed by Union-Find
   * @return the initialized hash map
   */
  private HashMap<Integer, ArrayList<Integer>> generateHashMap(int[] items) {
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(items.length);

    for(int i = 0; i < items.length; i++) {
      map.put(i, new ArrayList<>());
      map.get(i).add(i);
    }
    return map;
  }

  /**
   * Generates the initialized setsArray, used to determine the 'parent' of every 'item' in constant
   * time. Initially, the parent of every item is just itself.
   * @param items the array of 'items' managed by Union-Find
   * @return the initialized array
   */
  private int[] generateSetsArray(int[] items) {
    return IntStream.range(0, items.length).toArray();
  }
}
