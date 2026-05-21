public class FindtheLargestAreaofSquareInsideTwoRectangles {
    
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long maxSide = 0;
        int n = bottomLeft.length;

        // Check all pairs of rectangles
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Find overlap coordinates
                int intersectX1 = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int intersectY1 = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int intersectX2 = Math.min(topRight[i][0], topRight[j][0]);
                int intersectY2 = Math.min(topRight[i][1], topRight[j][1]);

                // Calculate overlap width and height
                int width = intersectX2 - intersectX1;
                int height = intersectY2 - intersectY1;

                // Only evaluate if there is a valid overlapping region
                if (width > 0 && height > 0) {
                    // The largest square in a rectangle is limited by its shortest side
                    long side = Math.min(width, height);
                    maxSide = Math.max(maxSide, side);
                }
            }
        }
        // Return the area of the largest square found
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        FindtheLargestAreaofSquareInsideTwoRectangles solver = new FindtheLargestAreaofSquareInsideTwoRectangles();

        // Test Case 1: Overlapping rectangles that form a square overlap
        int[][] bottomLeft1 = {{1, 1}, {2, 2}, {3, 1}};
        int[][] topRight1 = {{3, 3}, {4, 4}, {6, 6}};
        System.out.println("Test Case 1 Output: " + solver.largestSquareArea(bottomLeft1, topRight1)); 
        // Expected: 1 (The max overlapping square side is 1, area is 1)

        // Test Case 2: No overlapping rectangles
        int[][] bottomLeft2 = {{1, 1}, {3, 3}};
        int[][] topRight2 = {{2, 2}, {4, 4}};
        System.out.println("Test Case 2 Output: " + solver.largestSquareArea(bottomLeft2, topRight2)); 
        // Expected: 0 (No overlap)
    }
}


