public class MaximumMatrixSum {
    
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int negativeCount = 0;
        int minAbsoluteValue = Integer.MAX_VALUE;

        for (int[] row : matrix) {
            for (int val : row) {
                int absVal = Math.abs(val);
                totalSum += absVal;
                
                if (val < 0) {
                    negativeCount++;
                }
                
                if (absVal < minAbsoluteValue) {
                    minAbsoluteValue = absVal;
                }
            }
        }

        // If total count of negative numbers is odd, one element must stay negative.
        // We choose the smallest absolute value to minimize the loss.
        if (negativeCount % 2 != 0) {
            totalSum -= 2 * minAbsoluteValue;
        }

        return totalSum;
    }

    public static void main(String[] args) {
        MaximumMatrixSum solution = new MaximumMatrixSum();

        // Test cases
        int[][] matrix1 = {
            {1, -1},
            {-1, 1}
        };
        int[][] matrix2 = {
            {1, 2, 3},
            {-1, -2, -3},
            {1, 2, 3}
        };

        System.out.println("Max matrix sum 1: " + solution.maxMatrixSum(matrix1)); // Output: 4
        System.out.println("Max matrix sum 2: " + solution.maxMatrixSum(matrix2)); // Output: 16
    }
}


