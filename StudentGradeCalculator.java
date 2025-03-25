import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        int numSubjects;
        while (true) {
            System.out.print("Enter the number of subjects: ");
            if (scanner.hasNextInt()) {
                numSubjects = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (numSubjects > 0) break;
                System.out.println("⚠️ Invalid input! Number of subjects must be greater than zero.");
            } else {
                System.out.println("⚠️ Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Array to store marks
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Taking input for marks
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
                if (scanner.hasNextInt()) {
                    int mark = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (mark >= 0 && mark <= 100) {
                        marks[i] = mark;
                        totalMarks += mark;
                        break;
                    } else {
                        System.out.println("⚠️ Invalid marks! Please enter a value between 0 and 100.");
                    }
                } else {
                    System.out.println("⚠️ Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
        }

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Determine Grade
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';  // Fail
        }

        // Display Results
        System.out.println("\n📊 Results:");
        System.out.println("📌 Total Marks: " + totalMarks + "/" + (numSubjects * 100));
        System.out.printf("📌 Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("📌 Grade: " + grade);

        System.out.println("\n🎉 Thank you for using the Student Grade Calculator!");
        scanner.close();
    }
}
