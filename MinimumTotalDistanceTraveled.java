import java.util.*;

public class MinimumTotalDistanceTraveled {

    // Memoization table
    Long[][] memo;
    List<Integer> sortedRobots;
    int[][] sortedFactories;

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // 1. Sort robots by position
        Collections.sort(robot);
        this.sortedRobots = robot;

        // 2. Sort factories by position
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));
        this.sortedFactories = factory;

        memo = new Long[robot.size()][factory.length];
        return dfs(0, 0);
    }

    private long dfs(int rIdx, int fIdx) {
        // All robots repaired
        if (rIdx == sortedRobots.size()) return 0;
        // No more factories left
        if (fIdx == sortedFactories.length) return (long) 1e15; 
        // Return cached result
        if (memo[rIdx][fIdx] != null) return memo[rIdx][fIdx];

        // Choice 1: Skip current factory
        long res = dfs(rIdx, fIdx + 1);

        // Choice 2: Use current factory for 'k' robots
        long distance = 0;
        for (int k = 0; k < sortedFactories[fIdx][1]; k++) {
            if (rIdx + k < sortedRobots.size()) {
                distance += Math.abs(sortedRobots.get(rIdx + k) - sortedFactories[fIdx][0]);
                // Min(Skip, Use for k robots + rest)
                res = Math.min(res, distance + dfs(rIdx + k + 1, fIdx + 1));
            } else {
                break;
            }
        }

        return memo[rIdx][fIdx] = res;
    }

    public static void main(String[] args) {
        MinimumTotalDistanceTraveled sol = new MinimumTotalDistanceTraveled();
        
        // Example Test Case
        List<Integer> robot = new ArrayList<>(Arrays.asList(0, 4, 6));
        int[][] factory = {{2, 2}, {5, 2}};
        
        long result = sol.minimumTotalDistance(robot, factory);
        
        // Expected output: 4
        System.out.println("Minimum Total Distance: " + result);
    }
}

