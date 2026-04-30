
public class XORAfterRangeMultiplicationQueriesI {

    /**
     * Updates nums based on queries [li, ri, ki, vi] and returns final XOR.
     * Operation: For each query, multiply nums[idx] by vi for idx = li, li+ki, ..., <= ri.
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int MOD = 1_000_000_007; // Standard modulo for competitive programming

        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            int step = query[2];
            int value = query[3];

            // Perform range multiplication with jump steps
            for (int idx = left; idx <= right; idx += step) {
                // Use 1L to prevent integer overflow before applying modulo
                nums[idx] = (int) ((long) nums[idx] * value % MOD);
            }
        }

        // Calculate bitwise XOR of the final modified array
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    // Main function to test the implementation with example cases
    public static void main(String[] args) {
        XORAfterRangeMultiplicationQueriesI sol = new XORAfterRangeMultiplicationQueriesI();

        // Example 1: nums = [1, 1, 1], queries = [[0, 2, 1, 4]]
        // Expected Output: 4
        int[] nums1 = {1, 1, 1};
        int[][] queries1 = {{0, 2, 1, 4}};
        System.out.println("Example 1 Result: " + sol.xorAfterQueries(nums1, queries1));

        // Example 2: nums = [2, 3, 1, 5, 4], queries = [[1, 4, 2, 3], [0, 2, 1, 2]]
        // Expected Output: 31
        int[] nums2 = {2, 3, 1, 5, 4};
        int[][] queries2 = {{1, 4, 2, 3}, {0, 2, 1, 2}};
        System.out.println("Example 2 Result: " + sol.xorAfterQueries(nums2, queries2));
    }
}


