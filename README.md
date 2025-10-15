# Capstone 1: Accounting Ledger Application

## Project Overview
- Create a CLI application that tracks all financial transactions.
- All transactions are saved to file transactions.csv in the following format:
- date|time|description|vendor|amount
  2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
  2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00

## Features
- Add deposit/make payment
- See different transaction history
  - added custom search
- Check current remaining account balance

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
      - Display balance
      - Reports (switch statement)
        - Month to Date
        - Previous Month
        - Year To Date
        - Previous Year
        - Search by Vendor
        -Custom Search (challenge option)
        - Back
      - Home
    - Exit
  - Methods for catching invalid user input

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
    - Custom Search Method (challenge)

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
- try/catch error in validNumber method for catching non-numeric inputs
  - fixed by adding scanner input into try/catch scope
- LocalDate parsing error for startDate/endDate and NullPointException error for amount in challenge method
  - changed LocalDate and double to String inside the parameter and parsed it inside the method and in HomeScreen