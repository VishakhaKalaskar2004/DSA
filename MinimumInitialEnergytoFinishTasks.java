import java.util.Arrays;

public class MinimumInitialEnergytoFinishTasks {

    public int minimumEffort(int[][] tasks) {
        // Sort tasks by (minimum - actual) in descending order to prioritize 
        // tasks with the biggest "cushion" required first.
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        
        int totalEffort = 0;
        int currentEffort = 0;
        
        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];
            
            // If current effort is not enough to start the task, increase initial effort
            if (currentEffort < minimum) {
                totalEffort += (minimum - currentEffort);
                currentEffort = minimum;
            }
            // Complete the task
            currentEffort -= actual;
        }
        
        return totalEffort;
    }

    public static void main(String[] args) {
        MinimumInitialEnergytoFinishTasks sol = new MinimumInitialEnergytoFinishTasks();
        
        // Example Case:
        // Task 0: actual 1, min 3
        // Task 1: actual 2, min 4
        // Task 2: actual 10, min 11
        // Task 3: actual 10, min 10
        int[][] tasks = {{1,3},{2,4},{10,11},{10,12},{8,9}};
        
        int result = sol.minimumEffort(tasks);
        System.out.println("Minimum initial effort required: " + result); // Expected: 21
    }
}


