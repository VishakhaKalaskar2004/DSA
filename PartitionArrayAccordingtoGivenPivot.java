public class PartitionArrayAccordingtoGivenPivot {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];
        int index = 0;

        // Pass 1: Elements strictly less than the pivot
        for (int num : nums) {
            if (num < pivot) {
                result[index++] = num;
            }
        }

        // Pass 2: Elements equal to the pivot
        for (int num : nums) {
            if (num == pivot) {
                result[index++] = num;
            }
        }

        // Pass 3: Elements strictly greater than the pivot
        for (int num : nums) {
            if (num > pivot) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // FIXED: Instantiating the correct class name
        PartitionArrayAccordingtoGivenPivot solution = new PartitionArrayAccordingtoGivenPivot();

        // Test Case 1
        int[] nums1 = {9, 12, 5, 10, 14, 3, 10};
        int pivot1 = 10;
        int[] result1 = solution.pivotArray(nums1, pivot1);
        System.out.print("Test Case 1 Output: ");
        printArray(result1); // Expected: [9, 5, 3, 10, 10, 12, 14]

        // Test Case 2
        int[] nums2 = {-3, 4, 3, 2};
        int pivot2 = 2;
        int[] result2 = solution.pivotArray(nums2, pivot2);
        System.out.print("Test Case 2 Output: ");
        printArray(result2); // Expected: [-3, 2, 4, 3]
    }

    // Helper method to display array output format cleanly
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
