# Two Sum – Easy

**Package:** `br.tfeydev.two_sum`  
**LeetCode Problem:** https://leetcode.com/problems/two-sum/

---

## Problem Description

Given an array of integers `nums` and an integer `target`, return the indices of the two numbers that add up to the target.  
You may assume that each input has exactly one solution, and you cannot use the same element twice.

---

## Why This Solution?

The chosen solution uses a **HashMap** to store visited numbers and their indices.  
This makes it possible to check whether the complement (`target - nums[i]`) has already been seen.

### Key Points

- **Time Complexity:** O(n) — single pass through the array
- **Space Complexity:** O(n) — storing values in the HashMap
- **Efficient:** avoids nested loops or sorting
- **Clean & Industry Standard:** widely used solution in Java interviews

---

## File Structure

```bash
src/main/java/br/tfeydev/two_sum/
├── Solution.java
└── README.md
```

---

## How to Run

In IntelliJ:

Right-click → Run 'Solution.main()'


Or via terminal:

```bash
javac br/tfeydev/two_sum/Solution.java
java br.tfeydev.two_sum.Solution
```
