import java.util.Arrays;

public class CheckifStringsCanbeMadeEqualWithOperationsI {
    public boolean canBeEqual(String s1, String s2) {
        // Extract characters at even indices and sort them
        char[] even1 = {s1.charAt(0), s1.charAt(2)};
        char[] even2 = {s2.charAt(0), s2.charAt(2)};
        Arrays.sort(even1);
        Arrays.sort(even2);
        
        // Extract characters at odd indices and sort them
        char[] odd1 = {s1.charAt(1), s1.charAt(3)};
        char[] odd2 = {s2.charAt(1), s2.charAt(3)};
        Arrays.sort(odd1);
        Arrays.sort(odd2);
        
        // Check if the sorted even groups and sorted odd groups are equal
        return Arrays.equals(even1, even2) && Arrays.equals(odd1, odd2);
    }

    public static void main(String[] args) {
        CheckifStringsCanbeMadeEqualWithOperationsI sol = new CheckifStringsCanbeMadeEqualWithOperationsI();

        // Test Cases
        String s1 = "abcd", s2 = "cdab";
        String s3 = "abcd", s4 = "dacb";

        System.out.println("Can '" + s1 + "' and '" + s2 + "' be equal? " + sol.canBeEqual(s1, s2)); // Expected: true
        System.out.println("Can '" + s3 + "' and '" + s4 + "' be equal? " + sol.canBeEqual(s3, s4)); // Expected: false
    }
}


