package com.kelmorgan.datastructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSums {
    // given an array of numbers the indices for two numbers that will dd up to get target

    //brute force

    // time - o(n^2) space - o(1)
    public static int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // number[i] + x  = target
    // x = target - number[i]
    // time - o(n) space - o(n)
    public static int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int x = target - numbers[i];
            if (map.containsKey(x)) {
                return new int[]{map.get(x), i};
            }
            map.put(numbers[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }
}
