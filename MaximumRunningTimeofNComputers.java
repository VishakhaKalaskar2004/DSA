public class MaximumRunningTimeofNComputers {
    
    public long maxRunTime(int n, int[] batteries) {
        long totalSum = 0;
        for (int battery : batteries) {
            totalSum += battery;
        }
        
        // Lower bound is 0 minutes, upper bound is the average power distribution
        long low = 0;
        long high = totalSum / n;
        long ans = 0;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            // Check if all 'n' computers can simultaneously run for 'mid' minutes
            if (isFeasible(n, batteries, mid)) {
                ans = mid;        // 'mid' is valid, record it as a potential maximum
                low = mid + 1;    // Try to find a larger viable runtime
            } else {
                high = mid - 1;   // 'mid' is too long, shrink the upper bound
            }
        }
        
        return ans;
    }
    
    private boolean isFeasible(int n, int[] batteries, long targetTime) {
        long totalUsableEnergy = 0;
        
        for (int battery : batteries) {
            // A single battery cannot be used in parallel across multiple computers.
            // Thus, it can contribute at most 'targetTime' minutes to the pool.
            totalUsableEnergy += Math.min((long) battery, targetTime);
        }
        
        // To run 'n' computers for 'targetTime', we require a total energy of n * targetTime
        return totalUsableEnergy >= (n * targetTime);
    }

    public static void main(String[] args) {
        MaximumRunningTimeofNComputers solution = new MaximumRunningTimeofNComputers();

        // Test Case 1: Standard distribution
        int n1 = 2;
        int[] batteries1 = {3, 3, 3};
        // Expected Output: 4 (Run C1 on B1+partial B3, C2 on B2+partial B3)
        System.out.println("Test 1 Output: " + solution.maxRunTime(n1, batteries1)); 

        // Test Case 2: Evenly split uniform values
        int n2 = 2;
        int[] batteries2 = {1, 1, 1, 1};
        // Expected Output: 2
        System.out.println("Test 2 Output: " + solution.maxRunTime(n2, batteries2)); 

        // Test Case 3: Outlier battery exceeding bounds
        int n3 = 3;
        int[] batteries3 = {10, 2, 3};
        // Expected Output: 2 (Constrained by the smaller remaining battery capacities)
        System.out.println("Test 3 Output: " + solution.maxRunTime(n3, batteries3)); 
    }
}


