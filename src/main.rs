use std::env;

// Import the Solution structs for each problem and rename them
// to avoid conflicts, as they are all named 'Solution'.
use leetcode::problems::{
    two_sum::Solution as TwoSumSolution,
    palindrome_number::Solution as PalindromeNumberSolution,
    roman_to_integer::Solution as RomanToIntegerSolution,
    longest_common_prefix::Solution as LongestCommonPrefixSolution,
};

fn main() {
    // Read command-line arguments.
    let args: Vec<String> = env::args().collect();

    // Check if a problem name was provided.
    if args.len() < 2 {
        println!("Please provide the name of the problem to run.");
        println!("Available problems: two_sum, palindrome_number, roman_to_integer, longest_common_prefix");
        return;
    }

    let problem = &args[1];

    // Use a 'match' block to run the correct problem.
    match problem.as_str() {
        "two_sum" => {
            let nums = vec![2, 7, 11, 15];
            let target = 9;
            let result = TwoSumSolution::two_sum(nums.clone(), target);
            println!("Two Sum: Input: vec!{:?}, target: {} -> Result: {:?}", nums, target, result);
        }
        "palindrome_number" => {
            let number = 121;
            let result = PalindromeNumberSolution::is_palindrome(number);
            println!("Palindrome Number: Input: {} -> Is palindrome? {}", number, result);
        }
        "roman_to_integer" => {
            let roman = "MCMLXXIII".to_string();
            let result = RomanToIntegerSolution::roman_to_int(roman.clone());
            println!("Roman to Integer: Input: \"{}\" -> Result: {}", roman, result);
        }
        "longest_common_prefix" => {
            let input = vec!["flower".to_string(), "flow".to_string(), "flight".to_string()];
            let result = LongestCommonPrefixSolution::longest_common_prefix(input.clone());
            println!("Longest Common Prefix: Input: {:?} -> Result: \"{}\"", input, result);
        }
        _ => {
            println!("Unknown problem: {}", problem);
            println!("Available problems: two_sum, palindrome_number, roman_to_integer, longest_common_prefix");
        }
    }
}
