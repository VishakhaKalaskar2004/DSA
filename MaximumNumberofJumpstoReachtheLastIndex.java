import java.util.Arrays;

public class MaximumNumberofJumpstoReachtheLastIndex {

    /**
     * Finds the maximum number of jumps to reach the last index.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        // dp[i] will store the maximum number of jumps to reach index i from index 0
        int[] dp = new int[n];
        
        // Initialize with -1 to represent unreachable indices
        Arrays.fill(dp, -1);
        
        // Base case: 0 jumps are needed to stay at the starting index
        dp[0] = 0;

        // Iterate through each index j that we want to reach
        for (int j = 1; j < n; j++) {
            // For each reachable previous index i, check if we can jump to j
            for (int i = 0; i < j; i++) {
                // Condition for a valid jump:
                // 1. Index i must be reachable from the start (dp[i] != -1)
                // 2. The absolute difference must be within the target range
                if (dp[i] != -1 && Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        // Return the value at the last index; if it's still -1, it's unreachable
        return dp[n - 1];
    }

    public static void main(String[] args) {
        MaximumNumberofJumpstoReachtheLastIndex sol = new MaximumNumberofJumpstoReachtheLastIndex();

        // Test Case 1
        int[] nums1 = {1, 3, 6, 4, 1, 2};
        int target1 = 2;
        System.out.println("Output 1: " + sol.maximumJumps(nums1, target1)); // Expected: 3

        // Test Case 2
        int[] nums2 = {1, 3, 6, 4, 1, 2};
        int target2 = 3;
        System.out.println("Output 2: " + sol.maximumJumps(nums2, target2)); // Expected: 5

        // Test Case 3: Unreachable
        int[] nums3 = {1, 3, 6, 4, 1, 2};
        int target3 = 0;
        System.out.println("Output 3: " + sol.maximumJumps(nums3, target3)); // Expected: -1
    }
}


