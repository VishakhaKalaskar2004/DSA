public class MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        
        // 1. Create a 2D prefix sum array
        int[][] prefixSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = mat[i - 1][j - 1] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
            }
        }
        
        // 2. Binary search for the maximum square side length
        int low = 1;
        int high = Math.min(m, n);
        int maxSide = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (hasSquareWithSumLessThanThreshold(prefixSum, m, n, mid, threshold)) {
                maxSide = mid; // Update answer and look for a larger square
                low = mid + 1;
            } else {
                high = mid - 1; // Look for a smaller square
            }
        }
        
        return maxSide;
    }
    
    // Helper function to check if any square of side length 'len' has a sum <= threshold
    private boolean hasSquareWithSumLessThanThreshold(int[][] prefixSum, int m, int n, int len, int threshold) {
        for (int i = len; i <= m; i++) {
            for (int j = len; j <= n; j++) {
                // Calculate region sum using the inclusion-exclusion principle
                int currentSum = prefixSum[i][j] 
                               - prefixSum[i - len][j] 
                               - prefixSum[i][j - len] 
                               + prefixSum[i - len][j - len];
                               
                if (currentSum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold solver = new MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold();

        // Test Case 1
        int[][] mat1 = {
            {1, 1, 3, 2, 4, 3, 2},
            {1, 1, 3, 2, 4, 3, 2},
            {1, 1, 3, 2, 4, 3, 2}
        };
        int threshold1 = 4;
        // Expected output: 2 (The 2x2 subgrid of 1s in the top-left has sum 4 <= 4)
        System.out.println("Test 1 Output: " + solver.maxSideLength(mat1, threshold1));

        // Test Case 2
        int[][] mat2 = {
            {22, 12, 1, 14, 10},
            {12, 10, 0, 4, 11},
            {24, 5, 2, 4, 5},
            {7, 12, 8, 11, 7},
            {1, 5, 10, 1, 19}
        };
        int threshold2 = 100;
        // Expected output: 3
        System.out.println("Test 2 Output: " + solver.maxSideLength(mat2, threshold2));
    }
}


