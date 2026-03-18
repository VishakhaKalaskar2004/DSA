public class MinimumChangesToMakeAlternatingBinaryString {
    public int minOperations(String s) {
        // count1 represents the mismatches for the pattern starting with '0' (0101...)
        int count1 = 0; 

        for (int i = 0; i < s.length(); i++) {
            char expectedChar;
            if (i % 2 == 0) {
                expectedChar = '0';
            } else {
                expectedChar = '1';
            }

            if (s.charAt(i) != expectedChar) {
                count1++;
            }
        }

        // The mismatches for the pattern starting with '1' (1010...)
        // is the total length minus the mismatches for the '0'-starting pattern.
        int count2 = s.length() - count1;

        return Math.min(count1, count2);
    }

    public static void main(String[] args) {
        MinimumChangesToMakeAlternatingBinaryString solution = new MinimumChangesToMakeAlternatingBinaryString();

        // Test cases:
        String test1 = "0101";
        System.out.println("Input: " + test1 + ", Minimum Operations: " + solution.minOperations(test1)); // Expected: 0

        String test2 = "1111";
        System.out.println("Input: " + test2 + ", Minimum Operations: " + solution.minOperations(test2)); // Expected: 2

        String test3 = "10";
        System.out.println("Input: " + test3 + ", Minimum Operations: " + solution.minOperations(test3)); // Expected: 0
        
        String test4 = "10010100";
        System.out.println("Input: " + test4 + ", Minimum Operations: " + solution.minOperations(test4)); // Expected: 3
    }
}
