import java.util.*;

public class MinimumPairRemovaltoSortArrayII {
    
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Use long to prevent integer overflow during repetitive summation operations
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        // Simulating a doubly linked list via pointer tracking arrays
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            left[i] = i - 1;
            right[i] = i + 1;
        }

        // Maintain the count of active inversions (unsorted adjacent values)
        int inversionCount = 0;

        // TreeSet acts as a BST sorting by pair sum first, then by leftmost index
        TreeSet<Pair> bst = new TreeSet<>((p1, p2) -> {
            if (p1.sum != p2.sum) {
                return Long.compare(p1.sum, p2.sum);
            }
            return Integer.compare(p1.idx, p2.idx);
        });

        // Initialize and load tracking loops
        for (int i = 0; i < n - 1; i++) {
            bst.add(new Pair(arr[i] + arr[i + 1], i));
            if (arr[i] > arr[i + 1]) {
                inversionCount++;
            }
        }

        int operations = 0;

        // Process merges while there are elements out of order
        while (inversionCount > 0 && !bst.isEmpty()) {
            Pair smallest = bst.pollFirst();
            int i = smallest.idx;
            
            int nextIdx = right[i];
            int prevIdx = left[i];
            int nextNextIdx = right[nextIdx];

            // Safely remove structural pairings from inversion checks before modifications
            // 1. Remove the target pair itself
            if (arr[i] > arr[nextIdx]) inversionCount--;

            // 2. Remove the previous adjacent pair structure
            if (prevIdx >= 0 && prevIdx < n && right[prevIdx] < n) {
                bst.remove(new Pair(arr[prevIdx] + arr[i], prevIdx));
                if (arr[prevIdx] > arr[i]) inversionCount--;
            }

            // 3. Remove the next adjacent pair structure
            if (nextNextIdx < n) {
                bst.remove(new Pair(arr[nextIdx] + arr[nextNextIdx], nextIdx));
                if (arr[nextIdx] > arr[nextNextIdx]) inversionCount--;
            }

            // Perform the mathematical merge
            arr[i] += arr[nextIdx];
            operations++;

            // Resplice the doubly linked list pointers to drop nextIdx
            right[i] = nextNextIdx;
            if (nextNextIdx <= n) {
                left[nextNextIdx] = i;
            }

            // Re-evaluate and re-insert the newly formed neighboring pairs
            if (prevIdx >= 0 && prevIdx < n && right[prevIdx] < n) {
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

    // Pair container to track minimum sums with their leftmost identifier index
    private static class Pair {
        long sum;
        int idx;

        Pair(long sum, int idx) {
            this.sum = sum;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        MinimumPairRemovaltoSortArrayII solver = new MinimumPairRemovaltoSortArrayII();

        // Test Case 1: Standard out-of-order array
        int[] nums1 = {5, 2, 3, 1};
        System.out.println("Test 1 Result: " + solver.minimumPairRemoval(nums1)); 
        // Expected: 2 -> (3,1) merges to 4 -> [5, 2, 4], then (2,4) merges to 6 -> [5, 6] (Sorted)

        // Test Case 2: Array already sorted
        int[] nums2 = {1, 2, 2};
        System.out.println("Test 2 Result: " + solver.minimumPairRemoval(nums2)); 
        // Expected: 0
    }
}

