import java.util.Random;
import java.util.Scanner;

public class FunnyMathQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Funny Math Quiz!");
        System.out.println("Answer these hilarious math questions (or are they?).");
        System.out.println("Type 'exit' to quit anytime.");

        int score = 0;

        while (true) {
            int num1 = random.nextInt(10) + 1;
            int num2 = random.nextInt(10) + 1;
            int correctAnswer = num1 + num2;

            // Randomly generate a "funny" wrong answer
            int funnyAnswer = correctAnswer + random.nextInt(5) - 2;
            boolean isCorrectDisplayed = random.nextBoolean();

            System.out.println("What is " + num1 + " + " + num2 + "?");
            if (isCorrectDisplayed) {
                System.out.println("A. " + correctAnswer);
                System.out.println("B. " + funnyAnswer);
            } else {
                System.out.println("A. " + funnyAnswer);
                System.out.println("B. " + correctAnswer);
            }

            System.out.print("Your choice (A/B): ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("EXIT")) {
                System.out.println("Thanks for playing! Your final score: " + score);
                break;
            }

            boolean isCorrect = (choice.equals("A") && isCorrectDisplayed) || (choice.equals("B") && !isCorrectDisplayed);

            if (isCorrect) {
                System.out.println("Correct! You're a math genius (probably).\n");
                score++;
            } else {
                System.out.println("Wrong! But don't worry, math is hard for everyone.\n");
            }
        }

        scanner.close();
    }
}
