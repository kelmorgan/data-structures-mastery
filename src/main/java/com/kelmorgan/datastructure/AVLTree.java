package com.kelmorgan.datastructure;

public class AVLTree {

    private AVLTree.Node root;


    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int value, AVLTree.Node root) {
        if (root == null)
            return new Node(value);

        if (value < root.value)
            root.leftChild = insert(value, root.leftChild);
        else
            root.rightChild = insert(value, root.rightChild);

        return root;
    }


    private static class Node {
        private final int value;
        private AVLTree.Node leftChild;
        private AVLTree.Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }
}
