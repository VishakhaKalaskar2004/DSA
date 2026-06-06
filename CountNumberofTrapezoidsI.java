import java.util.HashMap;
import java.util.Map;

public class CountNumberofTrapezoidsI {
    public int countTrapezoids(int[][] points) {
        long MOD = 1_000_000_007L;
        
        // Map to count how many points share the same Y-coordinate
        Map<Integer, Integer> yCountMap = new HashMap<>();
        for (int[] point : points) {
            int y = point[1];
            yCountMap.put(y, yCountMap.getOrDefault(y, 0) + 1);
        }

        long totalTrapezoids = 0;
        long previousHorizontalLines = 0;

        // Iterate through each unique Y level
        for (int count : yCountMap.values()) {
            if (count >= 2) {
                // Number of pairs (segments) that can be formed at this height: count C 2
                long currentHorizontalLines = ((long) count * (count - 1) / 2) % MOD;
                
                // Form trapezoids by pairing current segments with all previously discovered segments
                totalTrapezoids = (totalTrapezoids + (currentHorizontalLines * previousHorizontalLines) % MOD) % MOD;
                
                // Accumulate the segment counts for subsequent levels
                previousHorizontalLines = (previousHorizontalLines + currentHorizontalLines) % MOD;
            }
        }

        return (int) totalTrapezoids;
    }

    public static void main(String[] args) {
        CountNumberofTrapezoidsI solution = new CountNumberofTrapezoidsI();

        // Example 1
        int[][] points1 = {{1, 0}, {2, 0}, {3, 0}, {2, 2}, {3, 2}};
        int result1 = solution.countTrapezoids(points1);
        System.out.println("Example 1 Output: " + result1); // Expected: 3

        // Example 2
        int[][] points2 = {{0, 0}, {1, 0}, {0, 1}, {2, 1}};
        int result2 = solution.countTrapezoids(points2);
        System.out.println("Example 2 Output: " + result2); // Expected: 1
    }
}

}
