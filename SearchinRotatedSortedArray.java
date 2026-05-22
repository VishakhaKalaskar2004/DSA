public class SearchinRotatedSortedArray {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if the left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if the target lies within the sorted left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // Otherwise, the right half must be sorted
            else {
                // Check if the target lies within the sorted right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchinRotatedSortedArray sol = new SearchinRotatedSortedArray();

        // Example 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Example 1 Output: " + sol.search(nums1, 0)); // Expected: 4

        // Example 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Example 2 Output: " + sol.search(nums2, 3)); // Expected: -1

        // Example 3
        int[] nums3 = {1};
        System.out.println("Example 3 Output: " + sol.search(nums3, 0)); // Expected: -1
    }
}


