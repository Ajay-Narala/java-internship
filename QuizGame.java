import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer; // Stores the index of the correct option (0, 1, 2, or 3)

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizGame {
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static int timeLimit = 10; // Time in seconds per question

    public static void main(String[] args) {
        // Define Quiz Questions
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1),
            new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Mark Twain", "Harper Lee", "J.K. Rowling", "Ernest Hemingway"}, 1),
            new Question("What is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 3),
            new Question("Which gas do plants absorb from the atmosphere?", new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"}, 2)
        };

        // Start Quiz
        startQuiz(questions);

        // Display Final Results
        System.out.println("\n===== Quiz Over! =====");
        System.out.println("Your final score: " + score + "/" + questions.length);
        System.out.println("🎉 Thanks for playing! 🎉");

        scanner.close(); // Close scanner at the end
    }

    public static void startQuiz(Question[] questions) {
        for (int i = 0; i < questions.length; i++) {
            askQuestion(questions[i], i + 1);
        }
    }

    public static void askQuestion(Question question, int questionNumber) {
        System.out.println("\nQuestion " + questionNumber + ": " + question.question);

        // Display options
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((i + 1) + ". " + question.options[i]);
        }

        // Start Timer
        Timer timer = new Timer();
        final boolean[] answered = {false};

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!answered[0]) {
                    System.out.println("\n⏳ Time's up! You didn't answer in time.");
                    answered[0] = true; // Prevent further input
                }
                timer.cancel();
            }
        };
        timer.schedule(task, timeLimit * 1000);

        // User Input
        System.out.print("Your answer (1-4): ");
        long startTime = System.currentTimeMillis();

        int userAnswer = -1;
        if (scanner.hasNextInt()) {
            userAnswer = scanner.nextInt();
        } else {
            System.out.println("⚠️ Invalid input! Moving to the next question.");
            scanner.nextLine(); // Consume invalid input
        }
        long endTime = System.currentTimeMillis();
        scanner.nextLine(); // Consume leftover newline

        // Cancel Timer
        answered[0] = true;
        timer.cancel();

        // Validate Answer
        if (userAnswer < 1 || userAnswer > 4) {
            System.out.println("Invalid choice! Moving to the next question.");
            return;
        }

        // Check if answered in time
        if ((endTime - startTime) / 1000 > timeLimit) {
            System.out.println("\n⏳ Too late! Your answer was not considered.");
            return;
        }

        // Check Answer
        if (userAnswer - 1 == question.correctAnswer) {
            System.out.println("✅ Correct! 🎉");
            score++;
        } else {
            System.out.println("❌ Wrong! The correct answer was: " + (question.correctAnswer + 1) + ". " + question.options[question.correctAnswer]);
        }
    }
}
