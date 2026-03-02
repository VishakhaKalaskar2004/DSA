import java.util.Arrays;
import java.util.Comparator;

class sortbybits {
    public int[] sortByBits(int[] arr) {
        // Convert int[] to Integer[] to use a custom comparator
        Integer[] nums = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(nums, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);

            // If bit counts are different, sort by bit count
            if (countA != countB) {
                return countA - countB;
            }
            // If bit counts are equal, sort by actual value
            return a - b;
        });

        // Convert Integer[] back to int[]
        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
    }
}

public class Main {
    public static void main(String[] args) {
        sortbybits solution = new sortbybits();

        // Example 1
        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] result1 = solution.sortByBits(arr1);
        System.out.println("Result 1: " + Arrays.toString(result1));

        // Example 2
        int[] arr2 = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        int[] result2 = solution.sortByBits(arr2);
        System.out.println("Result 2: " + Arrays.toString(result2));
    }
}
