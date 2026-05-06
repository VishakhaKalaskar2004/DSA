public class FurthestPointFromOrigin {
    
    public int furthestDistanceFromOrigin(String moves) {
        int countL = 0;
        int countR = 0;
        int wildcards = 0;

        // 1. Count moves in each direction and the wildcards
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                countL++;
            } else if (c == 'R') {
                countR++;
            } else {
                wildcards++;
            }
        }

        // 2. Furthest distance is the absolute difference + all wildcards
        // We add wildcards to whichever direction is already dominant
        return Math.abs(countL - countR) + wildcards;
    }

    public static void main(String[] args) {
        FurthestPointFromOrigin sol = new FurthestPointFromOrigin();

        // Test Cases
        String moves1 = "L_RL__R";
        String moves2 = "_R__LL_";
        String moves3 = "_______";

        System.out.println("Test 1: " + sol.furthestDistanceFromOrigin(moves1)); // Expected: 3
        System.out.println("Test 2: " + sol.furthestDistanceFromOrigin(moves2)); // Expected: 5
        System.out.println("Test 3: " + sol.furthestDistanceFromOrigin(moves3)); // Expected: 7
    }
}


