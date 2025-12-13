package br.com.techthordev;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Performance benchmark runner for the PrintInOrder problem (LeetCode 1114).
 * * Measures the average synchronization time over many repetitions.
 * The primary metric is the time taken to guarantee the 1 -> 2 -> 3 sequence.
 */
public class PrintInOrderPerformanceRunner {

    private static final int REPETITIONS = 100000; // Number of times to run the sequence
    private static final String METRIC_PREFIX = "THOR_PERF_RESULT|";

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        boolean overallSuccess = true;

        for (int i = 0; i < REPETITIONS; i++) {
            // Create a new Foo instance for each test run to ensure a clean state
            Foo foo = new Foo();

            // Define the three printing Runnables (they just run, no actual print needed for perf test)
            Runnable r1 = () -> {};
            Runnable r2 = () -> {};
            Runnable r3 = () -> {};

            // Create and start threads in a randomized order
            Thread t1 = new Thread(() -> {
                try { foo.first(r1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            Thread t2 = new Thread(() -> {
                try { foo.second(r2); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            Thread t3 = new Thread(() -> {
                try { foo.third(r3); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });

            // Start them (order is irrelevant, synchronization handles it)
            t1.start();
            t2.start();
            t3.start();

            try {
                // Wait for all three threads to complete the sequence
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                overallSuccess = false;
                break;
            }
        }

        long endTime = System.nanoTime();
        double durationMs = TimeUnit.NANOSECONDS.toMicros(endTime - startTime) / 1000.0;

        // Output the standardized metric string for the Spring Boot parser
        System.out.printf(
                METRIC_PREFIX + "TotalTime:%.3f|Reps:%d|Success:%b|Target:Concurrency%n",
                durationMs,
                REPETITIONS,
                overallSuccess
        );
    }
}