# Two Sum (LeetCode Problem)

This project contains a Java implementation of the classic LeetCode problem **Two Sum**.

## 📖 Problem Statement

Given an an array of integers `nums` and an integer `target`, return the **indices of the two numbers** such that they add up to `target`.  
It is guaranteed that exactly one solution exists.

For full description got directly to [LeetCode Problem Description](https://leetcode.com/problems/two-sum/description/)

## Examples

**Example 1:**
```bash
Input: nums = [2,7,11,15], target = 9 
Output: [0,1] 
Explanation: Because nums[0] + nums[1] == 9, 
we return [0, 1].
````

**Example 2:**

```bash
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

**Example 3:**

```bash
Input: nums = [3,3], target = 6
Output: [0,1]
```

## 🛠️ Java Solution

[View Solution.java](https://www.google.com/search?q=src/main/java/br/com/techthordev/Solution.java)
**// ÄNDERUNG: Pfad zur Solution.java vereinfacht und korrigiert**

## ⚡ Complexity Analysis

#### Time Complexity

>   - Best Case: O(1) — if the solution is found in the very first iteration.
>   - Average Case: O(n) — each element is processed once, with constant-time HashMap operations.
>   - Worst Case: O(n) — if the solution is found at the end of the array.

> → HashMap lookups (containsKey, get, put) are amortized O(1), so the loop dominates the complexity.

#### Space Complexity

>   - HashMap Storage: In the worst case, the HashMap stores all n elements before finding the solution → O(n).
>   - Output Array: Always of size 2 → O(1).
>   - Total: O(n) additional space.

#### Trade-offs

>   - Pros: Very efficient compared to the naive O(n²) double-loop approach
>   - Cons: Requires extra memory for the HashMap.
>   - Alternative: Sorting + two-pointer approach (O(n log n)), but it loses original indices and requires extra handling.

-----

## ⏱️ Performance Measurement

The actual execution time and complexity curve are verified using dedicated JUnit 5 performance tests against scaled input sizes ($N$).

### Test Setup

* **Runner:** [`TwoSumPerformanceRunner.java`](https://www.google.com/search?q=src/test/java/br/com/techthordev/TwoSumPerformanceRunner.java)
* **Measurement:** Uses `System.nanoTime()` and averages results over **15 repetitions** to mitigate JVM warm-up (JIT) and CPU fluctuations.
* **Input Scaling:** Tests run against $N = [1,000, 10,000, 100,000, 500,000, 1,000,000]$.

### Execution

To run the performance test and view the raw metrics (which are later parsed by the Spring Boot Backend):

```bash
# Execute the JUnit performance test within the module
./gradlew :algorithms:easy:two_sum:test
```

### Quick Local Run

To quickly test the functional correctness of the `Solution.java` during development:

```bash
# Execute the Main.java entry point
./gradlew :algorithms:easy:two_sum:run
```

-----

## 📂 Project Structure

```bash
src/
 └── main/java/br/com/techthordev/
     ├── Main.java             # Quick local functional test runner
     └── Solution.java         # The O(N) HashMap solution code
 └── test/java/br/com/techthordev/
     ├── TwoSumPerformanceRunner.java # Measures execution time across scaled N
     └── TwoSumTestInputs.java        # Generates the test data for the runner
```

## ✅ Notes

>   - The solution uses a HashMap to store previously seen numbers and their indices.
>   - For each number, we check if its complement (target - nums[i]) has already been seen.
>   - If yes, we return the pair of indices. Otherwise, we store the current number in the map.

