use leetcode::problems::roman_to_integer::Solution;

#[test]
fn roman_to_integer_iii() {
    assert_eq!(Solution::roman_to_int("III".to_string()), 3);
}

#[test]
fn roman_to_integer_lviii() {
    assert_eq!(Solution::roman_to_int("LVIII".to_string()), 58);
}

#[test]
fn roman_to_integer_mcmxciv() {
    assert_eq!(Solution::roman_to_int("MCMXCIV".to_string()), 1994);
}

#[test]
fn roman_to_integer_single_symbols() {
    assert_eq!(Solution::roman_to_int("I".to_string()), 1);
    assert_eq!(Solution::roman_to_int("V".to_string()), 5);
    assert_eq!(Solution::roman_to_int("X".to_string()), 10);
    assert_eq!(Solution::roman_to_int("L".to_string()), 50);
    assert_eq!(Solution::roman_to_int("C".to_string()), 100);
    assert_eq!(Solution::roman_to_int("D".to_string()), 500);
    assert_eq!(Solution::roman_to_int("M".to_string()), 1000);
}

#[test]
fn roman_to_integer_subtractive_pairs() {
    assert_eq!(Solution::roman_to_int("IV".to_string()), 4);
    assert_eq!(Solution::roman_to_int("IX".to_string()), 9);
    assert_eq!(Solution::roman_to_int("XL".to_string()), 40);
    assert_eq!(Solution::roman_to_int("XC".to_string()), 90);
    assert_eq!(Solution::roman_to_int("CD".to_string()), 400);
    assert_eq!(Solution::roman_to_int("CM".to_string()), 900);
}

#[test]
fn roman_to_integer_mixed_cases() {
    assert_eq!(Solution::roman_to_int("MDCLXVI".to_string()), 1666); // 1000+500+100+50+10+5+1
    assert_eq!(Solution::roman_to_int("MMMCMXCIX".to_string()), 3999); // largest typical value
}
