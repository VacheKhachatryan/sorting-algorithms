package com.example.sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Worst-case performance O(n^2)
//Best-case performance O(nlogn)
//Average performance O(nlogn)
public class QuickSort<T extends Comparable<T>> implements Sortable<T> {

  @Override
  public List<T> sort(List<T> list) {
    return partitionToList(doPartition(list));
  }

  private Partition doPartition(List<T> list) {
    if (list.isEmpty()) {
      return null;
    }
    int lastIndex = list.size() - 1;
    T partitionAround = list.get(lastIndex);
    list.remove(lastIndex);
    List<T> smaller = list.stream().filter(i -> i.compareTo(partitionAround) < 0).collect(
        Collectors.toList());
    List<T> greater = list.stream().filter(i -> i.compareTo(partitionAround) >= 0).collect(
        Collectors.toList());

    return new Partition<T>(partitionAround, doPartition(greater), doPartition(smaller));
  }

  private List<T> partitionToList (Partition<T> partition) {
    if(partition == null){
      return new ArrayList<>();
    }
    List<T> sortedList = new ArrayList<>();
    sortedList.add(partition.partitionAround);
    sortedList.addAll(0, partitionToList(partition.left));
    sortedList.addAll(partitionToList(partition.right));

    return sortedList;
  }

  static class Partition<T> {

    private final T partitionAround;

    private final Partition<T> right;
    private final Partition<T> left;

    public Partition(T partitionAround, Partition<T> right, Partition<T> left) {
      this.partitionAround = partitionAround;
      this.right = right;
      this.left = left;
    }
  }
}
