import java.util.Arrays;
import java.util.Scanner;

public class TotalWavinessofNumbersinRangeII {
    // Memoization tables for tight = true (isLess = true)
    // Dimensions: [index][prev_digit][prev2_digit]
    private long[][][] memoCount;
    private long[][][] memoWaviness;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;
        String s = Long.toString(n);
        int len = s.length();

        // Initialize memoization arrays with -1
        memoCount = new long[len][11][11];
        memoWaviness = new long[len][11][11];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 11; j++) {
                Arrays.fill(memoCount[i][j], -1);
                Arrays.fill(memoWaviness[i][j], -1);
            }
        }

        long[] result = dfs(0, 10, 10, false, false, s);
        return result[1]; // Return the total waviness
    }

    // Returns an array of size 2: [count_of_valid_numbers, total_waviness_sum]
    private long[] dfs(int idx, int prev, int prev2, boolean isLess, boolean isStarted, String s) {
        if (idx == s.length()) {
            return new long[]{1, 0};
        }

        // If the current prefix is strictly less, we can look up the memo table
        if (isLess && memoCount[idx][prev][prev2] != -1) {
            return new long[]{memoCount[idx][prev][prev2], memoWaviness[idx][prev][prev2]};
        }

        int limit = isLess ? 9 : (s.charAt(idx) - '0');
        long totalCount = 0;
        long totalWaviness = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nextIsLess = isLess || (d < limit);

            if (!isStarted && d == 0) {
                // Handling leading zeros (number has not started yet)
                long[] next = dfs(idx + 1, 10, 10, nextIsLess, false, s);
                totalCount += next[0];
                totalWaviness += next[1];
            } else {
                // Check if the previous digit forms a peak or a valley
                int contrib = 0;
                if (prev != 10 && prev2 != 10) {
                    if ((prev > prev2 && prev > d) || (prev < prev2 && prev < d)) {
                        contrib = 1;
                    }
                }

                long[] next = dfs(idx + 1, d, prev, nextIsLess, true, s);
                totalCount += next[0];
                totalWaviness += next[1] + (contrib * next[0]);
            }
        }

        // Save to memoization tables if the boundary constraint is lifted
        if (isLess) {
            memoCount[idx][prev][prev2] = totalCount;
            memoWaviness[idx][prev][prev2] = totalWaviness;
        }

        return new long[]{totalCount, totalWaviness};
    }

    public static void main(String[] args) {
        TotalWavinessofNumbersinRangeII solution = new TotalWavinessofNumbersinRangeII();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter num1: ");
        long num1 = scanner.nextLong();

        System.out.print("Enter num2: ");
        long num2 = scanner.nextLong();

        long result = solution.totalWaviness(num1, num2);
        System.out.println("Total Waviness in range [" + num1 + ", " + num2 + "] is: " + result);

        scanner.close();
    }
}


