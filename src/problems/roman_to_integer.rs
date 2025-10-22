/// # LeetCode 13: Roman to Integer
///
/// Converts a Roman numeral string to an integer.
/// Handles both additive and subtractive notation.
///
/// # Example
/// ```rust
/// use leetcode::problems::roman_to_integer::Solution;
///
/// assert_eq!(Solution::roman_to_int("III".to_string()), 3);
/// assert_eq!(Solution::roman_to_int("LVIII".to_string()), 58);
/// assert_eq!(Solution::roman_to_int("MCMXCIV".to_string()), 1994);
/// ```
pub struct Solution;

impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        // Mapping of Roman numerals to values
        let values = |c: char| -> i32 {
            match c {
                'I' => 1,
                'V' => 5,
                'X' => 10,
                'L' => 50,
                'C' => 100,
                'D' => 500,
                'M' => 1000,
                _ => 0,
            }
        };

        let chars: Vec<char> = s.chars().collect();
        let mut total = 0;

        for i in 0..chars.len() {
            let current = values(chars[i]);
            let next = if i + 1 < chars.len() {
                values(chars[i + 1])
            } else {
                0
            };

            if current < next {
                total -= current;
            } else {
                total += current;
            }
        }

        total
    }
}
