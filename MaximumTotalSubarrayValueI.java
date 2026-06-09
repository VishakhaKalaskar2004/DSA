public class MaximumTotalSubarrayValueI {
    
    public long maxTotalValue(int[] nums, int k) {
        int maxElement = Integer.MIN_VALUE;
        int minElement = Integer.MAX_VALUE;

        // Find the global maximum and minimum elements in the array
        for (int num : nums) {
            if (num > maxElement) {
                maxElement = num;
            }
            if (num < minElement) {
                minElement = num;
            }
        }

        // Calculate the maximum value achievable for a single subarray
        long maxSubarrayValue = (long) maxElement - minElement;

        // Multiply the maximum subarray value by k
        return maxSubarrayValue * k;
    }

    public static void main(String[] args) {
        MaximumTotalSubarrayValueI solution = new MaximumTotalSubarrayValueI();

        // Test Case 1
        int[] nums1 = {4, 2, 5, 1};
        int k1 = 3;
        long result1 = solution.maxTotalValue(nums1, k1);
        System.out.println("Test Case 1 Output: " + result1); // Expected: 12 (Subarray [5, 1] gives 5 - 1 = 4. 4 * 3 = 12)

        // Test Case 2
        int[] nums2 = {-3, 10, 2, -1};
        int k2 = 2;
        long result2 = solution.maxTotalValue(nums2, k2);
        System.out.println("Test Case 2 Output: " + result2); // Expected: 26 (Subarray [10, -3] gives 10 - (-3) = 13. 13 * 2 = 26)
    }
}


