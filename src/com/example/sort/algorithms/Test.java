package com.example.sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class showing the average time need to sort using each algorithm implemented in this project.
 * In test cases both large and small collections are used.
 */
public class Test {

  public static final String TEST_RESULT_MESSAGE = "It takes %s nanoseconds to complete sort of %s collection using %s";

  public static void main(String[] args) {
    //Small collection
    ArrayList<Integer> smallCollection = new ArrayList<>();
    smallCollection.add(6);
    smallCollection.add(-1);
    smallCollection.add(60);
    smallCollection.add(15);
    smallCollection.add(3);
    smallCollection.add(-30);
    smallCollection.add(8);
    smallCollection.add(16);
    smallCollection.add(56);
    smallCollection.add(-8);
    smallCollection.add(0);
    smallCollection.add(0);
    smallCollection.add(3);

    //Testing with small collection
//    testAlgorithm(new BubbleSort<Integer>(), smallCollection, false); // 126 600 ns
//    testAlgorithm(new HeapSort<Integer>(), smallCollection, false); // 1 218 400 ns
//    testAlgorithm(new InsertionSort<Integer>(), smallCollection, false); // 99 600 ns (The second-best result)
//    testAlgorithm(new QuickSort<Integer>(), smallCollection, false); // 5 379 500 ns
//    testAlgorithm(new SelectionSort<Integer>(), smallCollection, false); // 80 800 ns (The best result)
//    testAlgorithm(new MergeSort<>(), smallCollection, false); // 297 500 ns

    // Large collection
    List<Integer> largeCollection = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 6000; i++) {
      largeCollection.add(random.nextInt());
    }

    //Testing with large collection
//    testAlgorithm(new BubbleSort<>(), largeCollection, true); // 227 025 000 ns
//    testAlgorithm(new HeapSort<>(), largeCollection, true); // 4 543 000 000 ns
//    testAlgorithm(new InsertionSort<>(), largeCollection, true); // 100 521 000 ns
//    testAlgorithm(new QuickSort<>(), largeCollection, true); // 56 018 400 ns (The second-best result)
//    testAlgorithm(new SelectionSort<>(), largeCollection, true); // 84 821 400 ns
//    testAlgorithm(new MergeSort<>(), largeCollection, true); // 18 281 400 ns (The best result)
  }

  public static void testAlgorithm(Sortable<Integer> sortable, List<Integer> listToSort,
      boolean isLarge) {
    long start = System.nanoTime();
    sortable.sort(listToSort);
    System.out.printf((TEST_RESULT_MESSAGE) + "%n", System.nanoTime() - start,
        isLarge ? "large" : "small", sortable.getClass().getSimpleName());
  }
}
