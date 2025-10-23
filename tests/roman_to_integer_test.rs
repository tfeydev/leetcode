use leetcode::problems::roman_to_integer::Solution;

#[test]
fn example_1() {
    assert_eq!(Solution::roman_to_int("III".to_string()), 3);
}

#[test]
fn example_2() {
    assert_eq!(Solution::roman_to_int("LVIII".to_string()), 58);
}

#[test]
fn example_3() {
    assert_eq!(Solution::roman_to_int("MCMXCIV".to_string()), 1994);
}
