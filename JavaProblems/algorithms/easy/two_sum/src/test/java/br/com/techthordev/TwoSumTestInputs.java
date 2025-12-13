package br.com.techthordev;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Utility class to generate scalable test inputs for the Two Sum problem.
 * This is crucial for accurately measuring time complexity growth (O(N), O(N^2)).
 */
public class TwoSumTestInputs {

    // Define the scaling factors (N) for performance measurement.
    // These values should cover a wide range to see the Big O growth clearly.
    public static final int[] SCALING_N = {
            1000, 10000, 100000, 500000, 1000000
    };

    /**
     * Generates a random array of size N with a guaranteed "Two Sum" pair.
     * The guaranteed solution prevents the worst-case (no-solution) scenario from skewing results.
     * * @param n The size of the array (N).
     * @param target The target sum that must be present in the array.
     * @return The generated array of integers.
     */
    public static int[] generateTwoSumArray(int n, int target) {
        Random random = new Random();

        // 1. Generate N random numbers (e.g., between 0 and 1000, max value is arbitrary)
        int[] nums = IntStream.generate(() -> random.nextInt(1000))
                .limit(n)
                .toArray();

        // Ensure a solution is present:
        int index1 = random.nextInt(n);
        int index2;

        // Ensure index2 is different from index1
        do {
            index2 = random.nextInt(n);
        } while (index1 == index2);

        // Generate two numbers that sum up to the target
        int val1 = random.nextInt(target / 2) + 1;
        int val2 = target - val1;

        // 2. Insert the guaranteed solution into the array
        nums[index1] = val1;
        nums[index2] = val2;

        return nums;
    }
}
