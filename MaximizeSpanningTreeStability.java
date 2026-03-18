import java.util.*;

public class MaximizeSpanningTreeStability {

    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;

            if (rank[px] < rank[py]) parent[px] = py;
            else if (rank[px] > rank[py]) parent[py] = px;
            else {
                parent[py] = px;
                rank[px]++;
            }
            return true;
        }
    }

    public static int maxStability(int n, int[][] edges, int k) {
        int lo = 1, hi = 200000, ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (canBuild(n, edges, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private static boolean canBuild(int n, int[][] edges, int k, int x) {
        DSU dsu = new DSU(n);
        int count = 0;
        int upgrades = 0;

        List<int[]> optional = new ArrayList<>();

        // Step 1: Must edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 1) {
                if (s < x) return false;

                if (!dsu.union(u, v)) return false;

                count++;
            } else {
                optional.add(e);
            }
        }

        // Step 2: Strong edges (no upgrade)
        for (int[] e : optional) {
            int u = e[0], v = e[1], s = e[2];

            if (s >= x && dsu.union(u, v)) {
                count++;
                if (count == n - 1) return true;
            }
        }

        // Step 3: Upgrade edges
        for (int[] e : optional) {
            int u = e[0], v = e[1], s = e[2];

            if (s < x && s * 2 >= x && dsu.union(u, v)) {
                upgrades++;
                if (upgrades > k) return false;

                count++;
                if (count == n - 1) return true;
            }
        }

        return count == n - 1;
    }

    // 🔥 MAIN FUNCTION FOR TESTING
    public static void main(String[] args) {

        // Example 1
        int n1 = 3;
        int[][] edges1 = {
            {0, 1, 2, 1},
            {1, 2, 3, 0}
        };
        int k1 = 1;

        System.out.println("Output Example 1: " + maxStability(n1, edges1, k1));
        // Expected: 2

        // Example 2
        int n2 = 3;
        int[][] edges2 = {
            {0, 1, 4, 0},
            {1, 2, 3, 0},
            {0, 2, 1, 0}
        };
        int k2 = 2;

        System.out.println("Output Example 2: " + maxStability(n2, edges2, k2));
        // Expected: 6
    }
}