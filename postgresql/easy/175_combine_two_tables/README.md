# Problem 175 – Combine Two Tables

**LeetCode URL:** [https://leetcode.com/problems/combine-two-tables/](https://leetcode.com/problems/combine-two-tables/)

## Task

Combine the `Person` and `Address` tables to return all people along with their city and state.  
Include people without an address.

## Solution Notes

- The SQL solution is implemented in [`solution.sql`](./solution.sql).
- I used a **dedicated schema `lc_175`** to avoid table name conflicts with other problems.
- I chose a **LEFT JOIN** approach because it ensures that all entries from `Person` are included, even if they have no corresponding address, which is exactly what the problem requires.
- The structure keeps the solution minimal and testable locally in PostgreSQL, following a clear separation between schema setup and solution.
