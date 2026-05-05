public class MirrorDistanceofanInteger {
    /**
     * Returns the mirror distance of integer n, defined as abs(n - reverse(n)).
     */
    public int mirrorDistance(int n) {
        // Use Math.abs to return the absolute difference
        return Math.abs(n - reverse(n));
    }

    /**
     * Helper method to mathematically reverse the digits of an integer.
     */
    private int reverse(int x) {
        int reversed = 0;
        // Process each digit using modulo and division
        while (x > 0) {
            int lastDigit = x % 10;
            reversed = (reversed * 10) + lastDigit;
            x /= 10;
        }
        return reversed;
    }

    // --- MAIN FUNCTION FOR TESTING ---
    public static void main(String[] args) {
        MirrorDistanceofanInteger sol = new MirrorDistanceofanInteger();

        // Test Case 1: Standard input
        int n1 = 25;
        System.out.println("Input: n = " + n1);
        System.out.println("Output: " + sol.mirrorDistance(n1)); // Expected: 27 (abs(25-52))

        // Test Case 2: Trailing zero (10 becomes 01, which is treated as 1)
        int n2 = 10;
        System.out.println("\nInput: n = " + n2);
        System.out.println("Output: " + sol.mirrorDistance(n2)); // Expected: 9 (abs(10-1))

        // Test Case 3: Single digit (reverse is the same)
        int n3 = 7;
        System.out.println("\nInput: n = " + n3);
        System.out.println("Output: " + sol.mirrorDistance(n3)); // Expected: 0 (abs(7-7))
    }
}


