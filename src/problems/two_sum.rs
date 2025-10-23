use std::collections::HashMap;

/// # LeetCode 1: Two Sum
///
/// Implements the optimized solution for the **Two Sum problem**.
///
/// Given an array of integers (`nums`) and an integer target, return the **indices**
/// of the two numbers such that they add up to the target. It is guaranteed that exactly
/// one solution exists, and you may not use the same element twice.
///
/// # Method: Hash Map (One-Pass)
///
/// This solution uses a **Hash Map** (or Hash Table) to reduce the computational complexity
/// from $O(n^2)$ (Brute Force) to **$O(n)$** (Linear Time).
///
/// The algorithm iterates through the array only once (one-pass):
/// 1. For each number, it calculates the **complement** needed to reach the target.
/// 2. It instantly checks if the complement already exists as a key in the map.
/// 3. If found, the solution is immediate. If not, the current number is stored for future checks.
///
/// * **Map Content:** Keys store the **value** (`num`), and values store the **index** (`i`).
/// * **Check:** `map.get(&complement)` performs a fast $O(1)$ average time lookup for the partner.
/// * **Return:** If the partner is found at index `j`, the function returns `[j, i]`.
///
/// # Complexity Analysis
///
/// * **Time Complexity:** $O(n)$ — The array is processed exactly once, and Hash Map operations
///     (insertion and lookup) take $O(1)$ average time.
/// * **Space Complexity:** $O(n)$ — In the worst-case scenario, the Hash Map will store $n-1$ elements.
///
/// # Example (Based on LeetCode Test Case)
///
/// ```rust
/// use leetcode::problems::two_sum::Solution;
/// 
/// let nums = vec![2, 7, 11, 15];
/// let target = 9;
/// let result = Solution::two_sum(nums, target);
/// // The sum of nums[0] (2) and nums[1] (7) is 9.
/// assert_eq!(result, vec![0, 1]);
/// ```
///
pub struct Solution; // Essential wrapper for LeetCode's environment

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        // Creates the Hash Map with an initial capacity equal to the input size to minimize reallocations.
        let mut map: HashMap<i32, usize> = HashMap::with_capacity(nums.len());

        for (i, &num) in nums.iter().enumerate() {
            // Calculates the required partner value (complement).
            let complement = target - num;

            // Checks if the complement has already been processed and stored in the map.
            if let Some(&j) = map.get(&complement) {
                // Complement found! Return the indices: j (complement's index) and i (current number's index).
                return vec![j as i32, i as i32];
            }

            // If the complement is not found, store the current number and its index for future checks.
            map.insert(num, i);
        }

        // Returns an empty vector if no solution is found (LeetCode guarantees one, but this is the fallback).
        vec![]
    }
}