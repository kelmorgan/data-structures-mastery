
package com.kelmorgan.datastructure;

public class Factorial {

    public static int factorial(int n) {
        if (n <= 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int factorial2(int n) {

        for (int i = n - 1; i > 0; i--) {
            n *= i;
        }

        return n;
    }

    public static void main(String[] args) {
        System.out.println(factorial(4));
    }
}