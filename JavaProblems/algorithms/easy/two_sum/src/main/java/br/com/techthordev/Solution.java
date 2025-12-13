package br.com.techthordev;

import java.util.HashMap;
import java.util.Map;

/**
 * The standard O(N) solution for the Two Sum problem using a Hash Map.
 * This class simulates the user-submitted code that will be benchmarked.
 * * Time Complexity: O(N) (due to single pass through the array)
 * Space Complexity: O(N) (due to the HashMap storage)
 */
public class Solution {

    /**
     * Finds two indices in the array that sum up to the target.
     * @param nums The input array.
     * @param target The target sum.
     * @return An array containing the two indices, or an empty array if not found.
     */
    public int[] findTwoSum(int[] nums, int target) {

        // Stores the number as key and its index as value.
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // 1. Check if the complement needed is already in the map
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }

            // 2. If not found, add the current number and its index to the map
            numMap.put(nums[i], i);
        }

        // No solution found (should not happen with our TestInputs)
        return new int[0];
    }
}