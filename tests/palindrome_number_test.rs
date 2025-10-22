// tests/palindrome_number_test.rs

// The integration tests must import the Solution struct from the main crate (the "leetcode" library).
use leetcode::problems::palindrome_number::Solution;

#[test]
fn basic_palindrome_integration_test() {
    // Standard palindrome case: 121.
    assert_eq!(Solution::is_palindrome(121), true, "Test failed for 121.");
}

#[test]
fn negative_number_integration_test() {
    // Negative numbers should always return false due to the leading sign.
    assert_eq!(Solution::is_palindrome(-121), false, "Test failed for -121.");
}

#[test]
fn non_palindrome_integration_test() {
    // Standard non-palindrome case: 123.
    assert_eq!(Solution::is_palindrome(123), false, "Test failed for 123.");
}

#[test]
fn zero_ending_number_integration_test() {
    // Numbers ending in 0 (and not 0 itself) should return false.
    assert_eq!(Solution::is_palindrome(10), false, "Test failed for 10.");
}

#[test]
fn large_odd_digit_palindrome_integration_test() {
    // Palindrome with an odd number of digits (e.g., 12321).
    assert_eq!(Solution::is_palindrome(12321), true, "Test failed for 12321 (odd length).");
}

#[test]
fn large_even_digit_palindrome_integration_test() {
    // Palindrome with an even number of digits (e.g., 123321).
    assert_eq!(Solution::is_palindrome(123321), true, "Test failed for 123321 (even length).");
}