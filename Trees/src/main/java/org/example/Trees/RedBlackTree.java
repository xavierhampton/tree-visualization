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

        //Gives the color of the node
        public String getColor() {
            if (color.equals("red")) {
                return "RED";
            }
            else {
                return "BLACK";
            }
        }
    }

    private Node root;
    private int size;

    //It there's a root it sets it to that and if not it recursively calls another insert to insert it into the tree
    @Override
    public void insert(T value) {
        size++;
        if (this.root == null) {
            this.root = new Node(value);
            this.root.color = "black";
        }
        else {
            this.root = insert(this.root, value);
        }
    }

    //Creating flags for when rotations are needed
    boolean llRotationNeeded = false;
    boolean rrRotationNeeded = false;
    boolean lrRotationNeeded = false;
    boolean rlRotationNeeded = false;

    private Node insert(Node node, T value) {
        //Flag made if there is a double red
        boolean rrConflict = false;

        //Inserts the node and finds the conflicts as it is traveling down
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

        //Doing proper rotates if we found errors while inserting
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

        //Resolving the red red conflict
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
        //returns the node
        return(node);
    }

    //Performs a left rotation
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

    //Performs a right rotation
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

    //Finds if there is a value in the tree
    private boolean contains(Node node, Comparable value) {
        if (node == null) {
            return false;
        }
        if (value.compareTo(node.value) == 0) {
            return true;
        } else if (value.compareTo(node.value) < 0) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }

    //performs an inorder traversal
    private void inorderTraversal(Node node, List<T> result) {
        if (node != null) {
            inorderTraversal(node.left, result);
            result.add(node.value);
            inorderTraversal(node.right, result);
        }
    }


    @Override
    public boolean delete(T value) {
        int beginSize = size;
        root = delete(this.root, value);
        return (beginSize > size);
    }

    @Override
    public boolean contains(Comparable value) {return contains(this.root, value);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
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

    private Node delete(Node node, Comparable value) {
        if (node == null) {
            return null;
        }

        //If Less Than Go Left
        if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left, value);
        }
        //If Greater Than, Go Right
        else if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right, value);
        }

        //BST DELETION
        else {
            //NO CHILDREN
            if (node.left == null && node.right == null) {
                node = null;
                size--;
            }

            //ONE CHILD
            else if (node.left == null || node.right == null) {
                node = node.left != null ? node.left : node.right;
                size--;

            }
            //TWO CHILDREN
            else {
                //Swap with Successor and delete it
                Node successor = findMinNode(node.right);
                node.value = successor.value;

                node.right = delete(node.right, node.value);
            }

        }
        return node;
    }
    private Node findMinNode(Node n) {
        Node min = n;
        while (min.left != null) {
            min = min.left;
        }
        return min;

    }
}






