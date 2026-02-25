/**
 * Accurate implementation of "Prime Number of Set Bits in Binary Representation"
 * Optimized for Java 8+ environments.
 */
public class PrimeNumberOfSetBits {

    /**
     * Counts numbers in the range [left, right] with a prime count of set bits.
     * 
     * Logic:
     * 1. Iterate from left to right.
     * 2. Use Integer.bitCount() for high-performance bit counting.
     * 3. Check against a bitmask of primes under 32.
     */
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        
        /* 
         * Magic Bitmask: 0b101000101000101011001011010
         * This number has the N-th bit set if N is prime.
         * Primes < 32: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31
         */
        int primeMask = (1 << 2) | (1 << 3) | (1 << 5) | (1 << 7) | 
                        (1 << 11) | (1 << 13) | (1 << 17) | (1 << 19) | 
                        (1 << 23) | (1 << 29) | (1 << 31);

        for (int i = left; i <= right; i++) {
            // Get the number of set bits (1s) in the integer
            int bits = Integer.bitCount(i);
            
            // Check if the 'bits'-th bit in our mask is 1
            if (((primeMask >> bits) & 1) == 1) {
                count++;
            }
        }
        
        return count;
    }

    /**
     * Main method to execute and verify the logic.
     */
    public static void main(String[] args) {
        PrimeNumberOfSetBits solver = new PrimeNumberOfSetBits();

        // Test Case 1: [6, 10]
        // 6(110)->2, 7(111)->3, 8(100)->1, 9(1001)->2, 10(1010)->2
        // Primes: 2, 3, 2, 2 (Total 4)
        printResult(solver, 6, 10);

        // Test Case 2: [10, 15]
        // 10(2 bits), 11(3 bits), 12(2 bits), 13(3 bits), 14(3 bits), 15(4 bits)
        // Primes: 2, 3, 2, 3, 3 (Total 5)
        printResult(solver, 10, 15);
    }

    private static void printResult(PrimeNumberOfSetBits solver, int L, int R) {
        int result = solver.countPrimeSetBits(L, R);
        System.out.printf("Range [%d, %d] -> Count: %d%n", L, R, result);
    }
}
