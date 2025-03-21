package org.example.Trees;

import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> , Serializable {

    private class Node implements TreeNode<T>, Serializable {
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
        public T getValue() {
            return value;
        }

        @Override
        public TreeNode<T> getLeft() {
            return left;
        }

        @Override
        public TreeNode<T> getRight() {
            return right;
        }

        public String getColor() {
            return this.color;
        }
    }

    private Node root;


    @Override
    public void insert(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            this.root.color = "black";
        }
        else {
            this.root = insert(this.root, value);
        }
    }

    boolean llRotationNeeded = false;
    boolean rrRotationNeeded = false;
    boolean lrRotationNeeded = false;
    boolean rlRotationNeeded = false;

    private Node insert(Node node, T value) {
        boolean rrConflict = false;

        if (node == null) {
            return new Node(value);
        }
        else if ( value.compareTo(node.getValue()) < 0) {
            node.left = insert(node.left, value);
            node.left.parent = node;
            if (node != this.root) {
                if (node.color.equals("red") && node.left.color.equals("red")) {
                    rrConflict = true;
                }
            }
        }
        else {
            node.right = insert(node.right, value);
            node.right.parent = node;
            if (node != this.root) {
                if (node.color.equals("red") && node.right.color.equals("red")) {
                    rrConflict = true;
                }
            }
        }

        if (llRotationNeeded == true) {
            node = rotateLeft(node);
            node.color = "black";
            node.left.color = "red";
            llRotationNeeded = false;
        }
        else if (rrRotationNeeded == true) {
            node = rotateRight(node);
            node.color = "black";
            node.right.color = "red";
            rrRotationNeeded = false;
        }
        else if (rlRotationNeeded == true) {
            node.right = rotateRight(node.right);
            node.right.parent = node;
            node = rotateLeft(node);
            node.color = "black";
            node.left.color = "red";
            rlRotationNeeded = false;
        }
        else if (lrRotationNeeded == true) {
            node.left = rotateLeft(node.left);
            node.left.parent = node;
            node = rotateRight(node);
            node.color = "black";
            node.right.color = "red";
            lrRotationNeeded = false;
        }

        if (rrConflict == true) {
            if (node.parent.right == node) {
                if (node.parent.left == null || node.parent.left.color.equals("black")) {
                    if (node.left != null && node.left.color.equals("red")) {
                        rlRotationNeeded = true;
                    }
                    else if (node.right != null && node.right.color.equals("red")) {
                        llRotationNeeded = true;
                    }
                }
                else {
                    node.parent.left.color = "black";
                    node.color = "black";
                    if (node.parent != this.root) {
                        node.parent.color = "red";
                    }
                }
            }
            else {
                if (node.parent.right == null || node.parent.right.color.equals("black")) {
                    if (node.left != null && node.left.color.equals("red")) {
                        rrRotationNeeded = true;
                    }
                    else if (node.right != null && node.right.color.equals("red")) {
                        lrRotationNeeded = true;
                    }
                }
                else {
                    node.parent.right.color = "black";
                    node.color = "black";
                    if (node.parent != this.root) {
                        node.parent.color = "red";
                    }
                }
            }
            rrConflict = false;
        }
        return(node);
    }

    private Node rotateLeft(Node node) {
        Node temp1 = node.right;
        Node temp2 = temp1.left;
        temp1.left = node;
        node.right = temp2;
        node.parent  = temp1;
        if (temp2 != null) {
            temp2.parent = node;
        }
        return temp1;
    }

    private Node rotateRight(Node node) {
        Node temp1 = node.left;
        Node temp2 = temp1.right;
        temp1.right = node;
        node.left = temp2;
        node.parent  = temp1;
        if (temp2 != null) {
            temp2.parent = node;
        }
        return temp1;
    }


    @Override
    public boolean delete(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) {
        if (this.root == null) {
            return false;
        }
        return true;
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





}






