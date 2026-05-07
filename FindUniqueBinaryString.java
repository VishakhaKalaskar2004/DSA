import java.util.Arrays;

public class FindUniqueBinaryString {

    public String findDifferentBinaryString(String[] nums) {
        StringBuilder ans = new StringBuilder();
        
        // Cantor's Diagonal Argument:
        // Build a string where the i-th character is the opposite of nums[i].charAt(i)
        for (int i = 0; i < nums.length; i++) {
            char curr = nums[i].charAt(i);
            ans.append(curr == '0' ? '1' : '0');
        }
        
        return ans.toString();
    }

    public static void main(String[] args) {
        FindUniqueBinaryString sol = new FindUniqueBinaryString();

        // Example 1: Input ["01", "10"] -> Output "11" or "00"
        String[] nums1 = {"01", "10"};
        System.out.println("Input: " + Arrays.toString(nums1) + " -> Output: " + sol.findDifferentBinaryString(nums1));

        // Example 2: Input ["00", "01"] -> Output "11" or "10"
        String[] nums2 = {"00", "01"};
        System.out.println("Input: " + Arrays.toString(nums2) + " -> Output: " + sol.findDifferentBinaryString(nums2));

        // Example 3: Input ["111", "011", "001"] -> Output "101" (example result)
        String[] nums3 = {"111", "011", "001"};
        System.out.println("Input: " + Arrays.toString(nums3) + " -> Output: " + sol.findDifferentBinaryString(nums3));
    }
}


