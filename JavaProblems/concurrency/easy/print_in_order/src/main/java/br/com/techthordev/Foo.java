package br.com.techthordev;

import java.util.concurrent.Semaphore;

/**
 * Solution for the LeetCode Print in Order (FooBar) problem using Semaphores.
 * * Semaphores are used to strictly control the execution order:
 * - semaphore2: Blocks thread 'second' until 'first' is done.
 * - semaphore3: Blocks thread 'third' until 'second' is done.
 */
class Foo {

    // Semaphore to control the transition from 'first' to 'second'.
    // Initial permit: 0 (Blocks 'second' until 'first' releases it).
    private final Semaphore semaphore2 = new Semaphore(0);

    // Semaphore to control the transition from 'second' to 'third'.
    // Initial permit: 0 (Blocks 'third' until 'second' releases it).
    private final Semaphore semaphore3 = new Semaphore(0);

    public Foo() {
        // Constructor is empty as Semaphores are initialized above
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        // After "first" is printed, release the permit for 'second' to proceed.
        // This unblocks the thread waiting on semaphore2.acquire().
        semaphore2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // Wait for the 'first' method to finish and release the permit.
        // Blocks until semaphore2 has a permit available (i.e., first() ran).
        semaphore2.acquire();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        // After "second" is printed, release the permit for 'third' to proceed.
        // This unblocks the thread waiting on semaphore3.acquire().
        semaphore3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // Wait for the 'second' method to finish and release the permit.
        // Blocks until semaphore3 has a permit available (i.e., second() ran).
        semaphore3.acquire();

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

        // Note: No release needed here as this is the final step.
    }
}
