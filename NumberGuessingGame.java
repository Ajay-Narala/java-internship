import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 7;
        int score = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        System.out.println("🎉 Welcome to the Number Guessing Game! 🎉");
        System.out.println("You need to guess a number between " + lowerBound + " and " + upperBound + ".");

        while (playAgain) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n🎯 New Round Started! Try to guess the number.");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ": Enter your guess: ");

                // Validate user input
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠️ Invalid input! Please enter a valid number.");
                    scanner.nextLine();  // Consume invalid input
                    continue;
                }

                int guess = scanner.nextInt();
                scanner.nextLine();  // Consume leftover newline
                attempts++;

                if (guess < targetNumber) {
                    System.out.println("📉 Too low! Try again.");
                } else if (guess > targetNumber) {
                    System.out.println("📈 Too high! Try again.");
                } else {
                    System.out.println("🎉 Congratulations! You guessed the number in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1;  // More points for fewer attempts
                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The correct number was " + targetNumber + ".");
            }

            roundsPlayed++;
            System.out.println("🏆 Your current score: " + score);

            // Asking if the user wants to play again
            while (true) {
                System.out.print("\n🔄 Do you want to play another round? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                
                if (response.equals("yes")) {
                    playAgain = true;
                    break;
                } else if (response.equals("no")) {
                    playAgain = false;
                    break;
                } else {
                    System.out.println("⚠️ Invalid response! Please enter 'yes' or 'no'.");
                }
            }
        }

        System.out.println("\n🎮 Game Over! You played " + roundsPlayed + " rounds with a final score of " + score + ".");
        System.out.println("🎊 Thanks for playing! Have a great day! 🎊");

        scanner.close();
    }
}
