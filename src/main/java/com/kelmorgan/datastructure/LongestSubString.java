package com.kelmorgan.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubString {




    public static int lengthOfLongestSubstringBruteForce(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    private static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }


    public static int lengthOfLongestSubstringSlidingWindow(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (set.contains(c)) {
                    break; // Found duplicate, stop expanding
                }
                set.add(c);
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }

    public static int longestSubstringWithoutDuplicateCharacters(String input) {
        var set = new HashSet<Character>();
        int maxLength = 0;
        int left = 0;
        int right = 0;

        while (right < input.length()) {
            if (set.contains(input.charAt(right))) {
                set.remove(input.charAt(left));
                left++;
            }
            set.add(input.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }


        return maxLength;
    }

    public static int longestSubstringWithoutDuplicateCharactersUsingMap(String input) {
        var map = new HashMap<Character, Integer>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            char currentChar = input.charAt(right);

            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            map.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }


        return maxLength;
    }
}
