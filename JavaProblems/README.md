# 💻 Java Problems & Performance Analysis
---

This `JavaProblems` module is a multi-project Gradle setup designed to implement, test, and analyze solutions for algorithmic and concurrency challenges.

The primary goals of this repository are:
1.  **Efficient Solutions:** Implementing optimal solutions (e.g., $O(N)$ for Two Sum).
2.  **Performance Analysis:** Benchmarking solutions against scaled input sizes ($N$) to verify time complexity claims.
3.  **Clean Code:** Maintaining high code quality and clear Javadoc documentation.

## 📂 Project Structure Overview

The project modules are categorized by the nature of the challenge:

| Directory | Content | Description |
| :--- | :--- | :--- |
| **[algorithms/](./algorithms/README.md)** | LeetCode/HackerRank Style Problems | Categorized by difficulty (easy, medium, hard). Focuses on efficient data manipulation and logic. |
| **[concurrency/](./concurrency/README.md)** | Multithreading & Synchronization | Problems related to thread safety, deadlocks, and parallel processing using Java's `java.util.concurrent` package. |
| `build.gradle` | Main Gradle Configuration | Defines common dependencies and sub-project configurations. |
| `settings.gradle` | Module Setup | Defines the directory structure of the multi-module project. |

## 🚀 Getting Started

The project is built using **Gradle**. To run any specific sub-module's tests or main method, use the Gradle wrapper (`./gradlew`) from this root directory.

**Example Execution (Two Sum):**
```bash
# Execute the quick functional run method
./gradlew :algorithms:easy:two_sum:run

# Run the performance test suite and output metrics to console
./gradlew :algorithms:easy:two_sum:test