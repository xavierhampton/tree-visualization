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
    private Node root;
    private int size;

    @Override
    public void insert(T value) {
        this.root = insert(this.root, value);
        size++;
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
        return Color.PURPLE;
    }

    @Override
    public TreeNode getRoot() {
        return this.root;
    }

    private Node insert(Node n, T value) {
        if (n == null) {
            return new Node(value);
        }

        //Insert into correct position
        if (value.compareTo(n.value) < 0) {
            n.left = insert(n.left, value);
        } else if (value.compareTo(n.value) > 0) {
            n.right = insert(n.right, value);
        }
        else {
            return n;
        }


        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));

        int balance = getBalance(n);

        //If node is unbalanced perform rotations.

        //Left Left
        if (balance >= 1 && value.compareTo(n.left.value) < 0) {
            return rightRotate(n);
        }
        //Right Right
        else if (balance <= -1 && value.compareTo(n.right.value) > 0) {
            return leftRotate(n);
        }
        //Left Right
        else if (balance >= 1 && value.compareTo(n.left.value) > 0) {
            n.left = leftRotate(n);
            return rightRotate(n);
        }
        //Right Left
        else if (balance <= -1 && value.compareTo(n.right.value) < 0) {
            n.right = rightRotate(n);
            return leftRotate(n);
        }

        return n;

    }

    private Node rightRotate(Node n) {
        Node l = n.left;
        Node lr = l.right;

        l.right = n;
        n.left = lr;

        l.height = 1 + Math.max(getHeight(l.left), getHeight(l.right));
        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));

        return l;
    }

    private Node leftRotate(Node n) {
        Node r = n.right;
        Node rl = r.left;

        r.left = n;
        n.right = rl;

        r.height = 1 + Math.max(getHeight(r.left), getHeight(r.right));
        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));

        return r;
    }

    private int getHeight(Node n) {
        if (n == null) return 0;
        return n.height;
    }

    private int getBalance(Node n) {
        if (n == null) {
            return 0;
        }
        return getHeight(n.left) - getHeight(n.right);
    }
}
