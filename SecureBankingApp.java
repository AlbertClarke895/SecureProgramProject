import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private final String accountNumber;
    private String password;
    private double balance;

    public BankAccount(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

public class SecureBankingApp {
    private static final HashMap<String, BankAccount> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create Account\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 : createAccount();
                case 2 : login();
                case 3 : {
                    System.out.println("Exiting...");
                    return;
                }
                default : System.out.println("Invalid choice.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        accounts.put(accountNumber, new BankAccount(accountNumber, password));
        System.out.println("Account created successfully.");
    }

    private static void login() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (account.authenticate(password)) {
            manageAccount(account);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void manageAccount(BankAccount account) {
        while (true) {
            System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 : System.out.println("Current balance: " + account.getBalance());
                case 2 : {
                    System.out.print("Enter deposit amount: ");
                    double amount = scanner.nextDouble();
                    account.deposit(amount);
                }
                case 3 : {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = scanner.nextDouble();
                    account.withdraw(amount);
                }
                case 4 : {
                    System.out.println("Logging out...");
                    return;
                }
                default : System.out.println("Invalid choice.");
            }
        }
    }
}
