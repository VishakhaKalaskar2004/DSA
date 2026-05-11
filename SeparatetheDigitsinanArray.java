import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeparatetheDigitsinanArray {

    public int[] separateDigits(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        // 1. Iterate through each number in the input array
        for (int num : nums) {
            // 2. Convert each number to a string to easily access digits
            String s = String.valueOf(num);
            // 3. Add each character (converted back to int) to the list
            for (char c : s.toCharArray()) {
                ans.add(c - '0');
            }
        }
        // 4. Convert the List to a primitive int array for the return type
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        SeparatetheDigitsinanArray sol = new SeparatetheDigitsinanArray();
        
        // Test case 1
        int[] nums1 = {13, 25, 83, 77};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + Arrays.toString(sol.separateDigits(nums1)));
        
        // Test case 2
        int[] nums2 = {7, 1, 3, 9};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + Arrays.toString(sol.separateDigits(nums2)));
    }
}


