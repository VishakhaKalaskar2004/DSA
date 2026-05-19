public class MinimumTimeVisitingAllPoints {
    
    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;
        
        for (int i = 0; i < points.length - 1; i++) {
            int[] current = points[i];
            int[] next = points[i + 1];
            
            // Find the absolute differences in X and Y coordinates
            int diffX = Math.abs(next[0] - current[0]);
            int diffY = Math.abs(next[1] - current[1]);
            
            // The time taken is the maximum of the two differences
            totalTime += Math.max(diffX, diffY);
        }
        
        return totalTime;
    }

    public static void main(String[] args) {
        MinimumTimeVisitingAllPoints solution = new MinimumTimeVisitingAllPoints();

        // Test Case 1: Standard points with diagonal moves
        int[][] points1 = {{1, 1}, {3, 4}, {-1, 0}};
        int result1 = solution.minTimeToVisitAllPoints(points1);
        System.out.println("Test Case 1 Result: " + result1); // Expected: 7

        // Test Case 2: Linear points along a straight axis
        int[][] points2 = {{3, 2}, {-2, 2}};
        int result2 = solution.minTimeToVisitAllPoints(points2);
        System.out.println("Test Case 2 Result: " + result2); // Expected: 5
    }
}

