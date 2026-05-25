public class DeleteColumnstoMakeSortedIII {
    
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        
        // dp[i] stores the length of the longest valid column sequence ending at column i
        int[] dp = new int[m];
        int maxKept = 1;
        
        // Initialize all dp positions to 1 since a single column is always sorted
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                boolean isValid = true;
                
                // Check if column j can precede column i in EVERY row
                for (int k = 0; k < n; k++) {
                    if (strs[k].charAt(j) > strs[k].charAt(i)) {
                        isValid = false;
                        break;
                    }
                }
                
                // If valid, update the maximum sequence length ending at i
                if (isValid) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxKept = Math.max(maxKept, dp[i]);
        }
        
        // Minimum deletions = total columns minus maximum kept columns
        return m - maxKept;
    }

    public static void main(String[] args) {
        DeleteColumnstoMakeSortedIII sol = new DeleteColumnstoMakeSortedIII();

        // Example 1
        String[] strs1 = {"babca", "bbazb"};
        System.out.println("Example 1: " + sol.minDeletionSize(strs1)); // Expected: 3

        // Example 2
        String[] strs2 = {"edcba"};
        System.out.println("Example 2: " + sol.minDeletionSize(strs2)); // Expected: 4

        // Example 3
        String[] strs3 = {"ghi", "def", "abc"};
        System.out.println("Example 3: " + sol.minDeletionSize(strs3)); // Expected: 0
    }
}


