import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int maxAttempts = 10;
        int score = 0;

        System.out.println("Welcome to Guess the Number!");
        System.out.println("I have selected a number between " + lowerBound + " and " + upperBound);

        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            System.out.print("Enter your guess (Attempt " + attempts + "): ");
            int userGuess = scanner.nextInt();

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                score += 10; // You can adjust scoring as needed
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber);
            }
        }

        System.out.println("Your score: " + score);
        scanner.close();
    }
}
