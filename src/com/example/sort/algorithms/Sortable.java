package com.example.sort.algorithms;

import java.util.List;

public interface Sortable<T extends Comparable<T>> {

  List<T> sort(List<T> list);
}
