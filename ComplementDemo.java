public class ComplementDemo {

    static class Solution {
        /**
         * Computes the bitwise complement of a non-negative integer n.
         * The bitwise complement is the number found by flipping all the bits
         * of its binary representation that are *not* leading zeros.
         *
         * @param n The non-negative integer.
         * @return The bitwise complement of n.
         */
        public int bitwiseComplement(int n) {
            // Special case for 0, as its binary representation is "0"
            // and its complement is "1".
            if (n == 0) return 1;

            int mask = 0;
            int temp = n;

            // Create a mask of 1s with the same bit-length as n
            // e.g., if n = 5 (101), mask = 7 (111)
            while (temp > 0) {
                // Shift the current mask to the left and add a 1 in the least significant bit
                mask = (mask << 1) | 1;
                // Right shift temp to process the next bit
                temp >>= 1;
            }

            // XORing n with the mask flips all bits
            // 5 (101) XOR 7 (111) = 2 (010)
            return n ^ mask;
        }
    }

    public static void main(String[] args) {
        // Create an instance of the Solution class to call its method
        Solution sol = new Solution();

        // Test cases
        int n1 = 5; // Binary 101 -> Complement 010 (2)
        int n2 = 7; // Binary 111 -> Complement 000 (0)
        int n3 = 10; // Binary 1010 -> Complement 0101 (5)
        int n4 = 0; // Binary 0 -> Complement 1 (1)

        System.out.println("Input: " + n1 + ", Complement: " + sol.bitwiseComplement(n1));
        System.out.println("Input: " + n2 + ", Complement: " + sol.bitwiseComplement(n2));
        System.out.println("Input: " + n3 + ", Complement: " + sol.bitwiseComplement(n3));
        System.out.println("Input: " + n4 + ", Complement: " + sol.bitwiseComplement(n4));
    }
}
