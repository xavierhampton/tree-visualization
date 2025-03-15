package org.example.Trees;

import javafx.scene.paint.Color;

public class MaxHeap<T extends Comparable<T>> extends Heap<T> {
    //Parent of node at i is at i/2
    //left child at 2i
    //right child at 2i+1
    @Override
    protected void heapifyUp(int index) {
        if (index == 0) {
            return;
        }
        int parent = getParentIndex(index);
        if (heap.get(parent).compareTo(heap.get(index)) < 0) {
            swap(index, parent);
            heapifyUp(parent);
        }
    }

    @Override
    protected void heapifyDown(int index) {
        if (index >= (heap.size() - 1)) {
            return;
        }
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        if (right < heap.size()) {
            if (heap.get(left).compareTo(heap.get(right)) > 0) {
                if (heap.get(left).compareTo(heap.get(index)) > 0) {
                    swap(index, left);
                    heapifyDown(left);
                }
            } else if (heap.get(right).compareTo(heap.get(index)) > 0) {
                swap(index, right);
                heapifyDown(right);
            }
        } else if (left < heap.size()) {
            if (heap.get(left).compareTo(heap.get(index)) > 0) {
                swap(index, left);
                heapifyDown(left);
            }
        }
    }

    @Override
    public String type() {
        return "MaxHeap";
    }

    @Override
    public Color color() {
        return Color.MEDIUMAQUAMARINE;
    }
}
