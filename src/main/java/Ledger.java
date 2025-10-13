import java.io.*;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.*;

public class Ledger {

    //create ArrayList of Transaction object
    ArrayList<Transaction> transactions = new ArrayList<>();

    //setting time formatter
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    //setting method for writing transaction to transaction.csv using BufferedWriter
    public void writeTransaction() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/transaction.csv"))) {
            //try with resource to make sure FileWriter closes at the end of use
            //FileWriter creates new file each time

            bw.write("date|time|description|vendor|amount"); //writing header
            bw.newLine();

            for (Transaction transaction : transactions) { //for every transaction object in transactions ArrayList
                bw.write(transaction.toString()); //write transaction object in form of String into transaction.csv
                bw.newLine();//moves to next line in file
            }

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    //region write methods
    public void addDeposit(String description, String vendor, double amount){
        String date = LocalDate.now().toString();
        String time = LocalTime.now().format(timeFormatter).toString();
        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        transactions.add(deposit);
    }

    public void makePayment(String description, String vendor, double amount){
        String date = LocalDate.now().toString();
        String time = LocalTime.now().format(timeFormatter).toString();
        Transaction payment = new Transaction(date, time, description, vendor, amount);
        transactions.add(payment);
    }
    //endregion

    //setting method for reading transaction using BufferedReader
    public void readTransaction() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/transaction.csv"))) {

            String header = br.readLine();

            String info;
            while ((info = br.readLine()) != null) {
                String[] parts = info.split("\\|");
                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //region display methods
    public void displayAll() {
        if (transactions.isEmpty()){
            System.out.println("Account empty. There's nothing to display.\n");
        }
        for (Transaction transaction : transactions) {
            System.out.println(transaction + "\n");
        }
    }

    public void displayDeposit() {
        boolean available = false;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction + "\n");
                available = true;
            }
        }
        if (!available) {
            System.out.println("No deposit history available. \n");
        }

    }

    public void displayPayment() {
        boolean available = false;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0 ) {
                System.out.println(transaction + "\n");
                available = true;
            }
        }
        if (!available) {
            System.out.println("No payment history available. \n");
        }
    }
    //endregion

    //reports methods for options 1-5
    public ArrayList<Transaction> monthToDate() {
        ArrayList<Transaction> result = new ArrayList<>();
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd");
        for (Transaction transaction : transactions) {
            if(transactions.contains(date.format(dateFormatter))) {
                result.add(transaction);
            }
        }
        return result;
    }

    public ArrayList<Transaction> previousMonth() {
        ArrayList<Transaction> result = new ArrayList<>();

        return result;
    }




}
