import java.util.Arrays;

public class DestroyingAsteroids {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Sort asteroids to collide with the smallest ones first
        Arrays.sort(asteroids);
        
        // Use long to prevent integer overflow during accumulation
        long currentMass = mass; 
        
        for (int asteroid : asteroids) {
            // If the planet is smaller than the asteroid, it is destroyed
            if (currentMass < asteroid) {
                return false;
            }
            // Otherwise, the planet absorbs the asteroid's mass
            currentMass += asteroid;
        }
        
        return true;
    }

    public static void main(String[] args) {
        DestroyingAsteroids solver = new DestroyingAsteroids();

        // Test Case 1: Expected output: true
        int mass1 = 10;
        int[] asteroids1 = {3, 9, 19, 5, 21};
        System.out.println("Test 1 Result: " + solver.asteroidsDestroyed(mass1, asteroids1));

        // Test Case 2: Expected output: false
        int mass2 = 5;
        int[] asteroids2 = {4, 9, 23, 4};
        System.out.println("Test 2 Result: " + solver.asteroidsDestroyed(mass2, asteroids2));
    }
}

