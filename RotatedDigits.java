public class RotatedDigits {

    /**
     * Counts how many "good" numbers are in the range [1, n].
     * A number is good if rotating it results in a different valid number.
     */
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGood(int num) {
        boolean hasChangingDigit = false;
        while (num > 0) {
            int digit = num % 10;
            // 3, 4, and 7 are invalid when rotated
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            // 2, 5, 6, and 9 change to a different digit when rotated
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasChangingDigit = true;
            }
            // 0, 1, and 8 remain the same; they are valid but don't make the number "good" alone
            num /= 10;
        }
        return hasChangingDigit;
    }

    public static void main(String[] args) {
        RotatedDigits sol = new RotatedDigits();
        
        // Example 1: n = 10 (Good numbers: 2, 5, 6, 9)
        int n1 = 10;
        System.out.println("Input: " + n1 + " | Output: " + sol.rotatedDigits(n1)); // Output: 4

        // Example 2: n = 2
        int n2 = 2;
        System.out.println("Input: " + n2 + " | Output: " + sol.rotatedDigits(n2)); // Output: 1
    }
}



