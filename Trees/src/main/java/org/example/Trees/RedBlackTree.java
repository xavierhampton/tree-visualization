package org.example.Trees;

import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> , Serializable {
    private class Node implements TreeNode<T> , Serializable{
        T value;
        Node left, right;
        int height;

        Node(T value) {
            this.value = value; this.height = 1;
        }

        @Override
        public T getValue() { return value; }

        @Override
        public TreeNode<T> getLeft() { return left; }

        @Override
        public TreeNode<T> getRight() { return right; }

        public String getColor() { return null; }
    }

    @Override
    public void insert(T value) {

    }

    @Override
    public boolean delete(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public List<T> inorderTraversal() {
        return List.of();
    }

    @Override
    public String type() {
        return "RBT";
    }

    @Override
    public Color color() {
        return null;
    }

    @Override
    public TreeNode<T> getRoot() {
        return null;
    }
}
