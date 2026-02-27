import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.TreeSet;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        // ts[0] stores indices with an even total count of operations so far
        // ts[1] stores indices with an odd total count
        TreeSet<Integer>[] ts = new TreeSet[2];
        Arrays.setAll(ts, i -> new TreeSet<>());

        for (int i = 0; i <= n; i++) {
            ts[i % 2].add(i);
        }

        int cnt0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                cnt0++;
            }
        }

        // Remove initial count from the set
        ts[cnt0 % 2].remove(cnt0);
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(cnt0);
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int cur = q.poll();
                if (cur == 0) {
                    return ans;
                }

                // Calculate the range of possible next zero counts
                int l = cur + k - 2 * Math.min(cur, k);
                int r = cur + k - 2 * Math.max(k - n + cur, 0);

                TreeSet<Integer> t = ts[l % 2];
                Integer next = t.ceiling(l);

                // Add all reachable new counts to the queue and remove them from the set
                while (next != null && next <= r) {
                    q.offer(next);
                    t.remove(next);
                    next = t.ceiling(l);
                }
            }
            ans++;
        }
        return -1; // If not possible to reach all '1's
    }

    // Main function to run and test the minOperations method
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: s = "10011", k = 3
        String s1 = "10011";
        int k1 = 3;
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + sol.minOperations(s1, k1)); // Expected output: 1

        System.out.println();

        // Example 2: s = "101", k = 2
        String s2 = "101";
        int k2 = 2;
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + sol.minOperations(s2, k2)); // Expected output: -1

        System.out.println();

        // Example 3: s = "001", k = 2
        String s3 = "001";
        int k3 = 2;
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + sol.minOperations(s3, k3)); // Expected output: 2
    }
}
