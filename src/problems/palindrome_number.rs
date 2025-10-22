/// # LeetCode 9: Palindrome Number
///
/// Implements an optimized solution to check if an integer is a **palindrome**.
///
/// A palindrome is a number that reads the same backward as forward (e.g., 121).
/// This implementation avoids converting the integer to a string.
///
/// # Method: Reversing Half the Number (O(log₁₀ n) Time)
///
/// The algorithm works by reversing only the second half of the number and comparing it
/// to the first half. The reversal stops when the original number (`x`) is no longer
/// greater than the reversed number (`reversed`).
///
/// **Initial Checks:**
/// 1. If $x < 0$, return `false` (negative sign breaks symmetry).
/// 2. If $x$ ends in $0$ (i.e., $x \pmod{10} = 0$) and $x \neq 0$, return `false` (only 0 is a palindrome ending in 0).
///
/// **Final Comparison:**
/// - **Even Digits:** If the number has an even count of digits (e.g., 1221), then `x` equals `reversed`.
/// - **Odd Digits:** If the number has an odd count of digits (e.g., 121), the middle digit is removed
///   from the reversed number by checking `x == reserved / 10`.
///
/// # Complexity Analysis
///
/// * **Time Complexity:** $O(\log_{10} n)$ — We only process half of the digits of $n$.
/// * **Space Complexity:** $O(1)$ — Only constant space is used.
///
/// # Example
///
/// ```rust
/// use leetcode::problems::palindrome_number::Solution;
/// 
/// assert_eq!(Solution::is_palindrome(121), true);
/// assert_eq!(Solution::is_palindrome(-121), false);
/// ```
pub struct Solution;

impl Solution {
    pub fn is_palindrome(x: i32) -> bool {
        // Initial checks: Excludes negative numbers and numbers ending in 0 (except 0 itself).
        if x < 0 || (x % 10 == 0 && x != 0) {
            return false;
        }
            
        let mut x = x;
        let mut reversed = 0;

        // Loop runs until we've reversed half of the digits (reversed >= x).
        while x > reversed {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        // Comparison for even (x == reversed) or odd (x == reversed / 10) length.
        x == reversed || x == reversed / 10
    }
}