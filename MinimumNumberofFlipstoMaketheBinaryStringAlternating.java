
public class MinimumNumberofFlipstoMaketheBinaryStringAlternating {
    /**
     * Finds the minimum number of flips to make the binary string alternating.
     * Supports rotation by checking all possible windows of length n in s+s.
     */
    public int minFlips(String s) {
        int n = s.length();
        int diff1 = 0, diff2 = 0;
        String s2 = s + s; // Simulate all possible rotations
        
        // Two possible alternating patterns
        // Pattern 1: 0101...
        // Pattern 2: 1010...
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) != (i % 2 == 0 ? '0' : '1')) diff1++;
            if (s2.charAt(i) != (i % 2 == 0 ? '1' : '0')) diff2++;
            
            // Once window reaches length n, start comparing and sliding
            if (i >= n) {
                // Remove the effect of the character that just left the window
                if (s2.charAt(i - n) != ((i - n) % 2 == 0 ? '0' : '1')) diff1--;
                if (s2.charAt(i - n) != ((i - n) % 2 == 0 ? '1' : '0')) diff2--;
            }
            
            if (i >= n - 1) {
                ans = Math.min(ans, Math.min(diff1, diff2));
            }
        }
        return ans;
    }

    // Main function to run and test your solution
    public static void main(String[] args) {
        MinimumNumberofFlipstoMaketheBinaryStringAlternating sol = new MinimumNumberofFlipstoMaketheBinaryStringAlternating();

        // Example 1: Input "111000" -> Output should be 2
        String input1 = "111000";
        System.out.println("Input: " + input1 + " -> Output: " + sol.minFlips(input1));

        // Example 2: Input "010" -> Output should be 0 (already alternating)
        String input2 = "010";
        System.out.println("Input: " + input2 + " -> Output: " + sol.minFlips(input2));
        
        // Example 3: Input "1110" -> Output should be 1
        String input3 = "1110";
        System.out.println("Input: " + input3 + " -> Output: " + sol.minFlips(input3));
    }
}


