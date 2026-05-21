import java.util.HashSet;
import java.util.Set;

public class FindtheLengthoftheLongestCommonPrefix {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixes = new HashSet<>();
        
        // Generate and store all prefixes from arr1
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }

        int maxLen = 0;
        
        // Find longest matching prefix in arr2
        for (int num : arr2) {
            while (num > 0 && !prefixes.contains(num)) {
                num /= 10;
            }
            if (num > 0) {
                maxLen = Math.max(maxLen, String.valueOf(num).length());
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        FindtheLengthoftheLongestCommonPrefix solver = new FindtheLengthoftheLongestCommonPrefix();

        // Test Case 1
        int[] arr1 = {1, 10, 100};
        int[] arr2 = {1000};
        System.out.println("Test Case 1 Output: " + solver.longestCommonPrefix(arr1, arr2)); // Expected: 3 (prefix: 100)

        // Test Case 2
        int[] arr3 = {1, 2, 3};
        int[] arr4 = {4, 44, 444};
        System.out.println("Test Case 2 Output: " + solver.longestCommonPrefix(arr3, arr4)); // Expected: 0 (no common prefix)
    }
}


