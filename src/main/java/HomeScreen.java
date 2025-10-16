import java.util.*;
import java.io.*;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger(); //create new Ledger object by instantiating Ledger class (blueprint)
        ledger.readTransaction(); //calls .readTransaction() method (BufferReader/FileReader) loading transaction list in Ledger class from the .csv file

        while (true) {
            System.out.println("""
                    -Home Screen-
                    Please choose from following options:
                    D) Add Deposit
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit
                    """);
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D" -> { // -> auto ends after case / no break needed; {} for multiple lines
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

                            if (!ledger.isSufficient(amount)) {
                                System.err.println("Transaction denied. Insufficient amount.");
                            } else {
                                ledger.makePayment(description, vendor, -amount);
                                ledger.writeTransaction();
                                System.out.println("Payment successfully made.");
                        }
                }

                case "L" -> {
                    boolean ledgerMenu = true;
                    while (ledgerMenu) {
                        System.out.println("""
                                -Ledger Menu-
                                Choose from following Ledger options:
                                A) Display All Entries
                                D) Display Deposits
                                P) Display Payments
                                B) Display Balance
                                R) Reports
                                H) Home
                                """);
                        String ledgerChoice = scanner.nextLine().toUpperCase();
                        switch (ledgerChoice) {
                            case "A" -> ledger.displayAll();

                            case "D" -> ledger.displayDeposit();

                            case "P" -> ledger.displayPayment();

                            case "B" -> ledger.displayBalance();

                            case "R" -> {
                                boolean reportsMenu = true;
                                while (reportsMenu) {
                                    System.out.println("""
                                            -Reports Menu-
                                            Please choose from following report options:
                                            1. Month To Date
                                            2. Previous Month
                                            3. Year To Date
                                            4. Previous Year
                                            5. Search by Vendor
                                            6. Custom Search
                                            0. Back
                                            """);
                                    int reportsChoice = Integer.parseInt(scanner.nextLine());
                                    switch (reportsChoice) {
                                        case 1 -> ledger.monthToDate();

                                        case 2 -> ledger.previousMonth();

                                        case 3 -> ledger.yearToDate();

                                        case 4 -> ledger.previousYear();

                                        case 5 -> {
                                            System.out.println("Enter the name of the vendor you'd like to search for: ");
                                            String vendorName = scanner.nextLine();
                                            ledger.searchByVendor(vendorName);
                                        }
                                        case 6 -> {//custom search challenge
                                            System.out.println("Please enter the following search values:");

                                            System.out.println("Enter the start date in YYYY-MM-DD format or press Enter to skip:");
                                            String startInput = scanner.nextLine();
                                            if (startInput.isBlank()) {
                                                startInput = null;
                                            }

                                            System.out.println("Enter the end date in YYYY-MM-DD format or press Enter to skip:");
                                            String endInput = scanner.nextLine();
                                            if (endInput.isBlank()) {
                                                endInput = null;
                                            }

                                            System.out.println("Enter description word or press Enter to skip:");
                                            String descInput = scanner.nextLine();

                                            System.out.println("Enter vendor or press Enter to skip:");
                                            String vendorInput = scanner.nextLine();

                                            System.out.println("Enter amount or press Enter to skip:");
                                            String amountInput = scanner.nextLine();
                                            double amountSearch;
                                            if (amountInput.isBlank()) {
                                                amountSearch = 0;
                                            } else {
                                                amountSearch = Double.parseDouble(amountInput);
                                            }

                                            ArrayList<Transaction> filterList = ledger.customFilter(startInput, endInput, descInput, vendorInput, amountSearch);

                                            if (filterList.isEmpty()) {
                                                System.out.println("Matching transaction history not found.");
                                            } else {
                                                for (Transaction transaction : filterList) {
                                                    System.out.println(transaction + "\n");
                                                }
                                            }
                                        }
                                        case 0 -> reportsMenu = false; //brings user back to ledger menu
                                    }
                                }
                            }
                            case "H" ->
                                ledgerMenu = false; //brings user back to main menu
                        }
                    }
                }
                case "X" -> {
                    System.out.println("System closing..." +
                            "\nGoodbye!");
                    return; //breaks out entirely out of loop/switch/method
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
            try {
                double input = Double.parseDouble(scanner.nextLine()); //parse to not leave a newline (read as empty string) behind going from double to String later
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

