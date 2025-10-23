// tests/longest_common_prefix_test.rs

use leetcode::problems::longest_common_prefix::Solution; 

#[test]
fn test_lcp_standard_case() {
    // Standard test case: prefix exists ("fl").
    let input = vec![
        "flower".to_string(),
        "flow".to_string(),
        "flight".to_string(),
    ];
    let expected = "fl".to_string();
    assert_eq!(Solution::longest_common_prefix(input), expected);
}

#[test]
fn test_lcp_no_common_prefix() {
    // Edge case: no common prefix exists ("").
    let input = vec![
        "dog".to_string(),
        "racecar".to_string(),
        "car".to_string(),
    ];
    let expected = "".to_string();
    assert_eq!(Solution::longest_common_prefix(input), expected);
}

#[test]
fn test_lcp_empty_array() {
    // Edge case: empty input array.
    let input: Vec<String> = vec![];
    let expected = "".to_string();
    assert_eq!(Solution::longest_common_prefix(input), expected);
}

#[test]
fn test_lcp_one_empty_string() {
    // Edge case: one string is empty, LCP must be empty.
    let input = vec!["apple".to_string(), "".to_string(), "app".to_string()];
    let expected = "".to_string();
    assert_eq!(Solution::longest_common_prefix(input), expected);
}

#[test]
fn test_lcp_substring_case() {
    // Edge case: one string is a prefix of all others ("a").
    let input = vec![
        "a".to_string(),
        "ab".to_string(),
        "abc".to_string(),
    ];
    let expected = "a".to_string();
    assert_eq!(Solution::longest_common_prefix(input), expected);
}