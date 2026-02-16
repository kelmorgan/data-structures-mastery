package com.kelmorgan.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubString {



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
