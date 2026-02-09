import java.util.*;


public class nextPermutation {

    public static void main(String[] args) {
        nextPermutation sol = new nextPermutation();
        int[] nums = {1, 2, 3}; // Example input
        System.out.println("Original: " + Arrays.toString(nums));
        sol.nextPermutation(nums);
        System.out.println("Next Permutation: " + Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums){

        int n = nums.length;
        int i = n-2;

        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        if(i >= 0) {
            int j = n-1;
            while(j >= i && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1, n-1);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
