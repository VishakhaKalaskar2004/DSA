public class ShortestDistancetoTargetStringinaCircularArray {

    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                // Calculate direct distance
                int directDist = Math.abs(i - startIndex);
                // Calculate wrap-around circular distance
                int circularDist = n - directDist;
                
                // Keep the smaller of the two, and update global minimum
                int shortestPath = Math.min(directDist, circularDist);
                minDistance = Math.min(minDistance, shortestPath);
            }
        }

        // If minDistance was never updated, the target doesn't exist in the array
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) {
        ShortestDistancetoTargetStringinaCircularArray sol = new ShortestDistancetoTargetStringinaCircularArray();

        // Example 1
        String[] words1 = {"hello", "i", "am", "leetcode", "hello"};
        System.out.println("Result 1: " + sol.closestTarget(words1, "hello", 1)); // Output: 1

        // Example 2
        String[] words2 = {"a", "b", "leetcode"};
        System.out.println("Result 2: " + sol.closestTarget(words2, "leetcode", 0)); // Output: 1

        // Example 3
        String[] words3 = {"i", "eat", "apple"};
        System.out.println("Result 3: " + sol.closestTarget(words3, "banana", 1)); // Output: -1
    }
}


