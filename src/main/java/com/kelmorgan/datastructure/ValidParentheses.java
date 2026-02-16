package com.kelmorgan.datastructure;

import java.util.Stack;

public class ValidParentheses {


    public static boolean isValid(String str) {
        var stack = new Stack<Character>();
        var charArray = str.toCharArray();

        for (var c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                var top  = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        System.out.println(stack);
        return stack.isEmpty();
    }
}
