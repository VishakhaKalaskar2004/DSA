import java.util.Arrays;

public class TransformedArray {

    public int[] constructTransformedArray(int[] nums) {
        final int n = nums.length;
        int[] ans = new int[n];
        
        for (int i = 0; i < n; ++i) {
            // Formula to handle positive, negative, and large out-of-bounds steps safely.
            // Adding '+ n' inside ensures negative remainders are wrapped correctly.
            int targetIndex = (i + nums[i] % n + n) % n;
            ans[i] = nums[targetIndex];
        }
        
        return ans;
    }

    public static void main(String[] args) {
        TransformedArray solution = new TransformedArray();

        // Test Case 1: Mixed positive, negative, and zero values
        int[] nums1 = {3, -2, 1, 1};
        // Expected Output: [1, 1, 1, 3]
        System.out.println("Test 1 Output: " + Arrays.toString(solution.constructTransformedArray(nums1)));

        // Test Case 2: Values containing zero
        int[] nums2 = {-1, 4, -1};
        // Expected Output: [-1, 4, -1]
        System.out.println("Test 2 Output: " + Arrays.toString(solution.constructTransformedArray(nums2)));

        // Test Case 3: Values exceeding array boundaries
        int[] nums3 = {10, -10};
        // Expected Output: [10, 10]
        System.out.println("Test 3 Output: " + Arrays.toString(solution.constructTransformedArray(nums3)));
    }
}


