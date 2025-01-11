import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have chosen a number between 1 and 100.");
        System.out.println("Can you guess what it is?");


        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 7; // Maximum attempts

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've used all your attempts. The number was: " + targetNumber);
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
