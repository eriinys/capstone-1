import java.util.*;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();

        ledger.readTransaction();

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
                    System.out.println("Please enter description of deposit: ");

                    String description = scanner.nextLine();
                    System.out.println("Please enter the vendor: ");
                    String vendor = scanner.nextLine();
                    System.out.println("Please enter the deposit amount (number only): ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    ledger.addDeposit(description, vendor, amount);
                    System.out.println("Deposit successfully added.\n");
                }

                case "P" -> {
                    System.out.println("Please enter the description of the purchase: ");

                    String description = scanner.nextLine();
                    System.out.println("Please enter the vendor: ");
                    String vendor = scanner.nextLine();
                    System.out.println("Please enter the payment amount (number only): ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    for (Transaction transaction : ledger.transactions) {
                        if (amount < transaction.getAmount()) {
                            System.err.println("Transaction denied. Insufficient amount.");
                        } else {
                            ledger.makePayment(description, vendor, amount);
                            System.out.println("Payment successfully made.");
                        }
                    }
                }

                case "L" -> {
                    System.out.println("""
                            Choose from following Ledger options: 
                            A) Display all entries
                            D) Display deposits
                            P) Display payments
                            R) Reports
                            """);
                    String ledgerChoice = scanner.nextLine().toUpperCase();
                    switch (ledgerChoice) {
                        case "A" ->
                            ledger.displayAll();

                        case "D" ->
                            ledger.displayDeposit();

                        case "P" ->
                            ledger.displayPayment();

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
                            switch(reportsChoice){
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

                    }

                }

                case "X" -> {
                    System.out.println("System closing..." +
                            "\nGoodbye!");
                    return;
                }

                default -> System.out.println("Invalid input. Please choose from options D, P, L, and X: ");
            }

        }

    }

}

