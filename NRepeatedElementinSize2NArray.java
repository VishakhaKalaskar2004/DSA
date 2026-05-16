
public class NRepeatedElementinSize2NArray {

    public int repeatedNTimes(int[] nums) {
        // Look for any element that repeats within a distance of 3
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
                return nums[i];
            }
        }
        // Handle the edge case where the repeated element is at the very end (e.g., [x, y, z, x])
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        NRepeatedElementinSize2NArray solution = new NRepeatedElementinSize2NArray();

        // Test cases
        int[] nums1 = {1, 2, 3, 3};
        int[] nums2 = {2, 1, 2, 5, 3, 2};
        int[] nums3 = {5, 1, 5, 2, 5, 3, 5, 4};

        System.out.println("Repeated element: " + solution.repeatedNTimes(nums1)); // Output: 3
        System.out.println("Repeated element: " + solution.repeatedNTimes(nums2)); // Output: 2
        System.out.println("Repeated element: " + solution.repeatedNTimes(nums3)); // Output: 5
    }
}


