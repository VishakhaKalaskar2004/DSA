import java.util.Arrays;

public class FindthePrefixCommonArrayofTwoArrays {

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        int[] count = new int[n + 1];
        int prefixCommon = 0;

        for (int i = 0; i < n; i++) {
            count[A[i]]++;
            if (count[A[i]] == 2) {
                prefixCommon++;
            }

            count[B[i]]++;
            if (count[B[i]] == 2) {
                prefixCommon++;
            }

            ans[i] = prefixCommon;
        }

        return ans;
    }

    public static void main(String[] args) {
        FindthePrefixCommonArrayofTwoArrays sol = new FindthePrefixCommonArrayofTwoArrays();

        // Test Case 1
        int[] A1 = {1, 3, 2, 4};
        int[] B1 = {3, 1, 2, 4};
        int[] result1 = sol.findThePrefixCommonArray(A1, B1);
        System.out.println("Test Case 1 Output: " + Arrays.toString(result1)); 
        // Expected: [0, 2, 3, 4]

        // Test Case 2
        int[] A2 = {2, 3, 1};
        int[] B2 = {3, 1, 2};
        int[] result2 = sol.findThePrefixCommonArray(A2, B2);
        System.out.println("Test Case 2 Output: " + Arrays.toString(result2)); 
        // Expected: [0, 1, 3]
    }
}


