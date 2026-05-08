public class MatrixSimilarityAfterCyclicShifts {

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        // Optimization: shifting n times results in the same row
        k %= n;
        
        if (k == 0) return true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // For even rows, we check the element at (j + k) % n (left shift)
                // For odd rows, we check the element at (j - k + n) % n (right shift)
                // However, in a cyclic array, shifting left or right by k leads 
                // to the same equality condition: mat[i][j] == mat[i][(j + k) % n]
                if (mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MatrixSimilarityAfterCyclicShifts sol = new MatrixSimilarityAfterCyclicShifts();

        // Example 1: Similar matrix
        int[][] mat1 = {{1, 2, 1, 2}, {5, 5, 5, 5}, {6, 3, 6, 3}};
        int k1 = 2;
        System.out.println("Test Case 1: " + sol.areSimilar(mat1, k1)); // Expected: true

        // Example 2: Not similar
        int[][] mat2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k2 = 4;
        System.out.println("Test Case 2: " + sol.areSimilar(mat2, k2)); // Expected: false
    }
}


