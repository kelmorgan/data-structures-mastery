package com.kelmorgan.datastructure;

import java.util.Stack;

public class ReverseAString {

    public String reverseStringWithStack(String s) {

        var stack = new Stack<Character>();

        for (var c :  s.toCharArray()) {
            stack.push(c);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.toString();
    }

    public String reverseStringWithTwoPointers(String s) {
        var chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return String.valueOf(chars);
    }

    public static String reverseWithRecursion(String str) {
        if (str.isEmpty() || str.length() == 1) {
            return str;
        }

        return str.charAt(str.length() - 1) + reverseWithRecursion(str.substring(0, str.length() - 1));
    }
}
