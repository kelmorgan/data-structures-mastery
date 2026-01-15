package com.kelmorgan.datastructure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CharFinder {


    public char findFirstRepeatingCharacter(String str) {
        Set<Character> set = new LinkedHashSet<>();

        for (char c : str.toCharArray()) {
            if (set.contains(c))
                return c;
            set.add(c);
        }

        return Character.MIN_VALUE;
    }

    public char findFirstNonRepeatingCharacter(String str) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        var chars = str.toCharArray();

        for (char c : chars) {
            var count = charCountMap.get(c) != null ? charCountMap.get(c) : 0;

            charCountMap.put(c, count + 1);
        }

        for (char c : chars) {
            if (charCountMap.get(c) == 1) {
                return c;
            }
        }

        return Character.MIN_VALUE;
    }
}
