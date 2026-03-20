
public class possibleStableBinaryArrays {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        
        // dp[i][j][k] is the number of stable arrays with i zeros and j ones 
        // ending with digit k (0 or 1).
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base cases: arrays with only one type of digit up to the limit
        for (int i = 1; i <= Math.min(zero, limit); i++) dp[i][0][0] = 1;
        for (int j = 1; j <= Math.min(one, limit); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // Transition for arrays ending in 0
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    long invalid = dp[i - limit - 1][j][1];
                    dp[i][j][0] = (dp[i][j][0] - invalid + MOD) % MOD;
                }

                // Transition for arrays ending in 1
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    long invalid = dp[i][j - limit - 1][0];
                    dp[i][j][1] = (dp[i][j][1] - invalid + MOD) % MOD;
                }
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }

    public static void main(String[] args) {
        possibleStableBinaryArrays solution = new possibleStableBinaryArrays();

        // Example 1: zero = 1, one = 1, limit = 2
        // Possible arrays: [0, 1], [1, 0]
        System.out.println("Result (1, 1, 2): " + solution.numberOfStableArrays(1, 1, 2));

        // Example 2: zero = 1, one = 2, limit = 1
        // Possible arrays: [1, 0, 1]
        System.out.println("Result (1, 2, 1): " + solution.numberOfStableArrays(1, 2, 1));

        // Example 3: zero = 3, one = 3, limit = 2
        System.out.println("Result (3, 3, 2): " + solution.numberOfStableArrays(3, 3, 2));
    }
}
