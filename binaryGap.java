public class binaryGap {

    // Solution class and binaryGap method as requested
    static class Solution {
        public int binaryGap(int n) {
            int maxGap = 0;
            // Using a distance variable 'd' starting from a low value to handle 
            // the first '1' encounter easily without special flags.
            int d = -32; 
            
            while (n > 0) {
                if (n % 2 == 1) {
                    // When a '1' is found, update maxGap with current distance 'd'.
                    maxGap = Math.max(maxGap, d);
                    // Reset distance counter 'd' to 0 for the next gap calculation.
                    d = 0;
                }
                d++; // Increment distance
                n /= 2; // Shift bits
            }
            return maxGap;
        }
    }

    // Main function added to test the code
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test cases
        System.out.println("Binary Gap of 22 (10110): " + sol.binaryGap(22)); // Output: 2
        System.out.println("Binary Gap of 5 (101): " + sol.binaryGap(5));     // Output: 2
        System.out.println("Binary Gap of 8 (1000): " + sol.binaryGap(8));    // Output: 0
        System.out.println("Binary Gap of 1041 (10000010001): " + sol.binaryGap(1041)); // Output: 5
    }
}
