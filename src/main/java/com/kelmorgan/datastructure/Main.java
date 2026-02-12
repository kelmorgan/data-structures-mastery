package com.kelmorgan.datastructure;


public class Main {

    public static void main(String[] args) {

        var factorial = Factorial.factorial(5);

        System.out.println(factorial);

        AVLTree tree = new AVLTree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);

    }
}
