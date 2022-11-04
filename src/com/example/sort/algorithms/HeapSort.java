package com.example.sort.algorithms;

import java.util.ArrayList;
import java.util.List;

//Worst-case performance 0(nlogn)
//Best-case performance 0(nlogn)
//Average performance 0(nlogn)
public class HeapSort<T extends Comparable<T>> implements Sortable<T> {

  @Override
  public List<T> sort(List<T> list) {
    Node<T> rootNode = getNode(list, 1);
    return heapify(rootNode, list.size());
  }

  private Node<T> getNode(List<T> list, int startIndex) {
    int size = list.size();
    int leftIndex = startIndex * 2;
    int rightIndex = leftIndex + 1;
    return new Node<>(list.get(startIndex - 1), leftIndex <= size ? getNode(list, leftIndex) : null,
        rightIndex <= size ? getNode(list, rightIndex) : null);
  }

  private List<T> heapify(Node<T> rootNode, int size) {
    List<T> sortedList = new ArrayList<>();
    if (size == 1) {
      sortedList.add(rootNode.value);
      return sortedList;
    }
    int maxIndexToAdjust = size / 2 - 1;
    while (maxIndexToAdjust >= 0) {
      Node<T> nodeToAdjust = getNodeByWay(rootNode, getNodeWay(maxIndexToAdjust + 1));
      adjustNode(nodeToAdjust);
      maxIndexToAdjust--;
    }
    sortedList.add(rootNode.value);
    removeLast(rootNode, size);
    if (size > 1) {
      sortedList.addAll(heapify(rootNode, size - 1));
    }

    return sortedList;
  }

  private Node<T> getNodeByWay(Node<T> node, List<Integer> nodeWay) {
    for (int i = nodeWay.size() - 1; i >= 0; i--) {
      node = ((nodeWay.get(i) == 0) ? node.left : node.right);
    }

    return node;
  }

  private List<Integer> getNodeWay(Integer index) {
    List<Integer> way = new ArrayList<>();
    if (index == 1) {
      return way;
    } else if (index % 2 == 0) {
      way.add(0);
    } else {
      way.add(1);
    }
    way.addAll(getNodeWay(index / 2));

    return way;
  }

  private void adjustNode(Node<T> nodeToAdjust) {
    Node<T> rightNode = nodeToAdjust.right;
    Node<T> leftNode = nodeToAdjust.left;
    T currentValue = nodeToAdjust.value;
    T rightValue = rightNode != null ? rightNode.value : null;
    T leftValue = leftNode != null ? leftNode.value : null;
    if (rightValue != null) {
      if (rightValue.compareTo(currentValue) > 0 || leftValue.compareTo(currentValue) > 0) {
        if (rightValue.compareTo(leftValue) > 0) {
          swapValues(rightNode, nodeToAdjust);
          adjustNode(rightNode);
        } else {
          swapValues(leftNode, nodeToAdjust);
          adjustNode(leftNode);
        }
      }
    } else if (leftValue != null && leftValue.compareTo(currentValue) > 0) {
      swapValues(leftNode, nodeToAdjust);
    }
  }

  private void removeLast(Node<T> rootNode, int size) {
    if (size == 1) {
      return;
    }
    Node<T> penultimateNode = getNodeByWay(rootNode, getNodeWay(size / 2));
    T lastNodeValue;
    if (size % 2 == 0) {
      lastNodeValue = penultimateNode.left.value;
      penultimateNode.left = null;
    } else {
      lastNodeValue = penultimateNode.right.value;
      penultimateNode.right = null;
    }
    rootNode.value = lastNodeValue;
  }

  private void swapValues(Node<T> node1, Node<T> node2) {
    T forSwap = node1.value;
    node1.value = node2.value;
    node2.value = forSwap;
  }

  static class Node<T> {

    private Node<T> left;
    private Node<T> right;
    private T value;

    public Node(T value, Node<T> left, Node<T> right) {
      this.value = value;
      this.right = right;
      this.left = left;
    }
  }
}
