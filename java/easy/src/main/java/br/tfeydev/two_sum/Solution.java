package br.tfeydev.two_sum;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Solves the Two Sum problem.

     * Given an array of integers `nums` and an integer `target`,
     * returns the indices of the two numbers such that they add up to `target`.

     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    static void main() {
        Solution sol = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = sol.twoSum(nums, target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
    }
}
