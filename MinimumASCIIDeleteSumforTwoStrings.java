public class MinimumASCIIDeleteSumforTwoStrings {

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // dp[i][j] stores the minimum delete sum for s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: both strings are empty
        dp[0][0] = 0;
        
        // Base case: s2 is empty, delete all characters from s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        
        // Base case: s1 is empty, delete all characters from s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                        dp[i - 1][j] + s1.charAt(i - 1), // Delete from s1
                        dp[i][j - 1] + s2.charAt(j - 1)  // Delete from s2
                    );
                }
            }
        }
        
        return dp[m][n];
    }

    // Main function to execute and test the code
    public static void main(String[] args) {
        MinimumASCIIDeleteSumforTwoStrings solver = new MinimumASCIIDeleteSumforTwoStrings();

        // Test Case 1: Expected output is 231 ("sea" and "eat" -> delete 's' and 't' -> 115 + 116)
        String s1 = "sea";
        String s2 = "eat";
        int result1 = solver.minimumDeleteSum(s1, s2);
        System.out.println("Test Case 1 Result: " + result1); // Output: 231

        // Test Case 2: Expected output is 403
        String s3 = "delete";
        String s4 = "leet";
        int result2 = solver.minimumDeleteSum(s3, s4);
        System.out.println("Test Case 2 Result: " + result2); // Output: 403
    }
}


