import java.util.*;

public class MinimumDistanceBetweenThreeEqualElementsII {

    public int minimumDistance(int[] nums) {
        // Map to store list of indices for each unique number
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        // Iterate through each number's recorded indices
        for (List<Integer> indices : indexMap.values()) {
            // A good tuple requires at least 3 occurrences
            if (indices.size() < 3) continue;

            // Check every sliding window of 3 indices (i, j, k)
            // Simplified distance for sorted i < j < k is 2 * (k - i)
            for (int h = 0; h + 2 < indices.size(); h++) {
                int dist = 2 * (indices.get(h + 2) - indices.get(h));
                minDistance = Math.min(minDistance, dist);
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) {
        MinimumDistanceBetweenThreeEqualElementsII sol = new MinimumDistanceBetweenThreeEqualElementsII();

        // Example 1
        int[] nums1 = {1, 2, 1, 1, 3};
        System.out.println("Example 1: " + sol.minimumDistance(nums1)); // Output: 6

        // Example 2
        int[] nums2 = {1, 1, 2, 3, 2, 1, 2};
        System.out.println("Example 2: " + sol.minimumDistance(nums2)); // Output: 8

        // Example 3
        int[] nums3 = {1};
        System.out.println("Example 3: " + sol.minimumDistance(nums3)); // Output: -1
    }
}


