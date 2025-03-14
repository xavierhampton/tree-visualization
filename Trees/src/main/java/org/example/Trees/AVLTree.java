package org.example.Trees;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.List;

public class AVLTree<T extends Comparable<T>> implements Tree<T> , Serializable {

    private class Node implements TreeNode<T> , Serializable{
        T value;
        Node left, right;
        int height;

        Node(T value) {
            this.value = value;
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
    public void insert(Comparable value) {

    }

    @Override
    public boolean delete(Comparable value) {
        return false;
    }

    @Override
    public boolean contains(Comparable value) {
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
    public List inorderTraversal() {
        return List.of();
    }

    @Override
    public String type() {
        return "";
    }

    @Override
    public Color color() {
        return null;
    }

    @Override
    public TreeNode getRoot() {
        return null;
    }

    private TreeNode rightRotate(Node n) {
        Node l = n.left;
        Node r = n.right;

        l.right = n;
        r.left = r;

        l.height = 1 + Math.max(l.left.height, l.right.height);
        r.height = 1 + Math.max(l.right.height, l.left.height);

        return l;
    }

    private TreeNode leftRotate() {
        return null;
    }
}
