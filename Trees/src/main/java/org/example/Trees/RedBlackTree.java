package org.example.Trees;

import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> , Serializable {

    private class Node implements TreeNode<T> , Serializable{
        T value;
        Node left, right;
        Node parent;
        String color;

        Node(T value) {
            this.value = value;
            this.parent = null;
            this.color = "red";
        }

        @Override
        public T getValue() { return value; }

        @Override
        public TreeNode<T> getLeft() { return left; }

        @Override
        public TreeNode<T> getRight() { return right; }

        public String getColor() { return this.color; }
    }

    private Node root;
    private Node temp;



    @Override
    public void insert(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            this.root.color = "black";
        }
        else {
            //Binary Search
            Node x = new Node(value);
            temp = root;
            while((temp.left != null) && (temp.right != null)) {
                if (value.compareTo(temp.left.value) < 0) {
                    temp = temp.left;
                }
                else {
                    temp = temp.right;
                }
            }

            insert(this.root, x);
        }
    }

    @Override
    public boolean delete(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) { return contains(this.root, value);
    }

    @Override
    public void clear() {
        root = null;
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
        return this.root;
    }

    private Node rotateLeft(Node n) {
        Node x = n.right;
        Node y = x.left;
        x.left = n;
        n.right = y;
        n.parent = x;
        if(y != null) {
            y.parent = n;
        }
        return(x);
    }

    private Node rotateRight(Node n) {
        Node x = n.left;
        Node y = x.right;
        x.right = n;
        n.left = y;
        n.parent = x;
        if(y != null) {
            y.parent = n;
        }
        return(x);
    }

    private Node insert(Node T, Node x) {
        while (x != root && x.parent.color.equals("red")) {
            if (x.parent == x.parent.parent.left) {
                Node y = x.parent.parent.right;
                if (y.color.equals("red")) {
                    x.parent.color = "black";
                    y.color = "red";
                }
            }
        }


    }


}






