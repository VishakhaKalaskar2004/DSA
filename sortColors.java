public class sortColors {
    /**
     * Method to sort the array in-place using the Dutch National Flag algorithm.
     * This algorithm sorts an array of 0s, 1s, and 2s in a single pass.
     * It uses three pointers:
     * low: keeps track of the boundary between 0s and non-0s (elements < 1).
     * mid: the current element being examined.
     * high: keeps track of the boundary between 2s and non-2s (elements > 1).
     *
     * The goal is to move all 0s to the left, all 2s to the right, and all 1s to the middle.
     */
    public void sortcolor(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        // Iterate through the array until the middle pointer crosses the high pointer
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Case 1: Current element is 0.
                // Swap nums[low] and nums[mid]. This moves the 0 to the low section
                // and a non-0 (either 1 or 2) to the mid section for re-evaluation.
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                
                // Increment both low and mid pointers
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // Case 2: Current element is 1.
                // It is already in the correct relative position (between 0s and 2s).
                // Just move to the next element.
                mid++;
            } else {
                // Case 3: Current element is 2.
                // Swap with nums[high]. This moves the 2 to the high section.
                // The swapped element from high (which could be 0, 1, or 2)
                // is now at the mid position and needs to be checked in the next iteration,
                // so the mid pointer does not increment here.
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                // Decrement the high pointer
                high--;
            }
        }
    }

    public static void main(String[] args) {
        // Example array with 0s, 1s, and 2s
        int arr[] = {1, 0, 2, 1, 2, 0, 2};
        
        // Create an instance of the class to call the non-static method
        sortColors sorter = new sortColors();
        
        // Call the sort method on the array
        sorter.sortcolor(arr);
        
        // Print the sorted array to verify the algorithm's correctness
        System.out.print("Sorted array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
