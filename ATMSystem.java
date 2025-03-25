import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize account balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount! Please enter a positive value.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! Remaining balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount! Please enter a positive value.");
        }
    }

    // Check balance method
    public double getBalance() {
        return balance;
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    // Constructor to initialize ATM with a user's account
    public ATM(BankAccount account, Scanner scanner) {
        this.userAccount = account;
        this.scanner = scanner;
    }

    // Display the ATM menu
    public void showMenu() {
        while (true) {
            try {
                System.out.println("\n===== ATM MENU =====");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number between 1 and 4.");
                    scanner.nextLine(); // Clear the invalid input
                    continue;
                }

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your current balance: $" + userAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        if (scanner.hasNextDouble()) {
                            double depositAmount = scanner.nextDouble();
                            userAccount.deposit(depositAmount);
                        } else {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine(); // Clear invalid input
                        }
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        if (scanner.hasNextDouble()) {
                            double withdrawAmount = scanner.nextDouble();
                            userAccount.withdraw(withdrawAmount);
                        } else {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine(); // Clear invalid input
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option! Please select a valid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Prevent infinite loop on input errors
            }
        }
    }
}

// Main class
public class ATMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a single scanner instance
        BankAccount myAccount = new BankAccount(1000.00); // Create a bank account
        ATM atmMachine = new ATM(myAccount, scanner); // Pass scanner to ATM

        atmMachine.showMenu(); // Start the ATM menu

        scanner.close(); // Close scanner after use
        System.exit(0); // Ensure proper termination
    }
}
