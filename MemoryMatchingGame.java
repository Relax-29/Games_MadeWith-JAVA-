import java.util.*;

public class MemoryMatchingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the game board
        String[] cards = {"A", "B", "C", "D", "E", "F", "A", "B", "C", "D", "E", "F"};
        List<String> board = Arrays.asList(cards);
        Collections.shuffle(board);

        boolean[] revealed = new boolean[board.size()];
        int matches = 0;
        int attempts = 0;

        System.out.println("Welcome to the Memory Matching Game!");
        System.out.println("Find all matching pairs to win.");
        System.out.println("The board has 12 cards (6 pairs). Each card is represented by a number.");

        // Game loop
        while (matches < board.size() / 2) {
            printBoard(board, revealed);

            System.out.print("Enter the number of your first card (0-11): ");
            int firstCard = scanner.nextInt();
            while (firstCard < 0 || firstCard >= board.size() || revealed[firstCard]) {
                System.out.print("Invalid choice. Try again: ");
                firstCard = scanner.nextInt();
            }

            revealed[firstCard] = true;
            printBoard(board, revealed);

            System.out.print("Enter the number of your second card (0-11): ");
            int secondCard = scanner.nextInt();
            while (secondCard < 0 || secondCard >= board.size() || revealed[secondCard] || secondCard == firstCard) {
                System.out.print("Invalid choice. Try again: ");
                secondCard = scanner.nextInt();
            }

            revealed[secondCard] = true;
            printBoard(board, revealed);

            if (board.get(firstCard).equals(board.get(secondCard))) {
                System.out.println("It's a match!");
                matches++;
            } else {
                System.out.println("Not a match. Try again.");
                revealed[firstCard] = false;
                revealed[secondCard] = false;
            }

            attempts++;
        }

        System.out.println("Congratulations! You found all the matches in " + attempts + " attempts.");
        scanner.close();
    }

    // Method to print the game board
    private static void printBoard(List<String> board, boolean[] revealed) {
        System.out.println("\nBoard:");
        for (int i = 0; i < board.size(); i++) {
            if (revealed[i]) {
                System.out.print(" " + board.get(i) + " ");
            } else {
                System.out.print(" * ");
            }
        }
        System.out.println("\n");
    }
}
