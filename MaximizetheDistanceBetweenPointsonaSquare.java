import java.util.*;

public class MaximizetheDistanceBetweenPointsonaSquare {

    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] linear = new long[n];
        long perimeter = 4L * side;

        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (y == 0) linear[i] = x;
            else if (x == side) linear[i] = side + y;
            else if (y == side) linear[i] = 2L * side + (side - x);
            else linear[i] = 3L * side + (side - y);
        }
        Arrays.sort(linear);

        // Double the array to handle circularity linearly
        long[] doubled = new long[2 * n];
        for (int i = 0; i < n; i++) {
            doubled[i] = linear[i];
            doubled[i + n] = linear[i] + perimeter;
        }

        int low = 1, high = (int) (perimeter / k);
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(doubled, n, mid, k, perimeter)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canPlace(long[] doubled, int n, int dist, int k, long perimeter) {
        // We only need to test starting points within the first 'n' elements
        // And specifically, the optimal start must be within the first 'gap' 
        // to avoid redundant checks.
        int searchLimit = Math.min(n, n / k + 1); 
        
        for (int i = 0; i < n; i++) {
            int count = 1;
            long lastPos = doubled[i];
            int currIdx = i;
            
            // Greedily pick k-1 more points
            for (int j = 1; j < k; j++) {
                int nextIdx = findNext(doubled, currIdx + 1, i + n - 1, lastPos + dist);
                if (nextIdx == -1) {
                    count = -1;
                    break;
                }
                lastPos = doubled[nextIdx];
                currIdx = nextIdx;
                count++;
            }

            // Check if k points were placed and the gap back to start is valid
            if (count == k && (doubled[i] + perimeter - lastPos >= dist)) {
                return true;
            }
            
            // Optimization: if we can't even start here, we can't start further right
            if (i >= n / k + 1 && n > 100) break; 
        }
        return false;
    }

    // Binary search for the next point >= target position
    private int findNext(long[] arr, int left, int right, long target) {
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaximizetheDistanceBetweenPointsonaSquare sol = new MaximizetheDistanceBetweenPointsonaSquare();
        int side = 100000;
        // Simplified dummy points for the main example
        int[][] points = new int[200][2];
        for(int i=0; i<200; i++) points[i] = new int[]{0, i};
        int k = 4;
        System.out.println("Max Min Distance: " + sol.maxDistance(side, points, k));
    }
}


