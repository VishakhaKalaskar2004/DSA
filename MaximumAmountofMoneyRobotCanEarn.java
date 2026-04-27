import java.util.*;

class MaximumAmountofMoneyRobotCanEarn {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        // dp[r][c][k] = maximum coins at (r, c) with k neutralizations remaining
        // Initialize with a very small number to represent unvisited/impossible states
        long[][][] dp = new long[m][n][3];
        for (long[][] layer : dp) {
            for (long[] row : layer) {
                Arrays.fill(row, Long.MIN_VALUE / 2);
            }
        }

        // Base case: Starting at (0, 0)
        dp[0][0][2] = coins[0][0]; // Don't use neutralization
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0; // Use one neutralization if it's a robber
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                for (int k = 0; k <= 2; k++) {
                    if (dp[r][c][k] == Long.MIN_VALUE / 2) continue;

                    // Move Right or Down
                    int[][] nextSteps = {{r + 1, c}, {r, c + 1}};
                    for (int[] next : nextSteps) {
                        int nr = next[0], nc = next[1];
                        if (nr < m && nc < n) {
                            // Option 1: Don't neutralize the next cell
                            dp[nr][nc][k] = Math.max(dp[nr][nc][k], dp[r][c][k] + coins[nr][nc]);
                            
                            // Option 2: Neutralize the next cell (if it's a robber and we have k > 0)
                            if (k > 0 && coins[nr][nc] < 0) {
                                dp[nr][nc][k - 1] = Math.max(dp[nr][nc][k - 1], dp[r][c][k]);
                            }
                        }
                    }
                }
            }
        }

        // The answer is the max coins reaching the bottom-right with 0, 1, or 2 neutralizations left
        long res = Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
        return (int) res;
    }

    public static void main(String[] args) {
        MaximumAmountofMoneyRobotCanEarn sol = new MaximumAmountofMoneyRobotCanEarn();

        // Test Case 1: Simple path with robbers
        int[][] grid1 = {
            {10, 10, 10},
            {-10, -10, -10},
            {10, 10, 10}
        };
        System.out.println("Test 1 (Expected 40): " + sol.maximumAmount(grid1));

        // Test Case 2: Robbers worth neutralizing
        int[][] grid2 = {
            {1, -1, -1},
            {-1, -1, -1},
            {1, 1, 1}
        };
        System.out.println("Test 2 (Expected 3): " + sol.maximumAmount(grid2));
    }
}
