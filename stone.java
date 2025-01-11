import java.util.Random;
import java.util.Scanner;

public class stone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
       /*
        0 = Stone
        1 = Paper
        2 = Scissors
        */
        String[] choices = {"Stone", "Paper", "Scissors"};

        System.out.println("Welcome to Stone-Paper-Scissors!");
        System.out.println("Enter your choice: 0 for Stone, 1 for Paper, 2 for Scissors");

        while (true) {
            // User input
            System.out.print("Your choice (or type -1 to quit): ");
            int userChoice = scanner.nextInt();

            if (userChoice == -1) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (userChoice < 0 || userChoice > 2) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }


            int computerChoice = random.nextInt(3);

            System.out.println("You chose: " + choices[userChoice]);
            System.out.println("Computer chose: " + choices[computerChoice]);


            if (userChoice == computerChoice) {
                System.out.println("It's a draw!");
            } else if (
                    (userChoice == 0 && computerChoice == 2) || // Stone beats Scissors
                            (userChoice == 1 && computerChoice == 0) || // Paper beats Stone
                            (userChoice == 2 && computerChoice == 1)    // Scissors beats Paper
            ) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }

            System.out.println();
        }

        scanner.close();
    }
}
