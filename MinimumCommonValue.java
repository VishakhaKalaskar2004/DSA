public class MinimumCommonValue {
    
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        MinimumCommonValue solution = new MinimumCommonValue();

        // Test Case 1: Common element exists
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4};
        int result1 = solution.getCommon(nums1, nums2);
        System.out.println("Test Case 1 Result: " + result1); // Expected: 2

        // Test Case 2: Multiple common elements (returns the smallest)
        int[] nums3 = {1, 2, 3, 6};
        int[] nums4 = {2, 3, 4, 5};
        int result2 = solution.getCommon(nums3, nums4);
        System.out.println("Test Case 2 Result: " + result2); // Expected: 2

        // Test Case 3: No common elements
        int[] nums5 = {1, 3, 5};
        int[] nums6 = {2, 4, 6};
        int result3 = solution.getCommon(nums5, nums6);
        System.out.println("Test Case 3 Result: " + result3); // Expected: -1
    }
}


