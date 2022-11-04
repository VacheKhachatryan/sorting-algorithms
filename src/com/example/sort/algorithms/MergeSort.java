package com.example.sort.algorithms;

import java.util.ArrayList;
import java.util.List;

//Worst-case performance 0(nlogn)
//Best-case performance 0(nlogn)
//Average performance 0(nlogn)
public class MergeSort<T extends Comparable<T>> implements Sortable<T> {


  @Override
  public List<T> sort(List<T> list) {
    List<List<T>> divededSublists = divide(list);
    return merge(divededSublists);
  }

  private List<List<T>> divide(List<T> list) {
    List<List<T>> subLists = new ArrayList<>();
    int size = list.size();
    if (size <= 2) {
      if (size > 1 && list.get(0).compareTo(list.get(1)) > 0){
        T forSwap = list.get(0);
        list.set(0, list.get(1));
        list.set(1, forSwap);
      }
      subLists.add(list);
    } else {
      subLists.addAll(divide(list.subList(0, size / 2)));
      subLists.addAll(divide(list.subList(size / 2, size)));
    }

    return subLists;
  }

  private List<T> merge(List<List<T>> sublists) {
    int size = sublists.size();
    if(size == 1) {
      return sublists.get(0);
    }
    List<List<T>> nextSublist = new ArrayList<>();
    for (int i = 0; i < size; i += 2) {
      nextSublist.add(mergeAndSortSublists(sublists.get(i),
          (i + 1 < size) ? sublists.get(i + 1) : new ArrayList<>()));
    }

    return merge(nextSublist);
  }

  private List<T> mergeAndSortSublists(List<T> left, List<T> right) {
    List<T> mergedList = new ArrayList<>();
    int leftSize = left.size();
    int rightSize = right.size();
    int i = 0;
    int j = 0;
    while (i < leftSize && j < rightSize) {
      if (left.get(i).compareTo(right.get(j)) <= 0) {
        mergedList.add(left.get(i++));
      } else {
        mergedList.add(right.get(j++));
      }
    }
    while (i < leftSize) {
      mergedList.add(left.get(i++));
    }
    while (j < rightSize) {
      mergedList.add(right.get(j++));
    }
    return mergedList;
  }
}
