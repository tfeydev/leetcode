# 🚦 Print in Order (LeetCode 1114)
---

## 💡 Intuition & Approach

The core challenge is to enforce a strict sequential execution order (1 → 2 → 3) for three methods (`first()`, `second()`, `third()`) that are called by independent threads. This requires a signaling mechanism where each method waits for the completion of the preceding method.

### Approach: Enforcing Order with Semaphores

We utilize the `java.util.concurrent.Semaphore` class to control the flow between the threads.

We define two semaphores: `semaphore2` and `semaphore3`. Both are initialized with **zero permits**.

* **Why Zero Permits?**
  By initializing them to zero, we ensure that any call to the `.acquire()` method on either semaphore will **immediately block** the calling thread. This is the crucial mechanism to guarantee that `second()` and `third()` cannot run until the required signal is received.

* **Flow Control:**
    1.  **Thread 1 (`first`):** Executes freely. After completion, it calls `semaphore2.release()`. This increments the permit count of `semaphore2` to **one**, effectively providing the signal.
    2.  **Thread 2 (`second`):** Calls `semaphore2.acquire()`. Since the permit count is now one, the thread **acquires** the permit (decrementing the count back to zero) and is **unblocked**. After execution, it calls `semaphore3.release()`.
    3.  **Thread 3 (`third`):** Calls `semaphore3.acquire()`. It blocks until the permit is released by `second()`, ensuring the correct order.

This pattern transforms the semaphores into simple, efficient **binary thread gates** that enforce the strict 1 → 2 → 3 sequence.

---

## ⚙️ Time & Space Complexity

* **Time Complexity (Synchronization Logic):** $O(1)$. The overhead of acquiring and releasing a semaphore is constant time.
* **Space Complexity:** $O(1)$. We only use two fixed `Semaphore` objects, regardless of the number of threads (which is fixed at 3).

---

## 🚀 Execution

This module uses a `Main.java` runner to start the three threads in a random order, verifying that the output is always `firstsecondthird`.

```bash
./gradlew :concurrency:print_in_order:run