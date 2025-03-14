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
        Node lr = l.right;

        l.right = n;
        n.left = lr;

        l.height = 1 + Math.max(l.left.height, l.right.height);
        n.height = 1 + Math.max(n.right.height, n.left.height);

        return l;
    }

    private TreeNode leftRotate(Node n) {
        Node r = n.right;
        Node rl = r.left;

        r.left = n;
        n.right = rl;

        r.height = 1 + Math.max(r.left.height, r.right.height);
        n.height = 1 + Math.max(n.right.height, n.left.height);

        return r;
    }
}
