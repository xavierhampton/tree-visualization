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
        Node newNode = new Node(value);
        this.root = insert(this.root, newNode);
        fixViolations(newNode);
    }

    private Node insert(Node node, Node newNode) {
        if (node == null) {
            return newNode;
        }
        if (newNode.value.compareTo(node.value) < 0) {
            node.left = insert(node.left, newNode);
            node.left.parent = node;
        }
        else if (newNode.value.compareTo(node.value) > 0) {
            node.right = insert(node.right, newNode);
            node.right.parent = node;
        }
        return node;
    }

    private void fixViolations(Node node) {
        Node parent = node.parent;
        Node uncle;
        if (node != root && parent.getColor().equals("red")) {
            Node grandParent = node.parent.parent;
            if (grandParent.left == parent) {
                uncle = grandParent.right;
            }
            else {
                uncle = grandParent.left;
            }

            if (uncle != null && uncle.getColor().equals("red")) {
                reColor(parent, uncle, grandParent);
            }
            else if (parent.left == node) {
                leftRotateRecolor(parent, uncle, grandParent);
            }
            else if (parent.left != node) {
                rightRotateRecolor(parent, uncle, grandParent);
            }
        }
        root.color = "black";
    }

    private void reColor(Node parent, Node uncle, Node grandParent) {
        swapColor(parent);
        swapColor(uncle);
        swapColor(grandParent);
        fixViolations(grandParent);
    }

    private void swapColor(Node node) {
        if (node.color.equals("red")) {
            node.color = "black";
        }
        else {
            node.color = "red";
        }
    }

    private void leftRotateRecolor(Node node, Node parent, Node grandParent) {
        if (node.right == parent) {
            rotateLeft(parent);
        }

        swapColor(parent);
        swapColor(grandParent);
        rotateRight(grandParent);
        if (node.left == parent) {
            fixViolations(parent);
        }
        else {
            fixViolations(grandParent);
        }

    }

    private void rightRotateRecolor(Node node, Node parent, Node grandParent) {
        if (node.left == parent) {
            rotateRight(parent);
        }

        swapColor(parent);
        swapColor(grandParent);
        rotateLeft(grandParent);
        if (node.left == parent) {
            fixViolations(grandParent);
        }
        else {
            fixViolations(parent);
        }

    }

    private void rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        leftNode.right = node;
        leftNode.parent = node.parent;
        updateChildren(node, leftNode);
        node.parent = leftNode;
    }

    private void rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        rightNode.left = node;
        rightNode.parent = node.parent;
        updateChildren(node, rightNode);
        node.parent = rightNode;
    }

    private void updateChildren(Node node, Node temp) {
        if (node.parent == null) {
            root = temp;
        }
        else if (node.parent.left == node) {
            node.parent.left = temp;
        }
        else {
            node.parent.right = temp;
        }
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






