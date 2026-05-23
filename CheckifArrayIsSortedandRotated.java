public class CheckifArrayIsSortedandRotated {

    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckifArrayIsSortedandRotated solver = new CheckifArrayIsSortedandRotated();

        // Test Case 1: Sorted and rotated array
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1: " + solver.check(nums1)); // Expected: true

        // Test Case 2: Already sorted array (0 rotations)
        int[] nums2 = {1, 2, 3};
        System.out.println("Test 2: " + solver.check(nums2)); // Expected: true

        // Test Case 3: Not sorted and rotated
        int[] nums3 = {2, 1, 3, 4};
        System.out.println("Test 3: " + solver.check(nums3)); // Expected: false
    }
}

