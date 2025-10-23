/// # LeetCode 14: Longest Common Prefix
///
/// Implements the Horizontal Scanning method to find the longest common prefix (LCP)
/// among an array of strings.
///
/// The algorithm starts with the first string as the LCP candidate and iteratively 
/// shortens it from the end until it is a prefix of the next string in the array.
///
/// # Method: Horizontal Scanning
///
/// 1. Initialize the LCP candidate with the first string, $S_1$.
/// 2. For each subsequent string, $S_i$:
///    - Repeatedly shorten the candidate LCP from the end until $S_i$ starts with the LCP.
///    - If the LCP becomes empty at any point, the function immediately returns `""`.
/// 3. The remaining LCP candidate is the final result.
///
/// # Complexity Analysis
///
/// Let $N$ be the number of strings in the array and $M$ be the length of the shortest string.
///
/// * **Time Complexity:** $O(N \cdot M)$ — In the worst case (e.g., all strings are different but long, like "a", "b", "c"),
///   the total number of character comparisons is the sum of all lengths of the LCP candidates during the shortening process.
///   A tight upper bound is $O(N \cdot M)$.
/// * **Space Complexity:** $O(M)$ — Space is used only to store the resulting LCP string.
///
/// # Example
///
/// ```rust
/// use leetcode::problems::longest_common_prefix::Solution;
/// 
/// assert_eq!(Solution::longest_common_prefix(vec![
///     "flower".to_string(), 
///     "flow".to_string(), 
///     "flight".to_string()]), "fl".to_string());
/// ```
pub struct Solution;

impl Solution {
    pub fn longest_common_prefix(strs: Vec<String>) -> String {
        // Edge Case: Return empty string if the input array is empty.
        if strs.is_empty() {
            return String::new();
        }

        // Initialize the prefix with the first string.
        let mut prefix = strs[0].clone();

        // Iterate through the rest of the strings (from index 1).
        for i in 1..strs.len() {
            let current_string = &strs[i];

            // While the current string does NOT start with the current prefix:
            while !current_string.starts_with(&prefix) {
                // Shorten the prefix by removing the last character.
                prefix.pop();

                // If the prefix becomes empty, there is no common prefix among all strings.
                if prefix.is_empty() {
                    return String::new();
                }
            }
        }

        // The remaining prefix is the LCP.
        prefix
    }
}