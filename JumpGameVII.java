public class JumpGameVII {
    
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        // If the last character is not '0', we can never land on it.
        if (s.charAt(n - 1) != '0') {
            return false;
        }

        boolean[] dp = new boolean[n];
        dp[0] = true;
        int reachableCount = 0;

        for (int i = 1; i < n; i++) {
            // Add to sliding window: a new index becomes eligible to be jumped FROM
            if (i >= minJump) {
                if (dp[i - minJump]) {
                    reachableCount++;
                }
            }
            // Remove from sliding window: an index is now too far to jump FROM to reach 'i'
            if (i > maxJump) {
                if (dp[i - maxJump - 1]) {
                    reachableCount--;
                }
            }

            // If there's at least one reachable '0' in the [i-maxJump, i-minJump] range
            if (reachableCount > 0 && s.charAt(i) == '0') {
                dp[i] = true;
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        JumpGameVII sol = new JumpGameVII();
        
        // Example 1
        String s1 = "011010";
        int min1 = 2, max1 = 3;
        System.out.println("Example 1: " + sol.canReach(s1, min1, max1)); // Expected: true

        // Example 2
        String s2 = "01101110";
        int min2 = 2, max2 = 3;
        System.out.println("Example 2: " + sol.canReach(s2, min2, max2)); // Expected: false
    }
}

