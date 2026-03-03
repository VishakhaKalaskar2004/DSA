public class findKthBit {
    /**
     * Finds the kth bit (1-indexed) in the nth binary string Sn.
     * Sn is formed as: S1 = "0", Si = Si-1 + "1" + reverse(invert(Si-1)).
     * 
     * @param n The string sequence number.
     * @param k The 1-indexed position of the bit to find.
     * @return The character '0' or '1' at the kth position.
     */
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0'; // Base case: S1 is "0"
        }

        // Calculate the length of Sn (2^n - 1)
        int len = (1 << n) - 1; 
        // Calculate the middle index (1-based), which is 2^(n-1)
        int mid = len / 2 + 1; 

        if (k == mid) {
            return '1'; // The middle bit is always '1'
        } else if (k < mid) {
            // The k-th bit is in the first half, which is identical to S(n-1)
            return findKthBit(n - 1, k);
        } else {
            // The k-th bit is in the second half. 
            // Map k to its mirror position in S(n-1) and invert the result
            int mirrorK = len - k + 1;
            char bitInPrev = findKthBit(n - 1, mirrorK);
            // Invert the bit: if it's '0', return '1'; if it's '1', return '0'
            return (bitInPrev == '0') ? '1' : '0';
        }
    }

    public static void main(String[] args) {
        findKthBit sol = new findKthBit();
        
        // Test case 1: n = 3, k = 1. S3 = "0011011", 1st bit is '0'
        System.out.println("n=3, k=1: " + sol.findKthBit(3, 1)); // Output: 0
        
        // Test case 2: n = 4, k = 11. S4 = "001101110001011", 11th bit is '0'
        System.out.println("n=4, k=11: " + sol.findKthBit(4, 11)); // Output: 0
        
        // Test case 3: n = 2, k = 3. S2 = "011", 3rd bit is '1'
        System.out.println("n=2, k=3: " + sol.findKthBit(2, 3)); // Output: 1
    }
}


