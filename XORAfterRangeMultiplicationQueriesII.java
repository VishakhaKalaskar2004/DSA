import java.util.*;

public class XORAfterRangeMultiplicationQueriesII {
    private static final int MOD = 1_000_000_007;

    /**
     * Solves the XOR After Range Multiplication Queries problem using 
     * Square Root Decomposition to avoid Time Limit Exceeded (TLE) errors.
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] data = new long[n];
        for (int i = 0; i < n; i++) data[i] = nums[i];

        // Square Root Decomposition threshold
        // Strides smaller than B are batched; larger ones are processed directly.
        int B = 50; 
        long[][] batch = new long[B][n + B];
        for (int k = 1; k < B; k++) {
            Arrays.fill(batch[k], 1);
        }

        for (int[] query : queries) {
            int l = query[0], r = query[1], k = query[2];
            long v = query[3];
            
            // Optimization: Skip neutral multiplications
            if (v == 1) continue;

            if (k >= B) {
                // Large stride: Process in O(N/K), which is at most O(N/B)
                for (int i = l; i <= r; i += k) {
                    data[i] = (data[i] * v) % MOD;
                }
            } else {
                // Small stride: Use a Multiplicative Difference Array
                batch[k][l] = (batch[k][l] * v) % MOD;
                int nextOut = l + ((r - l) / k + 1) * k;
                if (nextOut < n) {
                    batch[k][nextOut] = (batch[k][nextOut] * modInverse(v)) % MOD;
                }
            }
        }

        // Apply all batched updates in one final pass per stride k < B
        for (int k = 1; k < B; k++) {
            for (int i = 0; i < n; i++) {
                if (i >= k) batch[k][i] = (batch[k][i] * batch[k][i - k]) % MOD;
                if (batch[k][i] != 1) {
                    data[i] = (data[i] * batch[k][i]) % MOD;
                }
            }
        }

        // Calculate final bitwise XOR of all array elements
        int result = 0;
        for (long val : data) {
            result ^= (int) val;
        }
        return result;
    }

    private long modInverse(long n) {
        return power(n, MOD - 2);
    }

    private long power(long x, long y) {
        long res = 1;
        x %= MOD;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % MOD;
            y = y >> 1;
            x = (x * x) % MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        XORAfterRangeMultiplicationQueriesII solver = new XORAfterRangeMultiplicationQueriesII();
        
        // Sample test case matching your input pattern
        int[] nums = {92991379, 56649752, 22210877, 13612059, 65969024};
        int[][] queries = {
            {0, 4, 1, 2}, // Multiply all by 2
            {0, 4, 2, 3}  // Multiply indices 0, 2, 4 by 3
        };

        int result = solver.xorAfterQueries(nums, queries);
        System.out.println("Final XOR result: " + result);
    }
}
