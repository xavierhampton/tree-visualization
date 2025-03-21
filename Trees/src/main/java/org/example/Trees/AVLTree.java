package org.example.Trees;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> implements Tree<T> , Serializable {

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

        public String getColor() { return null;}
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
        int beginSize = size;
        root = delete(this.root, value);
        return beginSize > size;
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
        return "AVL Tree";
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
        if (balance > 1 && value.compareTo(n.getLeft().getValue()) < 0) {
            return rightRotate(n);

        }
        //Right Right
        if (balance < -1 && value.compareTo(n.getRight().getValue()) > 0) {
            return leftRotate(n);
        }
        //Left Right
        if (balance > 1 && value.compareTo(n.getLeft().getValue()) > 0) {
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }
        //Right Left
        if (balance < -1 && value.compareTo(n.getRight().getValue()) < 0) {
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }

        return n;

    }

    private Node rightRotate(Node n) {
        Node l = n.left;
        Node lr = l.right;

        l.right = n;
        n.left = lr;

        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
        l.height = 1 + Math.max(getHeight(l.left), getHeight(l.right));

        return l;
    }

    private Node leftRotate(Node n) {
        Node r = n.right;
        Node rl = r.left;

        r.left = n;
        n.right = rl;

        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
        r.height = 1 + Math.max(getHeight(r.left), getHeight(r.right));

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

    private void inorderTraversal(Node node, List<T> result) {
        if (node != null) {
            inorderTraversal(node.left, result);
            result.add(node.value);
            inorderTraversal(node.right, result);
        }
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
//                Node nonNull = node.left != null ? node.left : node.right;
//
//                node.right = nonNull.right;
//                node.left = nonNull.left;
//                node.value = nonNull.value;
//
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

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);
        System.out.println(balance);

        //Left Left
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        //Right Right
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        //Left Right
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //Right Left
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
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
