package com.kelmorgan.datastructure;

import org.w3c.dom.ls.LSOutput;

import java.util.Stack;

public class ValidParentheses {


    public static boolean isValidBruteForce(String s) {

        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }

        return s.isEmpty();
    }

    public static boolean isValid(String str) {
        var stack = new Stack<Character>();
        var charArray = str.toCharArray();

        for (var c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                var top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        System.out.println(stack);
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "()[]}";
        System.out.println(isValidBruteForce(str));
    }
}
