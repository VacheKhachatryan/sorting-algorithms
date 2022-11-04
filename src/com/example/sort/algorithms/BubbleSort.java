package com.example.sort.algorithms;

import java.util.List;

//Worst-case performance O(n^2) comparisons O(n^2) swaps
//Best-case performance O(n) comparisons O(1) swaps
//Average performance O(n^2) comparisons O(n^2) swaps
public class BubbleSort<T extends Comparable<T>> implements Sortable<T> {

  @Override
  public List<T> sort(List<T> list) {
    boolean hasCorrection = false;
    for (int i = 0; i < list.size() - 1; i++) {
      T thisElement = list.get(i);
      T nextElement = list.get(i + 1);
      if (thisElement.compareTo(nextElement) > 0) {
        hasCorrection = true;
        list.set(i, nextElement);
        list.set(i + 1, thisElement);
      }
    }
    return hasCorrection ?
        sort(list) :
        list;
  }
}
