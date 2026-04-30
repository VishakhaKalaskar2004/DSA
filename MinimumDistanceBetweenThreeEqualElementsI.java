import java.util.*;


public class MinimumDistanceBetweenThreeEqualElementsI {

    /**
     * Calculates the minimum distance for a good tuple (i, j, k).
     * Time Complexity: O(n) - We traverse the array once to build the map and once more for distances.
     * Space Complexity: O(n) - To store the indices in a map.
     */
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        // Step 1: Store all indices for each unique number
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        // Step 2: For each number, find the minimum distance of a triplet
        for (List<Integer> indices : indexMap.values()) {
            if (indices.size() >= 3) {
                // To minimize 2 * (indices[k] - indices[i]), we check indices at i and i+2
                for (int i = 0; i < indices.size() - 2; i++) {
                    int first = indices.get(i);
                    int third = indices.get(i + 2);
                    int distance = 2 * (third - first);
                    minDistance = Math.min(minDistance, distance);
                }
            }
        }

        // Return -1 if no good tuple was found
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // Main function to run the provided examples
    public static void main(String[] args) {
        MinimumDistanceBetweenThreeEqualElementsI sol = new MinimumDistanceBetweenThreeEqualElementsI();

        // Example 1
        int[] nums1 = {1, 2, 1, 1, 3};
        System.out.println("Example 1 Output: " + sol.minimumDistance(nums1)); // Expected: 6

        // Example 2
        int[] nums2 = {1, 1, 2, 3, 2, 1, 2};
        System.out.println("Example 2 Output: " + sol.minimumDistance(nums2)); // Expected: 8

        // Example 3
        int[] nums3 = {1};
        System.out.println("Example 3 Output: " + sol.minimumDistance(nums3)); // Expected: -1
    }
}


