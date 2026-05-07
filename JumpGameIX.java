import java.util.Arrays;

public class JumpGameIX {

    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // Step 1: preMax[i] stores the max value from nums[0] to nums[i]
        int[] preMax = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }
        
        // Step 2: Traverse from right to left to find the max reachable value
        // sufMin tracks the smallest value to the right to enable forward jumps
        int sufMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            // Rule: If current preMax is greater than a min seen to the right,
            // we can jump forward then backward to reach a larger previous value.
            if (i < n - 1 && preMax[i] > sufMin) {
                ans[i] = ans[i + 1];
            } else {
                ans[i] = preMax[i];
            }
            sufMin = Math.min(sufMin, nums[i]);
        }
        
        return ans;
    }

    public static void main(String[] args) {
        JumpGameIX sol = new JumpGameIX();

        // Example 1
        int[] nums1 = {2, 1, 3};
        System.out.println("Input 1: [2, 1, 3] -> Output: " + Arrays.toString(sol.maxValue(nums1)));

        // Example 2
        int[] nums2 = {2, 3, 1};
        System.out.println("Input 2: [2, 3, 1] -> Output: " + Arrays.toString(sol.maxValue(nums2)));
    }
}

