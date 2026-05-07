public class FindAllPossibleStableBinaryArraysII {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        // dp[i][j][k] -> i zeros used, j ones used, k is the last digit added (0 or 1)
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base cases: single sequences of 0s or 1s within the limit
        for (int i = 1; i <= Math.min(zero, limit); i++) dp[i][0][0] = 1;
        for (int j = 1; j <= Math.min(one, limit); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // To end with 0: previous state could end with 0 or 1
                // We add one '0' to all valid arrays of (i-1, j)
                // But we must subtract arrays that already had 'limit' consecutive 0s
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }

                // To end with 1: same logic as above
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }

    public static void main(String[] args) {
        FindAllPossibleStableBinaryArraysII sol = new FindAllPossibleStableBinaryArraysII();

        // Example 1: zero = 1, one = 1, limit = 2
        // Possible: [0, 1], [1, 0]
        System.out.println("Result 1: " + sol.numberOfStableArrays(1, 1, 2)); // Output: 2

        // Example 2: zero = 1, one = 2, limit = 1
        // Possible: [1, 0, 1]
        System.out.println("Result 2: " + sol.numberOfStableArrays(1, 2, 1)); // Output: 1

        // Example 3: zero = 3, one = 3, limit = 2
        System.out.println("Result 3: " + sol.numberOfStableArrays(3, 3, 2)); // Output: 14
    }
}


