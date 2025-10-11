import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.*;

public class Ledger {
    //create ArrayList of Transaction object
    ArrayList<Transaction> transactions = new ArrayList<>();

    //setting date/time
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    //setting BufferedWriter for transaction.csv
    public void writeTransaction(String description, String vendor, double amount) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/transaction.csv"))) {

            bw.write("date|time|description|vendor|amount"); //writing header
            bw.newLine();

            //create new Transaction object
            Transaction t = new Transaction(date.toString(), time.format(timeFormatter), description, vendor, amount);

            transactions.add(t);
            bw.write(t.toString());
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }


}
