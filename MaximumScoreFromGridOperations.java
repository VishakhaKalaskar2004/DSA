import java.util.Arrays;

public class MaximumScoreFromGridOperations {

    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] prefix = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                prefix[j][i + 1] = prefix[j][i] + grid[i][j];
            }
        }

        // dp[h][0]: Increasing/Flat (current h >= prev h)
        // dp[h][1]: Decreasing (current h < prev h)
        // dp[h][2]: Valley/Reset (current column follows a column of height 0)
        long[][] dp = new long[n + 1][3];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);

        // Initial: Column -1 has "height 0"
        dp[0][0] = 0;

        for (int j = 0; j < n; j++) {
            long[][] next = new long[n + 1][3];
            for (int i = 0; i <= n; i++) Arrays.fill(next[i], -1);

            for (int h = 0; h <= n; h++) {
                for (int p = 0; p <= n; p++) {
                    // --- Case 1: Increasing (h >= p) ---
                    long prevInc = Math.max(dp[p][0], dp[p][2]);
                    if (prevInc != -1) {
                        long score = (j > 0 && h > p) ? prefix[j - 1][h] - prefix[j - 1][p] : 0;
                        next[h][0] = Math.max(next[h][0], prevInc + score);
                    }

                    // --- Case 2: Decreasing (h < p) ---
                    long prevAny = Math.max(dp[p][0], dp[p][1]);
                    if (prevAny != -1) {
                        long score = (p > h) ? prefix[j][p] - prefix[j][h] : 0;
                        next[h][1] = Math.max(next[h][1], prevAny + score);
                    }
                }
                
                // --- Case 3: Valley Transition ---
                if (j > 0) {
                    long maxPrev = -1;
                    for (int p = 0; p <= n; p++) {
                        for (int s = 0; s < 3; s++) maxPrev = Math.max(maxPrev, dp[p][s]);
                    }
                    next[0][2] = Math.max(next[0][2], maxPrev);
                }
            }
            dp = next;
        }

        long ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int s = 0; s < 3; s++) ans = Math.max(ans, dp[i][s]);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumScoreFromGridOperations sol = new MaximumScoreFromGridOperations();

        // Example 1
        int[][] grid1 = {
            {0,0,0,0,0},
            {0,0,3,0,0},
            {0,1,0,0,0},
            {5,0,0,3,0},
            {0,0,0,0,2}
        };
        System.out.println("Example 1 Output: " + sol.maximumScore(grid1)); // Expected: 11

        // Example 2
        int[][] grid2 = {
            {10,9,0,0,15},
            {7,1,0,8,0},
            {5,20,0,11,0},
            {0,0,0,1,2},
            {8,12,1,10,3}
        };
        System.out.println("Example 2 Output: " + sol.maximumScore(grid2)); // Expected: 94
    }
}


