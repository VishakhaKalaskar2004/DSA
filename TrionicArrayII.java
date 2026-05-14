public class TrionicArrayII {
    
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) {
            return 0; // A trionic array needs at least 4 elements (l < p < q < r)
        }

        // Use a small enough value to represent negative infinity and prevent underflow
        long NEG_INF = Long.MIN_VALUE / 2;

        // DP States:
        // f1: Max sum ending at current element during the 1st strictly increasing phase
        // f2: Max sum ending at current element during the strictly decreasing phase
        // f3: Max sum ending at current element during the 2nd strictly increasing phase
        long f1 = NEG_INF;
        long f2 = NEG_INF;
        long f3 = NEG_INF;
        long maxTotalSum = NEG_INF;

        for (int i = 1; i < n; i++) {
            int prev = nums[i - 1];
            int curr = nums[i];

            if (prev < curr) {
                // Phase 3 (Increasing) can continue from previous Phase 3 or start from Phase 2
                f3 = Math.max(f3, f2) + curr;
                maxTotalSum = Math.max(maxTotalSum, f3);

                // Phase 2 (Decreasing) breaks because the values are increasing
                f2 = NEG_INF;

                // Phase 1 (Increasing) can continue from previous Phase 1 or start fresh from 'prev'
                f1 = Math.max(f1, (long) prev) + curr;
            } else if (prev > curr) {
                // Phase 2 (Decreasing) can continue from previous Phase 2 or transition from Phase 1
                f2 = Math.max(f2, f1) + curr;

                // Phase 1 and Phase 3 break because values are decreasing
                f1 = NEG_INF;
                f3 = NEG_INF;
            } else {
                // Flat/Equal elements violate strict monotonicity; reset all states
                f1 = NEG_INF;
                f2 = NEG_INF;
                f3 = NEG_INF;
            }
        }

        return maxTotalSum;
    }

    public static void main(String[] args) {
        TrionicArrayII solution = new TrionicArrayII();

        // Example 1
        int[] nums1 = {0, -2, -1, -3, 0, 2, -1};
        System.out.println("Example 1 Output: " + solution.maxSumTrionic(nums1)); // Expected: -4

        // Example 2
        int[] nums2 = {1, 4, 2, 7};
        System.out.println("Example 2 Output: " + solution.maxSumTrionic(nums2)); // Expected: 14

        // Test Case 3: Array with no valid trionic pattern
        int[] nums3 = {1, 2, 3, 4};
        System.out.println("Test Case 3 Output (No valid sequence): " + solution.maxSumTrionic(nums3)); 
    }
}


