package br.com.techthordev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Import the solution and test input classes from the same package/module
import br.com.techthordev.Solution;
import br.com.techthordev.TwoSumTestInputs;

/**
 * JUnit 5 test class to measure the execution time complexity of the Two Sum solution
 * for various input sizes (N).
 */
public class TwoSumPerformanceRunner {

    private static final int REPETITIONS = 15; // Number of runs to average the time
    private static final int TARGET_SUM = 999;

    // Tag used by the Spring Boot ChallengeRunnerService to parse the test results from the log output.
    private static final String LOG_TAG = "THOR_PERF_RESULT|";

    @Test
    void measureTwoSumTimeComplexity() {

        System.out.println("--- Starting Two Sum Performance Test ---");

        for (int n : TwoSumTestInputs.SCALING_N) {

            // 1. Setup: Generate input data for the current N size
            int[] nums = TwoSumTestInputs.generateTwoSumArray(n, TARGET_SUM);

            long totalDurationNs = 0;
            boolean success = true;

            // Run the algorithm multiple times to get a stable average time
            for (int i = 0; i < REPETITIONS; i++) {
                Solution solution = new Solution(); // Instantiate the user's class

                long startTime = System.nanoTime();
                int[] result = solution.findTwoSum(nums, TARGET_SUM);
                long endTime = System.nanoTime();

                totalDurationNs += (endTime - startTime);

                // 2. Functional Check: Ensure the logic worked (must return two indices)
                if (result.length != 2 || result[0] < 0 || result[1] < 0 || result[0] == result[1]) {
                    success = false;
                    break; // Stop repetition on failure
                }
            }

            // 3. Calculate and Log Results
            double avgDurationMs = (totalDurationNs / (double) REPETITIONS) / 1_000_000.0;

            // Output the result in a pipe-separated, parsable format for the backend service:
            String resultLog = String.format("%sN:%d|Time:%.3f|Reps:%d|Success:%b|Target:%d",
                    LOG_TAG, n, avgDurationMs, REPETITIONS, success, TARGET_SUM);

            System.out.println(resultLog);

            // Fail the JUnit test if the solution logic was incorrect for any N
            Assertions.assertTrue(success, "The Two Sum logic failed for N=" + n + ".");
        }

        System.out.println("--- Performance Testing Finished ---");
    }
}