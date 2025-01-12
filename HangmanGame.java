import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List of words to guess
        String[] words = {"elephant", "giraffe", "kangaroo", "python", "java", "development"};

        // Select a random word
        String wordToGuess = words[(int) (Math.random() * words.length)];
        char[] wordArray = wordToGuess.toCharArray();
        char[] guessedWord = new char[wordArray.length];

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        int attempts = 6; // Number of wrong guesses allowed
        boolean wordGuessed = false;

        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word by entering one letter at a time.");
        System.out.println("You have " + attempts + " attempts to guess the word.");

        while (attempts > 0 && !wordGuessed) {
            System.out.print("Word: ");
            for (char c : guessedWord) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Enter a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            boolean correctGuess = false;
            for (int i = 0; i < wordArray.length; i++) {
                if (wordArray[i] == guess && guessedWord[i] != guess) {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                attempts--;
                System.out.println("Wrong guess! Attempts left: " + attempts);
            } else {
                System.out.println("Good guess!");
            }

            // Check if the word is fully guessed
            wordGuessed = true;
            for (char c : guessedWord) {
                if (c == '_') {
                    wordGuessed = false;
                    break;
                }
            }
        }

        if (wordGuessed) {
            System.out.println("Congratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("Game over! The word was: " + wordToGuess);
        }

        scanner.close();
    }
}
