public class FindMinimuminRotatedSortedArray {
    
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        // Binary search to find the inflection point (minimum element)
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // If mid element is greater than the rightmost element, 
            // the minimum must be in the right half.
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Otherwise, the minimum is at mid or in the left half.
            else {
                right = mid;
            }
        }
        
        // left and right converge to the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArray solution = new FindMinimuminRotatedSortedArray();

        // Test Case 1: Standard rotated sorted array
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1 Output: " + solution.findMin(nums1)); // Expected: 1

        // Test Case 2: Array rotated multiple times
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 2 Output: " + solution.findMin(nums2)); // Expected: 0

        // Test Case 3: Fully sorted array (0 rotations)
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Test 3 Output: " + solution.findMin(nums3)); // Expected: 11
    }
}


