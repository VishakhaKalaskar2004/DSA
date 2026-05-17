import java.util.Arrays;

public class MaxDotProductifTwoSubsequences {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        // dp[i][j] stores the max dot product using a subsequence of nums1[0...i-1] and nums2[0...j-1]
        int[][] dp = new int[n + 1][m + 1];
        
        // Initialize the DP table with a small value to handle negative products correctly
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -100_000_000);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int currentProduct = nums1[i - 1] * nums2[j - 1];
                
                dp[i][j] = Math.max(
                    currentProduct,                                 // Start a new subsequence with just this pair
                    Math.max(
                        currentProduct + dp[i - 1][j - 1],          // Add current pair to the previous best subsequence
                        Math.max(dp[i - 1][j], dp[i][j - 1])        // Skip current element from nums1 or nums2
                    )
                );
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        MaxDotProductifTwoSubsequences solution = new MaxDotProductifTwoSubsequences();

        // Test cases
        int[] nums1 = {2, 1, -2, 5};
        int[] nums2 = {3, 0, -6};
        System.out.println("Max Dot Product: " + solution.maxDotProduct(nums1, nums2)); // Output: 18

        int[] nums3 = {3, -2};
        int[] nums4 = {2, -6};
        System.out.println("Max Dot Product: " + solution.maxDotProduct(nums3, nums4)); // Output: 12

        int[] nums5 = {-1, -1};
        int[] nums6 = {1, 1};
        System.out.println("Max Dot Product: " + solution.maxDotProduct(nums5, nums6)); // Output: -1
    }
}


