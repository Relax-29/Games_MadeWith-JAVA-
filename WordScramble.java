import java.util.Random;
import java.util.Scanner;

public class WordScramble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // List of words to choose from
        String[] words = {"programming", "java", "developer", "game", "scramble", "keyboard", "computer"};

        // Randomly select a word
        String originalWord = words[random.nextInt(words.length)];

        // Scramble the selected word
        String scrambledWord = scrambleWord(originalWord);

        System.out.println("Welcome to the Word Scramble Game!");
        System.out.println("Unscramble the word: " + scrambledWord);
        System.out.println("Type your guess below:");

        boolean guessedCorrectly = false;
        int attempts = 0;

        while (!guessedCorrectly) {
            System.out.print("Your guess: ");
            String userGuess = scanner.nextLine();
            attempts++;

            if (userGuess.equalsIgnoreCase(originalWord)) {
                guessedCorrectly = true;
                System.out.println("Congratulations! You guessed the word correctly in " + attempts + " attempts.");
            } else {
                System.out.println("Wrong guess. Try again!");
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Method to scramble a word
    public static String scrambleWord(String word) {
        char[] letters = word.toCharArray();
        Random random = new Random();

        for (int i = 0; i < letters.length; i++) {
            int j = random.nextInt(letters.length);

            // Swap letters[i] and letters[j]
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }

        return new String(letters);
    }
}
