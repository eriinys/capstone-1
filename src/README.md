# Capstone 1: Accounting Ledger Application

## Project Overview
- Create a CLI application that tracks all financial transactions.
- All transactions are saved to file transactions.csv in the following format:
- date|time|description|vendor|amount
  2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
  2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00

## Features
- []

## Project Structure
- Home Screen (Main Entry Point)
  - Scanner for prompting users on selection
  - -switch statement inside while loop for home screen selection 
    - Add Deposit (save to csv file via BufferedWriter)
    - Make Payment 
    - Ledger Display
    - Exit (return;)
  - Methods for Add Deposit/Make Payment/Ledger Display
  
- Ledger
  - Add Deposit method
  - Make Payment method
  - Saving Transaction:
      - BufferedWriter (transaction.csv) under try/catch
      -  LocalDateTime.now() / DateTimeFormatter.ofPattern
  
  - BufferedReader (reading from transaction.csv) under try/catch
  - ArrayLists of Transaction Object
  - Ledger Display switch statement
    A) Display all entries 
    D) Display deposits
    P) Display payments
    R) Reports
      - switch statement 
        1) Month to Date
        2) Previous Month
        3) Year To Date
        4) Previous Year
        5) Search by Vendor (.contains/.equalsIgnoreCase
        0) Back 
    - Methods for Reports search (case 1-5)


- Transaction
    - Transaction field
    - Transaction constructor
    - getters/setters
    - toString method

## Testing