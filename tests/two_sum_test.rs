// Die Integrationstests müssen deine Crate importieren.
// Der Name der Crate (hier "leetcode") wird automatisch aus der Cargo.toml übernommen.
use leetcode::problems::two_sum::Solution;

#[test]
fn example_1_integration_test() {
    let nums = vec![2, 7, 11, 15];
    let target = 9;
    let expected = vec![0, 1];
    
    let mut result = Solution::two_sum(nums, target);
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test 1 fehlgeschlagen");
}

#[test]
fn example_2_unsorted_integration_test() {
    let nums = vec![3, 2, 4];
    let target = 6;
    let expected = vec![1, 2];

    let mut result = Solution::two_sum(nums, target);
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test 2 fehlgeschlagen");
}

#[test]
fn example_3_duplicates_integration_test() {
    let nums = vec![3, 3];
    let target = 6;
    let expected = vec![0, 1];

    let mut result = Solution::two_sum(nums, target);
    result.sort_unstable();
    
    assert_eq!(result, expected, "Integration Test 3 fehlgeschlagen");
}