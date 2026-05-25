public class DeleteColumnstoMakeSortedII {
    
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int deletions = 0;
        
        // sorted[i] is true if strs[i] < strs[i+1] is already guaranteed
        boolean[] sorted = new boolean[n - 1];
        
        for (int col = 0; col < m; col++) {
            boolean deleteColumn = false;
            
            // First pass: Check if this column causes a lexicographical violation
            for (int i = 0; i < n - 1; i++) {
                if (!sorted[i] && strs[i].charAt(col) > strs[i + 1].charAt(col)) {
                    deleteColumn = true;
                    break;
                }
            }
            
            if (deleteColumn) {
                deletions++;
            } else {
                // Second pass: Update the sorted status because we are keeping this column
                for (int i = 0; i < n - 1; i++) {
                    if (strs[i].charAt(col) < strs[i + 1].charAt(col)) {
                        sorted[i] = true;
                    }
                }
            }
        }
        
        return deletions;
    }

    public static void main(String[] args) {
        DeleteColumnstoMakeSortedII sol = new DeleteColumnstoMakeSortedII();

        // Example 1
        String[] strs1 = {"ca", "bb", "ac"};
        System.out.println("Example 1: " + sol.minDeletionSize(strs1)); // Expected: 1

        // Example 2
        String[] strs2 = {"xc", "yb", "za"};
        System.out.println("Example 2: " + sol.minDeletionSize(strs2)); // Expected: 0

        // Example 3
        String[] strs3 = {"zyx", "wvu", "tsr"};
        System.out.println("Example 3: " + sol.minDeletionSize(strs3)); // Expected: 3
    }
}

