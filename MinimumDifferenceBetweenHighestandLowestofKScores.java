import java.util.Arrays;

public class MinimumDifferenceBetweenHighestandLowestofKScores {
    
    // Completing the required LeetCode method
    public int minimumDifference(int[] nums, int k) {
        // If we only need to pick 1 element, the max and min are the same
        if (k == 1) {
            return 0;
        }
        
        // Sort the array to bring close numbers together
        Arrays.sort(nums);
        
        int minDiff = Integer.MAX_VALUE;
        
        // Use a sliding window of size k
        for (int i = 0; i <= nums.length - k; i++) {
            // Calculate difference between the highest and lowest score in the window
            int currentDiff = nums[i + k - 1] - nums[i];
            
            // Track the minimum difference found so far
            minDiff = Math.min(minDiff, currentDiff);
        }
        
        return minDiff;
    }

    // Adding the main function with test execution
    public static void main(String[] args) {
        MinimumDifferenceBetweenHighestandLowestofKScores solution = new MinimumDifferenceBetweenHighestandLowestofKScores();

        // Test Case 1
        int[] nums1 = {90};
        int k1 = 1;
        System.out.println("Test Case 1 Result: " + solution.minimumDifference(nums1, k1)); // Expected Output: 0

        // Test Case 2
        int[] nums2 = {9, 4, 1, 7};
        int k2 = 2;
        System.out.println("Test Case 2 Result: " + solution.minimumDifference(nums2, k2)); // Expected Output: 2
        
        // Test Case 3
        int[] nums3 = {10, 25, 4, 11, 13};
        int k3 = 3;
        System.out.println("Test Case 3 Result: " + solution.minimumDifference(nums3, k3)); // Expected Output: 3
    }
}


