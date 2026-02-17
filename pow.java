public class pow {
    public double myPow(double x, int n) {
        // Use long to avoid integer overflow when n is Integer.MIN_VALUE (-2147483648).
        // Taking -Integer.MIN_VALUE would overflow a 32-bit int.
        long N = n;
        
        // Handle negative exponent: x^-n = (1/x)^n
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1;
        double currentProduct = x;
        
        // Binary Exponentiation (Iterative Approach)
        // Complexity: O(log N)
        // Instead of multiplying x, n times, we square the base in each step
        // and reduce the exponent by half.
        for (long i = N; i > 0; i /= 2) {
            // If the current exponent is odd, multiply the result by currentProduct
            if (i % 2 == 1) {
                result *= currentProduct;
            }
            // Square the base for the next bit
            currentProduct *= currentProduct;
        }
        
        return result;
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        pow sol = new pow();
        
        // Test Case 1: Positive exponent
        System.out.println("2.0^10 = " + sol.myPow(2.0, 10)); // Expected: 1024.0
        
        // Test Case 2: Negative exponent
        System.out.println("2.1^3 = " + sol.myPow(2.1, 3));   // Expected: 9.261000000000001
        
        // Test Case 3: Negative exponent
        System.out.println("2.0^-2 = " + sol.myPow(2.0, -2)); // Expected: 0.25
        
        // Test Case 4: Zero exponent
        System.out.println("5.0^0 = " + sol.myPow(5.0, 0));   // Expected: 1.0
        
        // Test Case 5: Integer MIN_VALUE
        System.out.println("1.0^-2147483648 = " + sol.myPow(1.0, -2147483648)); // Expected: 1.0
    }
}
