import java.util.*;

public class SumofDistances {

    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        Map<Integer, List<Integer>> groupIndices = new HashMap<>();

        for (int i = 0; i < n; i++) {
            groupIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> indices : groupIndices.values()) {
            int m = indices.size();
            if (m <= 1) continue;

            long totalSum = 0;
            for (int index : indices) {
                totalSum += index;
            }

            long prefixSum = 0;
            for (int i = 0; i < m; i++) {
                int currentIndex = indices.get(i);
                long countLeft = i;
                long countRight = m - 1 - i;
                long suffixSum = totalSum - prefixSum - currentIndex;

                ans[currentIndex] = (countLeft * currentIndex - prefixSum) + (suffixSum - countRight * currentIndex);
                prefixSum += currentIndex;
            }
        }
        return ans;
    }

    // Main function to execute and test the solution
    public static void main(String[] args) {
        SumofDistances sol = new SumofDistances();
        
        // Sample input array [1, 3, 1, 1, 2]
        int[] nums = {1, 3, 1, 1, 2};
        
        // Call the distance method
        long[] result = sol.distance(nums);
        
        // Use Arrays.toString() for easy printing to console
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Output: " + Arrays.toString(result));
    }
}


