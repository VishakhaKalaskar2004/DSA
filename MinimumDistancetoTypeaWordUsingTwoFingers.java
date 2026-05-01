import java.util.Arrays;

public class MinimumDistancetoTypeaWordUsingTwoFingers {

    private static final int REST = 26;

    public int minimumDistance(String word) {
        int n = word.length();
        int[] dp = new int[27];
        Arrays.fill(dp, 0); 

        for (int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            int[] nextDp = new int[27];
            Arrays.fill(nextDp, Integer.MAX_VALUE / 2);

            for (int other = 0; other <= 26; other++) {
                // Option 1: Move the finger currently at 'curr' to 'next'
                nextDp[other] = Math.min(nextDp[other], dp[other] + getDist(curr, next));
                
                // Option 2: Move the 'other' finger to 'next'
                nextDp[curr] = Math.min(nextDp[curr], dp[other] + getDist(other, next));
            }
            dp = nextDp;
        }

        return Arrays.stream(dp).min().getAsInt();
    }

    private int getDist(int from, int to) {
        if (from == REST) return 0; 
        return Math.abs(from / 6 - to / 6) + Math.abs(from % 6 - to % 6);
    }

    public static void main(String[] args) {
        MinimumDistancetoTypeaWordUsingTwoFingers sol = new MinimumDistancetoTypeaWordUsingTwoFingers();

        // Test Case 1: "CAKE"
        // Explanation: Finger 1 types 'C', Finger 2 types 'A', then 'K', then 'E'.
        // Total distance: 0 + 0 + 2 + 1 = 3.
        String word1 = "CAKE";
        System.out.println("Test Case 1 (CAKE): " + sol.minimumDistance(word1)); // Output: 3

        // Test Case 2: "HAPPY"
        // Total distance: 6.
        String word2 = "HAPPY";
        System.out.println("Test Case 2 (HAPPY): " + sol.minimumDistance(word2)); // Output: 6

        // Test Case 3: "NEW"
        // Total distance: 3.
        String word3 = "NEW";
        System.out.println("Test Case 3 (NEW): " + sol.minimumDistance(word3)); // Output: 3
    }
}


