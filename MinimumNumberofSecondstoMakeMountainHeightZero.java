
public class MinimumNumberofSecondstoMakeMountainHeightZero {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 0;
        // Maximum possible time: slowest worker takes max height (1+2+...+MH)
        long right = (long) workerTimes[0] * mountainHeight * (long)(mountainHeight + 1) / 2;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canReduce(mountainHeight, workerTimes, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private boolean canReduce(int targetHeight, int[] workerTimes, long timeLimit) {
        long totalReducedHeight = 0;
        for (int wTime : workerTimes) {
            // Formula derived from: wTime * (h*(h+1)/2) <= timeLimit
            // Equivalent to finding max h such that h^2 + h - 2*timeLimit/wTime <= 0
            long h = (long) ((-1 + Math.sqrt(1 + 8.0 * timeLimit / wTime)) / 2);
            totalReducedHeight += h;
            if (totalReducedHeight >= targetHeight) return true;
        }
        return totalReducedHeight >= targetHeight;
    }

    public static void main(String[] args) {
        MinimumNumberofSecondstoMakeMountainHeightZero sol = new MinimumNumberofSecondstoMakeMountainHeightZero();

        // Test Case 1
        int mountainHeight1 = 4;
        int[] workerTimes1 = {2, 1, 1};
        System.out.println("Result 1: " + sol.minNumberOfSeconds(mountainHeight1, workerTimes1)); // Expected: 3

        // Test Case 2
        int mountainHeight2 = 10;
        int[] workerTimes2 = {3, 2, 2, 4};
        System.out.println("Result 2: " + sol.minNumberOfSeconds(mountainHeight2, workerTimes2)); // Expected: 12
    }
}


