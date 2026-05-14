import java.util.TreeSet;


public class DivideanArrayIntoSubarraysWithMinimumCostII {

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        long windowSum = 0;
        
        // Custom comparator to sort indices by their underlying array values.
        // If values are equal, tie-break using the index itself to handle duplicates.
        TreeSet<Integer> left = new TreeSet<>((a, b) -> nums[a] == nums[b] ? Integer.compare(a, b) : Integer.compare(nums[a], nums[b]));
        TreeSet<Integer> right = new TreeSet<>((a, b) -> nums[a] == nums[b] ? Integer.compare(a, b) : Integer.compare(nums[a], nums[b]));
        
        // We need k - 1 elements from the rest of the array
        int targetSize = k - 1; 
        
        // Initialize the first window with elements from index 1 to dist + 1
        for (int i = 1; i <= Math.min(n - 1, dist + 1); i++) {
            left.add(i);
            windowSum += nums[i];
        }
        
        // Balance elements: Push excess elements into the right set
        while (left.size() > targetSize) {
            int largestInLeft = left.pollLast();
            windowSum -= nums[largestInLeft];
            right.add(largestInLeft);
        }
        
        long minCost = windowSum;
        
        // Slide the window across the remaining array indices
        for (int i = dist + 2; i < n; i++) {
            // Remove the element that has dropped out of scope from the left side of the window
            int outOfScopeIndex = i - dist - 1;
            if (left.contains(outOfScopeIndex)) {
                windowSum -= nums[outOfScopeIndex];
                left.remove(outOfScopeIndex);
            } else {
                right.remove(outOfScopeIndex);
            }
            
            // Add the new element entering the window from the right
            int newIndex = i;
            left.add(newIndex);
            windowSum += nums[newIndex];
            
            // Balance 1: Push the largest element out of left to right to preserve size constraint
            int largestInLeft = left.pollLast();
            windowSum -= nums[largestInLeft];
            right.add(largestInLeft);
            
            // Balance 2: If left pool lacks elements, pull the smallest available from right
            if (left.size() < targetSize && !right.isEmpty()) {
                int smallestInRight = right.pollFirst();
                windowSum += nums[smallestInRight];
                left.add(smallestInRight);
            }
            
            // Update the global minimum sum encountered so far
            minCost = Math.min(minCost, windowSum);
        }
        
        // Return the best window sum plus the absolute fixed value of nums[0]
        return nums[0] + minCost;
    }

    public static void main(String[] args) {
        DivideanArrayIntoSubarraysWithMinimumCostII solution = new DivideanArrayIntoSubarraysWithMinimumCostII();

        // Test Case 1: Standard input
        int[] nums1 = {1, 3, 2, 6, 4, 2};
        int k1 = 3;
        int dist1 = 3;
        // Expected Output: 5 (nums[0] + nums[2] + nums[5] -> 1 + 2 + 2)
        System.out.println("Test 1 Minimum Cost: " + solution.minimumCost(nums1, k1, dist1));

        // Test Case 2: Tight constraint distance
        int[] nums2 = {10, 1, 2, 3, 4};
        int k2 = 3;
        int dist2 = 1;
        // Expected Output: 13 (nums[0] + nums[1] + nums[2] -> 10 + 1 + 2)
        System.out.println("Test 2 Minimum Cost: " + solution.minimumCost(nums2, k2, dist2));

        // Test Case 3: Large duplicate values
        int[] nums3 = {10, 8, 18, 9, 10, 15};
        int k3 = 4;
        int dist3 = 3;
        // Expected Output: 37 (nums[0] + nums[1] + nums[3] + nums[4] -> 10 + 8 + 9 + 10)
        System.out.println("Test 3 Minimum Cost: " + solution.minimumCost(nums3, k3, dist3));
    }
}


