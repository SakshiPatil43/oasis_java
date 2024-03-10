import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Type: " + type + "\nAmount: " + amount + "\nDescription: " + description;
    }
}

class TransactionHistory {
    private static Map<String, Transaction> transactions = new HashMap<>();

    public static void addTransaction(String userId, Transaction transaction) {
        transactions.put(userId, transaction);
    }

    public static void viewTransactionHistory(String userId) {
        if (transactions.containsKey(userId)) {
            System.out.println("Transaction History for User: " + userId);
            System.out.println(transactions.get(userId));
        } else {
            System.out.println("No transaction history found for User: " + userId);
        }
    }
}

class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
        } else {
            System.out.println("Insufficient funds for transfer!");
        }
    }
}

class User {
    private String userId;
    private String userPin;
    private Account account;

    public User(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.account = new Account(initialBalance);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public Account getAccount() {
        return account;
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample user
        User user = new User("12345", "5678", 1000.0);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter User PIN: ");
        String enteredPin = scanner.nextLine();

        if (user.getUserId().equals(enteredUserId) && user.getUserPin().equals(enteredPin)) {
            System.out.println("Authentication successful!");

            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        TransactionHistory.viewTransactionHistory(user.getUserId());
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        user.getAccount().withdraw(withdrawalAmount);
                        TransactionHistory.addTransaction(user.getUserId(), new Transaction("Withdrawal", withdrawalAmount, "ATM Withdrawal"));
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        user.getAccount().deposit(depositAmount);
                        TransactionHistory.addTransaction(user.getUserId(), new Transaction("Deposit", depositAmount, "ATM Deposit"));
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientUserId = scanner.next();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        User recipient = new User(recipientUserId, "", 0.0);
                        user.getAccount().transfer(recipient.getAccount(), transferAmount);
                        TransactionHistory.addTransaction(user.getUserId(), new Transaction("Transfer", transferAmount, "ATM Transfer to " + recipientUserId));
                        break;
                    case 5:
                        System.out.println("Quitting ATM. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting ATM.");
        }
    }
}
