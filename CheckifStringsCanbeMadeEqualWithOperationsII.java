
public class CheckifStringsCanbeMadeEqualWithOperationsII {

    public boolean checkStrings(String s1, String s2) {
        // Frequency arrays for characters at even and odd positions
        int[] evenFreq = new int[26];
        int[] oddFreq = new int[26];
        
        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                evenFreq[s1.charAt(i) - 'a']++;
                evenFreq[s2.charAt(i) - 'a']--;
            } else {
                oddFreq[s1.charAt(i) - 'a']++;
                oddFreq[s2.charAt(i) - 'a']--;
            }
        }
        
        // If all frequencies are 0, characters at even/odd indices matched
        for (int i = 0; i < 26; i++) {
            if (evenFreq[i] != 0 || oddFreq[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckifStringsCanbeMadeEqualWithOperationsII sol = new CheckifStringsCanbeMadeEqualWithOperationsII();
        
        // Example 1: Should return true
        String s1 = "abcdba", s2 = "cabdab";
        System.out.println("Example 1: " + sol.checkStrings(s1, s2)); // Output: true
        
        // Example 2: Should return false
        String s3 = "abe", s4 = "bea";
        System.out.println("Example 2: " + sol.checkStrings(s3, s4)); // Output: false
    }
}


