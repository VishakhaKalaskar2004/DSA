import java.util.Arrays;

public class CounttheNumberofSpecialCharactersII {
    public int numberOfSpecialChars(String word) {
        // Track the last seen index of lowercase letters (-1 means not seen)
        int[] lastLower = new int[26];
        // Track the first seen index of uppercase letters (-1 means not seen)
        int[] firstUpper = new int[26];
        
        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, -1);
        
        // Single pass to record positions
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) {
                lastLower[c - 'a'] = i; // Always update to find the LAST occurrence
            } else if (Character.isUpperCase(c)) {
                if (firstUpper[c - 'A'] == -1) {
                    firstUpper[c - 'A'] = i; // Only update once to find the FIRST occurrence
                }
            }
        }
        
        // Count valid special characters
        int specialCount = 0;
        for (int i = 0; i < 26; i++) {
            // Valid if both exist AND the last lowercase comes before the first uppercase
            if (lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]) {
                specialCount++;
            }
        }
        
        return specialCount;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        CounttheNumberofSpecialCharactersII solver = new CounttheNumberofSpecialCharactersII();

        // Test Case 1: Expected output: 3 (a, b, and c match perfectly)
        String test1 = "aaAbcBC";
        System.out.println("Input: " + test1 + " -> Output: " + solver.numberOfSpecialChars(test1));

        // Test Case 2: Expected output: 0 (lowercase 'b' appears after uppercase 'B')
        String test2 = "abcBcd";
        System.out.println("Input: " + test2 + " -> Output: " + solver.numberOfSpecialChars(test2));

        // Test Case 3: Expected output: 0 (No uppercase variants exist)
        String test3 = "abcd";
        System.out.println("Input: " + test3 + " -> Output: " + solver.numberOfSpecialChars(test3));
    }
}

