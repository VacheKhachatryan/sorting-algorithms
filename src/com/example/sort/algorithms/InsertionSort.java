package com.example.sort.algorithms;

import java.util.List;

//Worst-case performance O(n^2)
//Best-case performance O(n)
//Average performance O(n^2)
public class InsertionSort<T extends Comparable<T>> implements Sortable<T> {

  @Override
  public List<T> sort(List<T> list) {
    for (int i = 1; i < list.size(); i++) {
      boolean hasSmallerThan = false;
      for (int j = i - 1; j >= 0; j--) {
        T toInsert;
        if (list.get(j).compareTo(list.get(i)) > 0) {
          hasSmallerThan = true;
          if (j == 0) {
            toInsert = list.get(i);
            list.remove(i);
            list.add(j, toInsert);
            break;
          }
        } else if (hasSmallerThan && list.get(j).compareTo(list.get(i)) < 0) {
          toInsert = list.get(i);
          list.remove(i);
          list.add(j + 1, toInsert);
          break;
        }
      }
    }

    return list;
  }
}
