import java.util.Arrays;

public class MinimizeMaximumPairSuminArray {


    public int minPairSum(int[] nums) {
        // Sort the array to easily match smallest elements with largest elements
        Arrays.sort(nums);
        
        int maxPairSum = 0;
        int left = 0;
        int right = nums.length - 1;
        
        // Use two pointers to find the maximum sum of paired elements
        while (left < right) {
            int currentPairSum = nums[left] + nums[right];
            maxPairSum = Math.max(maxPairSum, currentPairSum);
            
            // Move pointers toward the center
            left++;
            right--;
        }
        
        return maxPairSum;
    }

    public static void main(String[] args) {
        MinimizeMaximumPairSuminArray solver = new MinimizeMaximumPairSuminArray();

        // Test Case 1: Elements can be optimally paired as (3,5) and (3,4)
        int[] nums1 = {3, 5, 2, 3};
        System.out.println("Test 1 Result: " + solver.minPairSum(nums1)); 
        // Expected Output: 7 (from pair 3 + 4 = 7)

        // Test Case 2: Elements can be optimally paired as (1,5), (2,4), and (3,3)
        int[] nums2 = {3, 5, 4, 2, 4, 6};
        System.out.println("Test 2 Result: " + solver.minPairSum(nums2)); 
        // Expected Output: 8 (from pairs 3+5=8, 4+4=8, 2+6=8)
    }
}


