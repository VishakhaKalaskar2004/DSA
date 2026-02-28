
class concatenationOfBinaryNumbers  {
    public int concatenatedBinary(int n) {
        long result = 0;
        int length = 0; // Represents the number of bits in the current number (i)
        long MOD = 1_000_000_007;

        for (int i = 1; i <= n; i++) {
            // If i is a power of 2, the number of bits required increases by 1
            // Example: 1 (1), 2 (10), 4 (100), 8 (1000)
            if ((i & (i - 1)) == 0) {
                length++;
            }

            // Shift result left by 'length' bits and add the current number 'i'
            result = ((result << length) | i) % MOD;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        concatenationOfBinaryNumbers  sol = new concatenationOfBinaryNumbers ();
        
        // Test Cases
        int[] testInputs = {1, 3, 12};
        
        System.out.println("--- Binary Concatenation Results ---");
        for (int n : testInputs) {
            int output = sol.concatenatedBinary(n);
            System.out.println("Input: n = " + n + " | Output: " + output);
        }
    }
}
