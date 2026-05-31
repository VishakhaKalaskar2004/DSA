import java.util.*;

public class MinimumPairRemovaltoSortArrayI {

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Use long to prevent integer overflow when summing elements
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        // Doubly linked list tracking via array pointers
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = i - 1;
            right[i] = i + 1;
        }

        // Track the count of out-of-order pairs (inversions)
        int inversionCount = 0;

        // Pair structure: key = pair sum, value = index of the left element
        // Sorts primarily by sum, and secondarily by leftmost index (tie-breaking)
        TreeSet<Pair> bst = new TreeSet<>((p1, p2) -> {
            if (p1.sum != p2.sum) {
                return Long.compare(p1.sum, p2.sum);
            }
            return Integer.compare(p1.idx, p2.idx);
        });

        // Initialize pairs and inversion counters
        for (int i = 0; i < n - 1; i++) {
            bst.add(new Pair(arr[i] + arr[i + 1], i));
            if (arr[i] > arr[i + 1]) {
                inversionCount++;
            }
        }

        int operations = 0;

        // Keep merging the minimum pair until no element is out of order
        while (inversionCount > 0 && !bst.isEmpty()) {
            Pair smallest = bst.pollFirst();
            int i = smallest.idx;
            int nextIdx = right[i];

            // Remove adjacent elements from the inversion evaluation
            // 1. Remove the pair itself
            if (arr[i] > arr[nextIdx]) inversionCount--;

            // 2. Remove the link to the preceding element
            int prevIdx = left[i];
            if (prevIdx >= 0) {
                bst.remove(new Pair(arr[prevIdx] + arr[i], prevIdx));
                if (arr[prevIdx] > arr[i]) inversionCount--;
            }

            // 3. Remove the link to the succeeding element
            int nextNextIdx = right[nextIdx];
            if (nextNextIdx < n) {
                bst.remove(new Pair(arr[nextIdx] + arr[nextNextIdx], nextIdx));
                if (arr[nextIdx] > arr[nextNextIdx]) inversionCount--;
            }

            // Perform the merge: element at index 'i' holds the new sum
            arr[i] += arr[nextIdx];
            operations++;

            // Update doubly linked list structure to drop 'nextIdx'
            right[i] = nextNextIdx;
            if (nextNextIdx < n) {
                left[nextNextIdx] = i;
            }

            // Re-evaluate and re-add updated neighboring pairs
            if (prevIdx >= 0) {
                bst.add(new Pair(arr[prevIdx] + arr[i], prevIdx));
                if (arr[prevIdx] > arr[i]) inversionCount++;
            }
            if (nextNextIdx < n) {
                bst.add(new Pair(arr[i] + arr[nextNextIdx], i));
                if (arr[i] > arr[nextNextIdx]) inversionCount++;
            }
        }

        return operations;
    }

    // Pair wrapper class for the TreeSet structure
    private static class Pair {
        long sum;
        int idx;

        Pair(long sum, int idx) {
            this.sum = sum;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        MinimumPairRemovaltoSortArrayI solver = new MinimumPairRemovaltoSortArrayI();

        // Test Case 1: Standard case where middle pairs reduce to sort the array
        int[] nums1 = {5, 2, 3, 1};
        System.out.println("Test 1 Result: " + solver.minimumPairRemoval(nums1)); // Output: 2

        // Test Case 2: Array already sorted
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test 2 Result: " + solver.minimumPairRemoval(nums2)); // Output: 0
    }
}


