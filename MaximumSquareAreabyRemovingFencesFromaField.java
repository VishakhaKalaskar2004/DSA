import java.util.*;

public class MaximumSquareAreabyRemovingFencesFromaField {

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Collect all possible gap sizes from horizontal and vertical fences
        Set<Integer> hGaps = getGaps(m, hFences);
        Set<Integer> vGaps = getGaps(n, vFences);

        // Find intersection
        hGaps.retainAll(vGaps);
        
        int maxSide = 0;
        for (int gap : hGaps) {
            maxSide = Math.max(maxSide, gap);
        }

        if (maxSide == 0) return -1;
        return (int) ((((long) maxSide * maxSide)) % 1_000_000_007);
    }

    private Set<Integer> getGaps(int limit, int[] fences) {
        int[] f = new int[fences.length + 2];
        System.arraycopy(fences, 0, f, 0, fences.length);
        f[fences.length] = 1;
        f[fences.length + 1] = limit;
        Arrays.sort(f);
        
        Set<Integer> gaps = new HashSet<>();
        for (int i = 0; i < f.length; i++) {
            for (int j = i + 1; j < f.length; j++) {
                gaps.add(f[j] - f[i]);
            }
        }
        return gaps;
    }

    public static void main(String[] args) {
        MaximumSquareAreabyRemovingFencesFromaField sol = new MaximumSquareAreabyRemovingFencesFromaField();
        System.out.println("Output: " + sol.maximizeSquareArea(4, 3, new int[]{2}, new int[]{2})); // Expected: 4
    }
}


