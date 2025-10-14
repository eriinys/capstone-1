import java.util.*;
import java.io.*;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger(); //create new Ledger object via instantiating Ledger class (blueprint)
        ledger.readTransaction(); //call .readTransaction() method on ledger object loading transactions from CSV into this ledger object's list

        while (true) {
            System.out.println("""
                    Please choose from following options:
                    D) Add Deposit
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit
                    """);
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D" -> { // -> {} auto stops after block; no break needed
                    String description = nonBlankInput(scanner,"Please enter description of deposit: ");
                    String vendor = nonBlankInput(scanner, "Please enter the vendor: ");

                    double amount = validNumber(scanner,"Please enter the deposit amount (number only): ");
                    ledger.addDeposit(description, vendor, amount);
                    ledger.writeTransaction();
                    System.out.println("Deposit successfully added.\n");
                }

                case "P" -> {
                    String description = nonBlankInput(scanner, "Please enter the description of the purchase: ");
                    String vendor = nonBlankInput(scanner, "Please enter the vendor: ");

                    double amount = validNumber(scanner, "Please enter the payment amount (number only): ");
                        for (Transaction transaction : ledger.transactions) {
                            if (amount > transaction.getAmount()) {
                                System.err.println("Transaction denied. Insufficient amount.");
                            } else {
                                ledger.makePayment(description, vendor, -amount);
                                ledger.writeTransaction();
                                System.out.println("Payment successfully made.");
                            }
                        }
                }

                case "L" -> {
                    boolean ledgerMenu = true;
                    while (ledgerMenu) {
                        System.out.println("""
                                Choose from following Ledger options: 
                                A) Display all entries
                                D) Display deposits
                                P) Display payments
                                R) Reports
                                H) Home
                                """);
                        String ledgerChoice = scanner.nextLine().toUpperCase();
                        switch (ledgerChoice) {
                            case "A" -> ledger.displayAll();

                            case "D" -> ledger.displayDeposit();

                            case "P" -> ledger.displayPayment();

                            case "R" -> {
                                System.out.println("""
                                        Please choose from following report options:
                                        1. Month To Date
                                        2. Previous Month
                                        3. Year To Date
                                        4. Previous Year
                                        5. Search by Vendor
                                        0. Back
                                        """);
                                int reportsChoice = Integer.parseInt(scanner.nextLine());
                                switch (reportsChoice) {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        break;
                                    case 0:
                                        break;

                                }
                            }
                            case "H" -> {
                                ledgerMenu = false;
                            }
                        }
                    }
                }

                case "X" -> {
                    System.out.println("System closing..." +
                            "\nGoodbye!");
                    return;
                }

                default -> System.out.println("Invalid input. Please choose from options D, P, L, and X: \n");
            }

        }

    }

    //method for catching blank user input
    public static String nonBlankInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input.isBlank()) {
                System.err.println("Error: Input cannot be blank. Please try again: \n");
            } else {
                return input;
            }
        }
    }

    //method for catching 0
    public static double validNumber(Scanner scanner, String prompt) {
        while(true) {
            System.out.println(prompt);
            double input = Double.parseDouble(scanner.nextLine());
            try {
                if (input == 0) {
                    System.err.println("Error: Value cannot be 0. Please try again: \n");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter only number. \n");
            }
        }
    }
}

