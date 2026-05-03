class RotateString {
    public boolean rotateString(String s, String goal) {
        // A rotation must have the same length as the original string
        if (s.length() != goal.length()) {
            return false;
        }

        // Concatenate s with itself. This contains all possible rotations of s.
        String doubled = s + s;

        // Check if goal is a substring of the doubled string
        return doubled.contains(goal);
    }

    public static void main(String[] args) {
        RotateString sol = new RotateString();

        // Test Case 1
        String s1 = "abcde";
        String goal1 = "cdeab";
        System.out.println("Test 1: " + sol.rotateString(s1, goal1)); // Expected: true

        // Test Case 2
        String s2 = "abcde";
        String goal2 = "abced";
        System.out.println("Test 2: " + sol.rotateString(s2, goal2)); // Expected: false
    }
}
