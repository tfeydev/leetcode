// The integration test file must import the struct or function it intends to test
// from the main crate (the "leetcode" library).
// The crate name is automatically derived from the [package] name in Cargo.toml.
use leetcode::problems::two_sum::Solution;

#[test]
fn example_1_integration_test() {
    // Standard test case: Array is sorted. 2 + 7 = 9. Expected indices: [0, 1].
    let nums = vec![2, 7, 11, 15];
    let target = 9;
    let expected = vec![0, 1];
    
    let mut result = Solution::two_sum(nums, target);
    // Sort the result to ensure the assertion works regardless of the index order returned.
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test 1 failed for standard case.");
}

#[test]
fn example_2_unsorted_integration_test() {
    // Array is unsorted, requiring the Hash Map to store indices correctly. 2 + 4 = 6.
    let nums = vec![3, 2, 4];
    let target = 6;
    let expected = vec![1, 2]; // Indices of 2 and 4.

    let mut result = Solution::two_sum(nums, target);
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test 2 failed for unsorted array.");
}

#[test]
fn example_3_duplicates_integration_test() {
    // Test case with duplicate numbers where the target is met by the duplicates themselves. 3 + 3 = 6.
    let nums = vec![3, 3];
    let target = 6;
    let expected = vec![0, 1]; // Indices of the two distinct 3s.

    let mut result = Solution::two_sum(nums, target);
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test 3 failed for duplicate values.");
}

// You could add another test case here, like:
/*
#[test]
fn edge_case_negative_numbers() {
    let nums = vec![-1, -2, -3, -4, -5];
    let target = -8; // -3 + -5 = -8
    let expected = vec![2, 4];

    let mut result = Solution::two_sum(nums, target);
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test failed for negative numbers.");
}
*/