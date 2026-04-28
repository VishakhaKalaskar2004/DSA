import java.util.*;

public class MaximumWallsDestroyedbyRobots {
    
    private Integer[][] memo;
    private int[][] robotPairs;
    private int[] sortedWalls;
    private int n;

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        this.n = robots.length;
        this.robotPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotPairs[i][0] = robots[i];
            robotPairs[i][1] = distance[i];
        }
        
        // Sort robots by position and walls for binary search
        Arrays.sort(robotPairs, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(walls);
        this.sortedWalls = walls;
        
        // memo[i][j]: max walls from robot 0 to i, given robot i+1's direction j (0: left, 1: right)
        this.memo = new Integer[n][2];
        return dp(n - 1, 1);
    }

    private int dp(int i, int nextDir) {
        if (i < 0) return 0;
        if (memo[i][nextDir] != null) return memo[i][nextDir];

        int currentPos = robotPairs[i][0];
        int range = robotPairs[i][1];

        // Choice 1: Fire Left
        int leftBound = Math.max(currentPos - range, (i > 0) ? robotPairs[i-1][0] + 1 : Integer.MIN_VALUE);
        int wallsLeft = countWallsInRange(leftBound, currentPos);
        int resLeft = dp(i - 1, 0) + wallsLeft;

        // Choice 2: Fire Right
        int rightBound = currentPos + range;
        if (i + 1 < n) {
            // Blocked by next robot depending on its firing direction
            if (nextDir == 0) rightBound = Math.min(rightBound, robotPairs[i+1][0] - robotPairs[i+1][1] - 1);
            else rightBound = Math.min(rightBound, robotPairs[i+1][0] - 1);
        }
        int wallsRight = countWallsInRange(currentPos, rightBound);
        int resRight = dp(i - 1, 1) + wallsRight;

        return memo[i][nextDir] = Math.max(resLeft, resRight);
    }

    private int countWallsInRange(int start, int end) {
        if (start > end) return 0;
        int leftIdx = lowerBound(sortedWalls, start);
        int rightIdx = lowerBound(sortedWalls, end + 1);
        return Math.max(0, rightIdx - leftIdx);
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        MaximumWallsDestroyedbyRobots sol = new MaximumWallsDestroyedbyRobots();
        
        // Example test case: robots at 3, 6; distances 2, 3; walls at 1, 2, 4, 5, 7, 8
        int[] robots = {3, 6};
        int[] distance = {2, 3};
        int[] walls = {1, 2, 4, 5, 7, 8};
        
        int result = sol.maxWalls(robots, distance, walls);
        System.out.println("Maximum unique walls destroyed: " + result);
    }
}


