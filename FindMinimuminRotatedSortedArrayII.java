public class FindMinimuminRotatedSortedArrayII {
    
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // When nums[mid] == nums[right], skip the duplicate
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArrayII solution = new FindMinimuminRotatedSortedArrayII();

        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 2, 2, 0, 1};

        System.out.println("Min of nums1: " + solution.findMin(nums1)); // Output: 1
        System.out.println("Min of nums2: " + solution.findMin(nums2)); // Output: 0
    }
}


