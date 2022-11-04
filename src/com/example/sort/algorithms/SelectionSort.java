package com.example.sort.algorithms;

import java.util.List;

//Worst-case performance O(n^2) comparisons, 0(n) swaps
//Best-case performance O(n^2) comparisons, 0(1) swaps
//Average performance O(n^2) comparisons, 0(n) swaps
public class SelectionSort<T extends Comparable<T>> implements Sortable<T> {

  @Override
  public List<T> sort(List<T> list) {
    int size = list.size();
    for (int i = 0; i < size - 1; i++) {
      int indexOfMin = i;
      for (int j = i + 1; j < size; j++) {
        if (list.get(j).compareTo(list.get(indexOfMin)) < 0) {
          indexOfMin = j;
        }
      }
      if (indexOfMin != i) {
        T forSwap = list.get(i);
        list.set(i, list.get(indexOfMin));
        list.set(indexOfMin, forSwap);
      }
    }

    return list;
  }
}
