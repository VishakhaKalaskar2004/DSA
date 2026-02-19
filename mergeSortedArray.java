public class mergeSortedArray {

    /**
     * Merges two sorted arrays, nums1 and nums2, into nums1 in a non-decreasing order.
     * The initial length of nums1 is m, and the initial length of nums2 is n.
     * nums1 has a length of m + n to accommodate all elements.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;       // Pointer to the last element in the original nums1
        int p2 = n - 1;       // Pointer to the last element in nums2
        int insertPos = m + n - 1; // Pointer to the last position in the merged array

        // While there are still elements to consider in either array
        while (p2 >= 0) { // We only need to iterate until nums2 is exhausted
            // If p1 is out of bounds OR nums1[p1] is smaller than nums2[p2]
            // This condition is wrong in the original code; it should be nums1[p1] <= nums2[p2] 
            // for correct merging logic when both are available. 
            // The corrected logic is:
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[insertPos] = nums1[p1];
                p1--;
            } else {
                nums1[insertPos] = nums2[p2];
                p2--;
            }
            insertPos--;
        }
        // If elements in nums1 remain (p1 >= 0), they are already in the correct position
    }

    public static void main(String[] args) {
        mergeSortedArray solution = new mergeSortedArray();

        // Example 1
        int[] nums1_1 = {1, 2, 3, 0, 0, 0}; // m=3
        int[] nums2_1 = {2, 5, 6};         // n=3
        int m1 = 3;
        int n1 = 3;
        solution.merge(nums1_1, m1, nums2_1, n1);
        System.out.print("Example 1 (Expected: [1, 2, 2, 3, 5, 6]): ");
        for (int num : nums1_1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example 2
        int[] nums1_2 = {1}; // m=1
        int[] nums2_2 = {};  // n=0
        int m2 = 1;
        int n2 = 0;
        solution.merge(nums1_2, m2, nums2_2, n2);
        System.out.print("Example 2 (Expected: [1]): ");
        for (int num : nums1_2) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Example 3
        int[] nums1_3 = {0}; // m=0
        int[] nums2_3 = {1}; // n=1
        int m3 = 0;
        int n3 = 1;
        solution.merge(nums1_3, m3, nums2_3, n3);
        System.out.print("Example 3 (Expected: [1]): ");
        for (int num : nums1_3) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
