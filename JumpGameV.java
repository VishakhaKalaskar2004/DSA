import java.util.Arrays;

public class JumpGameV {

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Initialize DP table with -1 to represent unvisited states
        
        int maxJumps = 0;
        // Try starting from every possible index to find the global maximum
        for (int i = 0; i < n; i++) {
            maxJumps = Math.max(maxJumps, dfs(arr, d, i, dp));
        }
        
        return maxJumps;
    }
    
    private int dfs(int[] arr, int d, int i, int[] dp) {
        // Return the cached result if already calculated
        if (dp[i] != -1) {
            return dp[i];
        }
        
        int n = arr.length;
        int maxFromHere = 1; // You can always stand on the current pillar (length 1)
        
        // Check jumps to the right
        for (int j = i + 1; j <= i + d && j < n; j++) {
            if (arr[j] >= arr[i]) break; // Blocked by a pillar that is equal or taller
            maxFromHere = Math.max(maxFromHere, 1 + dfs(arr, d, j, dp));
        }
        
        // Check jumps to the left
        for (int j = i - 1; j >= i - d && j >= 0; j--) {
            if (arr[j] >= arr[i]) break; // Blocked by a pillar that is equal or taller
            maxFromHere = Math.max(maxFromHere, 1 + dfs(arr, d, j, dp));
        }
        
        dp[i] = maxFromHere; // Memoize the result
        return dp[i];
    }

    public static void main(String[] args) {
        JumpGameV solver = new JumpGameV();

        // Test Case 1: Standard case with multiple jump options
        int[] arr1 = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d1 = 2;
        System.out.println("Test 1 Result: " + solver.maxJumps(arr1, d1)); // Expected: 4

        // Test Case 2: Array sorted in descending order
        int[] arr2 = {3, 3, 3, 3};
        int d2 = 3;
        System.out.println("Test 2 Result: " + solver.maxJumps(arr2, d2)); // Expected: 1

        // Test Case 3: Single element array
        int[] arr3 = {76};
        int d3 = 1;
        System.out.println("Test 3 Result: " + solver.maxJumps(arr3, d3)); // Expected: 1
    }
}

