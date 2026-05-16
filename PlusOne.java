public class PlusOne {
    
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1; // Fixed: Added index [0]
        return newNumber;
    }

    public static void main(String[] args) {
        PlusOne solution = new PlusOne();

        int[] digits1 = {1, 2, 3};
        int[] digits2 = {4, 3, 2, 1};
        int[] digits3 = {9, 9, 9};

        // Fixed: Changed System.print to System.out::print
        java.util.Arrays.stream(solution.plusOne(digits1)).forEach(System.out::print); 
        System.out.println();
        java.util.Arrays.stream(solution.plusOne(digits2)).forEach(System.out::print); 
        System.out.println();
        java.util.Arrays.stream(solution.plusOne(digits3)).forEach(System.out::print); 
        System.out.println();
    }
}

