package org.example.Trees;

import javafx.scene.paint.Color;

public class MinHeap<T extends Comparable<T>> extends Heap<T> {
    //Upheaps: used when adding a new value to ensure heap is satisfied
    @Override
    protected void heapifyUp(int index) {
        if (index == 0) {
            return;
        }
        int parent = getParentIndex(index);
        //If parent is greater than index, swap
        if (heap.get(parent).compareTo(heap.get(index)) > 0) {
            swap(index, parent);
            heapifyUp(parent); //Recursive call on parent
        }
    }

    //Downheaps: used in deletion to ensure heap is satisfied
    @Override
    protected void heapifyDown(int index) {
        //Would happen if index is last element i.e. child
        if (index >= (heap.size() - 1)) {
            return;
        }
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        //If right exists
        if (right < heap.size()) {
            //If left is less than right
            if (heap.get(left).compareTo(heap.get(right)) < 0) {
                //If left is less than index, swap
                if (heap.get(left).compareTo(heap.get(index)) < 0) {
                    swap(index, left);
                    heapifyDown(left); //Recursive call on swapped child
                }
            //Else if right is less than index, swap
            } else if (heap.get(right).compareTo(heap.get(index)) < 0) {
                swap(index, right);
                heapifyDown(right); //Recursive call on swapped child
            }
        //Else if left exists, (and right doesn't)
        } else if (left < heap.size()) {
            //If left is greater than index, swap
            if (heap.get(left).compareTo(heap.get(index)) < 0) {
                swap(index, left);
                heapifyDown(left); //Recursive call on swapped child
            }
        }
    }

    //Returns type of tree
    @Override
    public String type() {
        return "Min Heap";
    }

    //Returns set color
    @Override
    public Color color() {
        return Color.ORANGERED;
    }
}
