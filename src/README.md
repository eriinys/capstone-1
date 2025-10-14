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
    - Ledger Display (switch statement)
      - Display all entries
      - Display deposits
      - Display payments
      - Reports (switch statement)
        - Month to Date
        - Previous Month
        - Year To Date
        - Previous Year
        - Search by Vendor (.contains/.equalsIgnoreCase
        - Back
    - Exit
  - methods for catching invalid user input


- Ledger
  - ArrayLists of Transaction Object
  - Add Deposit method
  - Make Payment method
  - Writing Transaction:
    - BufferedWriter (transaction.csv) under try/catch
  - Reading Transaction:
    - BufferedReader (reading from transaction.csv) under try/catch
  - Ledger methods
    - displayAll
    - displayDeposits
    - displayPayments
    - Methods for Reports search (case 1-5)

- Transaction
    - Transaction field
    - Transaction constructor
    - getters/setters
    - toString method

## Testing/Bugfix History:

- deposit/payment methods were not recording in transaction.csv
  - fixed by loading transactions at start of HomeScreen
    - Ledger ledger = new Ledger();
      ledger.readTransaction()
  - fixed by adding ledger.writeTransaction(); in both case "D" and "P"
- display methods kept printing "no history available" exception
  - fixed by adding boolean to the methods
- added nonBlankInput and validNumber methods to HomeScreen to prevent invalid inputs from user
- loop issue with returning back to main menu from ledger menu
  - fixed by adding boolean ledgerMenu