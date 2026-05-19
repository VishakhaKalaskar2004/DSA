public class SeparateSquaresI {

    // Helper method to compute the total area of squares sitting strictly below a target y-coordinate line
    private double getAreaBelow(int[][] squares, double targetY) {
        double area = 0.0;
        for (int[] sq : squares) {
            double bottomY = sq[1];
            double side = sq[2];
            double topY = bottomY + side;

            if (targetY <= bottomY) {
                // The line is entirely below or at the bottom edge of this square
                continue;
            } else if (targetY >= topY) {
                // The line is entirely above or at the top edge of this square
                area += side * side;
            } else {
                // The line intersects the square; accumulate partial area
                area += side * (targetY - bottomY);
            }
        }
        return area;
    }

    public double separateSquares(int[][] squares) {
        double totalArea = 0.0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        // Establish boundaries and aggregate total area
        for (int[] sq : squares) {
            double bottomY = sq[1];
            double side = sq[2];
            double topY = bottomY + side;

            totalArea += side * side;
            low = Math.min(low, bottomY);
            high = Math.max(high, topY);
        }

        double targetArea = totalArea / 2.0;
        
        // Execute Binary Search up to standard precision bounds
        for (int iter = 0; iter < 100; iter++) {
            double mid = low + (high - low) / 2.0;
            if (getAreaBelow(squares, mid) >= targetArea) {
                high = mid; // Narrow search scope down
            } else {
                low = mid;  // Narrow search scope up
            }
        }

        return low;
    }

    public static void main(String[] args) {
        SeparateSquaresI solution = new SeparateSquaresI();

        // Test Case 1: Separate unconnected non-overlapping squares
        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        double result1 = solution.separateSquares(squares1);
        System.out.printf("Test Case 1 Result: %.5f\n", result1); // Expected: 1.00000

        // Test Case 2: Concentric or overlapping layout dimensions 
        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        double result2 = solution.separateSquares(squares2);
        System.out.printf("Test Case 2 Result: %.5f\n", result2); // Expected: 1.16667
    }
}


